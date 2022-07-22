package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.Category;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    /**==핵심 비지니스 로직==**/ //Setter 대신 핵심 비지니스 로직을 사용해서 값을 변경해야함
    // stock 재고 증가
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    //stock 재고 감소
    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0) { // 수량이 음수이면 안됨
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }
}
