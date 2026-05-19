package br.com.fh.comprasfh.produtos.repositories;

import br.com.fh.comprasfh.produtos.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutosRepository extends JpaRepository<Produto, Long> {

}
