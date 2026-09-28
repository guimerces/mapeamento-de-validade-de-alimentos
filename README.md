# 📌 Resumo da API - Mapeamento de Validade de Alimentos

## ⚙️ Configuração Base
* **Porta:** `8080`
* **Formato dos Dados:** JSON (`Content-Type: application/json`)

---

## 🛣️ Endpoints Ativos

### 1. `GET /produtos`
* **Função:** Teste de status da aplicação.
* **Corpo enviado:** Nenhum.
* **Retorno:** Por enquanto um Hello World.

---

### 2. `POST /produtos`
* **Função:** Cadastrar um novo produto no PostgreSQL.
* **Corpo enviado (JSON):**
```json
{
  "nome": "string",
  "dataValidade": "AAAA-MM-DD"
}
```

Retorno: Texto com o nome, validade e o ID gerado pelo banco.

Exemplo de teste via terminal:
  curl -X POST http://localhost:8080/produtos \
  -H "Content-Type: application/json" \
  -d '{"nome": "castanha de caju 50g", "dataValidade": "2026-09-28"}'
