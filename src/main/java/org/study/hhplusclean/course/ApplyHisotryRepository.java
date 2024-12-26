package org.study.hhplusclean.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.study.hhplusclean.domain.ApplyHistory;
import org.study.hhplusclean.domain.Course;

import java.util.List;

public interface ApplyHisotryRepository extends JpaRepository<ApplyHistory,Long> {
    List<ApplyHistory> getApplyHistoriesByUserId(long userId);

    ApplyHistory searchByCourseIdAndUserId(long courseId, long userId);
}
