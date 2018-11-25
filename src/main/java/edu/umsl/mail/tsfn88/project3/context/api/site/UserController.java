package edu.umsl.mail.tsfn88.project3.context.api.site;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger log = LogManager.getLogger();

    @RequestMapping(method = RequestMethod.GET)
    public void userGet() {
        log.trace("get /user");
    }

    @RequestMapping(method = RequestMethod.POST)
    public void userPost() {
        log.trace("post /user");
    }

    @RequestMapping(method = RequestMethod.OPTIONS)
    public void userOptions() {
        log.trace("options /user");
    }

    @RequestMapping(path = "/{userId}", method = RequestMethod.GET)
    public void userIdGet(@PathVariable long userId) {
        log.trace("get /userId = " + userId);
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
