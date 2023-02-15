package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.OpPlus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService { // ctrl + shift + T -> Test class 바로 생성 가능

    private final MemberRepository memberRepository;

//    @Autowired private MemberService memberService; //  필드 주입 방법


/*
    // setter 주입, 세팅 이후에 바뀔 이유가 없는데 public하기 노출되어서 잘 쓰지 않음.
    @Autowired public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
*/

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     * @param member
     * @return
     */
    public Long join(Member member) {

        //ex) 같은 중복이름은 등록이 안된다고 가정
        // Optional<Member> result = memberRepository.findByName(member.getName());
        // optional로 반환을 추천하지는 않는다. result.orElseGet() 값이 있으면 꺼내고 없으면 메쏘드를 실행하는 코드로 ~
        // optional 내에 함수를 사용해 exception 처리 바로 가능, null이 아니면 throw
        validateDuplicateMember(member); // 중복 회원 검증, ctrl + alt + shift + T로 메쏘드 생성함(window 단축키)
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException(("이미 존재하는 회원입니다"));
                });
    }

    /**
     * 전체 회원 조회
     * @return
     */

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
