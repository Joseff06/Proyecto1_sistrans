package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
@Table(name="Ips")
public class Ips {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private String nitIPS;
    private String nombreIPS;
    private String direccion;
    private String telefono;
    private int idEps;

    @OneToMany(mappedBy = "ips")
    private List<Disponibilidad> disponibilidades;

    @OneToMany(mappedBy = "ips")
    private List<Cita> citas;

    public Ips(){;}

    // Constructor
    public Ips(String nitIPS, String nombreIPS, String direccion, String telefono, int idEps) {
        this.nitIPS = nitIPS;
        this.nombreIPS = nombreIPS;
        this.direccion = direccion;
        this.telefono = telefono;
        this.idEps = idEps;
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

    public int getIdEps() {
        return idEps;
    }

    public void setIdEps(int idEps) {
        this.idEps = idEps;
    }

    public List<Disponibilidad> getDisponibilidades() {
        return disponibilidades;
    }

    public void setDisponibilidades(List<Disponibilidad> disponibilidades) {
        this.disponibilidades = disponibilidades;
    }

    public List<Cita> getCitas() {
        return citas;
    }

    public void setCitas(List<Cita> citas) {
        this.citas = citas;
    }
}