package uniandes.edu.co.proyecto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Eps;

import java.util.Collection;

public interface EpsRepository extends JpaRepository<Eps, Integer> {

    @Query(value = "SELECT * FROM Eps", nativeQuery = true)
    Collection<Eps> findAllEps();

    @Query(value = "SELECT * FROM Eps WHERE idEps = :id", nativeQuery = true)
    Eps findEpsById(@Param("id") int id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Eps (idEps, nombreEps) VALUES (nextval('eps_sequence'), :nombreEps)", nativeQuery = true)
    void insertarEps(@Param("nombreEps") String nombreEps);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Eps SET nombreEps = :nombreEps WHERE idEps = :id", nativeQuery = true)
    void actualizarEps(@Param("id") int id, @Param("nombreEps") String nombreEps);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Eps WHERE idEps = :id", nativeQuery = true)
    void eliminarEps(@Param("id") int id);
}
