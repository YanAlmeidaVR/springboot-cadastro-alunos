CREATE TABLE tb_notas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,

    nota1 DECIMAL(4,2) NOT NULL,
    nota2 DECIMAL(4,2) NOT NULL,
    media DECIMAL(4,2) NOT NULL,

    status_aprovacao VARCHAR(20) NOT NULL,
    aluno_id BIGINT NOT NULL UNIQUE,

    CONSTRAINT fk_notas_aluno
        FOREIGN KEY (aluno_id)
        REFERENCES tb_alunos(id)
);