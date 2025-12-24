package dev.YanAlmeida.CadastroDeAlunos.controller;

import dev.YanAlmeida.CadastroDeAlunos.dto.notas.NotaCreateDTO;
import dev.YanAlmeida.CadastroDeAlunos.dto.notas.NotaResponseDTO;
import dev.YanAlmeida.CadastroDeAlunos.service.NotaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notas")
public class NotaController {

    private final NotaService notaService;

    public NotaController(NotaService notaService) {
        this.notaService = notaService;
    }

    // Lista as notas cadastradas na tabela

    @GetMapping("/listar")
    public ResponseEntity<List<NotaResponseDTO>> listarNotas(){
        return ResponseEntity.ok(notaService.listarTodas());
    }

    // Busca as notas por ID
    @GetMapping("/listar/{id}")
    public ResponseEntity<NotaResponseDTO> listarPorId(@PathVariable Long id){
        return ResponseEntity.ok(notaService.buscarPorId(id));
    }

    // Criar uma instância NotaModel
    @PostMapping("/criar")
    public ResponseEntity<NotaResponseDTO> criar(@RequestBody NotaCreateDTO nota){
        NotaResponseDTO notaCriado = notaService.save(nota);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(notaCriado);
    }

    // Atualiza a instância existente
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<NotaResponseDTO> atualizar(@PathVariable Long id, @RequestBody NotaCreateDTO nota){
        return ResponseEntity.ok(notaService.atualizar(id,nota));
    }

    // Deleta a instância NotaModel por Id
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        notaService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
