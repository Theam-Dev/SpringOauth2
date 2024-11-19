package com.acledabank.springjwtauth.securitygroup;

import com.acledabank.springjwtauth.domain.SecurityGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecurityGroupRepo extends JpaRepository<SecurityGroup, Long> {
}
