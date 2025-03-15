package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import java.io.Serializable;

@Embeddable
public class MedicoIpsPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "registroMedico", referencedColumnName = "registroMedico")
    private Medico medico;

    @ManyToOne
    @JoinColumn(name = "nitIPS", referencedColumnName = "nitIPS")
    private Ips ips;

    public MedicoIpsPK() {
        super();
    }

    public MedicoIpsPK(Medico medico, Ips ips) {
        super();
        this.medico = medico;
        this.ips = ips;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Ips getIps() {
        return ips;
    }

    public void setIps(Ips ips) {
        this.ips = ips;
    }
}
