package org.rbo.service.impl;

import org.rbo.dao.AuthorityDao;
import org.rbo.dao.UserDao;
import org.rbo.exception.Auth.UserExistsException;
import org.rbo.exception.Auth.UserNotExistsException;
import org.rbo.model.Authority;
import org.rbo.model.User;
import org.rbo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author vitalii.levash
 */
@Service
public class UserServiceImpl implements UserService {

    private final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);


    @Autowired
    private UserDao userDao;

    @Autowired
    private AuthorityDao authorityDao;

    @Autowired
    private PasswordEncoder encoder;


    /**
     * Register a new user with default authority ROLE_USER
     * @param user requested user
     * @throws UserExistsException username or email already in use
     * @return created user
     */
    @Override
    @Transactional(readOnly = true)
    public User registerUser(User user) {

        userDao.findOneByUsernameOrEmail(user.getUsername(),user.getEmail())
                .map(u-> new UserExistsException(u.getUsername(),u.getEmail()));

        if (user.getAuthority().isEmpty()) {
            Authority authority = authorityDao.findOne("ROLE_USER");
            Set<Authority> authorities = new HashSet<>();
            authorities.add(authority);
            user.setAuthority(authorities);
            LOG.debug("Default authorities for user %s",user.getUsername());
        } else{
           user.setAuthority(processAuthority(user));
        }
        user.setEnabled(true);
        user.setAccountNonExpired(false);
        user.setAccountNonLocked(false);
        user.setCredentialsNonExpired(false);

        user.setPassword(encoder.encode(user.getPassword()));


        userDao.saveAndFlush(user);
        LOG.debug("Created user {}",user);

        return user;

    }

    /**
     * Update all information for specific user
     * @param
     */
    @Override
    @Transactional(readOnly = true)
    public void updateUser(User user) {

        userDao.findOneByUsername(user.getUsername())
                .map(u->{
                   u.setLastName(user.getLastName());
                   u.setFirstName(user.getFirstName());
                   u.setName(user.getName());
                   if (user.getAuthority()!=null){
                       user.setAuthority(processAuthority(user));
                   }
                   userDao.saveAndFlush(u);
                   LOG.info("Update user {}",u);
                   return u;
                }).orElseThrow(()-> new UserNotExistsException(user.getUsername()));

    }

    /**
     * Delete user
     * @param username requested username from user entity
     */
    @Override
    public void deleteUser(String username) {
      userDao.findOneByUsername(username).ifPresent(u->{
          userDao.delete(u);
          LOG.debug("Deleted user {}",u);
      });
    }

    /**
     * Get all users
     * @return List of all users
     */
    @Override
    public List<User> findAllUsers() {
        return userDao.findAll();
    }

    /**
     * Find user by his ID
     * @param id user id
     * @return
     * @throws UserNotExistsException if user not found
     */
    @Override
    public User findUserById(long id) {
        return Optional.of(userDao.findOne(id)).orElseThrow(()-> new UserNotExistsException());
    }

    /**
     * Find user by his username
     * @param username user id
     * @return user
     * @throws UserNotExistsException if user not found
     */
    @Override
    public User findUserByName(String username) {
        return userDao.findOneByUsername(username).orElseThrow(()-> new UserNotExistsException());
    }

    /**
     * Set custom authority
     * @param user current user
     * @return modified user with mapped authorities list
     */
    private Set<Authority> processAuthority(User user){
        Set<Authority> authorities = new HashSet<>();

        // TODO :: Make batch operation for getting authorities list
        user.getAuthority().stream().forEach(authority -> authorities.add(authorityDao.findOne(authority.getName())));

        LOG.debug("Custom authorities for user %s",user.getUsername());
        return authorities;
    }
}
