package com.zatas.cardapio.service;

import com.zatas.cardapio.entity.Pedido;
import com.zatas.cardapio.repository.PedidoRepository;

import java.util.List;
import java.util.Optional;

public class PedidoService {

    private PedidoRepository pedidoRepository;

    public PedidoService (PedidoRepository pedidoRepository){
        this.pedidoRepository = pedidoRepository;
    }

    public List<Pedido> getAll(){
        return pedidoRepository.findAll();
    }

    public Optional<Pedido> getById(Long id){
        return pedidoRepository.findById(id);
    }

    public Pedido save(Pedido pedido){
        return pedidoRepository.save(pedido);
    }

    public Optional<Pedido> update(Long id, Pedido pedido){
        return pedidoRepository.findById(id).map(pedidoDb ->{
           pedidoDb.setUser(pedido.getUser());
           pedidoDb.setProdutos(pedido.getProdutos());
           return pedidoRepository.save(pedidoDb);
        });
    }

    public void delete(Long id){
        pedidoRepository.deleteById(id);
    }
}
