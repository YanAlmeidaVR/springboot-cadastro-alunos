package dev.YanAlmeida.CadastroDeAlunos.mapper;


import dev.YanAlmeida.CadastroDeAlunos.dto.alunos.AlunoCreateDTO;
import dev.YanAlmeida.CadastroDeAlunos.dto.alunos.AlunoResponseDTO;
import dev.YanAlmeida.CadastroDeAlunos.entity.AlunoModel;
import org.springframework.stereotype.Component;

@Component
public class AlunoMapper{

    public AlunoModel toEntity(AlunoCreateDTO dto){
        AlunoModel aluno = new AlunoModel();
        aluno.setNome(dto.getNome());
        aluno.setCpf(dto.getCpf());
        aluno.setEmail(dto.getEmail());
        aluno.setIdade(dto.getIdade());

        return aluno;
    }

    public AlunoResponseDTO toResponse(AlunoModel aluno){
        AlunoResponseDTO dto = new AlunoResponseDTO();
        dto.setId(aluno.getId());
        dto.setNome(aluno.getNome());
        dto.setEmail(aluno.getEmail());
        dto.setIdade(aluno.getIdade());
        dto.setCpf(aluno.getCpf());

        return dto;
    }
}
