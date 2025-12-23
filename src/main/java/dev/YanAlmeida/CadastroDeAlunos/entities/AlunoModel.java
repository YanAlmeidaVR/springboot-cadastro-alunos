package dev.YanAlmeida.CadastroDeAlunos.Alunos.entity;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "tb_alunos")
@AllArgsConstructor
@NoArgsConstructor

public class AlunoModel{

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

    public Long getId(){
        return id;
    }

    public void setId(Long id){
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
