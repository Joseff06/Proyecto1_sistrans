package uniandes.edu.co.proyecto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Afiliado;

import java.util.Collection;

public interface AfiliadoRepository extends JpaRepository<Afiliado, Integer> {

    @Query(value = "SELECT * FROM Afiliado", nativeQuery = true)
    Collection<Afiliado> findAllAfiliados();

    @Query(value = "SELECT * FROM Afiliado WHERE numDoc = :id", nativeQuery = true)
    Afiliado findAfiliadoById(@Param("id") int id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Afiliado (tipoDoc, numDoc, nombre, fechaNacimiento, direccion, telefono, tipoAfiliado, parentesco, correo, contribuyente) VALUES (:tipoDoc, :numDoc, :nombre, :fechaNacimiento, :direccion, :telefono, :tipoAfiliado, :parentesco, :correo, :contribuyente)", nativeQuery = true)
    void insertarAfiliado(@Param("tipoDoc") String tipoDoc, @Param("numDoc") int numDoc, @Param("nombre") String nombre, @Param("fechaNacimiento") String fechaNacimiento, @Param("direccion") String direccion, @Param("telefono") int telefono, @Param("tipoAfiliado") String tipoAfiliado, @Param("parentesco") String parentesco, @Param("correo") String correo, @Param("contribuyente") int contribuyente);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Afiliado SET tipoDoc = :tipoDoc, numDoc = :numDoc, nombre = :nombre, fechaNacimiento = :fechaNacimiento, direccion = :direccion, telefono = :telefono, tipoAfiliado = :tipoAfiliado, parentesco = :parentesco, correo = :correo, contribuyente = :contribuyente WHERE numDoc = :id", nativeQuery = true)
    void actualizarAfiliado(@Param("id") int id, @Param("tipoDoc") String tipoDoc, @Param("numDoc") int numDoc, @Param("nombre") String nombre, @Param("fechaNacimiento") String fechaNacimiento, @Param("direccion") String direccion, @Param("telefono") int telefono, @Param("tipoAfiliado") String tipoAfiliado, @Param("parentesco") String parentesco, @Param("correo") String correo, @Param("contribuyente") int contribuyente);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Afiliado WHERE numDoc = :id", nativeQuery = true)
    void eliminarAfiliado(@Param("id") int id);
}
