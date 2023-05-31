package com.example.lab7_20200839_20182895.controller;
import com.example.lab7_20200839_20182895.entity.Solicitudes;
import com.example.lab7_20200839_20182895.repository.SolicitudesRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Optional;

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

    @PutMapping(value = "/aprobarSolicitud", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<HashMap<String, Object>> actualizarParcialX(Solicitudes solicitudes) {//para raw por json
        if (solicitudes.getId() != null && solicitudes.getId() > 0) {
            Optional<Solicitudes> optional = solicitudesRepository.findById(solicitudes.getId());
            if (optional.isPresent()) {
                Solicitudes solicitudOriginal = optional.get();

                if(solicitudes.getSolicitudProducto()!=null){
                    solicitudOriginal.setSolicitudProducto(solicitudes.getSolicitudProducto());
                }

                if(solicitudes.getSolicitudMonto()!=null){
                    solicitudOriginal.setSolicitudMonto(solicitudes.getSolicitudMonto());
                }

                if(solicitudes.getSolicitudFecha()!=null){
                    solicitudOriginal.setSolicitudFecha(solicitudes.getSolicitudFecha());
                }

                if(solicitudes.getUsuarios()!=null){
                    solicitudOriginal.setUsuarios(solicitudes.getUsuarios());
                }
                if(solicitudes.getSolicitudEstado()!=null){
                    solicitudOriginal.setSolicitudEstado(solicitudes.getSolicitudEstado());
                }

                solicitudOriginal.setSolicitudEstado("aprobado");
                solicitudesRepository.save(solicitudOriginal);
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("result", "actualizado");
                return ResponseEntity.ok().body(hashMap);
            } else {
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("result", "error");
                hashMap.put("msg", "no existe el producto");
                return ResponseEntity.badRequest().body(hashMap);
            }
        } else {
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("result", "error");
            hashMap.put("msg", "el id debe existir");
            return ResponseEntity.badRequest().body(hashMap);
        }
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<HashMap<String,String>> gestionExcepcion(HttpServletRequest request) {

        HashMap<String, String> responseMap = new HashMap<>();

        if (request.getMethod().equals("POST") || request.getMethod().equals("PUT")) {
            responseMap.put("estado", "“error");
            responseMap.put("msg", "Debe enviar una solicitud");

        }
        return ResponseEntity.badRequest().body(responseMap);
    }
}
