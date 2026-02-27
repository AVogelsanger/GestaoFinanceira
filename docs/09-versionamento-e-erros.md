# Tratamento de Erros

Centralizado via:

@RestControllerAdvice

Tipos tratados:
- RecursoNaoEncontradoException → 404
- IllegalArgumentException → 400

Estrutura padrão:

{
"timestamp": "...",
"status": 400,
"mensagem": "Descrição do erro"
}
