package co.ucc.ejercicio.controller;

import co.ucc.ejercicio.model.Administrative;
import co.ucc.ejercicio.service.AdministrativeService;
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
@RequestMapping("/api/administratives")
@Tag(name = "Administratives", description = "Operaciones administrativas")
@RequiredArgsConstructor
public class AdministrativeController {

    private final AdministrativeService service;

    @PostMapping
    @Operation(summary = "Crear administrativo", description = "Registra un nuevo usuario administrativo en la base de datos")
    public ResponseEntity<Administrative> create(@RequestBody Administrative administrative) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(administrative));
    }

    @PostMapping("/login")
    @Operation(summary = "Login de administrativo", description = "Valida credenciales contra la base de datos")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        if (!service.login(request.email(), request.password())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales invalidas");
        }
        return ResponseEntity.ok("Login exitoso");
    }

    @PostMapping("/notify/{id}")
    @Operation(summary = "Notificar administrativo", description = "Envia un correo al usuario administrativo con el mensaje indicado")
    public ResponseEntity<String> notify(@PathVariable Long id, @RequestBody NotificationRequest request) {
        service.sendNotification(id, request.message());
        return ResponseEntity.ok("Notificacion enviada");
    }

    @GetMapping
    @Operation(summary = "Listar administrativos", description = "Obtiene todos los administrativos")
    public ResponseEntity<List<Administrative>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar administrativo por id", description = "Obtiene un administrativo por su identificador")
    public ResponseEntity<Administrative> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar administrativo", description = "Elimina un administrativo por su identificador")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
