# crud_product Project

Este projeto usa Quarkus, framework subatomico Java.

Se você quer aprender mais sobre Quarkus, visite o website: https://quarkus.io/ .

A idea central e desenvolver um CRUD de baixa complexidade capaz de realizar o cadastro de um produto, lista-lo, alem
de outras operacoes basicas.

## Instalar docker, docker-compose e minikube em seu ambiente local

## Iniciando o banco de dados (Dentro do diretorio src/main/docker)

docker-compose up --build

## Empacotando e executando

```shell script
./gradlew build
```

## Executando em dev mode

```shell script
./gradlew quarkusDev
```

## Acesso ao console Quarkus

## Acesso a aplicação



## Guias relacionados

- Hibernate ORM ([guide](https://quarkus.io/guides/hibernate-orm)): Define your persistent model with Hibernate ORM and JPA
- YAML Configuration ([guide](https://quarkus.io/guides/config#yaml)): Use YAML to configure your Quarkus application
- Camel Gson ([guide](https://camel.apache.org/camel-quarkus/latest/reference/extensions/gson.html)): Marshal POJOs to JSON and back using Gson
