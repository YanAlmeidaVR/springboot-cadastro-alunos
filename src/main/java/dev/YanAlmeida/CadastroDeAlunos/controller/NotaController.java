package dev.YanAlmeida.CadastroDeAlunos.controller;

import dev.YanAlmeida.CadastroDeAlunos.dto.notas.NotaCreateDTO;
import dev.YanAlmeida.CadastroDeAlunos.dto.notas.NotaResponseDTO;
import dev.YanAlmeida.CadastroDeAlunos.service.NotaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notas")
@Tag(name = "Notas", description = "Endpoints para gerenciamento de notas dos alunos")
public class NotaController {

    private final NotaService notaService;

    public NotaController(NotaService notaService) {
        this.notaService = notaService;
    }

    @Operation(
            summary = "Lista todas as notas",
            description = "Retorna uma lista com todas as notas cadastradas no sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista de notas retornada com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = NotaResponseDTO.class)
                    )
            )
    })
    @GetMapping("/listar")
    public ResponseEntity<List<NotaResponseDTO>> listarNotas(){
        return ResponseEntity.ok(notaService.listarTodas());
    }

    @Operation(
            summary = "Busca uma nota por ID",
            description = "Retorna os dados de uma nota específica baseado no seu ID"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Nota encontrada com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = NotaResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Nota não encontrada",
                    content = @Content
            )
    })
    @GetMapping("/listar/{id}")
    public ResponseEntity<NotaResponseDTO> listarPorId(
            @Parameter(description = "ID da nota a ser buscada", required = true)
            @PathVariable Long id
    ){
        return ResponseEntity.ok(notaService.buscarPorId(id));
    }

    @Operation(
            summary = "Cria uma nova nota",
            description = "Cadastra uma nova nota no sistema associada a um aluno"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Nota criada com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = NotaResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados inválidos fornecidos",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Aluno não encontrado",
                    content = @Content
            )
    })
    @PostMapping("/criar")
    public ResponseEntity<NotaResponseDTO> criar(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dados da nota a ser criada",
                    required = true,
                    content = @Content(schema = @Schema(implementation = NotaCreateDTO.class))
            )
            @RequestBody NotaCreateDTO nota
    ){
        NotaResponseDTO notaCriado = notaService.save(nota);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(notaCriado);
    }

    @Operation(
            summary = "Atualiza uma nota existente",
            description = "Atualiza os dados de uma nota já cadastrada no sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Nota atualizada com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = NotaResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Nota não encontrada",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados inválidos fornecidos",
                    content = @Content
            )
    })
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<NotaResponseDTO> atualizar(
            @Parameter(description = "ID da nota a ser atualizada", required = true)
            @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Novos dados da nota",
                    required = true,
                    content = @Content(schema = @Schema(implementation = NotaCreateDTO.class))
            )
            @RequestBody NotaCreateDTO nota
    ){
        return ResponseEntity.ok(notaService.atualizar(id, nota));
    }

    @Operation(
            summary = "Deleta uma nota",
            description = "Remove uma nota do sistema baseado no seu ID"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Nota deletada com sucesso",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Nota não encontrada",
                    content = @Content
            )
    })
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(
            @Parameter(description = "ID da nota a ser deletada", required = true)
            @PathVariable Long id
    ) {
        notaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}