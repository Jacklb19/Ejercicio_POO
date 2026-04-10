package co.ucc.ejercicio.controller;

import co.ucc.ejercicio.model.Professor;
import co.ucc.ejercicio.service.ProfessorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/professors")
@Tag(name = "Professors", description = "Operaciones de profesores")
@RequiredArgsConstructor
public class ProfessorController {

    private final ProfessorService service;

    @PostMapping
    @Operation(summary = "Crear profesor", description = "Registra un nuevo profesor en la base de datos")
    public ResponseEntity<Professor> create(@RequestBody Professor professor) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(professor));
    }

    @PostMapping("/login")
    @Operation(summary = "Login de profesor", description = "Valida credenciales contra la base de datos")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        if (!service.login(request.email(), request.password())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales invalidas");
        }
        return ResponseEntity.ok("Login exitoso");
    }

    @PostMapping("/notify/{id}")
    @Operation(summary = "Notificar profesor", description = "Envia un correo al profesor con el mensaje indicado")
    public ResponseEntity<String> notify(@PathVariable Long id, @RequestBody NotificationRequest request) {
        service.sendNotification(id, request.message());
        return ResponseEntity.ok("Notificacion enviada");
    }

    @GetMapping
    @Operation(summary = "Listar profesores", description = "Obtiene todos los profesores")
    public ResponseEntity<List<Professor>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar profesor por id", description = "Obtiene un profesor por su identificador")
    public ResponseEntity<Professor> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar profesor", description = "Elimina un profesor por su identificador")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
