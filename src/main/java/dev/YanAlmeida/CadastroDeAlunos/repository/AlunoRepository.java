package dev.YanAlmeida.CadastroDeAlunos.repository;

import dev.YanAlmeida.CadastroDeAlunos.entity.AlunoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<AlunoModel, Long>{
}
