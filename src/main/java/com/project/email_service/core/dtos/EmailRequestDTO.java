package com.project.email_service.core.dtos;

public record EmailRequestDTO(String to, String subject, String body) {
}
