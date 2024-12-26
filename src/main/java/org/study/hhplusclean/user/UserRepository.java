package org.study.hhplusclean.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.study.hhplusclean.domain.Course;
import org.study.hhplusclean.domain.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    @Override
    Optional<User> findById(Long id);
}
