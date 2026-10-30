# 🚀 Desenvolvimento de Microsserviços com Quarkus

Repositório destinado aos estudos e à implementação prática dos conceitos apresentados no curso **Desenvolvimento Web com Quarkus**, com foco na construção de aplicações distribuídas, arquitetura de microsserviços, integração entre sistemas, segurança e observabilidade.

📚 **Curso:** [Desenvolvimento Web com Quarkus – Udemy](https://www.udemy.com/course/des-web-quarkus/)

## 🎯 Objetivo

Consolidar conhecimentos no desenvolvimento de aplicações Java utilizando Quarkus, explorando recursos e ferramentas do ecossistema de microsserviços.

O projeto utiliza como referência um cenário de marketplace de alimentação, envolvendo cadastro de restaurantes e pratos, realização de pedidos e integração entre diferentes serviços.

## 🏗️ Arquitetura do projeto

O cenário estudado contempla três módulos principais:

- **Cadastros:** gerenciamento de restaurantes e pratos.
- **Marketplace:** realização de pedidos pelos clientes.
- **Pedidos:** confirmação, acompanhamento, processamento e entrega dos pedidos.

Os módulos representam diferentes responsabilidades dentro de uma arquitetura distribuída, permitindo explorar comunicação, integração e organização dos microsserviços.

A imagem abaixo representa a arquitetura utilizada como referência para o desenvolvimento e estudo da aplicação.

![Arquitetura do projeto Ifood](image/arquitetura.png)

## 🛠️ Tecnologias e ferramentas

### Linguagem e framework
![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Quarkus](https://img.shields.io/badge/Quarkus-Framework-4695EB?style=for-the-badge&logo=quarkus&logoColor=white)
![Vert.x](https://img.shields.io/badge/Vert.x-Reactive-782A90?style=for-the-badge&logo=eclipsevertdotx&logoColor=white)

### Bancos de dados e persistência
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-4169E1?style=for-the-badge&logo=postgresql&logoColor=white)
![MongoDB](https://img.shields.io/badge/MongoDB-NoSQL-47A248?style=for-the-badge&logo=mongodb&logoColor=white)
![Flyway](https://img.shields.io/badge/Flyway-Migrations-CC0200?style=for-the-badge&logo=flyway&logoColor=white)

### Mensageria e comunicação
![Apache Kafka](https://img.shields.io/badge/Apache%20Kafka-Streaming-231F20?style=for-the-badge&logo=apachekafka&logoColor=white)
![RabbitMQ](https://img.shields.io/badge/RabbitMQ-Messaging-FF6600?style=for-the-badge&logo=rabbitmq&logoColor=white)
![ActiveMQ](https://img.shields.io/badge/ActiveMQ-Messaging-EA1D2C?style=for-the-badge)

### Segurança e integração
![Keycloak](https://img.shields.io/badge/Keycloak-Identity-4D4D4D?style=for-the-badge&logo=keycloak&logoColor=white)
![MapStruct](https://img.shields.io/badge/MapStruct-Mapping-3B82F6?style=for-the-badge)

### Observabilidade e monitoramento
![Prometheus](https://img.shields.io/badge/Prometheus-Monitoring-E6522C?style=for-the-badge&logo=prometheus&logoColor=white)
![Grafana](https://img.shields.io/badge/Grafana-Dashboards-F46800?style=for-the-badge&logo=grafana&logoColor=white)
![OpenTelemetry](https://img.shields.io/badge/OpenTelemetry-Tracing-000000?style=for-the-badge&logo=opentelemetry&logoColor=white)
![Jaeger](https://img.shields.io/badge/Jaeger-Distributed%20Tracing-60D0E4?style=for-the-badge)
![Micrometer](https://img.shields.io/badge/Micrometer-Metrics-6DB33F?style=for-the-badge)

### Infraestrutura e contêineres
![Docker](https://img.shields.io/badge/Docker-Containers-2496ED?style=for-the-badge&logo=docker&logoColor=white)

## 📖 Conteúdos estudados

- Desenvolvimento de APIs REST com Quarkus.
- Construção e organização de microsserviços.
- Persistência de dados com PostgreSQL e MongoDB.
- Versionamento e gerenciamento de banco de dados com Flyway.
- Autenticação e autorização com Keycloak e JWT.
- Comunicação assíncrona e mensageria.
- Integração com Kafka, RabbitMQ e ActiveMQ.
- Programação reativa e ecossistema Vert.x.
- Monitoramento de aplicações com Micrometer e Prometheus.
- Construção de dashboards com Grafana.
- Rastreamento distribuído com OpenTelemetry e Jaeger.
- Containerização de aplicações com Docker.


## 📌 Objetivo final

 Aprofundar os conhecimentos em Java e Quarkus, compreendendo na prática os fundamentos de arquitetura de microsserviços e as ferramentas utilizadas no desenvolvimento de aplicações distribuídas.
