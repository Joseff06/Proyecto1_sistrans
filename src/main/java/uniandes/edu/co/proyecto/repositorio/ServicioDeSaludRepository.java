package uniandes.edu.co.proyecto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.ServicioDeSalud;

import java.util.Collection;

public interface ServicioDeSaludRepository extends JpaRepository<ServicioDeSalud, String> {

    @Query(value = "SELECT * FROM ServicioDeSalud", nativeQuery = true)
    Collection<ServicioDeSalud> findAllServiciosDeSalud();

    @Query(value = "SELECT * FROM ServicioDeSalud WHERE codigo = :id", nativeQuery = true)
    ServicioDeSalud findServicioDeSaludById(@Param("id") String id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO ServicioDeSalud (codigo, nombre, descripcion) VALUES (:codigo, :nombre, :descripcion)", nativeQuery = true)
    void insertarServicioDeSalud(@Param("codigo") String codigo, @Param("nombre") String nombre, @Param("descripcion") String descripcion);

    @Modifying
    @Transactional
    @Query(value = "UPDATE ServicioDeSalud SET nombre = :nombre, descripcion = :descripcion WHERE codigo = :id", nativeQuery = true)
    void actualizarServicioDeSalud(@Param("id") String id, @Param("nombre") String nombre, @Param("descripcion") String descripcion);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM ServicioDeSalud WHERE codigo = :id", nativeQuery = true)
    void eliminarServicioDeSalud(@Param("id") String id);
}
