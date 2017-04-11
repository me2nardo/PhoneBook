package org.rbo.service;

import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.rbo.util.BehaviouralTestEmbedder.aBehaviouralTestRunner;

/**
 * Created by vlevash on 11.04.17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserService.class)
public class UserServiceFixture {

    @Autowired private UserService userService;

    @Test
    public void berlinClockAcceptanceTests() throws Exception {
        aBehaviouralTestRunner()
                .usingStepsFrom(this)
                .withStory("berlin-clock.story")
                .run();
    }

    @When("the time is $time")
    public void whenTheTimeIs(String time) {

    }

    @Then("the clock should look like $")
    public void thenTheClockShouldLookLike(String theExpectedBerlinClockOutput) {

    }

}
