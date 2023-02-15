package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 수동으로 빈 생성
public class SpringConfig {
/*
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository()); // 아래 memberRepository
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
 */
}
