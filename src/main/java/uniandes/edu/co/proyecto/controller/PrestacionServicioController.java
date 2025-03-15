package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.modelo.PrestacionServicio;
import uniandes.edu.co.proyecto.repositorio.PrestacionServicioRepository;

import java.util.Collection;

@RestController
@RequestMapping("/prestacionServicios")
public class PrestacionServicioController {

    @Autowired
    private PrestacionServicioRepository prestacionServicioRepository;

    @GetMapping
    public ResponseEntity<Collection<PrestacionServicio>> getPrestacionServicios() {
        try {
            Collection<PrestacionServicio> prestacionServicios = prestacionServicioRepository.findAllPrestacionServicios();
            return ResponseEntity.ok(prestacionServicios);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/new/save")
    public ResponseEntity<String> savePrestacionServicio(@RequestBody PrestacionServicio prestacionServicio) {
        try {
            prestacionServicioRepository.insertarPrestacionServicio(
                prestacionServicio.getFechaHora(),
                prestacionServicio.getNumDoc(),
                prestacionServicio.getRegistroMedico(),
                prestacionServicio.getNitIps(),
                prestacionServicio.getCodigoServicio()
            );
            return new ResponseEntity<>("Prestación de servicio creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la prestación de servicio", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{id}/edit/save")
    public ResponseEntity<String> updatePrestacionServicio(@PathVariable("id") int id, @RequestBody PrestacionServicio prestacionServicio) {
        try {
            prestacionServicioRepository.actualizarPrestacionServicio(
                id,
                prestacionServicio.getFechaHora(),
                prestacionServicio.getNumDoc(),
                prestacionServicio.getRegistroMedico(),
                prestacionServicio.getNitIps(),
                prestacionServicio.getCodigoServicio()
            );
            return new ResponseEntity<>("Prestación de servicio actualizada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar la prestación de servicio", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}/delete")
    public ResponseEntity<String> deletePrestacionServicio(@PathVariable("id") int id) {
        try {
            prestacionServicioRepository.eliminarPrestacionServicio(id);
            return new ResponseEntity<>("Prestación de servicio eliminada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar la prestación de servicio", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
