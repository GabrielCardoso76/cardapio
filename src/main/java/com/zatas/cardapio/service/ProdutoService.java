package com.zatas.cardapio.service;

import com.zatas.cardapio.entity.Produto;
import com.zatas.cardapio.repository.ProdutoRepository;

import java.util.List;
import java.util.Optional;

public class ProdutoService {

    private ProdutoRepository produtoRepository;

    public ProdutoService (ProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> getAll(){
        return produtoRepository.findAll();
    }

    public Produto getById(Long id){
        return produtoRepository.getReferenceById(id);
    }

    public Produto save(Produto produto){
        return produtoRepository.save(produto);
    }

    public Optional<Produto> update(Long id, Produto produto){
        return produtoRepository.findById(id).map(produtoDb ->{
            produtoDb.setNome(produto.getNome());
            produtoDb.setQuantidade(produto.getQuantidade());
            produtoDb.setObservacao(produto.getObservacao());
            produtoDb.setPedido(produto.getPedido());
            return produtoRepository.save(produtoDb);
        });
    }

    public void delete(Long id){
        produtoRepository.deleteById(id);
    }
}
