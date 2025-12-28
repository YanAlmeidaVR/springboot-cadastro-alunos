# 📚 Sistema de Cadastro de Alunos

<div align="center">
  
![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.1-brightgreen?style=for-the-badge&logo=spring)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-blue?style=for-the-badge&logo=postgresql)
![Maven](https://img.shields.io/badge/Maven-3.9-red?style=for-the-badge&logo=apache-maven)

Sistema web completo para gerenciamento de alunos e notas, desenvolvido com Spring Boot e interface responsiva.

[🚀 Funcionalidades](#-funcionalidades) • 
[🛠️ Tecnologias](#️-tecnologias) • 
[💻 Instalação](#-como-executar) • 
[📸 Screenshots](#-screenshots) • 
[📝 Arquitetura](#-arquitetura-do-projeto)

</div>

---

## 🎯 Sobre o Projeto

Sistema desenvolvido para gerenciar cadastros de alunos e suas respectivas notas, com validações robustas de CPF, verificação de unicidade de dados, e cálculo automático de médias e status de aprovação.

O projeto foi desenvolvido seguindo as melhores práticas de desenvolvimento backend, incluindo arquitetura em camadas, separação de responsabilidades, validações de regras de negócio e interface responsiva.

---

## 🚀 Funcionalidades

### 👥 Gestão de Alunos
- ✅ Cadastro completo de alunos
- ✅ Listagem com informações detalhadas
- ✅ Edição de dados cadastrais
- ✅ Exclusão com remoção em cascata de notas
- ✅ Validação matemática de CPF (algoritmo oficial da Receita Federal)
- ✅ Verificação de unicidade de CPF e Email
- ✅ Formatação automática de CPF (XXX.XXX.XXX-XX)

### 📊 Gestão de Notas
- ✅ Cadastro de notas por aluno
- ✅ Suporte para notas de 0 a 12
- ✅ Cálculo automático de média
- ✅ Definição automática de status (APROVADO/REPROVADO)
- ✅ Critério de aprovação: média ≥ 6.0
- ✅ Listagem com status visual (badges coloridos)
- ✅ Exclusão de notas

### 🎨 Interface
- ✅ Design moderno e responsivo (Bootstrap 5)
- ✅ Ícones intuitivos (Bootstrap Icons)
- ✅ Feedback visual de ações
- ✅ Navegação fluida entre páginas
- ✅ Confirmações de exclusão

---

## 🛠️ Tecnologias

### Backend
- **Java 21** - Linguagem principal
- **Spring Boot 4.0.1** - Framework principal
- **Spring Data JPA** - Persistência de dados
- **Spring Web MVC** - Controllers REST e View
- **Hibernate 7.2.0** - ORM
- **PostgreSQL 16** - Banco de dados relacional
- **Flyway** - Controle de versão do banco (migrations)
- **Lombok** - Redução de boilerplate
- **Bean Validation** - Validação de dados

### Frontend
- **Thymeleaf** - Template engine
- **Bootstrap 5.3.3** - Framework CSS
- **Bootstrap Icons 1.11.3** - Biblioteca de ícones

### Build & DevOps
- **Maven** - Gerenciamento de dependências
- **Git** - Controle de versão

---

## 💻 Como Executar

### Pré-requisitos

Certifique-se de ter instalado:
- [JDK 21](https://www.oracle.com/java/technologies/downloads/#java21)
- [PostgreSQL 16](https://www.postgresql.org/download/)
- [Maven 3.9+](https://maven.apache.org/download.cgi)
- [Git](https://git-scm.com/downloads)

### 1️⃣ Clone o Repositório
```bash
git clone https://github.com/seu-usuario/CadastroDeAlunos.git
cd CadastroDeAlunos
```

### 2️⃣ Configure o Banco de Dados

**Acesse o PostgreSQL:**
```bash
psql -U postgres
```

**Crie o banco de dados:**
```sql
CREATE DATABASE CadastroDeAlunos;
\q
```

### 3️⃣ Configure o `application.properties`

Edite o arquivo `src/main/resources/application.properties`:
```properties
# Configuração do Banco de Dados
spring.datasource.url=jdbc:postgresql://localhost:5432/CadastroDeAlunos
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha

# JPA/Hibernate
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# Flyway
spring.flyway.enabled=true
spring.flyway.locations=classpath:db/migration
```

### 4️⃣ Execute a Aplicação

**Com Maven:**
```bash
mvn spring-boot:run
```

**Ou compile e execute o JAR:**
```bash
mvn clean package
java -jar target/CadastroDeAlunos-0.0.1-SNAPSHOT.jar
```

### 5️⃣ Acesse a Aplicação

🌐 **URL:** http://localhost:8080/web/alunos

---

## 📸 Screenshots

<details open>
<summary>👁️ Clique para expandir/recolher</summary>

### 👥 Gestão de Alunos

**Listagem de Alunos**
![Lista de Alunos](docs/screenshots/01-lista-alunos.png)
> Visualização de todos os alunos cadastrados com CPF formatado, email e idade

**Cadastro de Aluno**
![Formulário de Aluno](docs/screenshots/02-form-aluno.png)
> Formulário intuitivo com validação de CPF e unicidade de dados

---

### 📊 Gestão de Notas

**Listagem de Notas**
![Lista de Notas](docs/screenshots/03-lista-notas.png)
> Visualização completa com notas individuais, média calculada e status de aprovação

**Cadastro de Nota**
![Formulário de Nota](docs/screenshots/04-form-nota.png)
> Seleção de aluno e entrada de notas com validação de intervalo (0-12)

</details>

---

## 📝 Arquitetura do Projeto

### Estrutura de Pastas
```
src/main/java/dev/YanAlmeida/CadastroDeAlunos/
├── 📁 config/              # Configurações da aplicação
│   └── FlywayConfig.java
│
├── 📁 controller/          # Camada de Apresentação
│   ├── AlunoController.java         # API REST de alunos
│   ├── NotaController.java          # API REST de notas
│   └── view/
│       ├── AlunoViewController.java # Controller Web de alunos
│       └── NotaViewController.java  # Controller Web de notas
│
├── 📁 service/             # Camada de Negócio
│   ├── AlunoService.java   # Regras de negócio de alunos
│   └── NotaService.java    # Regras de negócio de notas
│
├── 📁 repository/          # Camada de Persistência
│   ├── AlunoRepository.java # Acesso a dados de alunos
│   └── NotaRepository.java  # Acesso a dados de notas
│
├── 📁 dto/                 # Data Transfer Objects
│   ├── alunos/
│   │   ├── AlunoCreateDTO.java
│   │   └── AlunoResponseDTO.java
│   └── notas/
│       ├── NotaCreateDTO.java
│       └── NotaResponseDTO.java
│
├── 📁 entity/              # Entidades JPA
│   ├── AlunoModel.java
│   └── NotaModel.java
│
├── 📁 mapper/              # Conversores DTO ↔ Entity
│   ├── AlunoMapper.java
│   └── NotaMapper.java
│
├── 📁 exceptions/          # Exceções Customizadas
│   ├── aluno/
│   │   ├── AlunoNotFoundException.java
│   │   └── CpfErrorException.java
│   ├── nota/
│   │   └── NotaNotFoundException.java
│   └── infra/
│       └── GlobalExceptionHandler.java
│
└── 📁 enums/               # Enumerações
    └── StatusAprovacao.java

src/main/resources/
├── 📁 templates/           # Views Thymeleaf
│   ├── alunos/
│   │   ├── form.html       # Formulário de aluno
│   │   └── listar.html     # Listagem de alunos
│   ├── notas/
│   │   ├── form.html       # Formulário de nota
│   │   └── listar.html     # Listagem de notas
│   └── layout/
│       └── base.html       # Layout base (se existir)
│
├── 📁 db/migration/        # Migrations Flyway
│   └── V1__create_tables.sql
│
├── 📁 static/              # Arquivos estáticos
│
└── application.properties  # Configurações da aplicação
```

---

## 🏗️ Padrões de Projeto Utilizados

### 🎯 **Arquitetura em Camadas**
```
Controller → Service → Repository → Database
```
Separação clara de responsabilidades seguindo princípios SOLID

### 🔄 **DTO Pattern**
```java
CreateDTO → Service → Entity → Repository
Repository → Entity → Service → ResponseDTO
```
Transferência segura de dados entre camadas

### 🗺️ **Mapper Pattern**
Conversão centralizada entre DTOs e Entities usando MapStruct ou manual

### 🔐 **Repository Pattern**
Abstração do acesso a dados com Spring Data JPA

### ⚠️ **Exception Handling**
Tratamento centralizado de exceções com `@ControllerAdvice`

---

## 🔍 Regras de Negócio

### Validação de CPF
1. Remove caracteres não numéricos
2. Verifica se possui 11 dígitos
3. Bloqueia CPFs com números repetidos (111.111.111-11, etc)
4. Valida dígitos verificadores usando algoritmo oficial

### Validação de Unicidade
- CPF e Email devem ser únicos no sistema
- Permite que aluno mantenha seus próprios dados em edições
- Bloqueia tentativas de usar dados de outros alunos

### Cálculo de Notas
```
Média = (Nota1 + Nota2) / 2
Status = Média ≥ 6.0 ? APROVADO : REPROVADO
```

---

## 📊 Modelo de Dados

### Diagrama ER Simplificado
```
┌─────────────────┐         ┌─────────────────┐
│   TB_ALUNOS     │         │    TB_NOTAS     │
├─────────────────┤         ├─────────────────┤
│ id (PK)         │1       N│ id (PK)         │
│ nome            │────────<│ aluno_id (FK)   │
│ cpf (UNIQUE)    │         │ nota1           │
│ email (UNIQUE)  │         │ nota2           │
│ idade           │         │ media           │
└─────────────────┘         │ status_aprovacao│
                            └─────────────────┘
```

### Tabelas

**TB_ALUNOS**
```sql
id            BIGSERIAL PRIMARY KEY
nome          VARCHAR(100) NOT NULL
cpf           VARCHAR(14) UNIQUE NOT NULL
email         VARCHAR(100) UNIQUE NOT NULL
idade         INTEGER NOT NULL
```

**TB_NOTAS**
```sql
id                BIGSERIAL PRIMARY KEY
aluno_id          BIGINT REFERENCES tb_alunos(id)
nota1             NUMERIC(4,2) NOT NULL
nota2             NUMERIC(4,2) NOT NULL
media             NUMERIC(4,2) NOT NULL
status_aprovacao  VARCHAR(20) NOT NULL
```

---

## 🧪 Testando a Aplicação

### Endpoints da API REST

#### Alunos

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/api/alunos` | Lista todos os alunos |
| GET | `/api/alunos/{id}` | Busca aluno por ID |
| POST | `/api/alunos` | Cria novo aluno |
| PUT | `/api/alunos/{id}` | Atualiza aluno |
| DELETE | `/api/alunos/{id}` | Remove aluno |

#### Notas

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/api/notas` | Lista todas as notas |
| GET | `/api/notas/{id}` | Busca nota por ID |
| POST | `/api/notas` | Cria nova nota |
| DELETE | `/api/notas/{id}` | Remove nota |

### Exemplo de Requisição (POST /api/alunos)
```json
{
  "nome": "João Silva",
  "cpf": "12345678910",
  "email": "joao@example.com",
  "idade": 20
}
```

### Exemplo de Resposta
```json
{
  "id": 1,
  "nome": "João Silva",
  "cpf": "123.456.789-10",
  "email": "joao@example.com",
  "idade": 20
}
```

---

## 🚀 Próximas Melhorias

- [ ] Implementar autenticação e autorização (Spring Security)
- [ ] Adicionar paginação nas listagens
- [ ] Implementar filtros e busca avançada
- [ ] Exportação de relatórios (PDF/Excel)
- [ ] Dashboard com estatísticas
- [ ] Histórico de alterações
- [ ] Notificações por email
- [ ] Testes unitários e de integração
- [ ] Deploy em ambiente de produção
- [ ] Documentação da API com Swagger/OpenAPI

---

## 📚 Aprendizados

Durante o desenvolvimento deste projeto, foram aplicados conceitos importantes:

- ✅ Arquitetura em camadas e separação de responsabilidades
- ✅ Validações de regras de negócio complexas (CPF)
- ✅ Relacionamentos JPA (OneToMany, ManyToOne)
- ✅ Migrations com Flyway
- ✅ DTOs e Mappers para transferência segura de dados
- ✅ Exception Handling centralizado
- ✅ Validação de unicidade com tratamento de updates
- ✅ Interface responsiva com Bootstrap
- ✅ Query Methods do Spring Data JPA

---

## 🤝 Contribuindo

Contribuições são bem-vindas! Sinta-se à vontade para:

1. Fazer um fork do projeto
2. Criar uma branch para sua feature (`git checkout -b feature/MinhaFeature`)
3. Commit suas mudanças (`git commit -m 'Adiciona MinhaFeature'`)
4. Push para a branch (`git push origin feature/MinhaFeature`)
5. Abrir um Pull Request

---

## 📝 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

---

## 👨‍💻 Autor

**Yan Almeida**

- 💼 LinkedIn: [seu-linkedin](https://linkedin.com/in/seu-usuario)
- 🐱 GitHub: [@YanAlmeida](https://github.com/seu-usuario)
- 📧 Email: seu.email@example.com

---

## ⭐ Deixe uma Estrela!

Se este projeto te ajudou de alguma forma, considere deixar uma ⭐ no repositório!

---

<div align="center">
  
**Desenvolvido com ❤️ e ☕ por Yan Almeida**

</div>
