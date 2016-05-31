package org.rbo.service.impl;

import org.rbo.dao.UserDao;
import org.rbo.exception.Auth.impl.LoginExistsException;
import org.rbo.exception.Auth.impl.UserNotExistsException;
import org.rbo.model.User;
import org.rbo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Optional.ofNullable;

/**
 * Created by vitalii.levash on 18.04.2016.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    @Transactional(readOnly = true)
    public void addUser(User user) {
       if (userDao.findByLogin(user.getLogin())!=null){
           throw new LoginExistsException(user.getLogin());
       }
       user.setPassword(encoder.encode(user.getPassword()));
       userDao.saveAndFlush(user);
    }

    @Override
    @Transactional
    public void delete(final int id) {
        userDao.delete(getById(id));
    }


    @Override
    public User getById(final int id)  {
        return ofNullable(userDao.getOne(id)).orElseThrow(() -> new UserNotExistsException(id));
    }

    @Override
    public List<User> getUserList() {
        return userDao.findAll();
    }

    @Override
    public User findByLogin(final String user) {
        return userDao.findByLogin(user);
    }

}
