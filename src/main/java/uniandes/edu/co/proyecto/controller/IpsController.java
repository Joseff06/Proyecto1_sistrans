package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.modelo.Ips;
import uniandes.edu.co.proyecto.repositorio.IpsRepository;

import java.util.Collection;

@RestController
@RequestMapping("/ips")
public class IpsController {

    @Autowired
    private IpsRepository ipsRepository;

    @GetMapping
    public ResponseEntity<Collection<Ips>> getIps() {
        try {
            Collection<Ips> ipsList = ipsRepository.findAllIps();
            return ResponseEntity.ok(ipsList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/new/save")
    public ResponseEntity<String> saveIps(@RequestBody Ips ips) {
        try {
            ipsRepository.insertarIps(
                ips.getNitIPS(),
                ips.getNombreIPS(),
                ips.getDireccion(),
                ips.getTelefono(),
                ips.getIdEps()
            );
            return new ResponseEntity<>("Ips creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la ips", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{id}/edit/save")
    public ResponseEntity<String> updateIps(@PathVariable("id") String id, @RequestBody Ips ips) {
        try {
            ipsRepository.actualizarIps(
                id,
                ips.getNombreIPS(),
                ips.getDireccion(),
                ips.getTelefono(),
                ips.getIdEps()
            );
            return new ResponseEntity<>("Ips actualizada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar la ips", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}/delete")
    public ResponseEntity<String> deleteIps(@PathVariable("id") String id) {
        try {
            ipsRepository.eliminarIps(id);
            return new ResponseEntity<>("Ips eliminada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar la ips", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
