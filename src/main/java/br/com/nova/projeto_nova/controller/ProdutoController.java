package br.com.nova.projeto_nova.controller;

import br.com.nova.projeto_nova.bean.dto.ProdutoRequestDTO;
import br.com.nova.projeto_nova.bean.dto.ProdutoResponseDTO;
import br.com.nova.projeto_nova.bean.entity.Produto;
import br.com.nova.projeto_nova.mapper.GenericMapper;
import br.com.nova.projeto_nova.service.impl.ProdutoServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/produto/")
public class ProdutoController {

    @Autowired
    private ProdutoServiceImpl produtoService;

    @Autowired
    private final GenericMapper mapper;

    @GetMapping("/list")
    public ResponseEntity<List<ProdutoResponseDTO>>listarProdutos() {
        return ResponseEntity.ok(mapper.entidadeParaDTO(produtoService.listarProdutos(), ProdutoResponseDTO.class));
    }

    @PostMapping
    public ResponseEntity<ProdutoResponseDTO>create(@RequestBody ProdutoRequestDTO produtoRequestDTO){
        ProdutoResponseDTO produtoResponseDTO = mapper.entidadeParaDTO(produtoService.createProduto(produtoRequestDTO), ProdutoResponseDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoResponseDTO);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProdutoResponseDTO>update(@RequestBody ProdutoRequestDTO produtoRequestDTO,@PathVariable("id") Long id
    ){
        ProdutoResponseDTO produtoResponseDTO = mapper.entidadeParaDTO(produtoService.updateProduto(id,produtoRequestDTO), ProdutoResponseDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoResponseDTO);
    }



    @GetMapping("/{id}/acharProduto")
    public ResponseEntity<ProdutoResponseDTO>acharId(@PathVariable Long id){
        ProdutoResponseDTO produtoResponseDTO = mapper.entidadeParaDTO((produtoService.getIdProduto(id)), ProdutoResponseDTO.class);
        return ResponseEntity.status(HttpStatus.OK).body(produtoResponseDTO);
    }

    @PutMapping("/{id}/alterarStatus")
    public ResponseEntity<ProdutoResponseDTO> alterarStatus(@PathVariable Long id){
        ProdutoResponseDTO produtoResponseDTO = mapper.entidadeParaDTO(produtoService.alterarStatus(id), ProdutoResponseDTO.class);
        return ResponseEntity.ok(produtoResponseDTO);
    }
    @PutMapping("/{id}/{qtd}/alteraQuantidade")
    public ResponseEntity<ProdutoResponseDTO> alterarQuantidade(@PathVariable Long id,@PathVariable int qtd){
        ProdutoResponseDTO produtoResponseDTO = mapper.entidadeParaDTO(produtoService.trocaQtd(id,qtd), ProdutoResponseDTO.class);
        return ResponseEntity.ok(produtoResponseDTO);
    }
}
