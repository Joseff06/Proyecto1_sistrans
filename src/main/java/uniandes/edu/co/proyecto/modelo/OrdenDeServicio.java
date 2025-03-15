package uniandes.edu.co.proyecto.modelo;

public class OrdenDeServicio {
    private int idOrden;
    private String fecha;
    private String estado;

    // Constructor
    public OrdenDeServicio(int idOrden, String fecha, String estado) {
        this.idOrden = idOrden;
        this.fecha = fecha;
        this.estado = estado;
    }

    // Getters and Setters
    public int getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}