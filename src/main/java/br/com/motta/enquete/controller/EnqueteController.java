package br.com.motta.enquete.controller;

import br.com.motta.enquete.config.CommonApiResponses;
import br.com.motta.enquete.dto.*;
import br.com.motta.enquete.service.EnqueteService;
import br.com.motta.enquete.service.VotosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enquetes")
@Tag(name = "Enquete", description = "Controlador para CRUD de enquete, registro de voto e acesso ao resultado")
public class EnqueteController {

    private final EnqueteService enqueteService;
    private final VotosService votosService;

    public EnqueteController(EnqueteService enqueteService, VotosService votosService) {
        this.enqueteService = enqueteService;
        this.votosService = votosService;
    }

    @PostMapping
    @Operation(summary = "Cadastro de enquete")
    @ApiResponse(responseCode = "201", description = "Enquete cadastrada com sucesso")
    @CommonApiResponses
    public ResponseEntity<EnqueteResponseDTO> cadastrar(@Valid @RequestBody EnqueteRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(enqueteService.cadastrar(dto));
    }

    @PostMapping("/{enqueteId}/votos")
    @Operation(summary = "Registro de votos")
    @ApiResponse(responseCode = "201", description = "Voto registrado com sucesso")
    @CommonApiResponses
    public ResponseEntity<VotosResponseDTO> registrarVoto(@PathVariable Long enqueteId, @Valid @RequestBody VotosRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(votosService.registar(enqueteId, dto));
    }

    @GetMapping
    @Operation(summary = "Listar todas as enquetes")
    @ApiResponse(responseCode = "200")
    public ResponseEntity<List<EnqueteResponseDTO>> listar(){
        return ResponseEntity.ok(enqueteService.listar());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar enquete por id")
    @ApiResponse(responseCode = "200")
    @CommonApiResponses
    public ResponseEntity<EnqueteResponseDTO> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(enqueteService.buscarPorId(id));
    }

    @GetMapping("/{enqueteId}/resultado")
    @Operation(summary = "Acessar o resultado de uma enquete pelo id")
    @ApiResponse(responseCode = "200")
    @CommonApiResponses
    public ResponseEntity<EnqueteResultadoResponseDTO> resultado(@PathVariable Long enqueteId){
        return ResponseEntity.ok(enqueteService.resultado(enqueteId));
    }

    @PatchMapping("/{id}/encerrar")
    @Operation(summary = "Encerrar enquete")
    @ApiResponse(responseCode = "200")
    @CommonApiResponses
    public ResponseEntity<EnqueteResponseDTO> encerrar(@PathVariable Long id){
        return ResponseEntity.ok(enqueteService.encerrar(id));
    }

    @PatchMapping("/{id}/cancelar")
    @Operation(summary = "Cancelar")
    @ApiResponse(responseCode = "200")
    @CommonApiResponses
    public ResponseEntity<EnqueteResponseDTO> cancelar(@PathVariable Long id){
        return ResponseEntity.ok(enqueteService.cancelar(id));
    }
}
