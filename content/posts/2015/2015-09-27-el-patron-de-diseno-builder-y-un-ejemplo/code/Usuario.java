package io.github.picodotdev.pattern.builder;

public class Usuario {
  
    private String email;
    private String nombre;
    private String apellidos;
    private String telefono;
    private String direccion;
  
    private Usuario() {
    }
  
    Usuario(UsuarioBuilder builder) {
        if (builder.getEmail() == null) {
            throw new IllegalArgumentException("email es requerido");
        }
        this.email = builder.getEmail();
        this.nombre = builder.getNombre();
        this.apellidos = builder.getApellidos();
        this.telefono = builder.getTelefono();
        this.direccion = builder.getDireccion();
    }
}