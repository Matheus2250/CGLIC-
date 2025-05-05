// src/services/authService.js
import axios from 'axios';

const API_URL = 'http://localhost:8080/api/auth/';

const AuthService = {
  login: async (username, password) => {
    try {
      const response = await axios.post(API_URL + 'login', { username, password });
      if (response.data.token) {
        localStorage.setItem('token', response.data.token);
        localStorage.setItem('user', JSON.stringify(response.data.user));
      }
      return response.data;
    } catch (error) {
      throw error;
    }
  },

  logout: () => {
    localStorage.removeItem('token');
    localStorage.removeItem('user');
  },

  register: async (userData) => {
    try {
      return await axios.post(API_URL + 'register', userData);
    } catch (error) {
      throw error;
    }
  },

  getCurrentUser: () => {
    const userStr = localStorage.getItem('user');
    if (userStr) return JSON.parse(userStr);
    return null;
  },

  verifyToken: async () => {
    const token = localStorage.getItem('token');
    if (!token) return false;
    
    try {
      await axios.get(API_URL + 'verify', {
        headers: { Authorization: `Bearer ${token}` }
      });
      return true;
    } catch (error) {
      return false;
    }
  }
};

export default AuthService;