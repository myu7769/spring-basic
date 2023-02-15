package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;
    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository); //DI 디펜더시 인젝션
    }

    @AfterEach // Test 함수가 실행된 후 무조건 실행되는 코드
    public void afterEach(){
        memberRepository.clearStore();
    }
    @Test
    void 회원가입() { // 테스트는 한글로 적어도 된다!
        //given
        Member member = new Member();
        member.setName("spring");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName()); // static import alt+enter
    }
    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));// 오른쪽 로직을 실행해서 IllegalStateException이 발동해야됨
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");


/*       try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
        }*/



        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}