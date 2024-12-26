package org.study.hhplusclean.course;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.study.hhplusclean.domain.ApplyHistory;
import org.study.hhplusclean.domain.Course;
import org.study.hhplusclean.domain.User;
import org.study.hhplusclean.user.UserRepository;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final ApplyHisotryRepository applyHistoryRepository;
    private final UserRepository userRepository;

    public CourseService(CourseRepository courseRepository, ApplyHisotryRepository applyHistoryRepository, UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.applyHistoryRepository = applyHistoryRepository;
        this.userRepository = userRepository;
    }

    public ApplyCourseResponse applyCourse(long userId,long courseId){
        //사용자 조회 -> 없으면 사용자 등록 ??
        User user = userRepository.findById(userId).orElseThrow();
        //강의 조회 -> 없으면
        Course course = courseRepository.findById(courseId).orElseThrow();
        //강의 자리 없음
        if(course.getSeats()<=0) throw new RuntimeException("특강자리가 부족합니다.");
        //이미 등록한 강의
        ApplyHistory applyHistory = applyHistoryRepository.searchByCourseIdAndUserId(courseId,userId);
        if(!ObjectUtils.isEmpty(applyHistory)) throw new RuntimeException("이미 등록한 강의입니다.");

        course.setSeats(course.getSeats()-1);
        courseRepository.save(course);
        applyHistoryRepository.save(ApplyHistory.builder()
                .courseId(courseId)
                .userId(userId).build());

        return ApplyCourseResponse.builder()
                .courseId(courseId)
                .courseName(course.getTitle())
                .userId(userId)
                .resultMsg("Success").build();
    }

    public List<Course> getApplyableListByDate(String date){
        //date 체크
        if(date.isEmpty() || date.length()!=8) throw new RuntimeException("날짜형식을 맞춰주세요. (YYYYMMDD)");
        DateTimeFormatter format = DateTimeFormatter.ofPattern("YYYYMMDD");
        return courseRepository.searchCourseByOpenDate(LocalDate.parse(date,format));
    }

    public List<Course> getUserCourseList(long id){
        return applyHistoryRepository.getApplyHistoriesByUserId(id).stream().map(h->
            courseRepository.findById(h.getCourseId()).orElseThrow()
        ).toList();
    }
}
