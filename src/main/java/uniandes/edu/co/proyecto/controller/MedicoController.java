package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.modelo.Medico;
import uniandes.edu.co.proyecto.repositorio.MedicoRepository;

import java.util.Collection;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @GetMapping
    public ResponseEntity<Collection<Medico>> getMedicos() {
        try {
            Collection<Medico> medicos = medicoRepository.findAllMedicos();
            return ResponseEntity.ok(medicos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/new/save")
    public ResponseEntity<String> saveMedico(@RequestBody Medico medico) {
        try {
            medicoRepository.insertarMedico(
                medico.getRegistroMedico(),
                medico.getTipoDocumento(),
                medico.getNombre(),
                medico.getNumeroDocumento(),
                medico.getIdEspecialidad()
            );
            return new ResponseEntity<>("Medico creado exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear el medico", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{id}/edit/save")
    public ResponseEntity<String> updateMedico(@PathVariable("id") int id, @RequestBody Medico medico) {
        try {
            medicoRepository.actualizarMedico(
                id,
                medico.getTipoDocumento(),
                medico.getNombre(),
                medico.getNumeroDocumento(),
                medico.getIdEspecialidad()
            );
            return new ResponseEntity<>("Medico actualizado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar el medico", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}/delete")
    public ResponseEntity<String> deleteMedico(@PathVariable("id") int id) {
        try {
            medicoRepository.eliminarMedico(id);
            return new ResponseEntity<>("Medico eliminado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar el medico", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
