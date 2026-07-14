package br.com.motta.enquete.controller;

import br.com.motta.enquete.dto.EnqueteRequestDTO;
import br.com.motta.enquete.dto.EnqueteResponseDTO;
import br.com.motta.enquete.service.EnqueteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enquetes")
public class EnqueteController {

    private final EnqueteService enqueteService;

    public EnqueteController(EnqueteService enqueteService) {
        this.enqueteService = enqueteService;
    }

    @PostMapping
    public ResponseEntity<EnqueteResponseDTO> cadastrar(@Valid @RequestBody EnqueteRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(enqueteService.cadastrar(dto));
    }

    @GetMapping
    public ResponseEntity<List<EnqueteResponseDTO>> listar(){
        return ResponseEntity.ok(enqueteService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnqueteResponseDTO> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(enqueteService.buscarPorId(id));
    }

    @PatchMapping("/{id}/encerrar")
    public ResponseEntity<EnqueteResponseDTO> encerrar(@PathVariable Long id){
        return ResponseEntity.ok(enqueteService.encerrar(id));
    }

    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<EnqueteResponseDTO> cancelar(@PathVariable Long id){
        return ResponseEntity.ok(enqueteService.cancelar(id));
    }
}
