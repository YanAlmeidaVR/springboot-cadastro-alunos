# 📚 API REST - Sistema de Cadastro de Alunos

<div align="center">

![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.5-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-3.9-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)

**API REST completa para gerenciamento acadêmico de alunos e notas**

[API Docs](#-documentação-da-api) • [Instalação](#-instalação) • [Endpoints](#-endpoints) • [Arquitetura](#-arquitetura)

</div>

---

## 🎯 Sobre o Projeto

API REST desenvolvida com Spring Boot para gerenciamento de alunos e notas, implementando validações robustas, regras de negócio complexas e documentação completa com OpenAPI/Swagger.

### Principais Features

- 📊 **Cálculo Automático** - Médias e status de aprovação
- 🔐 **Validações** - Bean Validation com annotations (@CPF, @Email, @NotBlank)
- 🗄️ **Persistência** - Spring Data JPA + PostgreSQL
- 📖 **Documentação** - Swagger/OpenAPI 3.0 interativo
- 🔄 **Migrations** - Controle de versão do banco com Flyway
- ⚠️ **Exception Handling** - Tratamento centralizado de erros

---

## 🚀 Instalação

### Pré-requisitos

```bash
☕ Java 21+
🐘 PostgreSQL 16+
📦 Maven 3.9+
```

### Setup Rápido

**1. Clone o repositório**
```bash
git clone https://github.com/YanAlmeidaVR/CadastroDeAlunos.git
cd CadastroDeAlunos
```

**2. Configure o banco de dados**
```sql
CREATE DATABASE cadastro_alunos;
```

**3. Configure `application.properties`**
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/cadastro_alunos
spring.datasource.username=postgres
spring.datasource.password=sua_senha
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=true

spring.flyway.enabled=true
spring.flyway.baseline-on-migrate=true

springdoc.swagger-ui.path=/swagger-ui.html
springdoc.api-docs.path=/api-docs
```

**4. Execute**
```bash
mvn spring-boot:run
```

**5. Acesse a documentação**
```
📖 Swagger UI: http://localhost:8080/swagger-ui.html
📄 OpenAPI JSON: http://localhost:8080/api-docs
```

---

## 🛠️ Stack Tecnológica

| Tecnologia | Versão | Descrição |
|------------|--------|-----------|
| **Java** | 21 | Linguagem principal |
| **Spring Boot** | 3.3.5 | Framework core |
| **Spring Data JPA** | 3.3.5 | Camada de persistência |
| **Hibernate** | 6.5.3 | ORM |
| **PostgreSQL** | 16 | Banco de dados relacional |
| **Flyway** | 10.10.0 | Versionamento de schema |
| **SpringDoc OpenAPI** | 2.3.0 | Documentação da API |
| **Hibernate Validator** | 8.0.1 | Validações Bean Validation |
| **Lombok** | 1.18.34 | Redução de boilerplate |
| **Maven** | 3.9+ | Build e gerenciamento de dependências |

---

## 📖 Documentação da API

### Swagger UI Interativo

Acesse **http://localhost:8080/swagger-ui.html** para documentação completa e interativa com:

- ✅ Exploração de todos os endpoints
- ✅ Testes diretos pela interface
- ✅ Schemas detalhados de request/response
- ✅ Exemplos de dados
- ✅ Códigos de resposta HTTP documentados
- ✅ Validações e constraints

---

## 🔌 Endpoints

### 👥 Alunos

| Método | Endpoint | Descrição | Status |
|--------|----------|-----------|--------|
| `GET` | `/alunos/listar` | Lista todos os alunos | 200 |
| `GET` | `/alunos/listar/{id}` | Busca aluno por ID | 200, 404 |
| `POST` | `/alunos/criar` | Cria novo aluno | 201, 400 |
| `PUT` | `/alunos/atualizar/{id}` | Atualiza aluno existente | 200, 404 |
| `DELETE` | `/alunos/deletar/{id}` | Remove aluno (e suas notas) | 204, 404 |

**Exemplo - Criar Aluno:**
```json
POST /alunos/criar

{
  "nome": "Maria Silva",
  "cpf": "12345678910",
  "email": "maria@email.com",
  "idade": 22
}
```

**Resposta (201):**
```json
{
  "id": 1,
  "nome": "Maria Silva",
  "cpf": "123.456.789-10",
  "email": "maria@email.com",
  "idade": 22
}
```

### 📊 Notas

| Método | Endpoint | Descrição | Status |
|--------|----------|-----------|--------|
| `GET` | `/notas/listar` | Lista todas as notas | 200 |
| `GET` | `/notas/listar/{id}` | Busca nota por ID | 200, 404 |
| `POST` | `/notas/criar` | Cria nova nota | 201, 400 |
| `PUT` | `/notas/atualizar/{id}` | Atualiza nota existente | 200, 404 |
| `DELETE` | `/notas/deletar/{id}` | Remove nota | 204, 404 |

**Exemplo - Criar Nota:**
```json
POST /notas/criar

{
  "alunoId": 1,
  "nota1": 8.5,
  "nota2": 7.0
}
```

**Resposta (201):**
```json
{
  "id": 1,
  "alunoId": 1,
  "alunoNome": "Maria Silva",
  "nota1": 8.5,
  "nota2": 7.0,
  "media": 7.75,
  "statusAprovacao": "APROVADO"
}
```

---

## 🏗️ Arquitetura

### Estrutura em Camadas

```
┌──────────────┐
│  Controller  │  ← REST API (JSON) + Validações (@Valid)
└──────┬───────┘
       │
┌──────▼───────┐
│   Service    │  ← Regras de negócio + Lógica de cálculo
└──────┬───────┘
       │
┌──────▼───────┐
│  Repository  │  ← Spring Data JPA (Query Methods)
└──────┬───────┘
       │
┌──────▼───────┐
│  PostgreSQL  │  ← Banco de dados
└──────────────┘
```

### Estrutura de Diretórios

```
src/main/java/dev/YanAlmeida/CadastroDeAlunos/
├── 📁 config/
│   ├── FlywayConfig.java
│   └── OpenApiConfig.java
│
├── 📁 controller/
│   ├── AlunoController.java       # REST API de alunos
│   └── NotaController.java        # REST API de notas
│
├── 📁 service/
│   ├── AlunoService.java          # Lógica de negócio de alunos
│   └── NotaService.java           # Lógica de negócio de notas
│
├── 📁 repository/
│   ├── AlunoRepository.java       # Spring Data JPA
│   └── NotaRepository.java
│
├── 📁 entity/
│   ├── AlunoModel.java            # Entidade JPA
│   └── NotaModel.java
│
├── 📁 dto/
│   ├── alunos/
│   │   ├── AlunoCreateDTO.java    # Request DTO
│   │   └── AlunoResponseDTO.java  # Response DTO
│   └── notas/
│       ├── NotaCreateDTO.java
│       └── NotaResponseDTO.java
│
├── 📁 mapper/
│   ├── AlunoMapper.java           # Entity ↔ DTO
│   └── NotaMapper.java
│
├── 📁 exceptions/
│   ├── aluno/
│   │   └── AlunoNotFoundException.java
│   ├── nota/
│   │   └── NotaNotFoundException.java
│   └── infra/
│       └── GlobalExceptionHandler.java
│
└── 📁 enums/
    └── StatusAprovacao.java
```

### Padrões de Projeto

- ✅ **Layered Architecture** - Controller → Service → Repository
- ✅ **DTO Pattern** - Separação entre objetos de transferência e entidades
- ✅ **Repository Pattern** - Abstração de acesso a dados
- ✅ **Mapper Pattern** - Conversão entre DTOs e Entities
- ✅ **Exception Handling** - `@ControllerAdvice` para tratamento global
- ✅ **Bean Validation** - Validações declarativas (@Valid, @CPF, @Email)

---

## 🗄️ Modelo de Dados

```sql
┌─────────────────────────┐         ┌──────────────────────────┐
│      TB_ALUNOS          │         │       TB_NOTAS           │
├─────────────────────────┤         ├──────────────────────────┤
│ id (PK)      BIGSERIAL  │         │ id (PK)       BIGSERIAL  │
│ nome         VARCHAR    │ 1     N │ aluno_id (FK) BIGINT     │
│ cpf          VARCHAR    │◄────────┤ nota1         NUMERIC    │
│ email        VARCHAR    │         │ nota2         NUMERIC    │
│ idade        INTEGER    │         │ media         NUMERIC    │
│                         │         │ status        VARCHAR    │
└─────────────────────────┘         └──────────────────────────┘
   UNIQUE: cpf, email
```

**Constraints:**
- CPF e Email são únicos no sistema
- Relacionamento: 1 Aluno → N Notas
- Cascade: DELETE de aluno remove suas notas

---

## ⚙️ Regras de Negócio

### Validações (Bean Validation)

```java
// AlunoCreateDTO
@NotBlank(message = "Nome é obrigatório")
private String nome;

@NotBlank(message = "CPF é obrigatório")
@CPF(message = "CPF inválido")  // Hibernate Validator
private String cpf;

@Email(message = "Email inválido")
@NotBlank(message = "Email é obrigatório")
private String email;

@NotNull(message = "Idade é obrigatória")
@Min(value = 1, message = "Idade deve ser maior que zero")
private Integer idade;
```

### Unicidade de Dados

O `AlunoService` garante que:
- ✅ CPF e Email são únicos no cadastro
- ✅ Em atualizações, permite manter os próprios dados do aluno
- ✅ Bloqueia tentativas de usar CPF/Email de outros alunos
- ✅ Formatação automática de CPF: `12345678910` → `123.456.789-10`

### Cálculo de Notas

```java
// Automático no NotaService
Media = (Nota1 + Nota2) / 2

Status = {
    APROVADO   se Media >= 6.0
    REPROVADO  se Media < 6.0
}
```

---

## 🧪 Testando a API

### Via Swagger UI (Recomendado)

1. Acesse: **http://localhost:8080/swagger-ui.html**
2. Escolha o endpoint
3. Clique em **"Try it out"**
4. Preencha o payload
5. Clique em **"Execute"**

### Via cURL

```bash
# Criar aluno
curl -X POST http://localhost:8080/alunos/criar \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "João Silva",
    "cpf": "12345678910",
    "email": "joao@email.com",
    "idade": 20
  }'

