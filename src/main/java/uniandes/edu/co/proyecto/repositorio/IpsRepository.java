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

    @Query(value = "SELECT * FROM Ips WHERE nitIps = :id", nativeQuery = true)
    Ips findIpsById(@Param("id") String id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Ips (nitIps, nombreIps, direccion, telefono, idEps) VALUES (:nitIps, :nombreIps, :direccion, :telefono, :idEps)", nativeQuery = true)
    void insertarIps(@Param("nitIPS") String nitIps, @Param("nombreIPS") String nombreIps, @Param("direccion") String direccion, @Param("telefono") String telefono, @Param("idEps") int idEps);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Ips SET nombreIps = :nombreIps, direccion = :direccion, telefono = :telefono, idEps = :idEps WHERE nitIps = :id", nativeQuery = true)
    void actualizarIps(@Param("id") String id, @Param("nombreIPS") String nombreIps, @Param("direccion") String direccion, @Param("telefono") String telefono, @Param("idEps") int idEps);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Ips WHERE nitIps = :id", nativeQuery = true)
    void eliminarIps(@Param("id") String id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Ips_ServicioSalud (nitIps, codigo) VALUES (:nitIps, :codigo)", nativeQuery = true)
    void asignarServicio(@Param("nitIPS") String nitIps, @Param("codigo") String codigo);
}
