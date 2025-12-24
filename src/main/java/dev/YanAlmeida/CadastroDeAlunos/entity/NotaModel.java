package dev.YanAlmeida.CadastroDeAlunos.entity;

import dev.YanAlmeida.CadastroDeAlunos.enums.StatusAprovacao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_notas")
@AllArgsConstructor
@NoArgsConstructor

public class NotaModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nota1", nullable = false, precision = 4, scale = 2)
    private BigDecimal nota1;

    @Column(name = "nota2", nullable = false, precision = 4, scale = 2)
    private BigDecimal nota2;

    @Column(name = "media", nullable = false, precision = 4, scale = 2)
    private BigDecimal media;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_aprovacao", nullable = false)
    private StatusAprovacao statusAprovacao;

    @OneToOne
    @JoinColumn(name = "aluno_id", nullable = false, unique = true)
    private AlunoModel aluno;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public AlunoModel getAluno() {
        return aluno;
    }

    public void setAluno(AlunoModel aluno) {
        this.aluno = aluno;
    }
}
