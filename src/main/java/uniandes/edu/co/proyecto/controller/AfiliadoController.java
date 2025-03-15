package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.modelo.Afiliado;
import uniandes.edu.co.proyecto.repositorio.AfiliadoRepository;

import java.util.Collection;

@RestController
@RequestMapping("/afiliados")
public class AfiliadoController {

    @Autowired
    private AfiliadoRepository afiliadoRepository;

    @GetMapping
    public ResponseEntity<Collection<Afiliado>> getAfiliados() {
        try {
            Collection<Afiliado> afiliados = afiliadoRepository.findAllAfiliados();
            return ResponseEntity.ok(afiliados);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/new/save")
    public ResponseEntity<String> saveAfiliado(@RequestBody Afiliado afiliado) {
        try {
            afiliadoRepository.insertarAfiliado(
                afiliado.getTipoDocumento(),
                afiliado.getNumeroDocumento(),
                afiliado.getNombre(),
                afiliado.getFechaNacimiento(),
                afiliado.getDireccion(),
                afiliado.getTelefono(),
                afiliado.getTipoAfiliado(),
                afiliado.getParentesco(),
                afiliado.getCorreo()
            );
            return new ResponseEntity<>("Afiliado creado exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear el afiliado", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{id}/edit/save")
    public ResponseEntity<String> updateAfiliado(@PathVariable("id") String id, @RequestBody Afiliado afiliado) {
        try {
            afiliadoRepository.actualizarAfiliado(
                id,
                afiliado.getTipoDocumento(),
                afiliado.getNombre(),
                afiliado.getFechaNacimiento(),
                afiliado.getDireccion(),
                afiliado.getTelefono(),
                afiliado.getTipoAfiliado(),
                afiliado.getParentesco(),
                afiliado.getCorreo()
            );
            return new ResponseEntity<>("Afiliado actualizado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar el afiliado", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}/delete")
    public ResponseEntity<String> deleteAfiliado(@PathVariable("id") String id) {
        try {
            afiliadoRepository.eliminarAfiliado(id);
            return new ResponseEntity<>("Afiliado eliminado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar el afiliado", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
