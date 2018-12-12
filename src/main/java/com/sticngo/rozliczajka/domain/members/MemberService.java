package com.sticngo.rozliczajka.domain.members;



import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

  private final MemberRepository memberRepository;


  public List<Member> findAll() {
    return memberRepository.findAll();
  }

  public Member findById(Long id) {
    return memberRepository.findById(id).orElseThrow(() -> new MemberNotFoundException(id));
  }

  public Member getById(Long id) {
    return memberRepository.findById(id)
            .orElseThrow(() -> new MemberNotFoundException(id));
  }

  public Member findMembersByUserId(Long id) {
    return memberRepository.findMemberByUserId(id);
  }

  public Member save(Member member) {
    return memberRepository.save(member);
  }

  public void updateMember(Member member) {
    Member existmember = memberRepository.findById(member.getId())
        .orElseThrow(() -> new MemberNotFoundException(member.getId()));

    Optional.ofNullable(member.getFirstName())
        .ifPresent(existmember::setFirstName);

    Optional.ofNullable(member.getLastName())
            .ifPresent(existmember::setLastName);
    Optional.ofNullable(member.getEmail())
            .ifPresent(existmember::setEmail);

    save(member);
  }

  public void deleteById(Long id) {
    memberRepository.findById(id).orElseThrow(() -> new MemberNotFoundException(id));
    memberRepository.deleteById(id);
  }


}