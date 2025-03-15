package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.modelo.Eps;
import uniandes.edu.co.proyecto.repositorio.EpsRepository;

import java.util.Collection;

@RestController
@RequestMapping("/eps")
public class EpsController {

    @Autowired
    private EpsRepository epsRepository;

    @GetMapping
    public ResponseEntity<Collection<Eps>> getEps() {
        try {
            Collection<Eps> epsList = epsRepository.findAllEps();
            return ResponseEntity.ok(epsList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/new/save")
    public ResponseEntity<String> saveEps(@RequestBody Eps eps) {
        try {
            epsRepository.insertarEps(
                eps.getNombreEps()
            );
            return new ResponseEntity<>("Eps creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la eps", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{id}/edit/save")
    public ResponseEntity<String> updateEps(@PathVariable("id") int id, @RequestBody Eps eps) {
        try {
            epsRepository.actualizarEps(
                id,
                eps.getNombreEps()
            );
            return new ResponseEntity<>("Eps actualizada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar la eps", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}/delete")
    public ResponseEntity<String> deleteEps(@PathVariable("id") int id) {
        try {
            epsRepository.eliminarEps(id);
            return new ResponseEntity<>("Eps eliminada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar la eps", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
