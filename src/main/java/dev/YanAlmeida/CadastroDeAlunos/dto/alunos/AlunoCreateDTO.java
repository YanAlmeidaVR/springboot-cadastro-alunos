package dev.YanAlmeida.CadastroDeAlunos.dto.alunos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Dados necessários para criar ou atualizar um aluno")
public class AlunoCreateDTO {

    @NotBlank(message = "Nome é obrigatório")
    @Schema(description = "Nome completo do aluno", example = "João Silva", required = true)
    private String nome;

    @NotBlank(message = "CPF é obrigatório")
    @Schema(description = "CPF do aluno (apenas números)", example = "12345678900", required = true)
    private String cpf;

    @Email(message = "Email inválido")
    @NotBlank(message = "Email é obrigatório")
    @Schema(description = "Email do aluno", example = "joao.silva@email.com", required = true)
    private String email;

    @NotNull(message = "Idade é obrigatória")
    @Min(value = 1, message = "Idade deve ser maior que zero")
    @Schema(description = "Idade do aluno", example = "18", required = true, minimum = "1")
    private Integer idade;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }
}