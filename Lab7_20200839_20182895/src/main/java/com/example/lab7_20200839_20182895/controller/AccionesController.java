package com.example.lab7_20200839_20182895.controller;
import com.example.lab7_20200839_20182895.entity.Acciones;
import com.example.lab7_20200839_20182895.entity.Usuario;
import com.example.lab7_20200839_20182895.repository.AccionesRepository;
import com.example.lab7_20200839_20182895.repository.PagosRespository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping(value = "/guardar")
    public ResponseEntity<HashMap<String,Object>> guardarAcciones(
            @RequestBody Acciones acciones,
            @RequestParam (value = "fetchId",required = false) boolean fetchId){
        HashMap<String,Object> responseMap = new HashMap<>();
        accionesRepository.save(acciones);
        if (fetchId){
            responseMap.put("id creado", acciones.getId());
        }
        responseMap.put("estado","creado");
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMap);
    }
}
