package uniandes.edu.co.proyecto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.PrestacionServicio;

import java.util.Collection;

public interface PrestacionServicioRepository extends JpaRepository<PrestacionServicio, Integer> {

    @Query(value = "SELECT * FROM PrestacionServicio", nativeQuery = true)
    Collection<PrestacionServicio> findAllPrestacionServicios();

    @Query(value = "SELECT * FROM PrestacionServicio WHERE idPrestacionServicio = :id", nativeQuery = true)
    PrestacionServicio findPrestacionServicioById(@Param("id") int id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO PrestacionServicio (fechaHora, numDoc, registroMedico, nitIps, codigoServicio) VALUES (:fechaHora, :numDoc, :registroMedico, :nitIps, :codigoServicio)", nativeQuery = true)
    void insertarPrestacionServicio(@Param("fechaHora") String fechaHora, @Param("numDoc") String numDoc, @Param("registroMedico") int registroMedico, @Param("nitIps") String nitIps, @Param("codigoServicio") int codigoServicio);

    @Modifying
    @Transactional
    @Query(value = "UPDATE PrestacionServicio SET fechaHora = :fechaHora, numDoc = :numDoc, registroMedico = :registroMedico, nitIps = :nitIps, codigoServicio = :codigoServicio WHERE idPrestacionServicio = :id", nativeQuery = true)
    void actualizarPrestacionServicio(@Param("id") int id, @Param("fechaHora") String fechaHora, @Param("numDoc") String numDoc, @Param("registroMedico") int registroMedico, @Param("nitIps") String nitIps, @Param("codigoServicio") int codigoServicio);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM PrestacionServicio WHERE idPrestacionServicio = :id", nativeQuery = true)
    void eliminarPrestacionServicio(@Param("id") int id);
}
