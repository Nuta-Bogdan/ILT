package com.ibm.den.services;

import com.ibm.den.dto.PdfDto;
import com.ibm.den.entities.PdfHolder;
import com.ibm.den.repository.PdfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PdfService {
    @Autowired
    private PdfRepository pdfRepository;

    PdfService(PdfRepository pdfRepository) {
        this.pdfRepository = pdfRepository;
    }

    public PdfDto getPdf(String email) {
        return new PdfDto(pdfRepository.findByEmail(email));
    }

    public void createPdf(PdfDto pdfDto, String email) {
        PdfHolder pdfHolder = new PdfHolder();
        pdfHolder.setEmail(email);
        pdfHolder.setPdf(pdfDto.getPdf());
        pdfRepository.save(pdfHolder);
    }
}
