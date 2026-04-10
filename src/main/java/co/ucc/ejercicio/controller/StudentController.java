package co.ucc.ejercicio.controller;

import co.ucc.ejercicio.model.Student;
import co.ucc.ejercicio.service.StudentService;
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
@RequestMapping("/api/students")
@Tag(name = "Students", description = "Operaciones de estudiantes")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService service;

    @PostMapping
    @Operation(summary = "Crear estudiante", description = "Registra un nuevo estudiante en la base de datos")
    public ResponseEntity<Student> create(@RequestBody Student student) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(student));
    }

    @PostMapping("/login")
    @Operation(summary = "Login de estudiante", description = "Valida credenciales contra la base de datos")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        if (!service.login(request.email(), request.password())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales invalidas");
        }
        return ResponseEntity.ok("Login exitoso");
    }

    @PostMapping("/notify/{id}")
    @Operation(summary = "Notificar estudiante", description = "Envia un correo al estudiante con el mensaje indicado")
    public ResponseEntity<String> notify(@PathVariable Long id, @RequestBody NotificationRequest request) {
        service.sendNotification(id, request.message());
        return ResponseEntity.ok("Notificacion enviada");
    }

    @GetMapping
    @Operation(summary = "Listar estudiantes", description = "Obtiene todos los estudiantes")
    public ResponseEntity<List<Student>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar estudiante por id", description = "Obtiene un estudiante por su identificador")
    public ResponseEntity<Student> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar estudiante", description = "Elimina un estudiante por su identificador")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
