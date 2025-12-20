package dev.YanAlmeida.CadastroDeAlunos.Alunos.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlunoController {

    @GetMapping("/hello")
    public String hello(){
        return "Hello World!";
    }

}
