package dev.YanAlmeida.CadastroDeAlunos.exceptions.aluno;

public class AlunoNotFoundException extends RuntimeException {

  public AlunoNotFoundException() {
    super("Aluno não encontrado");
  }

  public AlunoNotFoundException(String message) {
    super(message);
  }
}
