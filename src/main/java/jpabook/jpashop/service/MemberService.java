package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
//@AllArgsConstructor -> 생성자 injection 만들어줌
@RequiredArgsConstructor // final을 가진 필드만 생성자를 만듬 -> 제일 좋은 방법!
public class MemberService {

    private final MemberRepository memberRepository;

    /**
    @Autowired
    // setter injection -> 테스트코드 작성할 때 직접 주입가능 but runtime 중에 바꿔지면 단점,,
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
     */

    // 회원 가입
    @Transactional
    public Long join(Member member) {

        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        // EXCEPTION
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    // 회원 전체 조희 -> 읽기 전용에는 Transactional을 굳이..
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    //회원 단건 조회
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
