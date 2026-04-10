package co.ucc.ejercicio.service;

import co.ucc.ejercicio.model.Professor;
import co.ucc.ejercicio.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfessorService {

    private final ProfessorRepository repository;
    private final EmailService emailService;

    public Professor create(Professor professor) {
        return repository.save(professor);
    }

    public List<Professor> findAll() {
        return repository.findAll();
    }

    public Professor findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profesor no encontrado"));
    }

    public boolean login(String email, String password) {
        return repository.findByEmail(email)
                .map(professor -> professor.login(email, password))
                .orElse(false);
    }

    public void sendNotification(Long id, String message) {
        Professor professor = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profesor no encontrado"));

        emailService.sendEmail(
                professor.getEmail(),
                "Notificacion academica para profesor",
                "Hola " + professor.getName() + ",\n\n" + message
        );
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
