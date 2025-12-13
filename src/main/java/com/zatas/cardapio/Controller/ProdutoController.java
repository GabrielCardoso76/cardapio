package com.zatas.cardapio.Controller;

import com.zatas.cardapio.entity.Produto;
import com.zatas.cardapio.service.ProdutoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produto")
public class ProdutoController {


    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public List<Produto> getAll(){
        return produtoService.getAll();
    }

    @GetMapping("/getById/{id}")
    public Produto getById(@PathVariable Long id){
        return produtoService.getById(id);
    }

    @PostMapping("/save")
    public Produto save(@RequestBody Produto produto){
        return produtoService.save(produto);
    }

    @PutMapping("/update/{id}")
    public Optional<Produto> update(@PathVariable Long id, @RequestBody Produto produto){
        return produtoService.update(id, produto);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        produtoService.delete(id);
    }
}
