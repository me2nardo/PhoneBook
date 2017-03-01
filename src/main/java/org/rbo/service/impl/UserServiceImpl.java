package org.rbo.service.impl;

import org.rbo.dao.AuthorityDao;
import org.rbo.dao.UserDao;
import org.rbo.exception.Auth.UserExistsException;
import org.rbo.exception.Auth.impl.UserNotExistsException;
import org.rbo.model.Authority;
import org.rbo.model.User;
import org.rbo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
     */
    @Override
    public User registerUser(User user) {

        userDao.findOneByUsernameOrEmail(user.getUsername(),user.getEmail())
                .map(u-> new UserExistsException(u.getUsername(),u.getEmail()));

        Authority authority = authorityDao.findOne("ROLE_USER");
        Set<Authority> authorities = new HashSet<>();
        authorities.add(authority);

        user.setEnabled(true);
        user.setAccountNonExpired(false);
        user.setAccountNonLocked(false);
        user.setCredentialsNonExpired(false);

        user.setPassword(encoder.encode(user.getPassword()));
        user.setAuthority(authorities);

        userDao.save(user);
        LOG.debug("Created user {}",user);

        return user;

    }

    /**
     * Update all information for specific user
     * @param user requested user
     */
    @Override
    public void updateUser(User user) {
        Optional.of(userDao.findOne(user.getId())).ifPresent(u->{
            userDao.findOneByUsernameOrEmail(u.getUsername(),u.getEmail())
                    .map(um-> new UserExistsException(um.getName(),um.getEmail()));
        });

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
     */
    @Override
    public User findUserById(long id) {
        return Optional.of(userDao.findOne(id)).orElseThrow(()-> new UserNotExistsException());
    }
}
