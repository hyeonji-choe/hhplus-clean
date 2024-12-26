package org.study.hhplusclean.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.study.hhplusclean.domain.Course;

public interface CourseRespository extends JpaRepository<Course,Long> {
}
