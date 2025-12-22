package dev.YanAlmeida.CadastroDeAlunos.Notas.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notas")
public class NotaController {
    @GetMapping("/hello")
    public String hello(){
        return "Hello World!";
    }

    @GetMapping("/listar")
    public String listar(){
        return null;
    }

    @GetMapping("/listar/{id}")
    public String listarPorId(){
        return null;
    }

    @PostMapping("/criar")
    public String criarNota(){
        return null;
    }

    @PutMapping("/alterar/{id}")
    public String alterarNotaPorId(){
        return null;
    }

    @DeleteMapping("/deletar/{id}")
    public String deletarPorId(){
        return null;
    }
}
