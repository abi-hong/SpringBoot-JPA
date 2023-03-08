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
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    /*@Autowired
    private MemberRepository memberRepository;*/

    //@RequiredArgsConstructor의 경우, final이 있는 필드만 생성자를 만들어줌
    private final MemberRepository memberRepository;

    /**
    생성자 Injection, @AllArgsConstructor를 쓰면 아래의 생성자 메소드 만들어줌
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }*/

    //회원 가입
    @Transactional
    public Long join(Member member) {
        //중복 회원 검증
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    // 회원 단건 조회
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
