package com.sticngo.rozliczajka.domain.members;


import com.sticngo.rozliczajka.infrastructure.persistence.AbstractInMemoryRepository;

import java.util.List;

public class InMemoryMemberRepository
    extends AbstractInMemoryRepository<Member>
    implements MemberRepository {

  @Override
  public Member findMemberByUserId(Long id) {
    return null;
  }
}
