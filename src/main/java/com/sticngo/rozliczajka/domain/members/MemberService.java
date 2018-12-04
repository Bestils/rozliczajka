package com.sticngo.rozliczajka.domain.members;


import com.sticngo.rozliczajka.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

  private final MemberRepository memberRepository;
  private final UserService userService;

  public List<Member> findAll() {
    return memberRepository.findAll();
  }

  public Member findById(Long id) {
    return memberRepository.findById(id).orElseThrow(() -> new MemberNotFoundException(id));
  }

  public List<Member> findCategoriesByUserId(Long id) {
    return memberRepository.findMembersByUserId(id);
  }

  public Member save(Member member) {
    return memberRepository.save(member);
  }

  public void updatemember(Member member) {
    Member existmember = memberRepository.findById(member.getId())
        .orElseThrow(() -> new MemberNotFoundException(member.getId()));

    Optional.ofNullable(member.getName())
        .ifPresent(existmember::setName);

    save(member);
  }

  public void deleteById(Long id) {
    memberRepository.findById(id).orElseThrow(() -> new MemberNotFoundException(id));
    memberRepository.deleteById(id);
  }

  public Member createForUser(@Valid Member member, Long currentUserId) {
    member.setUser(userService.getById(currentUserId));
    return memberRepository.save(member);
  }
}