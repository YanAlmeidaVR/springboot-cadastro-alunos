package dev.YanAlmeida.CadastroDeAlunos.service;

import dev.YanAlmeida.CadastroDeAlunos.entity.AlunoModel;
import dev.YanAlmeida.CadastroDeAlunos.exceptions.aluno.AlunoNotFoundException;
import dev.YanAlmeida.CadastroDeAlunos.exceptions.nota.NotaNotFoundException;
import dev.YanAlmeida.CadastroDeAlunos.repository.AlunoRepository;
import dev.YanAlmeida.CadastroDeAlunos.dto.notas.NotaCreateDTO;
import dev.YanAlmeida.CadastroDeAlunos.dto.notas.NotaResponseDTO;
import dev.YanAlmeida.CadastroDeAlunos.entity.NotaModel;
import dev.YanAlmeida.CadastroDeAlunos.enums.StatusAprovacao;
import dev.YanAlmeida.CadastroDeAlunos.mapper.NotaMapper;
import dev.YanAlmeida.CadastroDeAlunos.repository.NotaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotaService {

    private final NotaRepository notaRepository;
    private final AlunoRepository alunoRepository;
    private final NotaMapper notaMapper;

    public NotaService(NotaRepository notaRepository, AlunoRepository alunoRepository, NotaMapper notaMapper) {
        this.notaRepository = notaRepository;
        this.alunoRepository = alunoRepository;
        this.notaMapper = notaMapper;
    }

    // Cria uma nova instância na tabela NotaModel.
    public NotaResponseDTO save(NotaCreateDTO dto){
        AlunoModel aluno = alunoRepository.findById(dto.getAlunoId())
                .orElseThrow(NotaNotFoundException::new);

        validarNotas(dto.getNota1(), dto.getNota2());

        //DTO → Entity
        NotaModel nota = notaMapper.toEntity(dto, aluno);

        aplicarNotas(nota,dto.getNota1(),dto.getNota2());

        NotaModel salvo = notaRepository.save(nota);

        //Entity → ResponseDTO
        return notaMapper.toResponse(salvo);
    }



    // Retorna todas as notas cadastradas na tabela

    public List<NotaResponseDTO> listarTodas() {
        return notaRepository.findAll()
                .stream()
                .map(notaMapper::toResponse)
                .collect(Collectors.toList());
    }


    // Busca uma nota pelo ID.

    public NotaResponseDTO buscarPorId(Long id) {
        NotaModel nota = notaRepository.findById(id)
                .orElseThrow(NotaNotFoundException::new);
        return notaMapper.toResponse(nota);
    }


    // Atualiza as notas de um registro existente.

    public NotaResponseDTO atualizar(Long id, NotaCreateDTO dto){

        validarNotas(dto.getNota1(), dto.getNota2());

        NotaModel notaExistente = notaRepository.findById(id)
                .orElseThrow(NotaNotFoundException::new);

        aplicarNotas(notaExistente,dto.getNota1(),dto.getNota2());


        NotaModel atualizada = notaRepository.save(notaExistente);

        return notaMapper.toResponse(atualizada);
    }

    // Remove uma nota pelo ID.

    public void deletar(Long id) {
        NotaModel nota = notaRepository.findById(id)
                .orElseThrow(NotaNotFoundException::new);
        notaRepository.delete(nota);
    }



    /* ================= Regras de Negócio ================= */

    // Valida se as notas estão entre 0 e 12.

    private void validarNotas(BigDecimal nota1, BigDecimal nota2){
        if (nota1 == null || nota2 == null){
            throw new RuntimeException("Notas não podem ser nulas");
        }
        if (nota1.compareTo(BigDecimal.ZERO) < 0 || nota1.compareTo(BigDecimal.valueOf(12)) > 0
                || nota2.compareTo(BigDecimal.ZERO) < 0 || nota2.compareTo(BigDecimal.valueOf(12)) > 0) {
            throw new RuntimeException("Notas devem estar entre 0 e 12");
        }
    }

    // Aplica notas, calcula média e define status de aprovação.

    private void aplicarNotas(NotaModel nota, BigDecimal nota1, BigDecimal nota2){

        validarNotas(nota1, nota2);

        BigDecimal media = nota1
                .add(nota2)
                .divide(BigDecimal.valueOf(2), 2, RoundingMode.HALF_UP);

        nota.setNota1(nota1);
        nota.setNota2(nota2);
        nota.setMedia(media);

        if (media.compareTo(BigDecimal.valueOf(6)) >= 0){
            nota.setStatusAprovacao(StatusAprovacao.APROVADO);
        } else{
            nota.setStatusAprovacao(StatusAprovacao.REPROVADO);
        }
    }
}
