package com.example.yapeback.controller;

import com.example.yapeback.model.PrototipoXCanal;
import com.example.yapeback.service.CampanaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/campanas")
public class CampanaController {

    @Autowired
    private CampanaService campanaService;

    @GetMapping("/detalles/{codPrototipo}")
    public ResponseEntity<Map<String, Object>> obtenerDetallesCampana(@PathVariable Integer codPrototipo) {
        Map<String, Object> detalles = campanaService.obtenerDetallesPrototipo(codPrototipo);
        return ResponseEntity.ok(detalles);
    }
    
    @PutMapping("/NuevaCampana/{codPrototipo}")
    public ResponseEntity<?> crearNuevaCampana(@PathVariable("codPrototipo") Integer codPrototipo) {
        if (codPrototipo == null) {
            return ResponseEntity.badRequest().body("El código del prototipo no puede ser nulo.");
        }
        int filasInsertadas = campanaService.insertarCampanaPublicitaria(codPrototipo);
        if (filasInsertadas > 0) {
            return ResponseEntity.ok("Campaña creada exitosamente.");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se pudo crear la campaña.");
    }
    @GetMapping("/listado")
    public List<Map<String, Object>> obtenerCampanasConImagenes() {
        return campanaService.obtenerCampanasConImagenes();
    }
    @GetMapping("/reporte/{codCampana}")
    public List<PrototipoXCanal> getMetrics(@PathVariable Integer codCampana) {
        return campanaService.getMetricsByCampaign(codCampana);
    }

}
