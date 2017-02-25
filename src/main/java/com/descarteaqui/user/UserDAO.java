package com.descarteaqui.user;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
@Transactional
public interface UserDAO extends JpaRepository<AppUser, Long>{
	
	public AppUser findById(Long id);
	public Page<AppUser> findAll(Specification<?> spec, Pageable page);
	public AppUser findByUsername(String username);
}
