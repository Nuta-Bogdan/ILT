package com.ibm.den.dto;

import com.ibm.den.entities.PdfHolder;
import jakarta.persistence.*;
public class PdfDto {
    private String email;
    private byte[] pdf;

    public PdfDto(String email, byte[] pdf) {
        this.email = email;
        this.pdf = pdf;
    }

    public PdfDto() {
    }

    public PdfDto(PdfHolder pdfHolder) {
        this.email = pdfHolder.getEmail();
        this.pdf = pdfHolder.getPdf();
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

}
