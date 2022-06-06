# Cadastro usuário clean arch

## Introdução

Esse projeto tem como objetivo a criação de usuários visando as boas praticas de clean architecture.


## Configuração

Para iniciar esse projeto você precisará:
1. Java 17
2. Maven
3. Docker

Para iniciar o banco de dados rode o comando na pasta resources: $ docker-compose up -d
Para desliagar o banco de dados rode: $ docker-compose down

## API

Os resources estão localizados em http://localhost:8080/swagger-ui/index.html#/

- Criação de usuário resource /users method POST, irá criar um usuario dado uma entrada exemlo:
```JSON
{
  "name": "Pedro Pedreira",
  "email": "pedro@gmail.com",
  "password": "Olá 123",
  "cpf": "051.668.200-88"
}
```
Obs: serão feitas validações de formato de cpf, senha e formato de email


- Alteração de usuário resource /users/051.668.200-88 method PUT, irá alterar um usuário dado uma entrada exemlo:
```JSON
{
  "name": "Pedro Pedrote",
  "email": "pedro_pedrote@gmail.com",
  "password": "Olá 123"
}
```
Obs: A senha deve ser igual a cadastrada na criação do usuário

- Lista de usuários resource /users method GET, irá buscar uma lista paginada de usuários dado uma entrada exemlo:
```JSON
{
  "page": 0,
  "size": 10
}
```

exemplo de resposta:
```JSON
{
  "content": [
    {
      "cpf": "051.668.200-88",
      "name": "Pedro Pedrote",
      "email": "pedro_pedrote@gmail.com",
      "password": "Olá 123"
    }
  ],
  "pageNumber": 0,
  "pageSize": 10,
  "total": 1,
  "last": true
}
```