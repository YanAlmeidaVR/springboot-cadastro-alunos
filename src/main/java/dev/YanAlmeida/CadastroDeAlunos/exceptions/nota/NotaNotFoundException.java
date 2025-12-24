package dev.YanAlmeida.CadastroDeAlunos.exceptions.nota;

public class NotaNotFoundException extends RuntimeException {

    public NotaNotFoundException(){super("Nota não encontrada");}

    public NotaNotFoundException(String message) {
        super(message);
    }
}
