// src/services/diplanService.js
import api from './api';

const DiplanService = {
  uploadFile: async (file) => {
    const formData = new FormData();
    formData.append('file', file);
    return api.post('/diplan/upload', formData, {
      headers: {
        'Content-Type': 'multipart/form-data'
      }
    });
  },

  saveData: async (diplanData) => {
    return api.post('/diplan', diplanData);
  },

  getAllData: async (page = 0, size = 10) => {
    return api.get(`/diplan?page=${page}&size=${size}`);
  },

  getDataById: async (id) => {
    return api.get(`/diplan/${id}`);
  },

  updateData: async (id, diplanData) => {
    return api.put(`/diplan/${id}`, diplanData);
  },

  deleteData: async (id) => {
    return api.delete(`/diplan/${id}`);
  },

  getDataByAno: async (ano) => {
    return api.get(`/diplan/ano/${ano}`);
  },

  getDataByUnidade: async (unidade) => {
    return api.get(`/diplan/unidade/${unidade}`);
  },

  exportToExcel: async (ano, unidade) => {
    let url = '/diplan/export';
    const params = new URLSearchParams();
    
    if (ano) params.append('ano', ano);
    if (unidade) params.append('unidade', unidade);
    
    if (params.toString()) {
      url += `?${params.toString()}`;
    }
    
    return api.get(url, { responseType: 'blob' });
  }
};

export default DiplanService;