package org.study.hhplusclean.domain.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.study.hhplusclean.domain.Timestamped;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="user")
public class User extends Timestamped {
    @Id // ID 값, Primary Key로 사용하겠다는 뜻입니다.
    @GeneratedValue(strategy = GenerationType.AUTO) // 자동 증가 명령입니다.
    private Long id;
}
