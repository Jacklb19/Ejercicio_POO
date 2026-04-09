package co.ucc.ejercicio.model;

public interface Authenticable {
    boolean login(String user, String password);
}