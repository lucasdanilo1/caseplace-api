package com.restapi.desafioplace.controller;

import com.restapi.desafioplace.dto.marca.*;
import com.restapi.desafioplace.dto.marca.*;
import com.restapi.desafioplace.service.MarcaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("marca")
public class MarcaController {

    @Autowired
    private MarcaService service;

    @Transactional
    @PostMapping("cadastrar")
    public ResponseEntity<DadosMarcaDetalhadaDTO> cadastrar(@Valid @RequestBody DadosCadastroMarcaDTO dados, UriComponentsBuilder uriBuilder){
        var marca = service.cadastrar(dados);
        return ResponseEntity.created(
                uriBuilder.path("marca/{id}")
                        .buildAndExpand(marca.getId())
                        .toUri()
        ).body(new DadosMarcaDetalhadaDTO(marca));
    }

    @GetMapping("{id}")
    public ResponseEntity<DadosMarcaDetalhadaDTO> detalhar(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.detalhar(id));
    }

    @GetMapping("lista")
    public ResponseEntity<Page<DadosListagemMarcaDTO>> listar(@PageableDefault(size = 30) Pageable page) {
        return ResponseEntity.ok().body(service.listar(page));
    }

    @Transactional
    @PostMapping("lista")
    public ResponseEntity<Page<DadosListagemMarcaDTO>> listarFiltradas(@RequestBody FiltrosMarcaDTO filtros, Pageable page) {
        return ResponseEntity.ok().body(service.listarFiltradas(filtros, page));
    }

    @Transactional
    @PutMapping("atualizar/{id}")
    public ResponseEntity<DadosMarcaDetalhadaDTO> atualizar(@PathVariable Long id, @Valid @RequestBody AtualizaDadosMarcaDTO dados){
        return ResponseEntity.ok().body(service.atualizar(id, dados));
    }

    @Transactional
    @DeleteMapping("inativar/{id}")
    public ResponseEntity<DadosMarcaDetalhadaDTO> inativar(@PathVariable Long id){
        return ResponseEntity.ok().body(service.inativar(id));
    }

    @Transactional
    @PutMapping("ativar/{id}")
    public ResponseEntity<DadosMarcaDetalhadaDTO> ativar(@PathVariable Long id){
        return ResponseEntity.ok().body(service.ativar(id));
    }

}
