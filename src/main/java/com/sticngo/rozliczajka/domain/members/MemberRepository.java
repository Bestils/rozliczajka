package com.sticngo.rozliczajka.domain.members;

import java.util.List;

public interface MemberRepository extends com.sticngo.rozliczajka.infrastructure.persistence.Repository<Member> {
    List<Member> findMembersByUserId(Long userId);
}