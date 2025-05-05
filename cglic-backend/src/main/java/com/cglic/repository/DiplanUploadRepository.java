package com.cglic.repository;

import com.cglic.model.DiplanUpload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DiplanUploadRepository extends JpaRepository<DiplanUpload, Long> {
    // Encontra todos os uploads que estão ativos
    List<DiplanUpload> findByIsActiveTrue();
    
    // Encontra o upload ativo mais recente
    Optional<DiplanUpload> findFirstByIsActiveTrueOrderByUploadDateDesc();
}