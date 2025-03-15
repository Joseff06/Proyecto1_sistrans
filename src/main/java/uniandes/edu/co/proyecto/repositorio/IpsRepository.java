package uniandes.edu.co.proyecto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Ips;

import java.util.Collection;

public interface IpsRepository extends JpaRepository<Ips, String> {

    @Query(value = "SELECT * FROM Ips", nativeQuery = true)
    Collection<Ips> findAllIps();

    @Query(value = "SELECT * FROM Ips WHERE nitIPS = :id", nativeQuery = true)
    Ips findIpsById(@Param("id") String id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Ips (nitIPS, nombreIPS, direccion, telefono, idEps) VALUES (:nitIPS, :nombreIPS, :direccion, :telefono, :idEps)", nativeQuery = true)
    void insertarIps(@Param("nitIPS") String nitIPS, @Param("nombreIPS") String nombreIPS, @Param("direccion") String direccion, @Param("telefono") String telefono, @Param("idEps") int idEps);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Ips SET nombreIPS = :nombreIPS, direccion = :direccion, telefono = :telefono, idEps = :idEps WHERE nitIPS = :id", nativeQuery = true)
    void actualizarIps(@Param("id") String id, @Param("nombreIPS") String nombreIPS, @Param("direccion") String direccion, @Param("telefono") String telefono, @Param("idEps") int idEps);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Ips WHERE nitIPS = :id", nativeQuery = true)
    void eliminarIps(@Param("id") String id);
}
