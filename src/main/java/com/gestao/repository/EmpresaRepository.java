package com.gestao.repository;

import com.gestao.domain.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;



public interface EmpresaRepository extends JpaRepository<Empresa,Long>{

  boolean existsByCnpj(String cnpj);



}
