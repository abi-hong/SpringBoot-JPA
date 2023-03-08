package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository //Component 스캔의 대상이 됨
@RequiredArgsConstructor
public class MemberRepository {
    private final EntityManager em;

    //회원 저장
    public void save(Member member) { em.persist(member); }

    //회원 단건 조희
    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    //회원 리스트 조회
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    //특정이름의 회원 조회
    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

    }
}
