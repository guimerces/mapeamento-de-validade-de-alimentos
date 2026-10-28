# 📌 API Despensa - Controle de Validade de Alimentos

## 🧠 Dinâmica de Desenvolvimento e Aprendizado
Este projeto foi concebido e construído como um laboratório prático de consolidação de conceitos de backend corporativo moderno. A dinâmica de desenvolvimento seguiu o seguinte fluxo:
* **Papel da IA (Gemini):** Fornecimento do código-base das classes, detalhamento didático das camadas do ecossistema Spring Boot, suporte na sintaxe e apresentação de convenções corporativas de mercado (REST, DTOs, Mappers e convenções de nomenclatura).
* **Papel do Desenvolvedor:** Compreensão crítica do fluxo da requisição HTTP de ponta a ponta, questionamento dos papéis dos objetos, montagem de modelos mentais das conexões entre as classes, revisão estrutural, antecipação de refatorações de cabeça antes da validação com a IA e alinhamento do aprendizado teórico à prática em repositórios reais.

---

## ⚙️ Configuração Base
* **Porta da Aplicação:** `8080`
* **Porta do Banco (PostgreSQL):** `5432`
* **Formato dos Dados:** JSON (`Content-Type: application/json`)
* **Arquitetura Adotada:** Separação estrita em camadas desacopladas (`ProdutoRequestDTO`, `ProdutoResponseDTO`, `ProdutoMapper`, `ProdutoService`, `ProdutoRepository` e `ResponseEntity` com status semânticos `200`, `201`, `204` e `404`).

---

## 🚀 Como Executar o Projeto

### 1. Iniciar o Banco de Dados (PostgreSQL no Docker)
Caso o contêiner já esteja criado, execute no terminal:
```bash
docker start pg-despensa
```

*Nota (caso precise recriar o contêiner do zero):*
```bash
docker run --name pg-despensa --restart always -e POSTGRES_DB=despensa_db -e POSTGRES_PASSWORD=admin -p 5432:5432 -d postgres:16
```

### 2. Iniciar a Aplicação (Spring Boot)
Na raiz do projeto, execute o comando Maven Wrapper:
```bash
./mvnw spring-boot:run
```

Aguarde até a mensagem indicando que o servidor Tomcat subiu na porta **8080**.

### 3. Documentação Interativa (Swagger UI)
Com a aplicação em execução, acesse a bancada de testes interativa diretamente pelo navegador:
* **Local:** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
* **GitHub Codespaces:** Abra a porta `8080` na aba *Ports* e adicione `/swagger-ui.html` ao final da URL gerada.

---

## 🛣️ Endpoints Ativos

### 1. Cadastrar Produto
* **Método / Rota:** `POST /produtos`
* **Status HTTP:** `201 Created`
* **Função:** Cadastra um novo produto persistindo no banco relacional via JPA.

**Payload de Envio (`ProdutoRequestDTO`):**
```json
{
  "nome": "Castanha de Caju 50g",
  "dataValidade": "2026-09-28"
}
```

**Payload de Retorno (`ProdutoResponseDTO`):**
```json
{
  "id": 1,
  "nome": "Castanha de Caju 50g",
  "dataValidade": "2026-09-28"
}
```

**Exemplo de teste via terminal:**
```bash
curl -i -X POST http://localhost:8080/produtos \
  -H "Content-Type: application/json" \
  -d '{"nome": "Castanha de Caju 50g", "dataValidade": "2026-09-28"}'
```

---

### 2. Listar Todos os Produtos
* **Método / Rota:** `GET /produtos`
* **Status HTTP:** `200 OK`
* **Função:** Retorna todos os alimentos cadastrados na despensa em formato de array.

**Payload de Retorno (`List<ProdutoResponseDTO>`):**
```json
[
  {
    "id": 1,
    "nome": "Castanha de Caju 50g",
    "dataValidade": "2026-09-28"
  }
]
```

