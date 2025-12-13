package com.zatas.cardapio.Controller;

import com.zatas.cardapio.entity.Pedido;
import com.zatas.cardapio.service.PedidoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public List<Pedido> getAll(){
        return pedidoService.getAll();
    }

    @GetMapping("/getById/{id}")
    public Optional<Pedido> getById(@PathVariable Long id){
        return pedidoService.getById(id);
    }

    @PostMapping("/save")
    public Pedido save(@RequestBody Pedido pedido){
        return pedidoService.save(pedido);
    }

    @PutMapping("/update/{id}")
    public Optional<Pedido> update(@PathVariable Long id, @RequestBody Pedido pedido){
        return pedidoService.update(id, pedido);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        pedidoService.delete(id);
    }
}
