package uniandes.edu.co.proyecto.modelo;

public class Eps {
    private String nitEPS;
    private String nombreEPS;

    // Constructor
    public Eps(String nitEPS, String nombreEPS) {
        this.nitEPS = nitEPS;
        this.nombreEPS = nombreEPS;
    }

    // Getters and Setters
    public String getNitEPS() {
        return nitEPS;
    }

    public void setNitEPS(String nitEPS) {
        this.nitEPS = nitEPS;
    }

    public String getNombreEPS() {
        return nombreEPS;
    }

    public void setNombreEPS(String nombreEPS) {
        this.nombreEPS = nombreEPS;
    }
}