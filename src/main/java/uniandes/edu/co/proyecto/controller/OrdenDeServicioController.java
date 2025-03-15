package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.modelo.OrdenDeServicio;
import uniandes.edu.co.proyecto.repositorio.OrdenDeServicioRepository;

import java.util.Collection;

@RestController
@RequestMapping("/ordenesDeServicio")
public class OrdenDeServicioController {

    @Autowired
    private OrdenDeServicioRepository ordenDeServicioRepository;

    @GetMapping
    public ResponseEntity<Collection<OrdenDeServicio>> getOrdenesDeServicio() {
        try {
            Collection<OrdenDeServicio> ordenesDeServicio = ordenDeServicioRepository.findAllOrdenesDeServicio();
            return ResponseEntity.ok(ordenesDeServicio);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/new/save")
    public ResponseEntity<String> saveOrdenDeServicio(@RequestBody OrdenDeServicio ordenDeServicio) {
        try {
            ordenDeServicioRepository.insertarOrdenDeServicio(
                ordenDeServicio.getFechaHora(),
                ordenDeServicio.getIdEstadoOrden(),
                ordenDeServicio.getNumDoc(),
                ordenDeServicio.getRegistroMedico(),
                ordenDeServicio.getIdCita(),
                ordenDeServicio.getCodigo()
            );
            return new ResponseEntity<>("Orden de servicio creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la orden de servicio", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{id}/edit/save")
    public ResponseEntity<String> updateOrdenDeServicio(@PathVariable("id") int id, @RequestBody OrdenDeServicio ordenDeServicio) {
        try {
            ordenDeServicioRepository.actualizarOrdenDeServicio(
                id,
                ordenDeServicio.getFechaHora(),
                ordenDeServicio.getIdEstadoOrden(),
                ordenDeServicio.getNumDoc(),
                ordenDeServicio.getRegistroMedico(),
                ordenDeServicio.getIdCita(),
                ordenDeServicio.getCodigo()
            );
            return new ResponseEntity<>("Orden de servicio actualizada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar la orden de servicio", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}/delete")
    public ResponseEntity<String> deleteOrdenDeServicio(@PathVariable("id") int id) {
        try {
            ordenDeServicioRepository.eliminarOrdenDeServicio(id);
            return new ResponseEntity<>("Orden de servicio eliminada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar la orden de servicio", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
