package co.ucc.ejercicio.service;

import co.ucc.ejercicio.model.Student;
import co.ucc.ejercicio.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository repository;
    private final EmailService emailService;

    public Student create(Student student) {
        Student saved = repository.save(student);
        try {
            emailService.sendEmail(
                    saved.getEmail(),
                    "Bienvenido al sistema",
                    "Hola " + saved.getName() + ", tu cuenta fue creada exitosamente."
            );
        } catch (MailException ex) {
            System.out.println("No se pudo enviar el correo de bienvenida: " + ex.getMessage());
        }
        return saved;
    }

    public List<Student> findAll() {
        return repository.findAll();
    }

    public Student findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
    }

    public boolean login(String email, String password) {
        return repository.findByEmail(email)
                .map(student -> student.login(email, password))
                .orElse(false);
    }

    public void sendNotification(Long id, String message) {
        Student student = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        emailService.sendEmail(
                student.getEmail(),
                "Notificacion academica para estudiante",
                "Hola "
        );
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
