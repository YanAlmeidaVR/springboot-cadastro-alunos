package dev.YanAlmeida.CadastroDeAlunos.dto.alunos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Dados de resposta de um aluno")
public class AlunoResponseDTO {

    @Schema(description = "ID único do aluno", example = "1")
    private Long id;

    @Schema(description = "Nome completo do aluno", example = "João Silva")
    private String nome;

    @Schema(description = "CPF do aluno", example = "12345678900")
    private String cpf;

    @Schema(description = "Email do aluno", example = "joao.silva@email.com")
    private String email;

    @Schema(description = "Idade do aluno", example = "18")
    private int idade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getCpf(){
        return cpf;
    }

    public void setCpf(String cpf){
        this.cpf = cpf;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public int getIdade(){
        return idade;
    }

    public void setIdade(int idade){
        this.idade = idade;
    }
}