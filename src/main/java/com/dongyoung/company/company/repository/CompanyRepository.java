package com.dongyoung.company.company.repository;

import com.dongyoung.company.company.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,Long> {
    Company findByCompanyId(Long companyId);

}
