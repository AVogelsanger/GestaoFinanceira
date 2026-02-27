# Execução e Ambientes

## Ambiente Local

Banco via Docker:
docker compose up -d postgres

Aplicação:
mvn spring-boot:run -Dspring-boot.run.profiles=local

Porta: 8081

---

## Ambiente Docker Completo

mvn clean package
docker compose up --build

Porta: 8080
