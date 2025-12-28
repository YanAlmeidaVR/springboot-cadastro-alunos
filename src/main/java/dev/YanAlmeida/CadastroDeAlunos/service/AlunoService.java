package dev.YanAlmeida.CadastroDeAlunos.service;

import dev.YanAlmeida.CadastroDeAlunos.dto.alunos.AlunoCreateDTO;
import dev.YanAlmeida.CadastroDeAlunos.dto.alunos.AlunoResponseDTO;
import dev.YanAlmeida.CadastroDeAlunos.entity.AlunoModel;
import dev.YanAlmeida.CadastroDeAlunos.exceptions.aluno.CpfErrorException;
import dev.YanAlmeida.CadastroDeAlunos.exceptions.aluno.AlunoNotFoundException;
import dev.YanAlmeida.CadastroDeAlunos.mapper.AlunoMapper;
import dev.YanAlmeida.CadastroDeAlunos.repository.AlunoRepository;
import dev.YanAlmeida.CadastroDeAlunos.repository.NotaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final AlunoMapper alunoMapper;
    private final NotaRepository notaRepository;

    public AlunoService(AlunoRepository alunoRepository, AlunoMapper alunoMapper, NotaRepository notaRepository) {
        this.alunoRepository = alunoRepository;
        this.alunoMapper = alunoMapper;
        this.notaRepository = notaRepository;
    }

    public AlunoResponseDTO save(AlunoCreateDTO aluno) {
        String cpfFormatado = formatarCpfComMascara(aluno.getCpf());
        validarCpf(cpfFormatado);

        // Verifica se CPF já existe
        validarCpfUnico(cpfFormatado, null);

        // Verifica se email já existe
        validarEmailUnico(aluno.getEmail(), null);

        AlunoModel alunoModel = alunoMapper.toEntity(aluno);
        alunoModel.setCpf(cpfFormatado);

        AlunoModel salvo = alunoRepository.save(alunoModel);

        return alunoMapper.toResponse(salvo);
    }

    public List<AlunoResponseDTO> listarTodos(){
        return alunoRepository.findAll()
                .stream()
                .map(alunoMapper::toResponse)
                .collect(Collectors.toList());
    }

    public AlunoResponseDTO buscarPorId(Long id){
        AlunoModel aluno = alunoRepository.findById(id)
                .orElseThrow(AlunoNotFoundException::new);
        return alunoMapper.toResponse(aluno);
    }

    public AlunoResponseDTO atualizar(Long id, AlunoCreateDTO dto){
        AlunoModel alunoExistente = alunoRepository.findById(id)
                .orElseThrow(AlunoNotFoundException::new);

        String cpfFormatado = formatarCpfComMascara(dto.getCpf());
        validarCpf(cpfFormatado);

        // Verifica se CPF já existe em OUTRO aluno
        validarCpfUnico(cpfFormatado, id);

        // Verifica se email já existe em OUTRO aluno
        validarEmailUnico(dto.getEmail(), id);

        alunoExistente.setNome(dto.getNome());
        alunoExistente.setCpf(cpfFormatado);
        alunoExistente.setEmail(dto.getEmail());
        alunoExistente.setIdade(dto.getIdade());

        AlunoModel atualizado = alunoRepository.save(alunoExistente);

        return alunoMapper.toResponse(atualizado);
    }

    @Transactional
    public void deletar(Long id) {
        AlunoModel aluno = alunoRepository.findById(id)
                .orElseThrow(AlunoNotFoundException::new);

        notaRepository.deleteByAlunoId(aluno.getId());
        alunoRepository.delete(aluno);
    }

    /* ================= Regras de Negócio ================= */

    public String limparCpf(String cpf){
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

        if (numeros.chars().distinct().count() == 1){
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

    private void validarCpfUnico(String cpf, Long idAtual) {
        Optional<AlunoModel> alunoExistente = alunoRepository.findByCpf(cpf);

        if (alunoExistente.isPresent()){
            // Se for atualização e o CPF pertence ao próprio aluno, OK
            if (idAtual != null && alunoExistente.get().getId().equals(idAtual)) {
                return;
            }
            // Se for cadastro novo OU o CPF pertence a outro aluno
            throw new RuntimeException("Já existe um aluno cadastrado com este CPF");
        }
    }

    private void validarEmailUnico(String email, Long idAtual) {
        Optional<AlunoModel> alunoExistente = alunoRepository.findByEmail(email);

        if (alunoExistente.isPresent()) {
            // Se for atualização e o email pertence ao próprio aluno, OK
            if (idAtual != null && alunoExistente.get().getId().equals(idAtual)) {
                return;
            }
            // Se for cadastro novo OU o email pertence a outro aluno
            throw new RuntimeException("Já existe um aluno cadastrado com este e-mail");
        }
    }
}