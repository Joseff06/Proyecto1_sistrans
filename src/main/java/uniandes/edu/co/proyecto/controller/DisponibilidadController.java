package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.modelo.Disponibilidad;
import uniandes.edu.co.proyecto.repositorio.DisponibilidadRepository;

import java.util.Collection;

@RestController
@RequestMapping("/disponibilidades")
public class DisponibilidadController {

    @Autowired
    private DisponibilidadRepository disponibilidadRepository;

    @GetMapping
    public ResponseEntity<Collection<Disponibilidad>> getDisponibilidades() {
        try {
            Collection<Disponibilidad> disponibilidades = disponibilidadRepository.findAll();
            return ResponseEntity.ok(disponibilidades);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/consultar")
    public ResponseEntity<Collection<Disponibilidad>> consultarDisponibilidad(@RequestParam int codigoServicio) {
        try {
            Collection<Disponibilidad> disponibilidades = disponibilidadRepository.consultarDisponibilidad(codigoServicio);
            return ResponseEntity.ok(disponibilidades);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/agendar")
    public ResponseEntity<String> agendarServicio(@RequestParam int idDisponibilidad, @RequestParam int idOrdenServicio) {
        try {
            disponibilidadRepository.agendarServicio(idDisponibilidad, idOrdenServicio);
            return new ResponseEntity<>("Servicio agendado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al agendar el servicio", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/new/save")
    public ResponseEntity<String> saveDisponibilidad(@RequestBody Disponibilidad disponibilidad) {
        try {
            disponibilidadRepository.insertarDisponibilidad(
                disponibilidad.getFechaHora(),
                disponibilidad.isEstadoDisponibilidad(), // Cambié getEstadoDisponibilidad() a isEstadoDisponibilidad() para boolean
                disponibilidad.getMedico().getRegistroMedico(),
                disponibilidad.getIps().getNitIPS(),
                disponibilidad.getServicioDeSalud().getCodigo()
            );
            return new ResponseEntity<>("Disponibilidad creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la disponibilidad", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{id}/edit/save")
    public ResponseEntity<String> updateDisponibilidad(@PathVariable("id") int id, @RequestBody Disponibilidad disponibilidad) {
        try {
            disponibilidadRepository.actualizarDisponibilidad(
                id,
                disponibilidad.getFechaHora(),
                disponibilidad.isEstadoDisponibilidad(), // Cambié getEstadoDisponibilidad() a isEstadoDisponibilidad() para boolean
                disponibilidad.getMedico().getRegistroMedico(),
                disponibilidad.getIps().getNitIPS(),
                disponibilidad.getServicioDeSalud().getCodigo()
            );
            return new ResponseEntity<>("Disponibilidad actualizada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar la disponibilidad", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}/delete")
    public ResponseEntity<String> deleteDisponibilidad(@PathVariable("id") int id) {
        try {
            disponibilidadRepository.eliminarDisponibilidad(id);
            return new ResponseEntity<>("Disponibilidad eliminada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar la disponibilidad", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
