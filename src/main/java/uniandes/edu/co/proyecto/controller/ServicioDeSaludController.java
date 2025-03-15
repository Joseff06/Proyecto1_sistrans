package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.modelo.ServicioDeSalud;
import uniandes.edu.co.proyecto.repositorio.ServicioDeSaludRepository;

import java.util.Collection;

@RestController
@RequestMapping("/serviciosDeSalud")
public class ServicioDeSaludController {

    @Autowired
    private ServicioDeSaludRepository servicioDeSaludRepository;

    @GetMapping
    public ResponseEntity<Collection<ServicioDeSalud>> getServiciosDeSalud() {
        try {
            Collection<ServicioDeSalud> serviciosDeSalud = servicioDeSaludRepository.findAll();
            return ResponseEntity.ok(serviciosDeSalud);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/new/save")
    public ResponseEntity<String> saveServicioDeSalud(@RequestBody ServicioDeSalud servicioDeSalud) {
        try {
            servicioDeSaludRepository.insertarServicioDeSalud(
                servicioDeSalud.getCodigo(),
                servicioDeSalud.getNombre(),
                servicioDeSalud.getDescripcion()
            );
            return new ResponseEntity<>("Servicio de salud creado exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear el servicio de salud", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{id}/edit/save")
    public ResponseEntity<String> updateServicioDeSalud(@PathVariable("id") String id, @RequestBody ServicioDeSalud servicioDeSalud) {
        try {
            servicioDeSaludRepository.actualizarServicioDeSalud(
                id,
                servicioDeSalud.getNombre(),
                servicioDeSalud.getDescripcion()
            );
            return new ResponseEntity<>("Servicio de salud actualizado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar el servicio de salud", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}/delete")
    public ResponseEntity<String> deleteServicioDeSalud(@PathVariable("id") String id) {
        try {
            servicioDeSaludRepository.eliminarServicioDeSalud(id);
            return new ResponseEntity<>("Servicio de salud eliminado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar el servicio de salud", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
