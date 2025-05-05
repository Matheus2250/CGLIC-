// src/pages/diplan/diplan.js
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import DiplanService from '../../services/diplanService';
import './diplan.css';

const Diplan = () => {
  const navigate = useNavigate();
  const [selectedFile, setSelectedFile] = useState(null);
  const [isUploaded, setIsUploaded] = useState(false);
  const [tableData, setTableData] = useState([]);
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);

  const handleFileChange = (e) => {
    setSelectedFile(e.target.files[0]);
  };

  const handleUpload = async (e) => {
    e.preventDefault();
    if (!selectedFile) {
      setError('Por favor, selecione um arquivo para upload.');
      return;
    }

    setLoading(true);
    setError('');

    try {
      const response = await DiplanService.uploadFile(selectedFile);
      setTableData(response.data);
      setIsUploaded(true);
    } catch (err) {
      setError(err.response?.data?.message || 'Erro ao processar o arquivo.');
    } finally {
      setLoading(false);
    }
  };

  const handleConfirm = async () => {
    setLoading(true);
    setError('');

    try {
      // Aqui você poderia implementar a lógica para enviar os dados ao backend
      // Por exemplo, mapear tableData para o formato esperado pela API
      
      // Exemplo simplificado:
      await DiplanService.saveData({
        // Dados da planilha processados
        data: tableData
      });
      
      alert('Planilha substituída com sucesso!');
      navigate('/home');
    } catch (err) {
      setError(err.response?.data?.message || 'Erro ao salvar os dados.');
    } finally {
      setLoading(false);
    }
  };

  const handleBackToHome = () => {
    navigate('/home');
  };

  return (
    <div className="diplan-container">
      <div className="diplan-header">
        <h1>DIPLAN - Carregamento Diário de Planilha</h1>
        <button onClick={handleBackToHome} className="btn-back">
          Voltar para Home
        </button>
      </div>

      {error && <div className="error-message">{error}</div>}

      {!isUploaded ? (
        <div className="upload-section">
          <h2>Faça o upload da planilha diária</h2>
          <p>Atenção: Esta planilha substituirá a última carregada.</p>
          <form onSubmit={handleUpload}>
            <div className="file-input-container">
              <input 
                type="file" 
                onChange={handleFileChange} 
                accept=".xlsx,.xls,.csv"
                disabled={loading}
              />
            </div>
            <button type="submit" className="btn-upload" disabled={loading}>
              {loading ? 'Processando...' : 'Carregar Planilha'}
            </button>
          </form>
        </div>
      ) : (
        <div className="preview-section">
          <h2>Confirmar substituição da planilha</h2>
          <div className="table-container">
            <table className="data-table">
              <thead>
                <tr>
                  {/* Renderizar cabeçalhos dinâmicos baseados nos dados */}
                  {tableData.length > 0 && Object.keys(tableData[0]).map(key => (
                    <th key={key}>{key}</th>
                  ))}
                </tr>
              </thead>
              <tbody>
                {tableData.map((row, index) => (
                  <tr key={index}>
                    {/* Renderizar células dinâmicas baseadas nos dados */}
                    {Object.values(row).map((value, cellIndex) => (
                      <td key={cellIndex}>{value}</td>
                    ))}
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
          <div className="action-buttons">
            <button onClick={() => setIsUploaded(false)} className="btn-cancel" disabled={loading}>
              Cancelar
            </button>
            <button onClick={handleConfirm} className="btn-confirm" disabled={loading}>
              {loading ? 'Processando...' : 'Confirmar Substituição'}
            </button>
          </div>
        </div>
      )}
    </div>
  );
};

export default Diplan;