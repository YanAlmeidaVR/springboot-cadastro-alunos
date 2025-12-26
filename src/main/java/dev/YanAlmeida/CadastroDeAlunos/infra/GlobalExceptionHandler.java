package dev.YanAlmeida.CadastroDeAlunos.infra;

import dev.YanAlmeida.CadastroDeAlunos.exceptions.aluno.AlunoNotFoundException;
import dev.YanAlmeida.CadastroDeAlunos.exceptions.aluno.CpfErrorException;
import org.springframework.dao.DataIntegrityViolationException; // Importante adicionar
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CpfErrorException.class)
    public ResponseEntity<String> handleCpfError(CpfErrorException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

    @ExceptionHandler(AlunoNotFoundException.class)
    public ResponseEntity<String> handleAlunoNotFound(AlunoNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body("Erro: Já existe um registro com este CPF ou E-mail.");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        System.err.println("Erro crítico detectado: " + ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Ocorreu um erro inesperado no servidor. Por favor, tente novamente mais tarde.");
    }
}