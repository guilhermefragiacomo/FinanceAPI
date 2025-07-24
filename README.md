1. GET /transactions/ – Listar todas as transações.
Descrição: Retorna uma lista com todas as transações cadastradas no sistema.
Método HTTP: GET
Endpoint: /transactions/
Parâmetros: Nenhum parâmetro é necessário para esta requisição.
Códigos de resposta:
● 200 OK – Requisição bem-sucedida, transações retornadas.
● 500 Internal Server Error – Erro interno ao buscar os dados

1. GET /transactions/{id} – Listar transação com o id informado.
Descrição: Retorna uma única transação com o id informado no parâmetro.
Método HTTP: GET
Endpoint: /transactions/{id}
Parâmetros: Nenhum parâmetro é necessário para esta requisição.
Códigos de resposta:
● 200 OK – Requisição bem-sucedida, transação retornada.
● 400 Bad Request – Dados ausentes ou inválidos.
● 500 Internal Server Error – Erro interno ao buscar os dados.

1. GET /transactions/category/{id} – Listar transações pelo id da categoria informada.
Descrição: Retorna uma lista com todas as transações que façam parte da categoria informada cadastradas no sistema.
Método HTTP: GET
Endpoint: /transactions/category/{id}
Parâmetros: Nenhum parâmetro é necessário para esta requisição.
Códigos de resposta:
● 200 OK – Requisição bem-sucedida, transações retornadas.
● 400 Bad Request – Dados ausentes ou inválidos.
● 500 Internal Server Error – Erro interno ao buscar os dados.

1. GET /transactions/type/{id} – Listar transações pelo id do tipo informado.
Descrição: Retorna uma lista com todas as transações que façam parte do tipo informado cadastradas no sistema.
Método HTTP: GET
Endpoint: /transactions/type/{id}
Parâmetros: Nenhum parâmetro é necessário para esta requisição.
Códigos de resposta:
● 200 OK – Requisição bem-sucedida, transações retornadas.
● 400 Bad Request – Dados ausentes ou inválidos.
● 500 Internal Server Error – Erro interno ao buscar os dados.

1. GET /transactions/type/{id} – Listar transações pelo id do tipo informado.
Descrição: Retorna uma lista com todas as transações que façam parte do tipo informado cadastradas no sistema.
Método HTTP: GET
Endpoint: /transactions/type/{id}
Parâmetros: Nenhum parâmetro é necessário para esta requisição.
Códigos de resposta:
● 200 OK – Requisição bem-sucedida, transações retornadas.
● 400 Bad Request – Dados ausentes ou inválidos.
● 500 Internal Server Error – Erro interno ao buscar os dados.

1. GET /transactions/month/{id} – Listar transações pelo número do mês informado.
Descrição: Retorna uma lista com todas as transações que foram realizadas no mês informado cadastradas no sistema.
Método HTTP: GET
Endpoint: /transactions/month/{id}
Parâmetros: Nenhum parâmetro é necessário para esta requisição.
Códigos de resposta:
● 200 OK – Requisição bem-sucedida, transações retornadas.
● 400 Bad Request – Dados ausentes ou inválidos.
● 500 Internal Server Error – Erro interno ao buscar os dados.

1. GET /transactions/filter/ – Listar transações por tipo, categoria e mês informado.
Descrição: Retorna uma lista com todas as transações que possuem tipo, categoria e mês informado, sendo cada parâmetro opcional, cadastradas no sistema. Caso nenhum parâmetro seja informado, retornará todas as transações.
Método HTTP: GET
Endpoint: /transactions/filter/
Parâmetros:
● "category" – id da categoria (opcional)
● "type" – id do tipo (opcional)
● "month" – número do mês (opcional)
Códigos de resposta:
● 200 OK – Requisição bem-sucedida, transações retornadas.
● 500 Internal Server Error – Erro interno ao buscar os dados.

1. GET /transactions/summary/ – Lista um resumo financêiro.
Descrição: Retorna um resumo de todas as transações registradas no sistema, com receitas, despesas e saldo atual.
Método HTTP: GET
Endpoint: /transactions/summary/
Parâmetros: Nenhum parâmetro é necessário para esta requisição.
Códigos de resposta:
● 200 OK – Requisição bem-sucedida, transações retornadas.
● 500 Internal Server Error – Erro interno ao buscar os dados.

1. GET /transactions/summary/{id} – Listar um resumo financêiro baseado no id da categoria informada.
Descrição: Retorna um resumo das transações que façam parte da categoria informada, com receitas, despesas e o saldo geral.
Método HTTP: GET
Endpoint: /transactions/summary/{id}
Parâmetros: Nenhum parâmetro é necessário para esta requisição.
Códigos de resposta:
● 200 OK – Requisição bem-sucedida, transações retornadas.
● 400 Bad Request – Dados ausentes ou inválidos.
● 500 Internal Server Error – Erro interno ao buscar os dados.

1. GET /transactions/category/ – Listar todas as categorias
Descrição: Retorna uma lista com todas as categorias cadastradas no sistema.
Método HTTP: GET
Endpoint: /transactions/category/
Parâmetros: Nenhum parâmetro é necessário para esta requisição.
Códigos de resposta:
● 200 OK – Requisição bem-sucedida, tarefas retornadas.
● 500 Internal Server Error – Erro interno ao buscar os dados

2. POST /transactions/ – Criar nova transação
Descrição: Cadastra uma nova transação no banco de dados.
Método HTTP: POST
Endpoint: /transactions/
Corpo da requisição (JSON):
{
"description": "descrição da transação",
"value": 1220,
"type": 1,
"category": 1,
"date": "2025-07-24"
}
Códigos de resposta:
● 201 Created – Tarefa criada com sucesso.
● 400 Bad Request – Dados ausentes ou inválidos.
● 500 Internal Server Error – Erro ao persistir os dados.

2. POST /category/ – Criar nova categoria
Descrição: Cadastra uma nova categoria no banco de dados.
Método HTTP: POST
Endpoint: /category/
Corpo da requisição (JSON):
{
"name": "nome da categoria";
}
Códigos de resposta:
● 201 Created – Tarefa criada com sucesso.
● 400 Bad Request – Dados ausentes ou inválidos.
● 500 Internal Server Error – Erro ao persistir os dados.

2. PUT /transaction/{id} – Atualizar transação
Descrição: Atualiza uma transação já existente no banco de dados.
Método HTTP: PUT
Endpoint: /transaction/{id}
Corpo da requisição (JSON):
{
"description": "descrição da transação",
"value": 1220,
"type": 1,
"category": 1,
"date": "2025-07-24"
}
Códigos de resposta:
● 201 Created – Tarefa atualizada com sucesso.
● 400 Bad Request – Dados ausentes ou inválidos.
● 404 Not Found – Dados não encontrados no sistema.
● 500 Internal Server Error – Erro ao persistir os dados.

2. DELETE /transaction/{id} – Deletar transação
Descrição: Deleta uma transação já existente no banco de dados.
Método HTTP: DELETE
Endpoint: /transaction/{id}
Parâmetros: Nenhum parâmetro é necessário para esta requisição.
Códigos de resposta:
● 201 Created – Tarefa atualizada com sucesso.
● 400 Bad Request – Dados ausentes ou inválidos.
● 404 Not Found – Dados não encontrados no sistema.
● 500 Internal Server Error – Erro ao persistir os dados.
