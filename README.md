# Evently

Evently é um sistema de gerenciamento de eventos. Este projeto utiliza Spring Boot para o backend e PostgreSQL como banco de dados.

## Pré-requisitos

Antes de começar, certifique-se de ter as seguintes ferramentas instaladas:

- [Java 23](https://www.oracle.com/java/technologies/downloads/)
- [Docker](https://docs.docker.com/get-docker/)


## Configuração do Banco de Dados

O banco de dados utilizado é o PostgreSQL, e para facilitar a execução, você pode configurar o banco utilizando Docker.

### Passo 1: Construir a Imagem Docker

Navegue até o diretório raiz do projeto onde está localizado o `Dockerfile` e execute o seguinte comando para construir a imagem do banco de dados:

```bash
docker build -t postgres-server-img
```

### Passo 2: Executar o container

```bash
docker run -d --name evently-server-container -p 5432:5432 postgres-server-img
```

### Passo 3: Inicializar o container evently

```bash
docker start evently-server-container
```

### Passo 3: Rodar o projeto com a IDE.

Caso tudo dê certo, o sistema pode ser acessado através do localhost:

```bash
http://localhost:8080/
```

### Passo 4: Execute o script SQL em data.sql para login

