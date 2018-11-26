/*
 * Tyler Filla
 * CS 4012
 * Project 3
 */

package edu.umsl.mail.tsfn88.project3.context.api.site;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.umsl.mail.tsfn88.project3.context.api.site.entity.AdminCreds;
import edu.umsl.mail.tsfn88.project3.context.api.site.entity.StoredUser;
import edu.umsl.mail.tsfn88.project3.context.api.site.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger log = LogManager.getLogger();

    @Inject UserService userService;

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public List<StoredUser> userGet() {
        log.trace("get /user");

        return userService.listUsers();
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> userPost(HttpServletRequest request, @RequestBody StoredUser user) {
        log.trace("post /user");

        try {
            user = userService.createUser(user);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Location", request.getRequestURI() + "/" + user.id);
            return new ResponseEntity<>(user, headers, HttpStatus.CREATED);
        } catch (UserService.UserExistsException ignored) {
            // User exists by username
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (UserService.UserIncompleteException e) {
            // User incomplete
            return new ResponseEntity<>(new Object() {

                @JsonProperty
                List<String> missingFields = e.missingFields;

            }, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.OPTIONS)
    public ResponseEntity<Void> userOptions() {
        log.trace("options /user");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Allow", "GET,POST,OPTIONS");
        return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
    }

    @ResponseBody
    @RequestMapping(path = "/{userId}", method = RequestMethod.GET)
    public ResponseEntity<StoredUser> userIdGet(@PathVariable long userId) {
        log.trace("get /userId = " + userId);

        try {
            return ResponseEntity.ok(userService.getUser(userId));
        } catch (UserService.UserNotFoundException ignored) {
            // User not found by ID
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ResponseBody
    @RequestMapping(path = "/{userId}", method = RequestMethod.PUT)
    public ResponseEntity<StoredUser> userIdPut(@PathVariable long userId, @RequestBody StoredUser user) {
        log.trace("put /userId = " + userId);

        user.id = userId;
        try {
            return new ResponseEntity<>(userService.updateUser(user), HttpStatus.NO_CONTENT);
        } catch (UserService.UserNotFoundException ignored) {
            // User not found by ID
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ResponseBody
    @RequestMapping(path = "/{userId}", method = RequestMethod.DELETE)
    public ResponseEntity<StoredUser> userIdDelete(@PathVariable long userId, @RequestBody AdminCreds creds) {
        log.trace("delete /userId = " + userId);

        throw new IllegalStateException("not implemented");
        /*
        try {
            return ResponseEntity.ok(userService.removeUser(userId));
        } catch (UserService.UserNotFoundException ignored) {
            // User not found by ID
            return null;
        }
        */
    }

    @RequestMapping(path = "/{userId}", method = RequestMethod.OPTIONS)
    public ResponseEntity<Void> userIdOptions(@PathVariable long userId) {
        log.trace("options /userId = " + userId);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Allow", "GET,PUT,DELETE,OPTIONS");
        return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
    }

}
