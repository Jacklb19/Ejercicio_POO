package co.ucc.ejercicio.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Table(name = "administratives")
@Getter
@NoArgsConstructor
public class Administrative extends Person implements Approver, Notifiable, Authenticable {

    private String area;

    public Administrative(String name, String email, String area) {
        this(name, email, null, area);
    }

    public Administrative(String name, String email, String password, String area) {
        super(null, name, email, password);
        this.area = area;
    }

    @Override
    public void approveRequest(String requestCode) {
        System.out.println("Aprobando solicitud: " + requestCode);
    }

    @Override
    public void sendNotification(String message) {
        System.out.println("Admin notifica: " + message);
    }

    @Override
    public boolean login(String user, String password) {
        return Objects.equals(getEmail(), user) && Objects.equals(getPassword(), password);
    }
}
