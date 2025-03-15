package uniandes.edu.co.proyecto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uniandes.edu.co.proyecto.modelo.ServicioDeSalud;

import java.util.List;

@Repository
public interface RFC_3_ServicioSaludRepository extends JpaRepository<ServicioDeSalud, String> {

    @Query(value = "SELECT ss.nombre, " +
                   "       (SELECT COUNT(*) FROM DISPONIBILIDAD d WHERE d.codigoServicio = ss.codigo AND d.fecha BETWEEN :fechaInicio AND :fechaFin) AS serviciosDisponibles, " +
                   "       (SELECT COUNT(*) FROM ORDEN_SERVICIO os WHERE os.codigoServicio = ss.codigo AND os.fechaOrden BETWEEN :fechaInicio AND :fechaFin) AS serviciosUsados, " +
                   "       (SELECT COUNT(*) FROM ORDEN_SERVICIO os WHERE os.codigoServicio = ss.codigo AND os.fechaOrden BETWEEN :fechaInicio AND :fechaFin) / " +
                   "       (SELECT COUNT(*) FROM DISPONIBILIDAD d WHERE d.codigoServicio = ss.codigo AND d.fecha BETWEEN :fechaInicio AND :fechaFin) AS indiceUso " +
                   "FROM SERVICIOS_SALUD ss", nativeQuery = true)
    List<Object[]> mostrarIndiceUsoServicios(@Param("fechaInicio") String fechaInicio, @Param("fechaFin") String fechaFin);
}