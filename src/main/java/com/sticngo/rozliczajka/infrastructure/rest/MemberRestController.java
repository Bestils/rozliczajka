package com.sticngo.rozliczajka.infrastructure.rest;

import com.sticngo.rozliczajka.domain.members.Member;
import com.sticngo.rozliczajka.domain.members.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class MemberRestController {

  private final MemberService memberService;

  @GetMapping
  public List<Member> findAll() {
    return memberService.findAll();
  }

  @GetMapping("/{id}")
  public Member getMemberById(@PathVariable Long id) {
    return memberService.findById(id);
  }

  @PostMapping
  public Member addMember(@RequestBody Member category) {
    return memberService.save(category);
  }

  @PatchMapping
  public void updateMember(@RequestBody Member category) {
    memberService.updateMember(category);
  }

  @DeleteMapping("{id}")
  public void deleteMember(@PathVariable Long id) {
      memberService.deleteById(id);
  }
}
