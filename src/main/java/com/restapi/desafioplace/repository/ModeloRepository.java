package com.restapi.desafioplace.repository;

import com.restapi.desafioplace.dto.modelo.FiltrosModeloDTO;
import com.restapi.desafioplace.model.Modelo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ModeloRepository extends JpaRepository<Modelo, Long> {

    @Query("""
        SELECT m
        FROM Modelo m
        WHERE   (:#{#filtro.nome} IS NULL OR m.nome LIKE %:#{#filtro.nome}%) AND
                (:#{#filtro.ano} IS NULL OR m.ano = :#{#filtro.ano}) AND
                (:#{#filtro.ativo} IS NULL OR m.ativo = :#{#filtro.ativo}) AND
                (:#{#filtro.marcaId} IS NULL OR m.marca.id = :#{#filtro.marcaId})

       """)
    Page<Modelo> findAll(@Param("filtro") FiltrosModeloDTO filtros, Pageable page);

    @Override
    @Query("SELECT m FROM Modelo m ORDER BY m.nome ASC")
    Page<Modelo> findAll(Pageable pageable);

    Modelo findByNome(String nome);

    boolean existsByNome(String nome);
}
