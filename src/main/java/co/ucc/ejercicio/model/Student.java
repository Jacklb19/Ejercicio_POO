package co.ucc.ejercicio.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Table(name = "students")
@Schema(description = "Estudiante del sistema")
@Getter
@NoArgsConstructor
public class Student extends Person implements Notifiable, Authenticable {

    @Schema(description = "Codigo del estudiante", example = "EST-1001")
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
