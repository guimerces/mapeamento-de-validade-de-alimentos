# 📌 Despensa - API de Controle de Validade

API RESTful desenvolvida em **Spring Boot** para gerenciamento de despensas e monitoramento de validade de itens perecíveis. O sistema calcula dinamicamente o estado de consumo de cada alimento (`EM_DIA`, `ALERTA` ou `VENCIDO`) com base na antecedência configurada e mantém os dados isolados por usuário.

---

## 🧠 Dinâmica de Engenharia e Desenvolvimento Assistido por IA

Este repositório documenta a evolução da minha maturidade com Java e Spring Boot moderno — conectando a base teórica e de lógica do curso de Ciência da Computação com padrões da indústria, utilizando IA generativa como tutora e aceleradora do processo de desenvolvimento:

* **Fundamentação das Camadas e Endpoints (v1 & v2):** Foco em mapear mentalmente o fluxo completo da requisição HTTP pelas camadas do Spring Boot (Controller, Service, Repository, DTOs). A **v1** serviu como validação inicial do fluxo com uma operação pontual, enquanto a **v2** expandiu a superfície da API para quase a totalidade do CRUD de alimentos, incorporando validações de entrada e tratamento centralizado de erros.
* **Evolução de Domínio e Produtividade com IA (v3):** Com o fluxo básico consolidado, a v3 conclui o CRUD de produtos e introduz a entidade `Pessoa` com relacionamento 1:N e regras de negócio dedicadas. A IA atuou acelerando a criação da estrutura base e componentes repetitivos, permitindo concentrar os esforços na modelagem relacional, consistência dos dados e aplicação de boas práticas de orientação a objetos, como o **padrão Expert da metodologia GRASP**.

---

## 🏛️ Destaques da Arquitetura (v3)

* **Padrão Expert (GRASP):** A avaliação do status (`EM_DIA`, `ALERTA` ou `VENCIDO`) fica dentro da própria entidade `Produto`, detentora dos dados temporais (`dataValidade`, `tempoAvisoEmDias`).
* **Mapeamento Relacional e Isolamento:** Entidades `Pessoa` e `Produto` vinculadas via `@ManyToOne`, garantindo consultas isoladas por usuário proprietário.
* **Validação Declarativa:** Uso do Bean Validation (`@NotBlank`, `@NotNull`, `@FutureOrPresent`) nos DTOs para garantir a integridade dos dados logo na entrada da requisição.
* **Tratamento Centralizado:** `@RestControllerAdvice` capturando falhas e padronizando respostas de erro amigáveis (`400 Bad Request`, `404 Not Found`).

---

## ⚙️ Configuração Base

* **Porta da Aplicação:** `8080`
* **Porta do Banco:** `5432`
* **Banco Relacional:** PostgreSQL 16 (via Docker)
* **Documentação Interativa:** OpenAPI 3 / Swagger UI

---

## 🚀 Como Executar o Projeto

### 1. Iniciar o Banco de Dados (PostgreSQL no Docker)

Caso o contêiner já esteja criado:
```bash
docker start pg-despensa
```

Para criar o contêiner do zero:
```bash
docker run --name pg-despensa --restart always -e POSTGRES_DB=despensa_db -e POSTGRES_PASSWORD=admin -p 5432:5432 -d postgres:16
```

### 2. Iniciar a Aplicação (Spring Boot)

Na raiz do projeto:
```bash
./mvnw spring-boot:run
```

Acesse o Swagger UI no navegador:
* [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

---

## 🛣️ Endpoints Ativos

### Módulo de Identidade (`/pessoas`)
* `POST /pessoas` — Cadastro de usuário.
* `POST /pessoas/login` — Autenticação simples de credenciais.
* `GET /pessoas/{id}` — Consulta de perfil por ID.

### Módulo de Alimentos (`/produtos`)
* `POST /produtos` — Cadastro de produto vinculado a uma pessoa (`pessoaId`).
* `GET /produtos?pessoaId={id}` — Listagem de produtos filtrados por usuário com status dinâmico calculado.
* `GET /produtos/{id}` — Consulta unitária por identificador.
* `PUT /produtos/{id}` — Atualização cadastral e recálculo temporal de validade.
* `DELETE /produtos/{id}` — Exclusão definitiva do item (`204 No Content`).

---

## 📜 Histórico de Branches

* **`main` (v3):** Versão atual com modelo relacional completo (1:N), padrão Expert (GRASP), regras de negócio dedicadas e isolamento de produtos por usuário.
* **`v2-validations-exceptions`:** Expansão das operações do CRUD de produtos com validações declarativas, tratamento global de exceções e documentação via Swagger UI.
* **`base-simples-mvp` (v1):** Estrutura inicial do projeto (MVP) mapeando o fluxo da requisição HTTP de ponta a ponta.
