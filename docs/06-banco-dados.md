# Banco de Dados

## Tecnologia
- PostgreSQL 15
- Flyway para versionamento

## Estratégia

- ddl-auto=validate
- UUID como chave primária
- Integridade referencial via JPA

Relacionamentos:

- clientes 1:N contas
- contas 1:N investimentos
- produtos 1:N investimentos
