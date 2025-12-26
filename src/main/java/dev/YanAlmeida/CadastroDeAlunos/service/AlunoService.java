package dev.YanAlmeida.CadastroDeAlunos.service;

import dev.YanAlmeida.CadastroDeAlunos.dto.alunos.AlunoCreateDTO;
import dev.YanAlmeida.CadastroDeAlunos.dto.alunos.AlunoResponseDTO;
import dev.YanAlmeida.CadastroDeAlunos.entity.AlunoModel;
import dev.YanAlmeida.CadastroDeAlunos.exceptions.aluno.CpfErrorException;
import dev.YanAlmeida.CadastroDeAlunos.exceptions.aluno.AlunoNotFoundException;
import dev.YanAlmeida.CadastroDeAlunos.mapper.AlunoMapper;
import dev.YanAlmeida.CadastroDeAlunos.repository.AlunoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final AlunoMapper alunoMapper;

    public AlunoService(AlunoRepository alunoRepository, AlunoMapper alunoMapper) {
        this.alunoRepository = alunoRepository;
        this.alunoMapper = alunoMapper;
    }


    public AlunoResponseDTO save(AlunoCreateDTO aluno) {

        String cpfFormatado = formatarCpfComMascara(aluno.getCpf());
        validarCpf(cpfFormatado);

        AlunoModel alunoModel = alunoMapper.toEntity(aluno);
        alunoModel.setCpf(cpfFormatado);

        AlunoModel salvo = alunoRepository.save(alunoModel);

        return alunoMapper.toResponse(salvo);
    }


    // Retorna todos os alunos cadastrados.

    public List<AlunoResponseDTO> listarTodos(){
        return alunoRepository.findAll()
                .stream()
                .map(alunoMapper::toResponse)
                .collect(Collectors.toList());
    }

    // Busca um aluno pelo ID.

    public AlunoResponseDTO buscarPorId(Long id){
        AlunoModel aluno = alunoRepository.findById(id)
                .orElseThrow(AlunoNotFoundException::new);
        return alunoMapper.toResponse(aluno);
    }

    // Atualiza os dados de um aluno existente.

    public AlunoResponseDTO atualizar(Long id, AlunoCreateDTO dto){

        AlunoModel alunoExistente = alunoRepository.findById(id)
                .orElseThrow(AlunoNotFoundException::new);

        String cpfFormatado = formatarCpfComMascara(dto.getCpf());
        validarCpf(cpfFormatado);

        alunoExistente.setNome(dto.getNome());
        alunoExistente.setCpf(cpfFormatado);
        alunoExistente.setEmail(dto.getEmail());
        alunoExistente.setIdade(dto.getIdade());

        AlunoModel atualizado = alunoRepository.save(alunoExistente);

        return alunoMapper.toResponse(atualizado);
    }


    // Remove um aluno pelo ID.

    public void deletar(Long id) {
        if (!alunoRepository.existsById(id)) {
            throw new AlunoNotFoundException();
        }
        alunoRepository.deleteById(id);
    }



    /* ================= Regras de Negócio ================= */

    public String limparCpf(String cpf) {
        if (cpf == null || cpf.isBlank()) {
            throw new CpfErrorException("CPF não pode ser nulo ou vazio");
        }
        String numeros = cpf.replaceAll("\\D", "");
        if (numeros.length() != 11) {
            throw new CpfErrorException("CPF deve conter 11 dígitos");
        }
        return numeros;
    }

    public void validarCpf(String cpf) {
        String numeros = limparCpf(cpf);

        if (numeros.chars().distinct().count() == 1) {
            throw new CpfErrorException("CPF inválido");
        }

        if (!validarDigito(numeros, 9) || !validarDigito(numeros, 10)) {
            throw new CpfErrorException("CPF inválido");
        }
    }

    private boolean validarDigito(String cpf, int posicao) {
        int soma = 0;
        for (int i = 0; i < posicao; i++) {
            soma += (cpf.charAt(i) - '0') * (posicao + 1 - i);
        }
        int digito = 11 - (soma % 11);
        digito = digito >= 10 ? 0 : digito;
        return digito == (cpf.charAt(posicao) - '0');
    }

    public String formatarCpfComMascara(String cpf) {
        String numeros = limparCpf(cpf);
        return numeros.replaceFirst("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
    }

}