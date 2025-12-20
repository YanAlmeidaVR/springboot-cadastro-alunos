package dev.YanAlmeida.CadastroDeAlunos.Alunos.entity;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "tb_alunos")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class AlunoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cpf",unique = true,length = 11)
    private String cpf;

    @Column(name = "email",unique = true)
    private String email;

    @Column(name = "idade")
    private int idade;
}
