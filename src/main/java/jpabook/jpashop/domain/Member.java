package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Member {

    // 식별자
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    @Column
    private String name;
    @Embedded
    private Address address;
    @OneToMany(mappedBy = "member") // 읽기전용
    private List<Order> orders = new ArrayList<>();

}
