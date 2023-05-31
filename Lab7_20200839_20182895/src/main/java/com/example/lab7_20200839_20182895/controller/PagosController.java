package com.example.lab7_20200839_20182895.controller;
import com.example.lab7_20200839_20182895.entity.Pagos;
import com.example.lab7_20200839_20182895.repository.PagosRespository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/pagos")
public class PagosController {
    private final PagosRespository pagosRespository;

    public PagosController(PagosRespository pagosRespository) {
        this.pagosRespository = pagosRespository;
    }

    @GetMapping("/listarPagos")
    public List<Pagos> listarPagos() {
        return pagosRespository.findAll();
    }

    @PostMapping(value = "/registrarPago")
    public ResponseEntity<HashMap<String,Object>> registrarPago(
            @RequestBody Pagos pagos){
        HashMap<String,Object> responseMap = new HashMap<>();
        pagosRespository.save(pagos);
        responseMap.put("id creado", pagos.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMap);
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<HashMap<String,String>> gestionExcepcion(HttpServletRequest request) {

        HashMap<String, String> responseMap = new HashMap<>();

        if (request.getMethod().equals("POST")) {
            responseMap.put("estado", "“error");
            responseMap.put("msg", "Debe enviar un pago");

        }
        return ResponseEntity.badRequest().body(responseMap);
    }
}
