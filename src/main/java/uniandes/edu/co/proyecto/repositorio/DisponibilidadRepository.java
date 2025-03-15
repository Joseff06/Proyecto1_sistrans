package uniandes.edu.co.proyecto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Disponibilidad;

import java.util.Collection;

public interface DisponibilidadRepository extends JpaRepository<Disponibilidad, Integer> {

    @Query(value = "SELECT * FROM Disponibilidad", nativeQuery = true)
    Collection<Disponibilidad> findAllDisponibilidads();

    @Query(value = "SELECT * FROM Disponibilidad WHERE codigoServicio = :codigoServicio AND fechaHora BETWEEN NOW() AND DATE_ADD(NOW(), INTERVAL 4 WEEK)", nativeQuery = true)
    Collection<Disponibilidad> consultarDisponibilidad(@Param("codigoServicio") int codigoServicio);

    @Query(value = "SELECT * FROM Disponibilidad WHERE idDisponibilidad = :id", nativeQuery = true)
    Disponibilidad findById(@Param("id") int id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Disponibilidad SET idOrdenServicio = :idOrdenServicio WHERE idDisponibilidad = :idDisponibilidad", nativeQuery = true)
    void agendarServicio(@Param("idDisponibilidad") int idDisponibilidad, @Param("idOrdenServicio") int idOrdenServicio);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Disponibilidad (fechaHora, estadoDisponibilidad, registroMedico, nitIPS, codigoServicio) VALUES (:fechaHora, :estadoDisponibilidad, :registroMedico, :nitIPS, :codigoServicio)", nativeQuery = true)
    void insertarDisponibilidad(@Param("fechaHora") String fechaHora, @Param("estadoDisponibilidad") boolean estadoDisponibilidad, @Param("registroMedico") int registroMedico, @Param("nitIPS") String nitIPS, @Param("codigoServicio") String codigoServicio);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Disponibilidad SET fechaHora = :fechaHora, estadoDisponibilidad = :estadoDisponibilidad, registroMedico = :registroMedico, nitIPS = :nitIPS, codigoServicio = :codigoServicio WHERE idDisponibilidad = :id", nativeQuery = true)
    void actualizarDisponibilidad(@Param("id") int id, @Param("fechaHora") String fechaHora, @Param("estadoDisponibilidad") boolean estadoDisponibilidad, @Param("registroMedico") int registroMedico, @Param("nitIPS") String nitIPS, @Param("codigoServicio") String codigoServicio);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Disponibilidad WHERE idDisponibilidad = :id", nativeQuery = true)
    void eliminarDisponibilidad(@Param("id") int id);
}
