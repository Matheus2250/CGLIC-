package com.cglic.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "diplan")
public class Diplan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "upload_id", nullable = false)
    private DiplanUpload upload;
    
    @Column(name = "numero_contratacao")
    private String numeroContratacao;
    
    @Column(name = "status_contratacao")
    private String statusContratacao;
    
    @Column(name = "situacao_execucao")
    private String situacaoExecucao;
    
    @Column(name = "titulo_contratacao")
    private String tituloContratacao;
    
    @Column(name = "categoria_contratacao")
    private String categoriaContratacao;
    
    @Column(name = "uasg_atual")
    private String uasgAtual;
    
    @Column(name = "valor_total_contratacao")
    private BigDecimal valorTotalContratacao;
    
    @Column(name = "data_estimada_inicio")
    private LocalDate dataEstimadaInicio;
    
    @Column(name = "data_estimada_conclusao")
    private LocalDate dataEstimadaConclusao;
    
    @Column(name = "prazo_estimado_dias")
    private Integer prazoEstimadoDias;
    
    @Column(name = "area_requisitante")
    private String areaRequisitante;
    
    @Column(name = "numero_dfd")
    private String numeroDfd;
    
    @Column(name = "prioridade")
    private String prioridade;
    
    @Column(name = "numero_item_dfd")
    private String numeroItemDfd;
    
    @Column(name = "data_conclusao_dfd")
    private LocalDate dataConclusaoDfd;
    
    @Column(name = "classificacao_contratacao")
    private String classificacaoContratacao;
    
    @Column(name = "codigo_classe_grupo")
    private String codigoClasseGrupo;
    
    @Column(name = "nome_classe_grupo")
    private String nomeClasseGrupo;
    
    @Column(name = "codigo_pdm_material")
    private String codigoPdmMaterial;
    
    @Column(name = "nome_pdm_material")
    private String nomePdmMaterial;
    
    @Column(name = "codigo_material_servico")
    private String codigoMaterialServico;
    
    @Column(name = "descricao_material_servico")
    private String descricaoMaterialServico;
    
    @Column(name = "unidade_fornecimento")
    private String unidadeFornecimento;
    
    @Column(name = "valor_unitario")
    private BigDecimal valorUnitario;
    
    @Column(name = "quantidade")
    private Integer quantidade;
    
    @Column(name = "valor_total")
    private BigDecimal valorTotal;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    // Getters e Setters (por brevidade, vou omitir - mas você precisará implementá-los)
    // ...
}