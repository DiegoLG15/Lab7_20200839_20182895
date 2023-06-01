package com.example.lab7_20200839_20182895.controller;
import com.example.lab7_20200839_20182895.entity.Acciones;
import com.example.lab7_20200839_20182895.repository.AccionesRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/acciones")
public class AccionesController {

    private final AccionesRepository accionesRepository;

    public AccionesController(AccionesRepository accionesRepository) {
        this.accionesRepository = accionesRepository;
    }

    @PostMapping(value = "/save")
    public ResponseEntity<HashMap<String,Object>> guardarAcciones(
            @RequestBody Acciones acciones){
        HashMap<String,Object> responseMap = new HashMap<>();
        accionesRepository.save(acciones);
        responseMap.put("idCreado", acciones.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMap);
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<HashMap<String,String>> gestionExcepcion(HttpServletRequest request) {

        HashMap<String, String> responseMap = new HashMap<>();

        if (request.getMethod().equals("POST")) {
            responseMap.put("estado", "“error");
            responseMap.put("msg", "Debe guardar una accion");

        }
        return ResponseEntity.badRequest().body(responseMap);
    }
}
