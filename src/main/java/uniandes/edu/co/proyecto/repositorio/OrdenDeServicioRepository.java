package uniandes.edu.co.proyecto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.OrdenDeServicio;

import java.util.Collection;

public interface OrdenDeServicioRepository extends JpaRepository<OrdenDeServicio, Integer> {

    
    @Query(value = "SELECT * FROM OrdenDeServicio", nativeQuery = true)
    Collection<OrdenDeServicio> findAllOrdenesDeServicio();

    @Query(value = "SELECT * FROM OrdenDeServicio WHERE idOrdenServicio = :id", nativeQuery = true)
    OrdenDeServicio findOrdenDeServicioById(@Param("id") int id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO OrdenDeServicio (fechaHora, idEstadoOrden, numDoc, registroMedico, idCita, codigo) VALUES (:fechaHora, :idEstadoOrden, :numDoc, :registroMedico, :idCita, :codigo)", nativeQuery = true)
    void insertarOrdenDeServicio(@Param("fechaHora") String fechaHora, @Param("idEstadoOrden") int idEstadoOrden, @Param("numDoc") String numDoc, @Param("registroMedico") int registroMedico, @Param("idCita") int idCita, @Param("codigo") int codigo);

    @Modifying
    @Transactional
    @Query(value = "UPDATE OrdenDeServicio SET fechaHora = :fechaHora, idEstadoOrden = :idEstadoOrden, numDoc = :numDoc, registroMedico = :registroMedico, idCita = :idCita, codigo = :codigo WHERE idOrdenServicio = :id", nativeQuery = true)
    void actualizarOrdenDeServicio(@Param("id") int id, @Param("fechaHora") String fechaHora, @Param("idEstadoOrden") int idEstadoOrden, @Param("numDoc") String numDoc, @Param("registroMedico") int registroMedico, @Param("idCita") int idCita, @Param("codigo") int codigo);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM OrdenDeServicio WHERE idOrdenServicio = :id", nativeQuery = true)
    void eliminarOrdenDeServicio(@Param("id") int id);
}
