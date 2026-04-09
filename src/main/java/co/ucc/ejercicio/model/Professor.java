package co.ucc.ejercicio.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Table(name = "professors")
@Getter
@NoArgsConstructor
public class Professor extends Person implements Authenticable, Evaluator, Notifiable {

    private String specialty;

    public Professor(String name, String email, String specialty) {
        this(name, email, null, specialty);
    }

    public Professor(String name, String email, String password, String specialty) {
        super(null, name, email, password);
        this.specialty = specialty;
    }

    @Override
    public boolean login(String user, String password) {
        return Objects.equals(getEmail(), user) && Objects.equals(getPassword(), password);
    }

    @Override
    public void evaluate(Student student, double grade) {
        System.out.printf("Evaluando a %s con %.1f%n", student.getName(), grade);
    }

    @Override
    public void sendNotification(String message) {
        System.out.println("Notificando a " + getName() + ": " + message);
    }
}
