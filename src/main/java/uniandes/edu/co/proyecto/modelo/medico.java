package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
@Table(name="Medico")
public class Medico {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int registroMedico;
    private String nombre;
    private String tipoDocumento;
    private String numeroDocumento;
    private int idEspecialidad;

    @OneToMany(mappedBy = "Medico")
    private List<Disponibilidad> disponibilidades;

    public Medico(){;}

    // Constructor
    public Medico(int registroMedico, String nombre, String tipoDocumento, String numeroDocumento, int idEspecialidad) {
        this.registroMedico = registroMedico;
        this.nombre = nombre;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.idEspecialidad = idEspecialidad;
    }

    // Getters and Setters
    public int getRegistroMedico() {
        return registroMedico;
    }

    public void setRegistroMedico(int registroMedico) {
        this.registroMedico = registroMedico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public int getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(int idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    public List<Disponibilidad> getDisponibilidades() {
        return disponibilidades;
    }

    public void setDisponibilidades(List<Disponibilidad> disponibilidades) {
        this.disponibilidades = disponibilidades;
    }
}
