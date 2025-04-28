import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './dipli.css';

const Dipli = () => {
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
      { id: 1, campo1: '', campo2: '', campo3: '' },
      { id: 2, campo1: '', campo2: '', campo3: '' },
      { id: 3, campo1: '', campo2: '', campo3: '' },
    ];

    setTableData(mockData);
    setIsUploaded(true);
  };

  const handleCellChange = (id, field, value) => {
    const updatedData = tableData.map(row => 
      row.id === id ? { ...row, [field]: value } : row
    );
    setTableData(updatedData);
  };

  const handleSave = () => {
    // Aqui você implementará a lógica para salvar os dados
    console.log('Dados a serem salvos:', tableData);
    alert('Dados salvos com sucesso!');
  };

  const handleBackToHome = () => {
    navigate('/home');
  };

  return (
    <div className="dipli-container">
      <div className="dipli-header">
        <h1>DIPLI - Carregamento com Preenchimento Manual</h1>
        <button onClick={handleBackToHome} className="btn-back">
          Voltar para Home
        </button>
      </div>

      {!isUploaded ? (
        <div className="upload-section">
          <h2>Faça o upload da planilha</h2>
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
        <div className="edit-section">
          <h2>Edite os dados da planilha</h2>
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
                    <td>
                      <input
                        type="text"
                        value={row.campo1}
                        onChange={(e) => handleCellChange(row.id, 'campo1', e.target.value)}
                      />
                    </td>
                    <td>
                      <input
                        type="text"
                        value={row.campo2}
                        onChange={(e) => handleCellChange(row.id, 'campo2', e.target.value)}
                      />
                    </td>
                    <td>
                      <input
                        type="text"
                        value={row.campo3}
                        onChange={(e) => handleCellChange(row.id, 'campo3', e.target.value)}
                      />
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
          <div className="action-buttons">
            <button onClick={() => setIsUploaded(false)} className="btn-cancel">
              Cancelar
            </button>
            <button onClick={handleSave} className="btn-save">
              Salvar Dados
            </button>
          </div>
        </div>
      )}
    </div>
  );
};

export default Dipli;