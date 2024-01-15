package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.List;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void clearEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("hi");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        System.out.println("result = " + (result == member));

        // junit의 Assertions
        Assertions.assertEquals(member, result);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("sungwon1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("sungwon2");
        repository.save(member2);

        Member reuslt = repository.findByName("sungwon1").get();

        Assertions.assertEquals(member1, reuslt);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("member1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("member2");
        repository.save(member2);

        Member member3 = new Member();
        member3.setName("member3");
        repository.save(member3);

        Member member4 = new Member();
        member4.setName("member4");
        repository.save(member4);

        List<Member> result = repository.findAll();
    }
}
