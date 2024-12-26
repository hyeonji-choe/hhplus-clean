package org.study.hhplusclean.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.study.hhplusclean.domain.Course;

public interface CourseRepository extends JpaRepository<Course,Long> {
    Course getCourseById(long courseId);
}
