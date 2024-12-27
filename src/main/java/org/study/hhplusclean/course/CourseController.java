package org.study.hhplusclean.course;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.study.hhplusclean.domain.Course;

import java.util.List;

@RestController
@RequestMapping("/course")
@RequiredArgsConstructor
public class CourseController {

    private static final Logger log = LoggerFactory.getLogger(CourseController.class);

    private final CourseService courseService;
    /*
    특강신청
    userid,특강id -> 신청여부(성공/실패)
     */
    @PatchMapping("{userId}/apply/{courseId}")
    public ApplyCourseResponse apply(
            @PathVariable long userId,
            @PathVariable long courseId
    ) {
        return courseService.applyCourse(userId, courseId);
    }

    /*
    특강신청가능 리스트조회
    날짜YYYYMMDD -> list(course)
     */
    @GetMapping("{date}/lists")
    public List<Course> courseList(
            @PathVariable String date
    ) {
        return courseService.getApplyableListByDate(date);
    }

    /*
    특강신청완료 목록조회 : 특정회원의 신청 성공한 특강 목록
    userId -> list(course)
     */
    @GetMapping("{id}/courses")
    public List<Course> userCourseList(
            @PathVariable long id
    ) {
        return courseService.getUserCourseList(id);
    }
}
