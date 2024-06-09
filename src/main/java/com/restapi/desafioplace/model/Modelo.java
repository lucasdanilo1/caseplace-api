package com.restapi.desafioplace.model;

import com.restapi.desafioplace.dto.modelo.DadosCadastroModeloDTO;
import com.restapi.desafioplace.dto.modelo.AtualizaDadosModeloDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "modelo")
@Getter
@NoArgsConstructor
public class Modelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private Integer ano;

    @Column(nullable = false)
    private boolean ativo;

    @ManyToOne
    @JoinColumn(name = "marca_id", nullable = false)
    private Marca marca;

    public Modelo(DadosCadastroModeloDTO dados, Marca marca){
        this.nome = dados.nome();
        this.ano = dados.ano();
        this.marca = marca;
        this.ativo = true;
    }

    public void atualizarInformacoes(AtualizaDadosModeloDTO dados) {
        if(dados != null){
            if(dados.nome() != null) this.nome = dados.nome();
            if(dados.ano() != null) this.ano = dados.ano();
        }
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
