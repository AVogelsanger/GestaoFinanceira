# Regras de Negócio

## Cliente
- CPF deve ser único
- CEP obrigatório
- CEP validado via BrasilAPI
- Status padrão: ATIVO

## Conta
- Número da conta único
- Depósito > 0
- Saque permitido apenas se saldo suficiente
- Conta INATIVA não permite movimentação

## Investimento
- Conta deve estar ATIVA
- Valor deve ser > 0
- Cotação USD obrigatória
- Débito automático da conta
