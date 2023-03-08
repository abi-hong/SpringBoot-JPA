package jpabook.jpashop.repository;

import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    //item 저장
    public void save(Item item) {
        //item 처음 저장이면 id값이 없을 것
        if (item.getId() == null) {
            em.persist(item);
        } else { //merge는 update!
            em.merge(item);
        }
    }

    //item 조회
    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    //item 리스트 조회
    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
}
