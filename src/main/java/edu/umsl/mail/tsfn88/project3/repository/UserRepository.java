package edu.umsl.mail.tsfn88.project3.repository;

import edu.umsl.mail.tsfn88.project3.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * A JPA repository for user entities.
 * <p>
 * This is implemented automatically by Spring Data.
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