**Exemplo de teste via terminal:**
```bash
curl -i http://localhost:8080/produtos
```

---

### 3. Consultar Produto por ID
* **Método / Rota:** `GET /produtos/{id}`
* **Status HTTP:** `200 OK` (se localizado) ou `404 Not Found` (se inexistente)
* **Função:** Busca o produto pelo seu identificador primário via `@PathVariable`.

**Payload de Retorno (`ProdutoResponseDTO`):**
```json
{
  "id": 1,
  "nome": "Castanha de Caju 50g",
  "dataValidade": "2026-09-28"
}
```

**Exemplo de teste via terminal:**
```bash
curl -i http://localhost:8080/produtos/1
```

---

### 4. Consultar Produto por Nome
* **Método / Rota:** `GET /produtos/busca?nome={nome_do_produto}`
* **Status HTTP:** `200 OK` (se localizado) ou `404 Not Found` (se inexistente)
* **Função:** Filtra produto por nome exato via parâmetro de consulta (`@RequestParam`) em rota dedicada.

**Payload de Retorno (`ProdutoResponseDTO`):**
```json
{
  "id": 1,
  "nome": "Castanha de Caju 50g",
  "dataValidade": "2026-09-28"
}
```

**Exemplo de teste via terminal:**
```bash
curl -i "http://localhost:8080/produtos/busca?nome=Castanha%20de%20Caju%2050g"
```

---

### 5. Deletar Produto por ID
* **Método / Rota:** `DELETE /produtos/{id}`
* **Status HTTP:** `204 No Content` (se removido com sucesso) ou `404 Not Found` (se inexistente)
* **Função:** Remove o produto do banco de dados pelo identificador único via variável de caminho (`@PathVariable`).

* **Payload de Envio:** Nenhum.
* **Payload de Retorno:** Nenhum (*empty body*).

**Exemplo de teste via terminal:**
```bash
curl -i -X DELETE http://localhost:8080/produtos/1
```


---

## 🛡️ Validação Declarativa & Tratamento Global de Exceções (v2)

### 1. Bean Validation Declarativo na Borda
A entrada da API é fiscalizada na camada de apresentação (`@Valid` no Controller), impedindo que dados inconsistentes cheguem ao domínio ou ao banco de dados:
* `@NotBlank`: Impede nome vazio ou composto apenas de espaços no cadastro.
* `@NotNull`: Exige o envio da data de validade.
* `@FutureOrPresent`: Garante que alimentos já vencidos não sejam cadastrados como novos na despensa.

### 2. Tratamento Global de Exceções (`@RestControllerAdvice`)
A interceptação centralizada via `GlobalExceptionHandler` padroniza os erros em um payload uniforme (`ApiErro`):
* `MethodArgumentNotValidException`: Captura violações do Bean Validation e retorna status `400 Bad Request` com lista detalhada dos campos inválidos.
* `HttpMessageNotReadableException`: Intercepta payloads malformados ou datas fora do formato ISO-8601 (`AAAA-MM-DD`), retornando `400 Bad Request` amigável.


---

## 🛠️ Dívidas Técnicas Conscientes & Próximos Passos

> [!NOTE]
> Esta seção documenta decisões de engenharia pragmáticas tomadas durante o ciclo atual para viabilizar entregas rápidas sem comprometer o planejamento arquitetural futuro.

* **Endpoint de Consulta Textual (`GET /produtos/busca`):**
  * **Contexto:** A rota `/busca` foi adotada temporariamente para evitar conflitos na documentação de contratos OpenAPI/Swagger em relação ao endpoint de listagem geral (`GET /produtos`).
  * **Ação Futura (Refatoração):** Unificar a listagem irrestrita e o filtro textual sob o endpoint canônico `GET /produtos` com parâmetro opcional (`@RequestParam(required = false)`), preservando o Nível 2 do Modelo de Maturidade de Richardson (sem verbos nas URIs).
