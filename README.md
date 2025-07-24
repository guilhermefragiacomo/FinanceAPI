# Sistema de Transações Bancárias - API REST

## Descrição do Projeto

Esta é a API REST de um sistema bancário simples que permite gerenciar transações financeiras, como receitas e despesas, categorizá-las, e obter resumos financeiros com base em filtros dinâmicos.

## Propósito

O objetivo deste sistema é fornecer uma plataforma simples, mas eficaz, para controle financeiro, onde o usuário pode registrar, visualizar, filtrar e analisar suas transações por mês, tipo e categoria.

## Tecnologias Utilizadas

* Java (Servlets)
* Banco de Dados MySQL
* Servidor Apache Tomcat
* JSON para comunicação via HTTP
* RESTful API

## Como funciona

A API possui endpoints públicos (sem autenticação) para consultar, cadastrar, editar e excluir transações e categorias, além de resumos financeiros baseados em filtros diversos. Os dados são enviados e recebidos no formato JSON.

## Vídeo de Apresentação

https://youtu.be/ehfO7Uy8L_0

---

## Documentação da API

### GET /transactions/

**Descrição:** Lista todas as transações cadastradas.
**Códigos de resposta:**

* 200 OK – Transações retornadas com sucesso.
* 500 Internal Server Error – Erro ao buscar os dados.

---

### GET /transactions/{id}

**Descrição:** Retorna uma transação específica pelo ID.
**Códigos de resposta:**

* 200 OK – Transação retornada.
* 400 Bad Request – Dados inválidos.
* 500 Internal Server Error – Erro interno.

---

### GET /transactions/category/{id}

**Descrição:** Lista transações que pertencem à categoria informada.
**Códigos de resposta:**

* 200 OK – Transações retornadas.
* 400 Bad Request – Dados inválidos.
* 500 Internal Server Error – Erro interno.

---

### GET /transactions/type/{id}

**Descrição:** Lista transações de um tipo específico (ex: receita ou despesa).
**Códigos de resposta:**

* 200 OK – Transações retornadas.
* 400 Bad Request – Dados inválidos.
* 500 Internal Server Error – Erro interno.

---

### GET /transactions/month/{id}

**Descrição:** Lista transações registradas no mês especificado.
**Códigos de resposta:**

* 200 OK – Transações retornadas.
* 400 Bad Request – Dados inválidos.
* 500 Internal Server Error – Erro interno.

---

### GET /transactions/filter/

**Descrição:** Lista transações com base em filtros opcionais: tipo, categoria e mês.
**Parâmetros de query (todos opcionais):**

* `category`: ID da categoria
* `type`: ID do tipo
* `month`: Número do mês (1-12)

**Exemplo:** `/transactions/filter/?category=1&type=2&month=6`
**Códigos de resposta:**

* 200 OK – Transações retornadas.
* 500 Internal Server Error – Erro interno.

---

### GET /transactions/summary/

**Descrição:** Retorna resumo financeiro total: receitas, despesas e saldo.
**Códigos de resposta:**

* 200 OK – Resumo retornado.
* 500 Internal Server Error – Erro interno.

---

### GET /transactions/summary/{id}

**Descrição:** Retorna resumo financeiro de uma categoria específica.
**Códigos de resposta:**

* 200 OK – Resumo retornado.
* 400 Bad Request – Dados inválidos.
* 500 Internal Server Error – Erro interno.

---

### GET /transactions/category/

**Descrição:** Lista todas as categorias disponíveis.
**Códigos de resposta:**

* 200 OK – Categorias retornadas.
* 500 Internal Server Error – Erro interno.

---

### POST /transactions/

**Descrição:** Cria uma nova transação no sistema.
**Corpo da requisição (JSON):**

```json
{
  "description": "descrição da transação",
  "value": 1220,
  "type": 1,
  "category": 1,
  "date": "2025-07-24"
}
```

**Códigos de resposta:**

* 201 Created – Transação criada.
* 400 Bad Request – Dados inválidos.
* 500 Internal Server Error – Erro ao persistir.

---

### POST /category/

**Descrição:** Cria uma nova categoria.
**Corpo da requisição (JSON):**

```json
{
  "name": "nome da categoria"
}
```

**Códigos de resposta:**

* 201 Created – Categoria criada.
* 400 Bad Request – Dados inválidos.
* 500 Internal Server Error – Erro ao persistir.

---

### PUT /transaction/{id}

**Descrição:** Atualiza uma transação existente.
**Corpo da requisição (JSON):**

```json
{
  "description": "descrição da transação",
  "value": 1220,
  "type": 1,
  "category": 1,
  "date": "2025-07-24"
}
```

**Códigos de resposta:**

* 201 Created – Transação atualizada.
* 400 Bad Request – Dados inválidos.
* 404 Not Found – Transação não encontrada.
* 500 Internal Server Error – Erro ao persistir.

---

### DELETE /transaction/{id}

**Descrição:** Deleta uma transação existente.
**Códigos de resposta:**

* 201 Created – Transação deletada.
* 400 Bad Request – Dados inválidos.
* 404 Not Found – Transação não encontrada.
* 500 Internal Server Error – Erro ao excluir.
