# BARBER-001 — Validação do Ambiente de Desenvolvimento

## 1. Ambiente utilizado

- Java: 21.0.10
- Apache Maven: 3.9.16
- Spring Boot: 4.1.0
- Banco de dados: PostgreSQL
- Infraestrutura: Docker Compose

## 2. Problemas encontrados

Durante a configuração do ambiente, identifiquei um conflito na porta 8080, que impedia a inicialização da API.

Também descobri que existiam duas instâncias do PostgreSQL: uma instalada diretamente no Windows e outra executando em um contêiner Docker.

Além disso, ocorreu uma falha de autenticação porque a aplicação estava tentando acessar o banco com o usuário `root`, enquanto o banco do Docker utilizava `myuser`.

## 3. Soluções aplicadas

- Configurei a API para executar na porta 8081.
- Ajustei o mapeamento do PostgreSQL no Docker para `5431:5432`.
- Configurei o datasource do Spring Boot para utilizar o banco `mydatabase`, acessível pela porta 5431.
- Ajustei as configurações de autenticação para utilizar o usuário correto do PostgreSQL.

## 4. Testes realizados

Realizei a execução dos testes do projeto pelo Maven e validei o funcionamento da API utilizando o Postman.

O endpoint `GET /companies` retornou HTTP 200 OK, apresentando as empresas cadastradas.

Também acessei o PostgreSQL diretamente pelo contêiner Docker e executei:

```sql
SELECT id, name FROM companies;
```

A consulta retornou os registros `Barber Shop Victor` e `Teste`, correspondentes aos dados apresentados pela API.

## 5. Resultado final

A aplicação Spring Boot iniciou corretamente, conseguiu se conectar ao PostgreSQL executado no Docker e retornou os registros esperados.

A integração entre a API e o banco de dados foi validada com sucesso.