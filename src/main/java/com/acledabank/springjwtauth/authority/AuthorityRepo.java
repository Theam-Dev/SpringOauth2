package com.acledabank.springjwtauth.authority;

import com.acledabank.springjwtauth.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepo extends JpaRepository<Authority, Long> {
}
