# 📌 Resumo da API - Mapeamento de Validade de Alimentos (Versão Simplificada)

> **Nota Didática:** Esta branch contém uma versão inicial simplificada da API criada para validar a "fiação básica" de ponta a ponta (Rede > Controller > Service > Repository > Banco).  
> Algumas decisões foram tomadas propositalmente para reduzir ruído cognitivo no início dos estudos:
> * As respostas devolvem mensagens de texto (`String`) em vez de envelopes JSON formais.
> * O Controller não utiliza `ResponseEntity` nem status HTTP semânticos (ex: 201 Created, 404 Not Found), respondendo com código padrão 200 OK.
> * A arquitetura corporativa completa (com `ResponseDTO`, `Mapper` e tratamento de status) está implementada na branch `main` ou em outra branch mais atual.

---

## ⚙️ Configuração Base
* **Porta da Aplicação:** `8080`
* **Porta do Banco (PostgreSQL):** `5432`
* **Formato de Envio dos Dados:** JSON (`Content-Type: application/json`)

---

## 🚀 Como Executar o Projeto

### 1. Iniciar o Banco de Dados (PostgreSQL no Docker)
Caso o contêiner já esteja criado, execute no terminal:
```bash
docker start pg-despensa
````
Nota (caso precise recriar o contêiner do zero):

````Bash
docker run --name pg-despensa --restart always -e POSTGRES_DB=despensa_db -e POSTGRES_PASSWORD=admin -p 5432:5432 -d postgres:16
````

2. Iniciar a Aplicação (Spring Boot)
Na raiz do projeto, execute o comando Maven Wrapper:

````Bash
./mvnw spring-boot:run
````

Aguarde até a mensagem indicando que o Tomcat iniciou na porta 8080.

🛣️ Endpoints Ativos
1. Status da Aplicação
Método / Rota: GET /produtos

Função: Teste de status e conectividade da aplicação.

Corpo enviado: Nenhum.

Retorno: Mensagem textual confirmando que o endpoint está ativo.

2. Cadastrar Produto
Método / Rota: POST /produtos

Função: Cadastrar um novo produto no PostgreSQL.

Corpo enviado (JSON):

````
JSON
{
  "nome": "string",
  "dataValidade": "AAAA-MM-DD"
}
````
Retorno: Mensagem textual confirmando o cadastro com o nome, validade e ID gerado pelo banco.

Exemplo de teste via terminal:

````Bash
curl -X POST http://localhost:8080/produtos \
  -H "Content-Type: application/json" \
  -d '{"nome": "castanha de caju 50g", "dataValidade": "2026-09-28"}'
````
3. Consultar Validade por Nome
Método / Rota: GET /produtos?nome={nome_do_produto}

Função: Buscar a data de validade de um produto pelo nome via parâmetro de URL (@RequestParam).

Corpo enviado: Nenhum.

Retorno: Mensagem textual com o nome e validade encontrados.

Exemplo de teste via terminal:

````Bash
curl "http://localhost:8080/produtos?nome=castanha%20de%20caju%2050g"
````

4. Consultar Produto por ID
Método / Rota: GET /produtos/{id}

Função: Buscar os dados do produto pelo seu identificador único via variável de caminho (@PathVariable).

Corpo enviado: Nenhum.

Retorno: Mensagem textual com os dados do produto ou aviso de que não foi localizado.

Exemplo de teste via terminal:

````Bash
curl http://localhost:8080/produtos/1
````

---
