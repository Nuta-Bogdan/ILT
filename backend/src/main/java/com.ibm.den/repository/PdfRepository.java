package com.ibm.den.repository;

import com.ibm.den.entities.PdfHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PdfRepository extends JpaRepository<PdfHolder, Long> {
    public PdfHolder findByEmail(String email);
}
