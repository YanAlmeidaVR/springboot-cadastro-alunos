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

    // Cria uma nova instância na tabela AlunoModel.

    public AlunoResponseDTO save(AlunoCreateDTO aluno){

        String cpfLimpo = limparCpf(aluno.getCpf());
        validarCpf(cpfLimpo);

        AlunoModel alunoModel = alunoMapper.toEntity(aluno);
        alunoModel.setCpf(cpfLimpo);

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

        String cpfLimpo = limparCpf(dto.getCpf());
        validarCpf(cpfLimpo);

        alunoExistente.setNome(dto.getNome());
        alunoExistente.setCpf(cpfLimpo);
        alunoExistente.setEmail(dto.getEmail());
        alunoExistente.setIdade(dto.getIdade());

        AlunoModel atualizado = alunoRepository.save(alunoExistente);

        return alunoMapper.toResponse(atualizado);
    }

    // Remove um aluno pelo ID.

    public void deletar(Long id){
        AlunoModel aluno = alunoRepository.findById(id)
                .orElseThrow(AlunoNotFoundException::new);
        alunoRepository.delete(aluno);
    }


    /* ================= Regras de Negócio ================= */

    // Remove máscara do CPF e valida os dígitos.
    // Retorna sempre CPF com 11 dígitos.

    private String limparCpf(String cpf){

            if (cpf == null){
                throw new CpfErrorException("CPF não pode ser nulo");
            }

            return cpf.replaceAll("\\D", "");
        }


        // Validação dos dígitos verificadores do CPF.

    private void validarCpf(String cpf){

        if (cpf == null || cpf.length() != 11){
            throw new CpfErrorException("CPF deve conter 11 dígitos");
        }

        if (cpf.chars().distinct().count() == 1){
            throw new CpfErrorException("CPF inválido");
        }

        int soma = 0;
        for (int i = 0; i < 9; i++){
            soma += (cpf.charAt(i) - '0') * (10 - i);
        }

        int digito1 = 11 - (soma % 11);
        digito1 = digito1 >= 10 ? 0 : digito1;

        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += (cpf.charAt(i) - '0') * (11 - i);
        }

        int digito2 = 11 - (soma % 11);
        digito2 = digito2 >= 10 ? 0 : digito2;

        if (digito1 != (cpf.charAt(9) - '0') ||
                digito2 != (cpf.charAt(10) - '0')) {
            throw new CpfErrorException("CPF inválido");
        }
    }

}