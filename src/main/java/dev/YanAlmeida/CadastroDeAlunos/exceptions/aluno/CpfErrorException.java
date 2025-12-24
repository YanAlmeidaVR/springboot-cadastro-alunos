package dev.YanAlmeida.CadastroDeAlunos.exceptions.aluno;

public class CpfErrorException extends RuntimeException {

    public CpfErrorException(){super("Cpf Inválido!");}

    public CpfErrorException(String message) {
        super(message);
    }
}
