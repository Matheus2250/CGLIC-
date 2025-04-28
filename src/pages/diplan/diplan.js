import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './diplan.css';

const Diplan = () => {
  const navigate = useNavigate();
  const [selectedFile, setSelectedFile] = useState(null);
  const [isUploaded, setIsUploaded] = useState(false);
  const [tableData, setTableData] = useState([]);

  const handleFileChange = (e) => {
    setSelectedFile(e.target.files[0]);
  };

  const handleUpload = (e) => {
    e.preventDefault();
    if (!selectedFile) {
      alert('Por favor, selecione um arquivo para upload.');
      return;
    }

    // Aqui você implementará a lógica para processar o arquivo
    console.log('Arquivo selecionado:', selectedFile.name);

    // Simulando dados de uma planilha
    const mockData = [
      { id: 1, campo1: 'Dado 1', campo2: 'Dado 2', campo3: 'Dado 3' },
      { id: 2, campo1: 'Dado 4', campo2: 'Dado 5', campo3: 'Dado 6' },
      { id: 3, campo1: 'Dado 7', campo2: 'Dado 8', campo3: 'Dado 9' },
    ];

    setTableData(mockData);
    setIsUploaded(true);
  };

  const handleConfirm = () => {
    // Aqui você implementará a lógica para confirmar a substituição
    console.log('Substituição confirmada');
    alert('Planilha substituída com sucesso!');
    navigate('/home');
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
              />
            </div>
            <button type="submit" className="btn-upload">
              Carregar Planilha
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
                  <th>ID</th>
                  <th>Campo 1</th>
                  <th>Campo 2</th>
                  <th>Campo 3</th>
                </tr>
              </thead>
              <tbody>
                {tableData.map(row => (
                  <tr key={row.id}>
                    <td>{row.id}</td>
                    <td>{row.campo1}</td>
                    <td>{row.campo2}</td>
                    <td>{row.campo3}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
          <div className="action-buttons">
            <button onClick={() => setIsUploaded(false)} className="btn-cancel">
              Cancelar
            </button>
            <button onClick={handleConfirm} className="btn-confirm">
              Confirmar Substituição
            </button>
          </div>
        </div>
      )}
    </div>
  );
};

export default Diplan;