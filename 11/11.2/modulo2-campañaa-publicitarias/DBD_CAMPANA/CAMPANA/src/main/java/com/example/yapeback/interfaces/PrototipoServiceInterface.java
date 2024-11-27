package com.example.yapeback.interfaces;

import java.util.List;
import java.util.Map;

import com.example.yapeback.model.Audiencia;
import com.example.yapeback.model.Prototipo;
import com.example.yapeback.model.PrototipoDTO;


public interface PrototipoServiceInterface {
    Prototipo crearPrototipo(Prototipo prototipo, Audiencia audiencia, String objetivosStr, List<Integer> codCanales);
    void asociarPrototipoConCanales(int codPrototipo, List<Integer> codCanales);
	List<PrototipoDTO> getPrototiposPendientes();
	void actualizarEstadoPrototipo(Integer codPrototipo);
	void cambiarEstado(int codPrototipo, String nuevoEstado);
	List<Map<String, Object>> getApprovedPrototypesWithPhotos();
	int marcarPrototipoComoTerminado(Integer codPrototipo);
	List<Map<String, Object>> getPrototiposByEmpleado(Integer idEmpleado);

	

  
}
