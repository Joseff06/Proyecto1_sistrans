package uniandes.edu.co.proyecto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Cita;

import java.util.Collection;

public interface CitaRepository extends JpaRepository<Cita, Integer> {

    @Query(value = "SELECT * FROM Cita", nativeQuery = true)
    Collection<Cita> findAllCitas();

    @Query(value = "SELECT * FROM Cita WHERE idCita = :id", nativeQuery = true)
    Cita findCitaById(@Param("id") int id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Cita (fechaHora, numDoc, idDisponibilidad, nitIps) VALUES (:fechaHora, :numDoc, :idDisponibilidad, :nitIps)", nativeQuery = true)
    void insertarCita(@Param("fechaHora") String fechaHora, @Param("numDoc") String numDoc, @Param("idDisponibilidad") int idDisponibilidad, @Param("nitIps") String nitIps);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Cita SET fechaHora = :fechaHora, numDoc = :numDoc, idDisponibilidad = :idDisponibilidad, nitIps = :nitIps WHERE idCita = :id", nativeQuery = true)
    void actualizarCita(@Param("id") int id, @Param("fechaHora") String fechaHora, @Param("numDoc") String numDoc, @Param("idDisponibilidad") int idDisponibilidad, @Param("nitIps") String nitIps);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Cita WHERE idCita = :id", nativeQuery = true)
    void eliminarCita(@Param("id") int id);
}
