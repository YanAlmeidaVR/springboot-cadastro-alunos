package dev.YanAlmeida.CadastroDeAlunos.dto.notas;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Dados necessários para criar ou atualizar uma nota")
public class NotaCreateDTO{

    @NotNull(message = "AlunoId é obrigatório")
    @Schema(description = "ID do aluno ao qual a nota pertence", example = "1", required = true)
    private Long alunoId;

    @NotNull(message = "Nota1 é obrigatória")
    @DecimalMin(value = "0.0", inclusive = true, message = "Nota1 não pode ser negativa")
    @Schema(description = "Primeira nota do aluno", example = "8.5", required = true, minimum = "0.0")
    private BigDecimal nota1;

    @NotNull(message = "Nota2 é obrigatória")
    @DecimalMin(value = "0.0", inclusive = true, message = "Nota2 não pode ser negativa")
    @Schema(description = "Segunda nota do aluno", example = "7.0", required = true, minimum = "0.0")
    private BigDecimal nota2;

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
}