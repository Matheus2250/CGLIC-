// src/App.js
import React, { useEffect, useState } from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import Login from './components/auth/Login';
import Register from './components/auth/Register';
import Home from './pages/home/Home';
import Dipli from './pages/dipli/dipli';
import Diplan from './pages/diplan/diplan';
import ProtectedRoute from './components/auth/ProtectedRoute';
import AuthService from './services/authService';
import './App.css';

function App() {
  const [isCheckingAuth, setIsCheckingAuth] = useState(true);

  useEffect(() => {
    // Verificar a validade do token ao carregar o aplicativo
    const verifyAuth = async () => {
      const token = localStorage.getItem('token');
      if (token) {
        try {
          await AuthService.verifyToken();
        } catch (error) {
          console.error("Token inválido:", error);
          AuthService.logout();
        }
      }
      setIsCheckingAuth(false);
    };

    verifyAuth();
  }, []);

  if (isCheckingAuth) {
    // Mostrar indicador de carregamento enquanto verifica a autenticação
    return <div>Carregando...</div>;
  }

  return (
    <Router>
      <div className="App">
        <Routes>
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Register />} />
          <Route path="/home" element={
            <ProtectedRoute>
              <Home />
            </ProtectedRoute>
          } />
          <Route path="/dipli" element={
            <ProtectedRoute>
              <Dipli />
            </ProtectedRoute>
          } />
          <Route path="/diplan" element={
            <ProtectedRoute>
              <Diplan />
            </ProtectedRoute>
          } />
          <Route path="/" element={<Navigate to="/login" />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;