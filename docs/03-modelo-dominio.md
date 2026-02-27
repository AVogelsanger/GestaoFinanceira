# Modelo de Domínio

## Entidades Principais

### Cliente
- id (UUID)
- cpf (único)
- status (ATIVO/INATIVO)
- Endereço via BrasilAPI

Relacionamento:
Cliente 1:N Conta

---

### Conta
- saldoAtual (BigDecimal)
- status (ATIVA/INATIVA)
- numeroConta (único)

Relacionamento:
Conta N:1 Cliente
Conta 1:N Investimento

Regras:
- Não permite saque se saldo insuficiente
- Não permite movimentação se INATIVA

---

### Investimento
- valorAplicado
- valorUSD
- cotacaoUSD
- status

Fluxo:
1. Consulta cotação USD via BrasilAPI
2. Converte valor
3. Debita conta
4. Persiste investimento

---

### ProdutoFinanceiro
- Tipo (RENDA_FIXA, CDB, FUNDO, etc)
- Taxa de rendimento
