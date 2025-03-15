package uniandes.edu.co.proyecto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

@SpringBootTest
class ProyectoApplicationTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void contextLoads() {
    }

    @Test
    void testConsultarAgendaDisponibilidad() {
        String sql = "SELECT ss.nombre AS nombre_servicio, d.fechaHora AS fecha_hora_disponibilidad, i.nombreIps AS nombre_ips, m.nombre AS nombre_medico " +
                     "FROM Disponibilidad d " +
                     "JOIN ServicioDeSalud ss ON d.codigoServicio = ss.codigo " +
                     "JOIN Ips i ON d.nitIps = i.nitIps " +
                     "JOIN Medico m ON d.registroMedico = m.registroMedico " +
                     "WHERE d.codigoServicio = ? " +
                     "AND d.fechaHora BETWEEN NOW() AND DATE_ADD(NOW(), INTERVAL 4 WEEK)";
        List<Map<String, Object>> results = jdbcTemplate.queryForList(sql, 1);
        results.forEach(System.out::println);
    }

    @Test
    void testMostrarServiciosMasSolicitados() {
        String sql = "SELECT ss.nombre AS nombre_servicio, COUNT(*) AS cantidad_solicitudes " +
                     "FROM OrdenDeServicio os " +
                     "JOIN ServicioDeSalud ss ON os.codigo = ss.codigo " +
                     "WHERE os.fechaHora BETWEEN ? AND ? " +
                     "GROUP BY ss.nombre " +
                     "ORDER BY cantidad_solicitudes DESC " +
                     "LIMIT 20";
        List<Map<String, Object>> results = jdbcTemplate.queryForList(sql, "2023-01-01", "2023-12-31");
        results.forEach(System.out::println);
    }

    @Test
    void testMostrarIndiceUsoServicios() {
        String sql = "SELECT ss.nombre AS nombre_servicio, (COUNT(d.idDisponibilidad) / COUNT(os.idOrdenServicio)) AS indice_uso " +
                     "FROM ServicioDeSalud ss " +
                     "LEFT JOIN Disponibilidad d ON ss.codigo = d.codigoServicio " +
                     "LEFT JOIN OrdenDeServicio os ON ss.codigo = os.codigo " +
                     "WHERE d.fechaHora BETWEEN ? AND ? " +
                     "AND os.fechaHora BETWEEN ? AND ? " +
                     "GROUP BY ss.nombre";
        List<Map<String, Object>> results = jdbcTemplate.queryForList(sql, "2023-01-01", "2023-12-31", "2023-01-01", "2023-12-31");
        results.forEach(System.out::println);
    }

    @Test
    void testMostrarUtilizacionServiciosPorAfiliado() {
        String sql = "SELECT ss.nombre AS nombre_servicio, os.fechaHora AS fecha_tomado, m.nombre AS nombre_medico, i.nombreIps AS nombre_ips " +
                     "FROM OrdenDeServicio os " +
                     "JOIN ServicioDeSalud ss ON os.codigo = ss.codigo " +
                     "JOIN Medico m ON os.registroMedico = m.registroMedico " +
                     "JOIN Ips i ON os.nitIps = i.nitIps " +
                     "WHERE os.numDoc = ? " +
                     "AND os.fechaHora BETWEEN ? AND ?";
        List<Map<String, Object>> results = jdbcTemplate.queryForList(sql, "123456789", "2023-01-01", "2023-12-31");
        results.forEach(System.out::println);
    }
}
