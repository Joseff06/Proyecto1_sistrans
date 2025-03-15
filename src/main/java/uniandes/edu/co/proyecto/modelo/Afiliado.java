package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
@Table(name="Afiliado")
public class Afiliado {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private String tipoDocumento;
    private String numeroDocumento;
    private String nombre;
    private String fechaNacimiento;
    private String direccion;
    private String telefono;
    private String tipoAfiliado;
    private String parentesco;
    private String correo;

    @OneToMany(mappedBy = "afiliado")
    private List<Cita> citas;

    public Afiliado(){;}
    // Constructor
    public Afiliado(String tipoDocumento, String numeroDocumento, String nombre, String fechaNacimiento, String direccion, String telefono, String tipoAfiliado, String parentesco, String correo) {
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = direccion;
        this.telefono = telefono;
        this.tipoAfiliado = tipoAfiliado;
        this.parentesco = parentesco;
        this.correo = correo;
    }

    // Getters and Setters
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
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

    public String getTipoAfiliado() {
        return tipoAfiliado;
    }

    public void setTipoAfiliado(String tipoAfiliado) {
        this.tipoAfiliado = tipoAfiliado;
    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}