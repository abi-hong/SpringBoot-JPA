package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.Category;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //상속관계매핑
@DiscriminatorColumn(name = "dtype")
public abstract class Item {
    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;
    @Column
    private String name;
    @Column
    private int price;
    @Column
    private  int stockQuantity;
    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

}
