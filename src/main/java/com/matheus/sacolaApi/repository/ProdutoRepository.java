package com.matheus.sacolaApi.repository;


import com.matheus.sacolaApi.model.Produto;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>  {
}
