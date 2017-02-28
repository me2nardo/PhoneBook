package org.rbo.dao;

import org.junit.Assert;
import org.rbo.model.Authority;
import org.rbo.model.User;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.Set;

import static org.hamcrest.Matchers.is;


/**
 * <p>Data JPA test for repository UserDao.</p>
 * @author vitalii.levash
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class UserTestDao {

    @Autowired
    private UserDao userDao;

    /**
     * Test Find user by his username.
     */
    @Test
    public void findOneByUsernameTest() throws Exception{
        Optional<User> user = userDao.findOneByUsername("sJohn");

        Assert.assertThat(user.get().getUsername(),is("sJohn"));
    }

    /**
     * Test Find user by his username. No user found.
     */
    @Test
    public void findOneByUsernameFailedTest() throws Exception{
        Optional<User> user = userDao.findOneByUsername("sJJohn");

        Assert.assertThat(user.isPresent(),is(false));
    }

    /**
     * Find user by his username with Authorities.
     * Demo authority ROLE_DEMO for sJohn
     */
    @Test
    public void findOneWithAuthoritiesByUsernameTest() throws Exception{
       User user = userDao.findOneWithAuthoritiesByUsername("sJohn").get();
       Set<Authority> result = user.getAuthority();

       Assert.assertThat(result.size(),is(1));

       String name = result.stream().map(u->u.getName()).findFirst().get();
       Assert.assertThat(name,is("ROLE_DEMO"));
    }

}
