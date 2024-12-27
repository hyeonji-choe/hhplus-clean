package org.study.hhplusclean.course;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ApplyCourseResponse {
    private long courseId;
    private String courseName;
    private long userId;
    private String resultMsg;
}
