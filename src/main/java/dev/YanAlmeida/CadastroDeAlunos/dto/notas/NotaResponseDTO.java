package dev.YanAlmeida.CadastroDeAlunos.dto.notas;

import dev.YanAlmeida.CadastroDeAlunos.enums.StatusAprovacao;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Dados de resposta de uma nota")
public class NotaResponseDTO{

    @Schema(description = "ID único da nota", example = "1")
    private Long id;

    @Schema(description = "ID do aluno", example = "1")
    private Long alunoId;

    @Schema(description = "Nome do aluno", example = "João Silva")
    private String alunoNome;

    @Schema(description = "Primeira nota do aluno", example = "8.5")
    private BigDecimal nota1;

    @Schema(description = "Segunda nota do aluno", example = "7.0")
    private BigDecimal nota2;

    @Schema(description = "Média calculada das notas", example = "7.75")
    private BigDecimal media;

    @Schema(description = "Status de aprovação do aluno", example = "APROVADO",
            allowableValues = {"APROVADO", "RECUPERACAO", "REPROVADO"})
    private StatusAprovacao statusAprovacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(Long alunoId) {
        this.alunoId = alunoId;
    }

    public BigDecimal getNota1() {
        return nota1;
    }

    public void setNota1(BigDecimal nota1) {
        this.nota1 = nota1;
    }

    public BigDecimal getNota2() {
        return nota2;
    }

    public void setNota2(BigDecimal nota2) {
        this.nota2 = nota2;
    }

    public BigDecimal getMedia() {
        return media;
    }

    public void setMedia(BigDecimal media) {
        this.media = media;
    }

    public StatusAprovacao getStatusAprovacao() {
        return statusAprovacao;
    }

    public void setStatusAprovacao(StatusAprovacao statusAprovacao) {
        this.statusAprovacao = statusAprovacao;
    }

    public String getAlunoNome() {
        return alunoNome;
    }

    public void setAlunoNome(String alunoNome) {
        this.alunoNome = alunoNome;
    }
}