package com.project.email_service.controllers;

import com.project.email_service.application.EmailSenderService;
import com.project.email_service.core.dtos.EmailRequestDTO;
import com.project.email_service.core.exceptions.EmailSenderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/email")
public class EmailSenderController {

    private final EmailSenderService emailSenderService;

    @Autowired
    public EmailSenderController(EmailSenderService emailSenderService) {
        this.emailSenderService = emailSenderService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequestDTO request) {
        try {
            this.emailSenderService.sendEmail(request.to(), request.subject(), request.body());

            return ResponseEntity.ok("email enviado com sucesso");
        } catch (EmailSenderException exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Não foi possível enviar o seu email");
        }
    }
}
