# Sistema de Controle de Dados

Este é um sistema para controle de dados que permite carregar e processar planilhas Excel. O sistema tem duas modalidades principais:

- **DIPLI**: Para carregamento de planilhas com preenchimento manual de dados
- **DIPLAN**: Para carregamento diário de planilhas que substituem a anterior

## Tecnologias Utilizadas

### Frontend
- React

### Backend (a ser implementado)
- Java Spring Boot ou .NET

### Processamento de Planilhas
- Apache POI ou bibliotecas .NET

### Banco de Dados
- PostgreSQL

## Estrutura do Projeto

```
sistema-controle-dados/
├── public/               # Arquivos públicos do React
├── src/                  # Código fonte
│   ├── components/       # Componentes React
│   │   ├── Login.js      # Tela de login
│   │   ├── Register.js   # Tela de registro
│   │   ├── Home.js       # Tela principal (escolha DIPLI/DIPLAN)
│   │   ├── Dipli.js      # Componente DIPLI
│   │   └── Diplan.js     # Componente DIPLAN
│   ├── App.js            # Componente principal e rotas
│   └── App.css           # Estilos globais
└── package.json          # Dependências do projeto
```

## Como Executar

1. Clone o repositório
   ```
   git clone https://github.com/seu-usuario/sistema-controle-dados.git
   cd sistema-controle-dados
   ```

2. Instale as dependências
   ```
   npm install
   ```

3. Execute o projeto
   ```
   npm start
   ```

4. Acesse no navegador
   ```
   http://localhost:3000
   ```

## Funcionalidades

- [x] Tela de Login
- [x] Tela de Registro
- [x] Tela Home para escolha entre DIPLI e DIPLAN
- [x] Upload de planilhas Excel
- [x] Visualização dos dados em tabela
- [x] Edição manual de dados (DIPLI)
- [x] Substituição automática de planilha (DIPLAN)
- [ ] Integração com backend
- [ ] Persistência dos dados no banco
