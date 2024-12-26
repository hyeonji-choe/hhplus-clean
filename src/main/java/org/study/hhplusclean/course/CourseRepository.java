package org.study.hhplusclean.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.study.hhplusclean.domain.Course;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course,Long> {
    @Override
    Optional<Course> findById(Long id);

    List<Course> searchCourseByOpenDate(LocalDate openDate);
}
