package dev.YanAlmeida.CadastroDeAlunos.infra;

import dev.YanAlmeida.CadastroDeAlunos.exceptions.aluno.AlunoNotFoundException;
import dev.YanAlmeida.CadastroDeAlunos.exceptions.aluno.CpfErrorException;
import dev.YanAlmeida.CadastroDeAlunos.exceptions.nota.NotaNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.handler.ResponseStatusExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AlunoNotFoundException.class)
    public ResponseEntity<String> handleAlunoNotFound(AlunoNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Aluno não encontrado");
    }

    @ExceptionHandler(CpfErrorException.class)
    public ResponseEntity<String> handleCpfError(CpfErrorException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

    @ExceptionHandler(NotaNotFoundException.class)
    public ResponseEntity<String> handleNotaNotFound(NotaNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Nota não encontrada");
    }
}

