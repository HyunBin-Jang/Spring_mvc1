package hello.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MemberRepositoryTest {
    MemberRepository memberRepository = MemberRepository.getInstance();

    // 각 Test 끝날때 마다 실행
    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void save(){
        //given
        Member member = new Member("hello", 20);

        //when
        Member savedMember = memberRepository.save(member);

        //then
        Member findMember = memberRepository.findById(savedMember.getId());
        Assertions.assertThat(savedMember).isEqualTo(findMember);
    }

    @Test
    void findAll(){
        Member member1 = new Member("member1", 20);
        Member member2 = new Member("member2", 30);

        memberRepository.save(member1);
        memberRepository.save(member2);

        List<Member> memberList = memberRepository.findAll();

        Assertions.assertThat(memberList.size()).isEqualTo(2);
        Assertions.assertThat(memberList).contains(member1, member2);
    }
}
