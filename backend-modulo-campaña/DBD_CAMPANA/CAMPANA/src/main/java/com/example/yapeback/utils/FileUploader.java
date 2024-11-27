package com.example.yapeback.utils;

import java.io.File;

public class FileUploader {
    public static void createUploadsDirectory() {
        File uploadsDir = new File("C:/CUEVA%20GAVILAN%20BENJAMIN%20JOSUE/POO_ES_CUEVA/DBD_CAMPANA/CAMPANA/uploads/"); // Cambia esta ruta si es necesario
        if (!uploadsDir.exists()) {
            uploadsDir.mkdirs(); // Crear el directorio si no existe
            System.out.println("Directorio 'uploads' creado en: " + uploadsDir.getAbsolutePath());
        } else {
            System.out.println("Directorio 'uploads' ya existe.");
        }
    }
}
