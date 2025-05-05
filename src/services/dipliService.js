// src/services/dipliService.js
import api from './api';

const DipliService = {
  uploadFile: async (file) => {
    const formData = new FormData();
    formData.append('file', file);
    return api.post('/files/preview-excel', formData, {
      headers: {
        'Content-Type': 'multipart/form-data'
      }
    });
  },

  saveData: async (dipliData) => {
    return api.post('/dipli', dipliData);
  },

  getAllData: async () => {
    return api.get('/dipli');
  },

  getDataById: async (id) => {
    return api.get(`/dipli/${id}`);
  },

  updateData: async (id, dipliData) => {
    return api.put(`/dipli/${id}`, dipliData);
  },

  deleteData: async (id) => {
    return api.delete(`/dipli/${id}`);
  }
};

export default DipliService;