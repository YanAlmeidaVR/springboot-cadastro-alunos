package dev.YanAlmeida.CadastroDeAlunos.repository;

import dev.YanAlmeida.CadastroDeAlunos.entity.NotaModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotaRepository  extends JpaRepository<NotaModel, Long>{
    void deleteByAlunoId(Long alunoId);
}
