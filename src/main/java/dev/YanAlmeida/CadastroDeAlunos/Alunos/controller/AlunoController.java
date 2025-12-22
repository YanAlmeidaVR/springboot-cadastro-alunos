package dev.YanAlmeida.CadastroDeAlunos.Alunos.controller;

import dev.YanAlmeida.CadastroDeAlunos.Alunos.dto.AlunoCreateDTO;
import dev.YanAlmeida.CadastroDeAlunos.Alunos.dto.AlunoResponseDTO;
import dev.YanAlmeida.CadastroDeAlunos.Alunos.service.AlunoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    // Lista todos os alunos cadastrados.

    @GetMapping("/listar")
    public ResponseEntity<List<AlunoResponseDTO>> listarAlunos(){
        return ResponseEntity.ok(alunoService.listarTodos());
    }

    //Busca um aluno pelo ID.

    @GetMapping("/listar/{id}")
    public ResponseEntity<AlunoResponseDTO> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(alunoService.buscarPorId(id));
    }

    //Cria um novo aluno.

    @PostMapping("/criar")
    public ResponseEntity<AlunoResponseDTO> criar(@RequestBody AlunoCreateDTO aluno){
        AlunoResponseDTO alunoCriado = alunoService.save(aluno);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(alunoCriado);
    }

    // Atualiza um aluno existente.

    @PutMapping("atualizar/{id}")
    public ResponseEntity<AlunoResponseDTO> atualizar(@PathVariable Long id, @RequestBody AlunoCreateDTO dto){
        return ResponseEntity.ok(alunoService.atualizar(id, dto));
    }

    // Deleta um aluno por Id

    @DeleteMapping("/deletar/{id}")
    public void deletar(@PathVariable Long id) {
        alunoService.deletar(id);
    }

}
