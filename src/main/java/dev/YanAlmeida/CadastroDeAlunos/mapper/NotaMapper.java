package dev.YanAlmeida.CadastroDeAlunos.mapper;

import dev.YanAlmeida.CadastroDeAlunos.entity.AlunoModel;
import dev.YanAlmeida.CadastroDeAlunos.dto.notas.NotaCreateDTO;
import dev.YanAlmeida.CadastroDeAlunos.dto.notas.NotaResponseDTO;
import dev.YanAlmeida.CadastroDeAlunos.entity.NotaModel;
import org.springframework.stereotype.Component;

@Component
public class NotaMapper{

    public NotaModel toEntity(NotaCreateDTO dto, AlunoModel aluno){

        NotaModel nota = new NotaModel();
        nota.setAluno(aluno);
        nota.setNota1(dto.getNota1());
        nota.setNota2(dto.getNota2());

        return nota;
    }

    public NotaResponseDTO toResponse(NotaModel model){

        NotaResponseDTO dto = new NotaResponseDTO();
        dto.setId(model.getId());
        dto.setAlunoId(model.getAluno().getId());
        dto.setNota1(model.getNota1());
        dto.setNota2(model.getNota2());
        dto.setMedia(model.getMedia());
        dto.setStatusAprovacao(model.getStatusAprovacao());

        return dto;
    }

}
