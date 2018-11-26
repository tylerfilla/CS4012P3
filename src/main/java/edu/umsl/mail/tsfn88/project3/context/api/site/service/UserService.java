/*
 * Tyler Filla
 * CS 4012
 * Project 3
 */

package edu.umsl.mail.tsfn88.project3.context.api.site.service;

import edu.umsl.mail.tsfn88.project3.context.api.site.entity.StoredUser;

import java.util.List;

public interface UserService {

    /**
     * Create a user.
     *
     * @param user The user
     * @return The user
     */
    StoredUser createUser(StoredUser user);

    /**
     * Update a user.
     *
     * @param user The user
     * @return The user
     */
    StoredUser updateUser(StoredUser user);

    /**
     * Remove a user by its unique ID.
     *
     * @param id The user's unique ID
     * @return The removed user
     */
    StoredUser removeUser(long id);

    /**
     * Get a user by its unique ID.
     *
     * @param id The user's unique ID
     * @return The user
     */
    StoredUser getUser(long id);

    /**
     * List all users.
     *
     * @return The user list
     */
    List<StoredUser> listUsers();

    /**
     * An exception thrown to indicate a user already exists.
     */
    class UserExistsException extends RuntimeException {
    }

    /**
     * An exception thrown to indicate a user could not be found.
     */
    class UserNotFoundException extends RuntimeException {
    }

    /**
     * An exception thrown to indicate a user in incomplete.
     */
    class UserIncompleteException extends RuntimeException {

        /**
         * A list of missing field names.
         */
        public List<String> missingFields;

    }

}
