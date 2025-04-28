<<<<<<< HEAD
# Getting Started with Create React App

This project was bootstrapped with [Create React App](https://github.com/facebook/create-react-app).

## Available Scripts

In the project directory, you can run:

### `npm start`

Runs the app in the development mode.\
Open [http://localhost:3000](http://localhost:3000) to view it in your browser.

The page will reload when you make changes.\
You may also see any lint errors in the console.

### `npm test`

Launches the test runner in the interactive watch mode.\
See the section about [running tests](https://facebook.github.io/create-react-app/docs/running-tests) for more information.

### `npm run build`

Builds the app for production to the `build` folder.\
It correctly bundles React in production mode and optimizes the build for the best performance.

The build is minified and the filenames include the hashes.\
Your app is ready to be deployed!

See the section about [deployment](https://facebook.github.io/create-react-app/docs/deployment) for more information.

### `npm run eject`

**Note: this is a one-way operation. Once you `eject`, you can't go back!**

If you aren't satisfied with the build tool and configuration choices, you can `eject` at any time. This command will remove the single build dependency from your project.

Instead, it will copy all the configuration files and the transitive dependencies (webpack, Babel, ESLint, etc) right into your project so you have full control over them. All of the commands except `eject` will still work, but they will point to the copied scripts so you can tweak them. At this point you're on your own.

You don't have to ever use `eject`. The curated feature set is suitable for small and middle deployments, and you shouldn't feel obligated to use this feature. However we understand that this tool wouldn't be useful if you couldn't customize it when you are ready for it.

## Learn More

You can learn more in the [Create React App documentation](https://facebook.github.io/create-react-app/docs/getting-started).

To learn React, check out the [React documentation](https://reactjs.org/).

### Code Splitting

This section has moved here: [https://facebook.github.io/create-react-app/docs/code-splitting](https://facebook.github.io/create-react-app/docs/code-splitting)

### Analyzing the Bundle Size

This section has moved here: [https://facebook.github.io/create-react-app/docs/analyzing-the-bundle-size](https://facebook.github.io/create-react-app/docs/analyzing-the-bundle-size)

### Making a Progressive Web App

This section has moved here: [https://facebook.github.io/create-react-app/docs/making-a-progressive-web-app](https://facebook.github.io/create-react-app/docs/making-a-progressive-web-app)

### Advanced Configuration

This section has moved here: [https://facebook.github.io/create-react-app/docs/advanced-configuration](https://facebook.github.io/create-react-app/docs/advanced-configuration)

### Deployment

This section has moved here: [https://facebook.github.io/create-react-app/docs/deployment](https://facebook.github.io/create-react-app/docs/deployment)

### `npm run build` fails to minify

This section has moved here: [https://facebook.github.io/create-react-app/docs/troubleshooting#npm-run-build-fails-to-minify](https://facebook.github.io/create-react-app/docs/troubleshooting#npm-run-build-fails-to-minify)
=======
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
>>>>>>> 3f6ea15eacacbca14c2a8b2fa530937c5c55f62f
