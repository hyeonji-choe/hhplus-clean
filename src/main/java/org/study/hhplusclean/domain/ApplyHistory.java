package org.study.hhplusclean.domain.course;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.study.hhplusclean.domain.Timestamped;
import org.study.hhplusclean.domain.user.User;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="apply_history")
public class ApplyHistory extends Timestamped {

    @Id // ID 값, Primary Key로 사용하겠다는 뜻입니다.
    @GeneratedValue(strategy = GenerationType.AUTO) // 자동 증가 명령입니다.
    private Long id;
    @Column(nullable = false)
    private Long userId;
    @Column(nullable = false)
    private Long courseId;

    @ManyToOne
    @JoinColumn(name="userId")
    User user;

    @ManyToOne
    @JoinColumn(name="courseId")
    Course course;

    public ApplyHistory insert(long userId,long courseId){
        this.userId=userId;
        this.courseId=courseId;
        return this;
    }
}
