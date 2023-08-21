# Wbdsjunior's Tap On Phone

Tap On Phone é uma forma de receber pagamento através de smartphones que possuam a tecnologia Near Field Communication (NFC), fazendo a leitura de cartões ou outros smarphones por aproximação.

Este projeto visa criar um backend (REST Web API) para simular o cadastro de estabelecimentos, seus smartphones e pagamentos, utilizando, para fins didáticos, arquitetura de microsserviços, com tecnologia Java, Spring Boot, PostgreSQL, Apache Kafka e MongoDB, _tentando_ valer-se de práticas de desenho, como: Orientação a Objetos, Arquitetura Limpa (Arquitetura Hexagonal), Padrões de Projeto (GoF, PoEAA e etc.), Código Limpo, SOLID e etc.

## Arquitetura

![Arquitura.](./taponphone-architecture.png)

## Docker (e docker compose)

### Para contruir o artefatos e contêineres:
```docker compose up -d --build```

### Para acessar a REST Web API
- [markets](http://localhost:8081/swagger-ui/index.html)
- [payments](http://localhost:8082/swagger-ui/index.html)

### Para reiniciar os contêineres:
```docker compose restart```

### Para parar os contêineres:
```docker compose stop```

### Para inciar os contêineres (se estiverem parados):
```docker compose start```

### Para remover os contêineres do seu computador (ou servidor):
```docker compose down --remove-orphans --rmi local -v```