# Listar alunos
curl http://localhost:8080/alunos/listar

# Criar nota
curl -X POST http://localhost:8080/notas/criar \
  -H "Content-Type: application/json" \
  -d '{
    "alunoId": 1,
    "nota1": 8.5,
    "nota2": 7.0
  }'

# Listar notas
curl http://localhost:8080/notas/listar
```

### Via Postman/Insomnia

Importe a especificação OpenAPI:
```
http://localhost:8080/api-docs
```

---

## 📋 Migrations (Flyway)

### V1__create_tables.sql

```sql
CREATE TABLE tb_alunos (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(14) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    idade INTEGER NOT NULL
);

CREATE TABLE tb_notas (
    id BIGSERIAL PRIMARY KEY,
    aluno_id BIGINT NOT NULL,
    nota1 NUMERIC(4,2) NOT NULL CHECK (nota1 >= 0 AND nota1 <= 12),
    nota2 NUMERIC(4,2) NOT NULL CHECK (nota2 >= 0 AND nota2 <= 12),
    media NUMERIC(4,2) NOT NULL,
    status_aprovacao VARCHAR(20) NOT NULL,
    FOREIGN KEY (aluno_id) REFERENCES tb_alunos(id)
);
```

---

## 🔒 Tratamento de Erros

### Exception Handler Global

```java
@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(AlunoNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleAlunoNotFound() {
        return ResponseEntity.status(404).body(...);
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> handleValidation() {
        return ResponseEntity.status(400).body(...);
    }
}
```

### Códigos de Status HTTP

| Status | Descrição |
|--------|-----------|
| `200` | Sucesso (GET, PUT) |
| `201` | Criado com sucesso (POST) |
| `204` | Deletado com sucesso (DELETE) |
| `400` | Validação falhou |
| `404` | Recurso não encontrado |
| `500` | Erro interno do servidor |

---

## 🚀 Melhorias Futuras

- [ ] Autenticação e autorização (Spring Security + JWT)
- [ ] Paginação e ordenação nas listagens
- [ ] Filtros e busca avançada
- [ ] Testes unitários e de integração (JUnit 5 + Mockito)
- [ ] Testes de API (REST Assured)
- [ ] Cache com Redis
- [ ] Logs estruturados
- [ ] Métricas com Actuator + Prometheus
- [ ] CI/CD (GitHub Actions)
- [ ] Containerização (Docker + Docker Compose)
- [ ] Deploy na nuvem (AWS/Azure/Heroku)

---

## 👨‍💻 Autor

**Yan Almeida**

- 💼 LinkedIn: [Yan Almeida](https://www.linkedin.com/in/yandealmeida)
- 🐱 GitHub: [@YanAlmeidaVR](https://github.com/YanAlmeidaVR)

---

## 📝 Licença

Este projeto está sob a licença MIT.

---

<div align="center">

**Desenvolvido com ☕ por Yan Almeida**

⭐ Se este projeto foi útil, deixe uma estrela no repositório!

</div>
