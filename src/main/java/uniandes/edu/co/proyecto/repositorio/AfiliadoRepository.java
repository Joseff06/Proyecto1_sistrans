package uniandes.edu.co.proyecto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Afiliado;

import java.util.Collection;

public interface AfiliadoRepository extends JpaRepository<Afiliado, String> {

    @Query(value = "SELECT * FROM Afiliado", nativeQuery = true)
    Collection<Afiliado> findAllAfiliados();

    @Query(value = "SELECT * FROM Afiliado WHERE numeroDocumento = :id", nativeQuery = true)
    Afiliado findAfiliadoById(@Param("id") String id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Afiliado (tipoDocumento, numeroDocumento, nombre, fechaNacimiento, direccion, telefono, tipoAfiliado, parentesco, correo) VALUES (:tipoDocumento, :numeroDocumento, :nombre, :fechaNacimiento, :direccion, :telefono, :tipoAfiliado, :parentesco, :correo)", nativeQuery = true)
    void insertarAfiliado(@Param("tipoDocumento") String tipoDocumento, @Param("numeroDocumento") String numeroDocumento, @Param("nombre") String nombre, @Param("fechaNacimiento") String fechaNacimiento, @Param("direccion") String direccion, @Param("telefono") String telefono, @Param("tipoAfiliado") String tipoAfiliado, @Param("parentesco") String parentesco, @Param("correo") String correo);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Afiliado SET tipoDocumento = :tipoDocumento, nombre = :nombre, fechaNacimiento = :fechaNacimiento, direccion = :direccion, telefono = :telefono, tipoAfiliado = :tipoAfiliado, parentesco = :parentesco, correo = :correo WHERE numeroDocumento = :id", nativeQuery = true)
    void actualizarAfiliado(@Param("id") String id, @Param("tipoDocumento") String tipoDocumento, @Param("nombre") String nombre, @Param("fechaNacimiento") String fechaNacimiento, @Param("direccion") String direccion, @Param("telefono") String telefono, @Param("tipoAfiliado") String tipoAfiliado, @Param("parentesco") String parentesco, @Param("correo") String correo);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Afiliado WHERE numeroDocumento = :id", nativeQuery = true)
    void eliminarAfiliado(@Param("id") String id);
}
