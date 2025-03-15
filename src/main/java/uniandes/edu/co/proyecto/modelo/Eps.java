package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
@Table(name="Eps")
public class Eps {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int idEps;
    private String nombreEps;

    @OneToMany(mappedBy = "eps")
    private List<Ips> ipsList;

    // Constructor
    public Eps(int idEps, String nombreEps) {
        this.idEps = idEps;
        this.nombreEps = nombreEps;
    }

    // Getters and Setters
    public int getIdEps() {
        return idEps;
    }

    public void setIdEps(int idEps) {
        this.idEps = idEps;
    }

    public String getNombreEps() {
        return nombreEps;
    }

    public void setNombreEps(String nombreEps) {
        this.nombreEps = nombreEps;
    }

    public List<Ips> getIpsList() {
        return ipsList;
    }

    public void setIpsList(List<Ips> ipsList) {
        this.ipsList = ipsList;
    }
}