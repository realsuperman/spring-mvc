package hello.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import javax.annotation.processing.SupportedAnnotationTypes;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemberRepositoryTest {
    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach(){ // 하나의 테스트가 끝날때마다 함
        memberRepository.clearStore();
    }

    @Test
    void save(){
        Member member = new Member("최성훈",23);

        Member savedMember = memberRepository.save(member);

        Member findMember = memberRepository.findById(savedMember.getId());
        assertThat(findMember).isSameAs(savedMember);
    }

    @Test
    void findAll(){
        Member member1 = new Member("member1",20);
        Member member2 = new Member("member2",30);
        memberRepository.save(member1); memberRepository.save(member2);
        List<Member> result = memberRepository.findAll();
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(member1,member2);
    }
}
