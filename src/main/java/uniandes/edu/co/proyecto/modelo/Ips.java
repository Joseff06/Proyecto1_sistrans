package uniandes.edu.co.proyecto.modelo;

public class Ips {
    private String nitIPS;
    private String nombreIPS;
    private String direccion;
    private String telefono;

    // Constructor
    public Ips(String nitIPS, String nombreIPS, String direccion, String telefono) {
        this.nitIPS = nitIPS;
        this.nombreIPS = nombreIPS;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    // Getters and Setters
    public String getNitIPS() {
        return nitIPS;
    }

    public void setNitIPS(String nitIPS) {
        this.nitIPS = nitIPS;
    }

    public String getNombreIPS() {
        return nombreIPS;
    }

    public void setNombreIPS(String nombreIPS) {
        this.nombreIPS = nombreIPS;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}