package co.ucc.ejercicio.service;

import co.ucc.ejercicio.model.Administrative;
import co.ucc.ejercicio.repository.AdministrativeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdministrativeService {

    private final AdministrativeRepository repository;
    private final EmailService emailService;

    public Administrative create(Administrative administrative) {
        return repository.save(administrative);
    }

    public List<Administrative> findAll() {
        return repository.findAll();
    }

    public Administrative findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Administrativo no encontrado"));
    }

    public boolean login(String email, String password) {
        return repository.findByEmail(email)
                .map(administrative -> administrative.login(email, password))
                .orElse(false);
    }

    public void sendNotification(Long id, String message) {
        Administrative administrative = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Administrativo no encontrado"));

        emailService.sendEmail(
                administrative.getEmail(),
                "Notificacion administrativa",
                "Hola " + administrative.getName() + ",\n\n" + message
        );
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
