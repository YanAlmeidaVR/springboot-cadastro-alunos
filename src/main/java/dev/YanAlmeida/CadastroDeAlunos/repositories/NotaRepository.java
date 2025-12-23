package dev.YanAlmeida.CadastroDeAlunos.Notas.repository;

import dev.YanAlmeida.CadastroDeAlunos.Notas.entity.NotaModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotaRepository  extends JpaRepository<NotaModel, Long>{
}
