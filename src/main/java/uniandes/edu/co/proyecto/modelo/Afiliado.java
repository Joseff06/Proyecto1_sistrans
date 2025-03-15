package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;


@Entity
@Table(name="Afiliado")
public class Afiliado {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int numDoc;
    private String tipoDoc;
    private String nombre;
    private String fechaNacimiento;
    private String direccion;
    private int telefono;
    private String tipoAfiliado;
    private String parentesco;
    private int contribuyente;
    private String correo;
    // Constructor
    public Afiliado(int numDoc, String tipoDoc, String nombre, String fechaNacimiento, String direccion, int telefono, String tipoAfiliado, String parentesco, int contribuyente) {
        this.numDoc = numDoc;
        this.tipoDoc = tipoDoc;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = direccion;
        this.telefono = telefono;
        this.tipoAfiliado = tipoAfiliado;
        this.parentesco = parentesco;
        this.contribuyente = contribuyente;
        this.correo = correo;
    }

    // Getters and Setters
    public int getNumDoc() {
        return numDoc;
    }

    public void setNumDoc(int numDoc) {
        this.numDoc = numDoc;
    }

    public String getTipoDoc() {
        return tipoDoc;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setTipoDoc(String tipoDoc) {
        this.tipoDoc = tipoDoc;
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

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
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

    public int getContribuyente() {
        return contribuyente;
    }

    public void setContribuyente(int contribuyente) {
        this.contribuyente = contribuyente;
    }
}