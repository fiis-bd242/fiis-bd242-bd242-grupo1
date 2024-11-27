package com.example.yapeback.service;


import com.example.yapeback.interfaces.RecursosRepository;
import com.example.yapeback.interfaces.RecursosServiceInterface;
import com.example.yapeback.model.Recursos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class RecursosService implements RecursosServiceInterface {



    @Autowired
    private RecursosRepository recursosRepository;

    @Override
    public void guardarRecurso(Recursos recurso) {
        recursosRepository.save(recurso);
    }
    
    private final JdbcTemplate jdbcTemplate;

    public RecursosService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    public Recursos encontrarPorPrototipoYTipo(Integer cod_prototipo, Integer id_tipo_recurso) {
        return recursosRepository.findByCodPrototipoAndIdTipoRecurso(cod_prototipo, id_tipo_recurso);
    }
    @Override
    public void actualizarUrl_recurso(String url_recurso, Integer cod_prototipo, Integer id_tipo_recurso) {
        recursosRepository.actualizarUrlRecurso(url_recurso, cod_prototipo, id_tipo_recurso);
    }

    @Override
    public String guardarArchivo(MultipartFile file) {
        try {
            // Generar nombre único
            String nombreArchivo = System.currentTimeMillis() + "_" + file.getOriginalFilename();

            // Crear ruta base de forma centralizada
            Path uploadPath = Paths.get(System.getProperty("user.dir"), "uploads");
            Path rutaArchivo = uploadPath.resolve(nombreArchivo);

            // Crear directorios si no existen
            Files.createDirectories(uploadPath);

            // Guardar archivo
            file.transferTo(rutaArchivo.toFile());

            // Retornar URL pública relativa
            return "/uploads/" + nombreArchivo;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Recursos> obtenerRecursosPorPrototipo(Integer codPrototipo) {
        String sql = """
                SELECT 
                    r.url_recurso, 
                    r.id_tipo_recurso, 
                    r.cod_prototipo
                FROM 
                    Prototipo p
                JOIN 
                    recursos r ON p.cod_prototipo = r.cod_prototipo
                JOIN 
                    Tipo_recurso tr ON r.id_tipo_recurso = tr.id_tipo_recurso
                WHERE 
                    p.cod_prototipo = ?
                ORDER BY 
                    r.nombre_recurso
                """;

        RowMapper<Recursos> rowMapper = (rs, rowNum) -> {
            Recursos recurso = new Recursos();
            recurso.setUrl_recurso(rs.getString("url_recurso"));
            recurso.setId_tipo_recurso(rs.getInt("id_tipo_recurso"));
            recurso.setCod_prototipo(rs.getInt("cod_prototipo"));
            return recurso;
        };
        return jdbcTemplate.query(sql, rowMapper, codPrototipo);
    }

    
}

