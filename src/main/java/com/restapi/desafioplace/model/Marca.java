package com.restapi.desafioplace.model;

import com.restapi.desafioplace.dto.marca.AtualizaDadosMarcaDTO;
import com.restapi.desafioplace.dto.marca.DadosCadastroMarcaDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "marca")
@Getter
@NoArgsConstructor
public class Marca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(name = "cod_denatran", nullable = false)
    private Integer codDenatran;

    @Column(nullable = false)
    private boolean ativo;

    @OneToMany(mappedBy = "marca", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Modelo> modelos = new ArrayList<>();

    public Marca(DadosCadastroMarcaDTO dados){
        this.nome = dados.nome();
        this.codDenatran = dados.codDenatran();
        this.ativo = true;
    }

    public void atualizarInformacoes(AtualizaDadosMarcaDTO dados){
        if(dados != null){
            if(dados.nome() != null) this.nome = dados.nome();
            if(dados.codDenatran() != null) this.codDenatran = dados.codDenatran();
        }
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
