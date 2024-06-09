package com.restapi.desafioplace.repository;

import com.restapi.desafioplace.dto.marca.FiltrosMarcaDTO;
import com.restapi.desafioplace.model.Marca;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MarcaRepository extends JpaRepository<Marca, Long> {

    Marca findByNome(String nome);

    @Query("""
        SELECT m
        FROM Marca m
        WHERE   (:#{#filtro.nome} IS NULL OR m.nome LIKE %:#{#filtro.nome}%) AND
                (:#{#filtro.codDenatran} IS NULL OR m.codDenatran = :#{#filtro.codDenatran}) AND
                (:#{#filtro.ativo} IS NULL OR m.ativo = :#{#filtro.ativo})
       """)
    Page<Marca> findAll(@Param("filtro") FiltrosMarcaDTO filtros, Pageable page);

    @Override
    @Query("SELECT m FROM Marca m ORDER BY m.nome ASC")
    Page<Marca> findAll(Pageable pageable);

    boolean existsByNome(String nome);

    boolean existsByCodDenatran(Integer cod);
}
