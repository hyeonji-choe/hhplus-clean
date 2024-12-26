package org.study.hhplusclean.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Table(name="apply_history")
public class ApplyHistory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "user_id", nullable = false)
    private Long userId;
    @Column(name = "course_id", nullable = false)
    private Long courseId;

    public ApplyHistory insert(long userId,long courseId){
        this.userId=userId;
        this.courseId=courseId;
        return this;
    }
}
