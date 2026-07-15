package br.com.motta.enquete.controller;

import br.com.motta.enquete.dto.*;
import br.com.motta.enquete.service.EnqueteService;
import br.com.motta.enquete.service.VotosService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enquetes")
public class EnqueteController {

    private final EnqueteService enqueteService;
    private final VotosService votosService;

    public EnqueteController(EnqueteService enqueteService, VotosService votosService) {
        this.enqueteService = enqueteService;
        this.votosService = votosService;
    }

    @PostMapping
    public ResponseEntity<EnqueteResponseDTO> cadastrar(@Valid @RequestBody EnqueteRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(enqueteService.cadastrar(dto));
    }

    @PostMapping("/{enqueteId}/votos")
    public ResponseEntity<VotosResponseDTO> registrarVoto(@PathVariable Long enqueteId, @Valid @RequestBody VotosRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(votosService.registar(enqueteId, dto));
    }

    @GetMapping
    public ResponseEntity<List<EnqueteResponseDTO>> listar(){
        return ResponseEntity.ok(enqueteService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnqueteResponseDTO> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(enqueteService.buscarPorId(id));
    }
    @GetMapping("/{enqueteId}/resultado")
    public ResponseEntity<EnqueteResultadoResponseDTO> resultado(@PathVariable Long enqueteId){
        return ResponseEntity.ok(enqueteService.resultado(enqueteId));
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
