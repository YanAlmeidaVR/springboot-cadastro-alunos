package dev.YanAlmeida.CadastroDeAlunos.Alunos.service;

import dev.YanAlmeida.CadastroDeAlunos.Alunos.dto.AlunoCreateDTO;
import dev.YanAlmeida.CadastroDeAlunos.Alunos.dto.AlunoResponseDTO;
import dev.YanAlmeida.CadastroDeAlunos.Alunos.entity.AlunoModel;
import dev.YanAlmeida.CadastroDeAlunos.Alunos.mapper.AlunoMapper;
import dev.YanAlmeida.CadastroDeAlunos.Alunos.repository.AlunoRepository;
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

    // Cria uma nova instância na tabela AlunoModel.

    public AlunoResponseDTO save(AlunoCreateDTO aluno) {

        //DTO → Entity
        AlunoModel alunoModel = alunoMapper.toEntity(aluno);

        alunoModel.setCpf(limparCpf(aluno.getCpf()));

        AlunoModel salvo = alunoRepository.save(alunoModel);

        //Entity → ResponseDTO
        return alunoMapper.toResponse(salvo);
    }

    // Retorna todos os alunos cadastrados.

    public List<AlunoResponseDTO> listarTodos() {
        return alunoRepository.findAll()
                .stream()
                .map(alunoMapper::toResponse)
                .collect(Collectors.toList());
    }

    // Busca um aluno pelo ID.

    public AlunoResponseDTO buscarPorId(Long id) {
        AlunoModel aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
        return alunoMapper.toResponse(aluno);
    }

    // Atualiza os dados de um aluno existente.

    public AlunoResponseDTO atualizar(Long id, AlunoCreateDTO dto) {

        AlunoModel alunoExistente = alunoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        alunoExistente.setNome(dto.getNome());
        alunoExistente.setCpf(limparCpf(dto.getCpf()));
        alunoExistente.setEmail(dto.getEmail());
        alunoExistente.setIdade(dto.getIdade());

        AlunoModel atualizado = alunoRepository.save(alunoExistente);

        return alunoMapper.toResponse(atualizado);
    }

    // Remove um aluno pelo ID.

    public void deletar(Long id) {
        AlunoModel aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
        alunoRepository.delete(aluno);
    }


    /* ================= Regras de Negócio ================= */

    // Remove máscara do CPF e valida os dígitos.
    // Retorna sempre CPF com 11 dígitos.

    private String limparCpf(String cpf){

        if (cpf == null) {
            throw new IllegalArgumentException("CPF não pode ser nulo");
        }

        String apenasNumeros = cpf.replaceAll("\\D", "");

        if (!cpfValido(apenasNumeros)){
            throw new IllegalArgumentException("CPF inválido");
        }

        return apenasNumeros;
    }

    // Validação dos dígitos verificadores do CPF.

    private boolean cpfValido(String cpf){

        if (cpf.length() != 11) return false;

        // evita CPFs como 11111111111
        if (cpf.chars().distinct().count() == 1) return false;

        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += (cpf.charAt(i) - '0') * (10 - i);
        }

        int digito1 = 11 - (soma % 11);
        digito1 = digito1 >= 10 ? 0 : digito1;

        soma = 0;
        for (int i = 0; i < 10; i++){
            soma += (cpf.charAt(i) - '0') * (11 - i);
        }

        int digito2 = 11 - (soma % 11);
        digito2 = digito2 >= 10 ? 0 : digito2;

        return digito1 == (cpf.charAt(9) - '0')
                && digito2 == (cpf.charAt(10) - '0');
    }
}