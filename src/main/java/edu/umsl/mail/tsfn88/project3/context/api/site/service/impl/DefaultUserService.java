/*
 * Tyler Filla
 * CS 4012
 * Project 3
 */

package edu.umsl.mail.tsfn88.project3.context.api.site.service.impl;

import edu.umsl.mail.tsfn88.project3.context.api.site.entity.AdminCreds;
import edu.umsl.mail.tsfn88.project3.context.api.site.entity.StoredUser;
import edu.umsl.mail.tsfn88.project3.context.api.site.service.UserService;
import edu.umsl.mail.tsfn88.project3.entity.User;
import edu.umsl.mail.tsfn88.project3.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@EnableTransactionManagement
public class DefaultUserService implements UserService {

    /**
     * The admin username.
     */
    private static final String ADMIN_USERNAME = "admin";

    /**
     * The admin password.
     */
    private static final String ADMIN_PASSWORD = "admin";

    @Inject UserRepository userRepository;

    @Override
    @Transactional
    public StoredUser createUser(StoredUser user) {
        if (userRepository.existsById(user.id))
            throw new UserExistsException();

        if (userRepository.existsByUsername(user.username))
            throw new UserExistsException();

        // Validate user
        validateUser(user);

        // Set last modified timestamp
        user.modified = Instant.now().toEpochMilli();

        return toStoredUser(userRepository.save(toUserEntity(user, null)));
    }

    @Override
    @Transactional
    public StoredUser updateUser(StoredUser user) {
        if (!userRepository.existsById(user.id))
            throw new UserNotFoundException();

        // Set last modified timestamp
        user.modified = Instant.now().toEpochMilli();

        User entity = userRepository.getOne(user.id);

        return toStoredUser(userRepository.save(toUserEntity(user, entity)));
    }

    @Override
    @Transactional
    public StoredUser removeUser(long id, AdminCreds creds) {
        if (!ADMIN_USERNAME.equals(creds.adminusername) || !ADMIN_PASSWORD.equals(creds.adminpassword))
            throw new AuthenticationException();

        if (!userRepository.existsById(id))
            throw new UserNotFoundException();

        // Get existing user
        User entity = userRepository.getOne(id);
        StoredUser user = toStoredUser(entity);

        // Delete the user by ID
        userRepository.delete(entity);

        return user;
    }

    @Override
    @Transactional
    public StoredUser getUser(long id) {
        if (!userRepository.existsById(id))
            throw new UserNotFoundException();

        return toStoredUser(userRepository.getOne(id));
    }

    @Override
    @Transactional
    public List<StoredUser> listUsers() {
        List<StoredUser> users = new ArrayList<>();
        userRepository.findAll().forEach(user -> users.add(toStoredUser(user)));
        return users;
    }

    private static User toUserEntity(StoredUser user, User defaultEntity) {
        User entity = new User();

        if (defaultEntity == null) {
            defaultEntity = entity;
        }

        entity.setId(user.id);
        entity.setUsername(Objects.toString(user.username, defaultEntity.getUsername()));
        entity.setPassword(Objects.toString(user.password, defaultEntity.getPassword()));
        entity.setFname(Objects.toString(user.firstname, defaultEntity.getFname()));
        entity.setLname(Objects.toString(user.lastname, defaultEntity.getLname()));
        entity.setModified(Timestamp.from(Instant.ofEpochMilli(user.modified)));

        return entity;
    }

    private static StoredUser toStoredUser(User entity) {
        StoredUser storedUser = new StoredUser();
        storedUser.id = entity.getId();
        storedUser.username = entity.getUsername();
        storedUser.password = entity.getPassword();
        storedUser.firstname = entity.getFname();
        storedUser.lastname = entity.getLname();
        storedUser.modified = entity.getModified().getTime();

        return storedUser;
    }

    private static void validateUser(StoredUser user) {
        // Collect details on missing fields
        if (user.username == null || user.password == null || user.firstname == null || user.lastname == null) {
            List<String> missingFields = new ArrayList<>();

            if (user.username == null) {
                missingFields.add("username");
            }
            if (user.password == null) {
                missingFields.add("password");
            }
            if (user.firstname == null) {
                missingFields.add("firstname");
            }
            if (user.lastname == null) {
                missingFields.add("lastname");
            }

            UserIncompleteException ex = new UserIncompleteException();
            ex.missingFields = missingFields;
            throw ex;
        }
    }

}
