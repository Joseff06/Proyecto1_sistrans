package uniandes.edu.co.proyecto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uniandes.edu.co.proyecto.modelo.OrdenDeServicio;

import java.util.List;

@Repository
public interface RFC_2_OrdenServicioRepository extends JpaRepository<OrdenDeServicio, Integer> {

    @Query(value = "SELECT ss.nombre, COUNT(os.idOrden) AS totalSolicitudes " +
                   "FROM ORDEN_SERVICIO os " +
                   "JOIN SERVICIOS_SALUD ss ON os.codigoServicio = ss.codigo " +
                   "WHERE os.fechaOrden BETWEEN :fechaInicio AND :fechaFin " +
                   "GROUP BY ss.nombre " +
                   "ORDER BY totalSolicitudes DESC " +
                   "FETCH FIRST 20 ROWS ONLY", nativeQuery = true)
    List<Object[]> mostrarServiciosMasSolicitados(@Param("fechaInicio") String fechaInicio, @Param("fechaFin") String fechaFin);
}
