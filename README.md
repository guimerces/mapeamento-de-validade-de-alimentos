# 📌 API Despensa - Controle de Validade de Alimentos

## 🧠 Dinâmica de Desenvolvimento e Aprendizado
Este projeto foi concebido e construído como um laboratório prático de consolidação de conceitos de backend corporativo moderno. A dinâmica de desenvolvimento seguiu o seguinte fluxo:
* **Papel da IA (Gemini):** Fornecimento do código-base das classes, detalhamento didático das camadas do ecossistema Spring Boot, suporte na sintaxe e apresentação de convenções corporativas de mercado (REST, DTOs, Mappers e convenções de nomenclatura).
* **Papel do Desenvolvedor:** Compreensão do fluxo da requisição HTTP de ponta a ponta, questionamento dos papéis dos objetos, montagem de modelos mentais das conexões entre as classes, revisão estrutural, antecipação de refatorações de cabeça antes da validação com a IA e alinhamento do aprendizado teórico à prática em repositórios reais.

---

## ⚙️ Configuração Base
* **Porta da Aplicação:** `8080`
* **Porta do Banco (PostgreSQL):** `5432`
* **Formato dos Dados:** JSON (`Content-Type: application/json`)
* **Arquitetura Adotada:** Separação estrita em camadas desacopladas (`ProdutoRequestDTO`, `ProdutoResponseDTO`, `ProdutoMapper`, `ProdutoService`, `ProdutoRepository` e `ResponseEntity` com status semânticos `200`, `201` e `404`).

---

## 🚀 Como Executar o Projeto

### 1. Iniciar o Banco de Dados (PostgreSQL no Docker)
Caso o contêiner já esteja criado, execute no terminal:
```bash
docker start pg-despensa
```
Nota (caso precise recriar o contêiner do zero):


Bash
```
docker run --name pg-despensa --restart always -e POSTGRES_DB=despensa_db -e POSTGRES_PASSWORD=admin -p 5432:5432 -d postgres:16
```

2. Iniciar a Aplicação (Spring Boot)
Na raiz do projeto, execute o comando Maven Wrapper:

```Bash
./mvnw spring-boot:run
```
Aguarde até a mensagem indicando que o servidor Tomcat subiu na porta 8080.

🛣️ Endpoints Ativos
1. Cadastrar Produto
Método / Rota: POST /produtos

Status HTTP: 201 Created

Função: Cadastra um novo produto persistindo no banco relacional via JPA.

Payload de Envio (ProdutoRequestDTO):

```JSON
{
  "nome": "Castanha de Caju 50g",
  "dataValidade": "2026-09-28"
}
```

Payload de Retorno (ProdutoResponseDTO):

```JSON
{
  "id": 1,
  "nome": "Castanha de Caju 50g",
  "dataValidade": "2026-09-28"
}
```

Exemplo de teste via terminal:

```Bash
curl -i -X POST http://localhost:8080/produtos \
  -H "Content-Type: application/json" \
  -d '{"nome": "Castanha de Caju 50g", "dataValidade": "2026-09-28"}'
```

2. Consultar Produto por ID
Método / Rota: GET /produtos/{id}

Status HTTP: 200 OK (se localizado) ou 404 Not Found (se inexistente)

Função: Busca o produto pelo seu identificador primário via @PathVariable.

Payload de Retorno (ProdutoResponseDTO):

```JSON
{
  "id": 1,
  "nome": "Castanha de Caju 50g",
  "dataValidade": "2026-09-28"
}
```

Exemplo de teste via terminal:

```Bash
curl -i http://localhost:8080/produtos/1
```

3. Consultar Produto por Nome
Método / Rota: GET /produtos?nome={nome_do_produto}

Status HTTP: 200 OK (se localizado) ou 404 Not Found (se inexistente)

Função: Filtra produto por nome exato via parâmetro de consulta (@RequestParam).

Payload de Retorno (ProdutoResponseDTO):

```JSON
{
  "id": 1,
  "nome": "Castanha de Caju 50g",
  "dataValidade": "2026-09-28"
}
```

Exemplo de teste via terminal:

```
Bash
curl -i "http://localhost:8080/produtos?nome=Castanha%20de%20Caju%2050g"
```
