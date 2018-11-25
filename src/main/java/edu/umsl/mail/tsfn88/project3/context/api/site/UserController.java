/*
 * Tyler Filla
 * CS 4012
 * Project 3
 */

package edu.umsl.mail.tsfn88.project3.context.api.site;

import edu.umsl.mail.tsfn88.project3.context.api.site.entity.StoredUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger log = LogManager.getLogger();

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public List<StoredUser> userGet() {
        log.trace("get /user");

        StoredUser s = new StoredUser();
        s.username = "username";
        s.password = "password";
        s.firstname = "firstname";
        s.lastname = "lastname";
        return Collections.singletonList(s);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void userPost() {
        log.trace("post /user");
    }

    @RequestMapping(method = RequestMethod.OPTIONS)
    public void userOptions() {
        log.trace("options /user");
    }

    @ResponseBody
    @RequestMapping(path = "/{userId}", method = RequestMethod.GET)
    public StoredUser userIdGet(@PathVariable long userId) {
        log.trace("get /userId = " + userId);

        StoredUser s = new StoredUser();
        s.username = "username for " + userId;
        s.password = "password";
        s.firstname = "firstname";
        s.lastname = "lastname";
        return s;
    }

    @RequestMapping(path = "/{userId}", method = RequestMethod.PUT)
    public void userIdPut(@PathVariable long userId) {
        log.trace("put /userId = " + userId);
    }

    @RequestMapping(path = "/{userId}", method = RequestMethod.DELETE)
    public void userIdDelete(@PathVariable long userId) {
        log.trace("delete /userId = " + userId);
    }

    @RequestMapping(path = "/{userId}", method = RequestMethod.OPTIONS)
    public void userIdOptions(@PathVariable long userId) {
        log.trace("options /userId = " + userId);
    }

}
