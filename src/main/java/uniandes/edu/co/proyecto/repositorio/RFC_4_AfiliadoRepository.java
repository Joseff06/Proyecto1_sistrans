package uniandes.edu.co.proyecto.repositorio;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uniandes.edu.co.proyecto.modelo.Afiliado;

import java.util.List;

@Repository
public interface RFC_4_AfiliadoRepository extends JpaRepository<Afiliado, Integer> {

    @Query(value = "SELECT ss.nombre AS nombreServicio, os.fechaOrden, m.nombreMedico, i.nombreIPS " +
                   "FROM ORDEN_SERVICIO os " +
                   "JOIN SERVICIOS_SALUD ss ON os.codigoServicio = ss.codigo " +
                   "JOIN MEDICO m ON os.regMedico = m.regMedico " +
                   "JOIN MEDICO_IPS mi ON m.regMedico = mi.regMedico " +
                   "JOIN IPS i ON mi.nitIPS = i.nitIPS " +
                   "WHERE os.numDocAfiliado = :numDocAfiliado " +
                   "AND os.fechaOrden BETWEEN :fechaInicio AND :fechaFin", nativeQuery = true)
    List<Object[]> mostrarUtilizacionServiciosAfiliado(@Param("numDocAfiliado") int numDocAfiliado, @Param("fechaInicio") String fechaInicio, @Param("fechaFin") String fechaFin);
}