package co.ucc.ejercicio.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Table(name = "students")
@Getter
@NoArgsConstructor
public class Student extends Person implements Notifiable, Authenticable {

    private String studentCode;

    public Student(String name, String email, String studentCode) {
        this(name, email, null, studentCode);
    }

    public Student(String name, String email, String password, String studentCode) {
        super(null, name, email, password);
        this.studentCode = studentCode;
    }

    @Override
    public void sendNotification(String message) {
        System.out.println("Notificando a " + getName() + ": " + message);
    }

    @Override
    public boolean login(String user, String password) {
        return Objects.equals(getEmail(), user) && Objects.equals(getPassword(), password);
    }
}
