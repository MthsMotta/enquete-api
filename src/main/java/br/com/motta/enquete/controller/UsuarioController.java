package br.com.motta.enquete.controller;

import br.com.motta.enquete.dto.UsuarioRequestDTO;
import br.com.motta.enquete.dto.UsuarioResponseDTO;
import br.com.motta.enquete.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuario", description = "Controlador para cadastrar usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    @Operation(summary = "Cadastro de usuario")
    @ApiResponse(responseCode = "201", description = "Usuario cadastrado com sucesso")
    @ApiResponse(responseCode = "400", description = "Email duplicado")
    public ResponseEntity<UsuarioResponseDTO> cadastrar(@Valid @RequestBody UsuarioRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.cadastrar(dto));
    }
}
