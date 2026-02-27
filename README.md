📌 Gestão Financeira API
API REST desenvolvida em Spring Boot 3 (Java 21) para gerenciamento de:

Clientes
Contas bancárias
Investimentos
Produtos financeiros
Importação e exportação de dados
Integrações externas (BrasilAPI e MockBank)
Projeto estruturado seguindo boas práticas de arquitetura em camadas e princípios de domínio.

🚀 Tecnologias Utilizadas
🔹 Backend
Java 21
Spring Boot 3.2.5
Spring Web (REST)
Spring Data JPA
Spring Validation
Spring WebFlux (WebClient)
Flyway (versionamento de banco)
Lombok
Swagger / OpenAPI (springdoc)
🔹 Banco de Dados
PostgreSQL 15
Docker
🔹 Integrações
BrasilAPI (consulta CEP e câmbio)
MockBank API (saldo externo)
🔹 Ferramentas
Maven
Docker Compose
Apache POI (importação/exportação Excel)
🏗 Arquitetura do Projeto
A aplicação segue arquitetura em camadas:

text

Controller → Service → Repository → Database
📂 Estrutura de Pacotes
text

cliente/
conta/
investimento/
produtoFinanceiro/
controller/
infra/
config/
exportacao/
importacao/
✅ Responsabilidades
Camada	Responsabilidade
Controller	Exposição da API REST
Service	Regras de negócio
Repository	Acesso a dados
Entity	Modelagem de domínio
Infra	Tratamento global de erros e integrações externas
📌 Funcionalidades Implementadas
👤 Clientes
Criar cliente
Atualizar cliente
Buscar por ID
Listar com paginação
Filtrar por status
Inativar cliente
Exportar clientes para Excel
Importar clientes via planilha
🏦 Contas
Criar conta
Atualizar conta
Buscar por ID
Listar por cliente
Filtrar por status
Inativar conta
✅ Depositar valor
✅ Sacar valor
Consultar saldo externo (MockBank)
💰 Investimentos
Criar investimento
Conversão automática para USD
Consulta de cotação via BrasilAPI
Débito automático da conta
📦 Produtos Financeiros
Criar produto financeiro
🔐 Tratamento Global de Erros
Implementado via:

text

@RestControllerAdvice
Retorno padronizado:

json

{
  "timestamp": "2026-02-27T10:00:00",
  "status": 400,
  "mensagem": "Mensagem de erro"
}
📄 Documentação da API (Swagger)
Disponível em:

Rodando local:
text

http://localhost:8081/swagger-ui.html
Rodando via Docker:
text

http://localhost:8080/swagger-ui.html
🐳 Como Executar o Projeto
✅ Opção 1 — Rodar Localmente (Recomendado para Desenvolvimento)
1️⃣ Subir PostgreSQL no Docker
bash

docker compose up -d postgres
2️⃣ Rodar aplicação
bash

./mvnw spring-boot:run "-Dspring-boot.run.profiles=local"
Aplicação ficará disponível em:

text

http://localhost:8081
✅ Opção 2 — Rodar Tudo via Docker
1️⃣ Gerar o JAR
bash

./mvnw clean package -DskipTests
2️⃣ Subir containers
bash

docker compose up --build -d
Aplicação disponível em:

text

http://localhost:8080
🗄 Banco de Dados
Configuração Docker:

Database: gestaofinanceira
User: postgres
Password: postgres
Port: 5432
Flyway realiza versionamento automático das migrations.

📊 Paginação
Endpoints de listagem utilizam Pageable.

Exemplo:

text

GET /clientes?page=0&size=10&sort=nome,asc
🌍 Integrações Externas
📌 BrasilAPI
Consulta CEP
Consulta cotação USD
📌 MockBank API
Consulta saldo externo da conta
Base URL:
text

http://localhost:3000
💡 Boas Práticas Aplicadas
✅ Separação clara de responsabilidades
✅ Encapsulamento de regras de negócio nas entidades
✅ Uso de BigDecimal para valores monetários
✅ Controle transacional com @Transactional
✅ Versionamento de banco com Flyway
✅ DTOs para entrada e saída
✅ Tratamento global de exceções
✅ Integração reativa com WebClient
🔄 Fluxo de Desenvolvimento Recomendado
Durante desenvolvimento:

text

docker compose up -d postgres
./mvnw spring-boot:run "-Dspring-boot.run.profiles=local"
Após alterações:

text

./mvnw clean package -DskipTests
docker compose up --build -d
🚀 Melhorias Futuras
Autenticação JWT
Controle de concorrência com @Version (Optimistic Locking)
Histórico de movimentações bancárias
Testes unitários com Mockito
Testes de integração com Testcontainers
Pipeline CI/CD
Profile específico para produção
Rate limiting
Monitoramento com Spring Actuator
👨‍💻 Autor
Projeto desenvolvido para fins de estudo e evolução técnica em arquitetura backend com Spring Boot.

📌 Considerações Finais
Este projeto simula um núcleo básico de sistema bancário, aplicando:

Modelagem de domínio
Controle transacional
Integrações externas
Persistência relacional
Paginação
Versionamento de banco
A estrutura foi organizada para permitir evolução para um cenário corporativo real.
