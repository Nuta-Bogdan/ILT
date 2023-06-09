package com.ibm.den.entities;

import jakarta.persistence.*;
@Entity
@Table(name = "pdf_holder")
public class PdfHolder extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(nullable = false)
    private String email;
    @Lob
    @Column(nullable = false)
    private byte[] pdf;

    public PdfHolder(String email, byte[] pdf) {
        this.email = email;
        this.pdf = pdf;
    }

    public PdfHolder() {
    }

    public String getEmail() {
        return email;
    }

    public byte[] getPdf() {
        return pdf;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPdf(byte[] pdf) {
        this.pdf = pdf;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
