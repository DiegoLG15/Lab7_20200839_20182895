package com.example.lab7_20200839_20182895.controller;


import com.example.lab7_20200839_20182895.entity.Usuario;
import com.example.lab7_20200839_20182895.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/listar")
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }
    @PostMapping(value = "/crear")
    public ResponseEntity<HashMap<String,Object>> crearUsuario(
            @RequestBody Usuario usuario,
            @RequestParam (value = "fetchId",required = false) boolean fetchId){
        HashMap<String,Object> responseMap = new HashMap<>();
        usuarioRepository.save(usuario);
        if (fetchId){
            responseMap.put("id creado", usuario.getId());
        }
        responseMap.put("estado","creado");
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMap);
    }
}
