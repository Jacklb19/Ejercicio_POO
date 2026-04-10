package co.ucc.ejercicio.controller;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Mensaje para notificar por correo")
public record NotificationRequest(
        @Schema(description = "Contenido del mensaje", example = "Revisa tu bandeja de entrada institucional")
        String message
) {
}
