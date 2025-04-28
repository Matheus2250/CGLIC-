import React from 'react';
import { useNavigate } from 'react-router-dom';
import './Home.css';

const Home = () => {
  const navigate = useNavigate();
  
  // Função para lidar com o logout
  const handleLogout = () => {
    // Aqui você implementaria a lógica de logout
    // Por enquanto apenas navegamos para a tela de login
    navigate('/login');
  };

  // Funções para navegar para as respectivas páginas
  const navigateToDipli = () => {
    navigate('/dipli');
  };

  const navigateToDiplan = () => {
    navigate('/diplan');
  };

  return (
    <div className="home-container">
      <header className="home-header">
        <h1>Sistema de Controle de Dados</h1>
        <div className="user-info">
          <span>Bem-vindo, Usuário</span>
          <button onClick={handleLogout} className="btn-logout">Sair</button>
        </div>
      </header>

      <main className="home-content">
        <h2>Selecione um módulo:</h2>
        
        <div className="modules-container">
          <div className="module-card" onClick={navigateToDipli}>
            <div className="module-icon">DIPLI</div>
            <h3>DIPLI</h3>
            <p>Carregamento de planilhas com preenchimento manual</p>
          </div>
          
          <div className="module-card" onClick={navigateToDiplan}>
            <div className="module-icon">DIPLAN</div>
            <h3>DIPLAN</h3>
            <p>Carregamento automático de planilhas diárias</p>
          </div>
        </div>
      </main>

      <footer className="home-footer">
        <p>&copy; 2025 Sistema de Controle de Dados</p>
      </footer>
    </div>
  );
};

export default Home;