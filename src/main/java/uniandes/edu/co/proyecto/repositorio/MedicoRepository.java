package uniandes.edu.co.proyecto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Medico;

import java.util.Collection;

public interface MedicoRepository extends JpaRepository<Medico, Integer> {

    @Query(value = "SELECT * FROM Medico", nativeQuery = true)
    Collection<Medico> findAllMedicos();

    @Query(value = "SELECT * FROM Medico WHERE registroMedico = :id", nativeQuery = true)
    Medico findMedicoById(@Param("id") int id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Medico (registroMedico, nombre, tipoDocumento, numeroDocumento, idEspecialidad) VALUES (nextval('medico_sequence'), :nombre, :tipoDocumento, :numeroDocumento, :idEspecialidad)", nativeQuery = true)
    void insertarMedico(@Param("nombre") String nombre, @Param("tipoDocumento") String tipoDocumento, @Param("numeroDocumento") String numeroDocumento, @Param("idEspecialidad") int idEspecialidad);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Medico SET nombre = :nombre, tipoDocumento = :tipoDocumento, numeroDocumento = :numeroDocumento, idEspecialidad = :idEspecialidad WHERE registroMedico = :id", nativeQuery = true)
    void actualizarMedico(@Param("id") int id, @Param("nombre") String nombre, @Param("tipoDocumento") String tipoDocumento, @Param("numeroDocumento") String numeroDocumento, @Param("idEspecialidad") int idEspecialidad);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Medico WHERE registroMedico = :id", nativeQuery = true)
    void eliminarMedico(@Param("id") int id);
}
