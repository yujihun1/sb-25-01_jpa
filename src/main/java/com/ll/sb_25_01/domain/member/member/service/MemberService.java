package com.ll.sb_25_01.domain.member.member.service;

import com.ll.sb_25_01.domain.member.member.entitiy.Member;
import com.ll.sb_25_01.domain.member.member.repository.MemberRepositroy;
import com.ll.sb_25_01.global.rsData.RsData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor

public class MemberService {
    private final MemberRepositroy memberRepositroy;

    @Transactional
    public RsData<Member> join(String username, String password) {
        Member member = Member.builder()
                .modifyDate(LocalDateTime.now())
                .username(username)
                .password(password)
                .build();
        memberRepositroy.save(member);

        return RsData.of("200", "%s님 가입을 환영합니다.".formatted(username), member);
    }

    public Optional<Member> findById(long id) {
        return memberRepositroy.findById(id);
    }

    public long count(){
        return memberRepositroy.count();
    }
}
