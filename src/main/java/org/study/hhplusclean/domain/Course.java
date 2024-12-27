package org.study.hhplusclean.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name="course")
public class Course extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "tutor", nullable = false)
    private String tutor;

    @Column(name = "seats", nullable = false)
    @Version
    @Builder.Default
    private Integer seats = 30;

    @Column(name = "open_date", nullable = false)
    private LocalDate openDate;


    public Course(String title, String tutor) {
        this.title = title;
        this.tutor = tutor;
    }
}
