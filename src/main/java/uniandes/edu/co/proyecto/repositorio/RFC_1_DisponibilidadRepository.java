package uniandes.edu.co.proyecto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uniandes.edu.co.proyecto.modelo.Disponibilidad;

import java.util.List;

@Repository
public interface RFC_1_DisponibilidadRepository extends JpaRepository<Disponibilidad, Integer> {

    @Query(value = "SELECT ss.nombre AS nombreServicio, d.fecha, d.estadoDisponibilidad, i.nombreIPS, m.nombreMedico " +
                   "FROM DISPONIBILIDAD d " +
                   "JOIN SERVICIOS_SALUD ss ON d.codigoServicio = ss.codigo " +
                   "JOIN IPS_SERVICIOS_SALUD iss ON ss.codigo = iss.codigoServicio " +
                   "JOIN IPS i ON iss.nitIPS = i.nitIPS " +
                   "JOIN MEDICO m ON d.regMedico = m.regMedico " +
                   "WHERE d.codigoServicio = :codigoServicio " +
                   "AND d.fecha BETWEEN SYSDATE AND SYSDATE + INTERVAL '4' WEEK", nativeQuery = true)
    List<Object[]> consultarDisponibilidadServicio(@Param("codigoServicio") int codigoServicio);
}
