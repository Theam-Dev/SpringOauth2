package com.acledabank.springjwtauth.domain.primary.repository;

import com.acledabank.springjwtauth.domain.primary.model.SecurityGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecurityGroupRepo extends JpaRepository<SecurityGroup, Long> {
}
