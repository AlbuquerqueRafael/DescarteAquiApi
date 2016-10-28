package com.descarteaqui.DAO;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.descarteaqui.domain.Company;


@Repository
@Transactional
public interface CompanyDAO extends JpaRepository<Company, Long>{
	
	public Company findById(Long id);
	public Page<Company> findAll(Specification<?> spec, Pageable page);
	
}
