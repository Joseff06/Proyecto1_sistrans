package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.modelo.Cita;
import uniandes.edu.co.proyecto.repositorio.CitaRepository;

import java.util.Collection;

@RestController
@RequestMapping("/citas")
public class CitaController {

    @Autowired
    private CitaRepository citaRepository;

    @GetMapping
    public ResponseEntity<Collection<Cita>> getCitas() {
        try {
            Collection<Cita> citas = citaRepository.findAllCitas();
            return ResponseEntity.ok(citas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/new/save")
    public ResponseEntity<String> saveCita(@RequestBody Cita cita) {
        try {
            citaRepository.insertarCita(
                cita.getFechaHora(),
                cita.getNumDoc(),
                cita.getIdDisponibilidad(),
                cita.getNitIps()
            );
            return new ResponseEntity<>("Cita creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la cita", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{id}/edit/save")
    public ResponseEntity<String> updateCita(@PathVariable("id") int id, @RequestBody Cita cita) {
        try {
            citaRepository.actualizarCita(
                id,
                cita.getFechaHora(),
                cita.getNumDoc(),
                cita.getIdDisponibilidad(),
                cita.getNitIps()
            );
            return new ResponseEntity<>("Cita actualizada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar la cita", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}/delete")
    public ResponseEntity<String> deleteCita(@PathVariable("id") int id) {
        try {
            citaRepository.eliminarCita(id);
            return new ResponseEntity<>("Cita eliminada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar la cita", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
