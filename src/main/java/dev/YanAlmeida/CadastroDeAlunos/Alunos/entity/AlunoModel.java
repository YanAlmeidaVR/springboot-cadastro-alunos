package dev.YanAlmeida.CadastroDeAlunos.Alunos.entity;
import jakarta.persistence.*;

@Entity
public class AlunoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


}
