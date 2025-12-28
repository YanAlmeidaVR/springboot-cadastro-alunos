package dev.YanAlmeida.CadastroDeAlunos.repository;

import dev.YanAlmeida.CadastroDeAlunos.entity.AlunoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AlunoRepository extends JpaRepository<AlunoModel, Long> {

    Optional<AlunoModel> findByCpf(String cpf);

    Optional<AlunoModel> findByEmail(String email);
}