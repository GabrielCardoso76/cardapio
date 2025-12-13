package com.zatas.cardapio.repository;

import com.zatas.cardapio.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {}
