package com.sticngo.rozliczajka.domain.members;

import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MemberRepository extends com.sticngo.rozliczajka.infrastructure.persistence.Repository<Member> {
    Member findMemberByUserId(Long userId);
}