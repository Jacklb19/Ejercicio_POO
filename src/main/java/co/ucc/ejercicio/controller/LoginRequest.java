package co.ucc.ejercicio.controller;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Credenciales de acceso")
public record LoginRequest(String email, String password) {
}
