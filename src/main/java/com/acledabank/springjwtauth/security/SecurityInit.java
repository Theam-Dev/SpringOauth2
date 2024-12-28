package com.acledabank.springjwtauth.security;

import com.acledabank.springjwtauth.domain.primary.repository.AuthorityRepo;
import com.acledabank.springjwtauth.domain.primary.model.Authority;
import com.acledabank.springjwtauth.domain.primary.model.SecurityGroup;
import com.acledabank.springjwtauth.domain.primary.model.User;
import com.acledabank.springjwtauth.domain.primary.repository.SecurityGroupRepo;
import com.acledabank.springjwtauth.domain.primary.repository.UserRepo;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class SecurityInit {
    private final AuthorityRepo authorityRepo;
    private final UserRepo userRepo;
    private final SecurityGroupRepo securityGroupRepo;
    private final PasswordEncoder passwordEncoder;
 //@PostConstruct
    void init() {
        //user management
        Authority userRead = new Authority();
        userRead.setName("user:read");
        Authority userWrite = new Authority();
        userWrite.setName("user:write");
        //Report
        Authority reportRead = new Authority();
        reportRead.setName("report:read");
        Authority reportWrite = new Authority();
        reportWrite.setName("report:write");

        authorityRepo.saveAll(List.of(userRead, userWrite, reportRead, reportWrite));


        SecurityGroup adminGroup = new SecurityGroup();
        adminGroup.setName("administrator");
        adminGroup.setAuthorities(List.of(userRead,userWrite, reportRead, reportWrite));

        SecurityGroup staffGroup = new SecurityGroup();
        staffGroup.setName("Staff");
        staffGroup.setAuthorities(List.of(userRead,reportRead));

        securityGroupRepo.saveAll(List.of(adminGroup,staffGroup));

        User users = new User();
        users.setUsername("admin");
        users.setEmail("admin@gmail.com");
        users.setPassword(passwordEncoder.encode("admin"));
        users.setGender("male");
        users.setDob(LocalDate.of(2002,9,1));
        users.setFamilyName("John");
        users.setGivenName("Son");
        users.setPhoneNumber("0998878940");
        users.setAccountNonExpired(true);
        users.setAccountNonLocked(true);
        users.setCredentialsNonExpired(true);
        users.setIsEnabled(true);
        users.setEmailVerified(true);
        users.setSecurityGroups(Set.of(adminGroup));

        User staffs = new User();
        staffs.setUsername("staff");
        staffs.setEmail("staff@gmail.com");
        staffs.setPassword(passwordEncoder.encode("staff"));
        staffs.setGender("female");
        staffs.setDob(LocalDate.of(2002,8,1));
        staffs.setFamilyName("Mary");
        staffs.setGivenName("Nona");
        staffs.setPhoneNumber("0998878870");
        staffs.setAccountNonExpired(true);
        staffs.setAccountNonLocked(true);
        staffs.setCredentialsNonExpired(true);
        staffs.setIsEnabled(true);
        staffs.setEmailVerified(true);
        staffs.setSecurityGroups(Set.of(adminGroup));

        userRepo.saveAll(List.of(users,staffs));
    }
}
