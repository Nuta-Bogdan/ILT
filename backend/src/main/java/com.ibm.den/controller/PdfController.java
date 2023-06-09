package com.ibm.den.controller;

import com.ibm.den.dto.PdfDto;
import com.ibm.den.services.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/pdf")
public class PdfController {
    @Autowired
    private PdfService PdfService;

    PdfController(PdfService PdfService) {
        this.PdfService = PdfService;
    }

    @GetMapping("/{email}")
    public PdfDto getPdf(@PathVariable String email) {
        return PdfService.getPdf(email);
    }

    @PostMapping("/{email}")
    public void createPdf(@RequestBody PdfDto pdfDto, @PathVariable String email) {
        PdfService.createPdf(pdfDto, email);
    }
}
