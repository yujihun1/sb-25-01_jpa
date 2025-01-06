package com.ll.sb_25_01.domain.member.member.repository;

import com.ll.sb_25_01.domain.member.member.entitiy.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepositroy extends JpaRepository<Member, Long> {
}
