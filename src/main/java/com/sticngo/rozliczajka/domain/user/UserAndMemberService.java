package com.sticngo.rozliczajka.domain.user;


import com.sticngo.rozliczajka.domain.members.Member;
import com.sticngo.rozliczajka.domain.members.MemberRepository;
import com.sticngo.rozliczajka.domain.members.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j

@Transactional
@RequiredArgsConstructor
public class UserAndMemberService {
    private final UserService userService;
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final MemberRepository memberRepository;

    public void createUserAndMember(UserAndMember userAndMember) {


        User user = new User();
        Member member = new Member();

        user.setId(userAndMember.getUserId());
        user.setPassword(userAndMember.getPassword());
        user.setLogin(userAndMember.getLogin());
//        user.setMember(member);
        member.setFirstName(userAndMember.getFirstName());
        member.setLastName(userAndMember.getLastName());
        member.setId(userAndMember.getUserId());
        member.setEmail(userAndMember.getEmail());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        memberRepository.save(member);
    }


}
