# Arquitetura do Sistema

## Modelo Arquitetural

O sistema adota uma arquitetura em camadas (Layered Architecture):

Controller → Service → Repository → Database

---

## Camadas

### 1. Controller Layer
Responsável pela exposição REST da API.

- Validação de entrada
- Conversão DTO → Entidade
- Controle de status HTTP

### 2. Service Layer
Contém as regras de negócio e orquestração transacional.

- Validações de domínio
- Integração com APIs externas
- Controle transacional (@Transactional)

### 3. Repository Layer
Abstração de persistência via Spring Data JPA.

### 4. Domain Model
Entidades JPA com encapsulamento de regras críticas.

Exemplo:
- Conta.debitar()
- Conta.depositar()

---

## Padrões Aplicados

- DTO Pattern
- Repository Pattern
- Service Layer Pattern
- Exception Handling Centralizado
- Transaction Script
- Encapsulamento de regras no domínio
