package com.example.yapeback.controller;

import com.example.yapeback.model.EtiquetaBusqueda;
import com.example.yapeback.service.EtiquetaBusquedaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api-asesor/{idEmpleado}/etiqueta")
public class EtiquetaBusquedaController {

    @Autowired
    private EtiquetaBusquedaService etiquetaBusquedaService;

    @GetMapping("/buscar")
    public List<EtiquetaBusqueda> buscarEtiqueta(@PathVariable Long idEmpleado, @RequestParam String busqueda) {
        List<EtiquetaBusqueda> etiquetas = etiquetaBusquedaService.buscarEtiqueta(busqueda);
        if (etiquetas.isEmpty()) {
            System.out.println("No se encontraron resultados para: " + busqueda);
        }
        return etiquetas;
    }
}
