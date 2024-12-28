package com.acledabank.springjwtauth.domain.primary.repository;

import com.acledabank.springjwtauth.domain.primary.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepo extends JpaRepository<Authority, Long> {
}
