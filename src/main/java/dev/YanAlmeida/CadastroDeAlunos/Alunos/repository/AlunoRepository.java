package dev.YanAlmeida.CadastroDeAlunos.Alunos.repository;

import dev.YanAlmeida.CadastroDeAlunos.Alunos.entity.AlunoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<AlunoModel, Long> {
}
