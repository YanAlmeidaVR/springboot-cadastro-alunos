package dev.YanAlmeida.CadastroDeAlunos.controller;

import dev.YanAlmeida.CadastroDeAlunos.dto.alunos.AlunoCreateDTO;
import dev.YanAlmeida.CadastroDeAlunos.dto.alunos.AlunoResponseDTO;
import dev.YanAlmeida.CadastroDeAlunos.service.AlunoService;
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
@RequestMapping("/alunos")
@Tag(name = "Alunos", description = "Endpoints para gerenciamento de alunos")
public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @Operation(
            summary = "Lista todos os alunos",
            description = "Retorna uma lista com todos os alunos cadastrados no sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista de alunos retornada com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AlunoResponseDTO.class)
                    )
            )
    })
    @GetMapping("/listar")
    public ResponseEntity<List<AlunoResponseDTO>> listarAlunos(){
        return ResponseEntity.ok(alunoService.listarTodos());
    }

    @Operation(
            summary = "Busca um aluno por ID",
            description = "Retorna os dados de um aluno específico baseado no seu ID"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Aluno encontrado com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AlunoResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Aluno não encontrado",
                    content = @Content
            )
    })
    @GetMapping("/listar/{id}")
    public ResponseEntity<AlunoResponseDTO> listarPorId(
            @Parameter(description = "ID do aluno a ser buscado", required = true)
            @PathVariable Long id
    ){
        return ResponseEntity.ok(alunoService.buscarPorId(id));
    }

    @Operation(
            summary = "Cria um novo aluno",
            description = "Cadastra um novo aluno no sistema com os dados fornecidos"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Aluno criado com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AlunoResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados inválidos fornecidos",
                    content = @Content
            )
    })
    @PostMapping("/criar")
    public ResponseEntity<AlunoResponseDTO> criar(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dados do aluno a ser criado",
                    required = true,
                    content = @Content(schema = @Schema(implementation = AlunoCreateDTO.class))
            )
            @RequestBody AlunoCreateDTO aluno
    ){
        AlunoResponseDTO alunoCriado = alunoService.save(aluno);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(alunoCriado);
    }

    @Operation(
            summary = "Atualiza um aluno existente",
            description = "Atualiza os dados de um aluno já cadastrado no sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Aluno atualizado com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AlunoResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Aluno não encontrado",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados inválidos fornecidos",
                    content = @Content
            )
    })
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<AlunoResponseDTO> atualizar(
            @Parameter(description = "ID do aluno a ser atualizado", required = true)
            @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Novos dados do aluno",
                    required = true,
                    content = @Content(schema = @Schema(implementation = AlunoCreateDTO.class))
            )
            @RequestBody AlunoCreateDTO dto
    ){
        return ResponseEntity.ok(alunoService.atualizar(id, dto));
    }

    @Operation(
            summary = "Deleta um aluno",
            description = "Remove um aluno do sistema baseado no seu ID"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Aluno deletado com sucesso",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Aluno não encontrado",
                    content = @Content
            )
    })
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(
            @Parameter(description = "ID do aluno a ser deletado", required = true)
            @PathVariable Long id
    ) {
        alunoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}