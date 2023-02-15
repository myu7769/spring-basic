package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryMemberRepository implements MemberRepository { //인터페이스 함수 추가할때 alt+엔터하면 편함

    private static Map<Long, Member> store = new HashMap<>(); //ConcurrentHashMap으로 데이터 동기화로 개발하는게 좋음
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); // null 이 될 가능성이 있으면 optional로 감싸서 리턴함
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream() // store value 중에 name과 같으면 아무거나 리턴
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();

    }


}
