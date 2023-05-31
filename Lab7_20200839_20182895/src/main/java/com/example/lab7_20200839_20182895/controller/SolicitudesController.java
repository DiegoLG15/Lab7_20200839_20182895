package com.example.lab7_20200839_20182895.controller;
import com.example.lab7_20200839_20182895.entity.Solicitudes;
import com.example.lab7_20200839_20182895.repository.SolicitudesRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/solicitudes")
public class SolicitudesController {
    private final SolicitudesRepository solicitudesRepository;

    public SolicitudesController(SolicitudesRepository solicitudesRepository) {
        this.solicitudesRepository = solicitudesRepository;
    }

    @PostMapping(value = "/registro")
    public ResponseEntity<HashMap<String,Object>> guardarSolicitud(
            @RequestBody Solicitudes solicitudes,
            @RequestParam (value = "fetchId",required = false) boolean fetchId){
        HashMap<String,Object> responseMap = new HashMap<>();
        solicitudes.setSolicitudEstado("pendiente");
        solicitudesRepository.save(solicitudes);
        if (fetchId){
            responseMap.put("id creado", solicitudes.getId());
        }
        responseMap.put("Monto solicitado",solicitudes.getSolicitudMonto());
        responseMap.put("id",solicitudes.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMap);
    }
}
