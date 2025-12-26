CREATE TABLE tb_alunos (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255),
    cpf VARCHAR(14) UNIQUE,
    email VARCHAR(255) UNIQUE,
    idade INTEGER
);

CREATE TABLE tb_notas (
    id BIGSERIAL PRIMARY KEY,
    nota1 NUMERIC(4,2) NOT NULL,
    nota2 NUMERIC(4,2) NOT NULL,
    media NUMERIC(4,2) NOT NULL,
    status_aprovacao VARCHAR(30) NOT NULL,
    aluno_id BIGINT NOT NULL UNIQUE,
    CONSTRAINT fk_tb_notas_aluno
        FOREIGN KEY (aluno_id)
        REFERENCES tb_alunos (id)
);
