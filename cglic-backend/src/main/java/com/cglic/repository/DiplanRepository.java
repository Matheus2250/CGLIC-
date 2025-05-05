package com.cglic.repository;

import com.cglic.model.Diplan;
import com.cglic.model.DiplanUpload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiplanRepository extends JpaRepository<Diplan, Long> {
    // Encontra todos os registros associados a um upload específico
    List<Diplan> findByUpload(DiplanUpload upload);
}