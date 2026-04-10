package co.ucc.ejercicio.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@MappedSuperclass
@Schema(description = "Base comun para las entidades del sistema")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public abstract class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador unico", example = "1")
    private Long id;

    @Schema(description = "Nombre completo", example = "Maria Perez")
    private String name;
    @Schema(description = "Correo electronico", example = "maria@ucc.edu.co")
    private String email;
    @Schema(description = "Contrasena de acceso", example = "123456")
    private String password;
}
