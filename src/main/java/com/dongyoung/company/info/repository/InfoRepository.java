package com.dongyoung.company.info.repository;

import com.dongyoung.company.info.entity.Info;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InfoRepository extends JpaRepository<Info,Long> {
}
