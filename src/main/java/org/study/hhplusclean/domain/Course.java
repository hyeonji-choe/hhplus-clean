package org.study.hhplusclean.domain.course;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.study.hhplusclean.domain.Timestamped;

import java.time.LocalDate;

@Entity // 테이블임을 나타냅니다.
@Builder
@AllArgsConstructor
@NoArgsConstructor// 기본생성자를 대신 생성해줍니다.
@ToString
@Table(name="course")
public class Course extends Timestamped {

    @Id // ID 값, Primary Key로 사용하겠다는 뜻입니다.
    @GeneratedValue(strategy = GenerationType.AUTO) // 자동 증가 명령입니다.
    private Long id;

    @Column(nullable = false) // 컬럼 값이고 반드시 값이 존재해야 함을 나타냅니다.
    private String title;

    @Column(nullable = false)
    private String tutor;

    @Column(nullable = false)
    private Integer seats;

    @Column(nullable = false)
    private LocalDate openDate;


    public Course(String title, String tutor) {
        this.title = title;
        this.tutor = tutor;
    }
}
