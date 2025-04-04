package com.gestao.service;



import com.gestao.domain.Empresa;

import com.gestao.domain.Fornecedor;
import com.gestao.dto.EmpresaDTO;
import com.gestao.infra.exceptions.FornecedorNaoEncontradoException;
import com.gestao.infra.exceptions.RecursoNaoEncontradoException;
import com.gestao.infra.exceptions.RegraNegocioException;
import com.gestao.repository.EmpresaRepository;


import com.gestao.repository.FornecedorRepository;
import com.gestao.utils.CepUtils;
import com.gestao.utils.FornecedorValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmpresaService {

  @Autowired
  private EmpresaRepository empresaRepository;
@Autowired
private FornecedorRepository fornecedorRepository;

    @Transactional
    public Empresa salvar(EmpresaDTO dto ) {
        List<Fornecedor> fornecedores = fornecedorRepository.findAllById(dto.getFornecedorIds());

        // Verificar se todos os fornecedores existem
        if (fornecedores.size() != dto.getFornecedorIds().size()) {
            List<Long> idsNaoEncontrados = dto.getFornecedorIds().stream()
                    .filter(id -> fornecedores.stream().noneMatch(f -> f.getId().equals(id)))
                    .collect(Collectors.toList());

            throw new FornecedorNaoEncontradoException("Erro: Os seguintes fornecedores não foram encontrados: " + idsNaoEncontrados);
        }
              String cnpj = dto.getCnpj().replaceAll("[^0-9]", ""); // Remove caracteres não numéricos
        if (cnpj.length() < 14) {
            throw new RegraNegocioException("CNPJ deve ter 14 dígitos!");
        }
        String cep = dto.getCep().replaceAll("[^0-9]", ""); // Remove caracteres não numéricos
        if (cep.length() < 8) {
            throw new RegraNegocioException("Cep deve ter 8 dígitos!");
        }
        if (empresaRepository.existsByCnpj(dto.getCnpj())) {
            throw new RegraNegocioException("CNPJ já cadastrado.");
        }
        // Buscar estado com base no CEP
        String estado = CepUtils.buscarUfPorCep(cep);
        if (estado == null || estado.isEmpty()) {
            throw new RegraNegocioException("Não foi possível determinar o estado a partir do CEP informado.");
        }
        Empresa empresa= new Empresa();
        empresa.setNomeFantasia(dto.getNomeFantasia());
        empresa.setCnpj(dto.getCnpj());
        empresa.setCep(cep);
        empresa.setEstado(estado);

// Valida aqui também
        FornecedorValidator.validarFornecedorMenorDeIdadeComEmpresaPR(empresa, fornecedores);


        empresa.getFornecedores().addAll(fornecedores);
        return empresaRepository.save(empresa);
    }



  // Listar todas
  public List<Empresa> filtrar(String nomeFantasia, String cnpj) {
      return empresaRepository.filtrar(nomeFantasia, cnpj);

  }
  // Buscar por ID
  public Empresa buscarPorId(Long id) {
    return empresaRepository.findById(id)
            .orElseThrow(() -> new RecursoNaoEncontradoException("Empresa não encontrada!"));
  }

    public Empresa atualizarEmpresa(Long empresaId, EmpresaDTO dto) {
        Empresa empresa = empresaRepository.findById(empresaId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Empresa não encontrada com o ID: " + empresaId));
        // Buscar fornecedores pelo ID
        List<Fornecedor> fornecedores = fornecedorRepository.findAllById(dto.getFornecedorIds());

        // Verificar se todos os fornecedores existem
        if (fornecedores.size() != dto.getFornecedorIds().size()) {
            List<Long> idsNaoEncontrados = dto.getFornecedorIds().stream()
                    .filter(id -> fornecedores.stream().noneMatch(f -> f.getId().equals(id)))
                    .collect(Collectors.toList());

            throw new FornecedorNaoEncontradoException("Erro: Os seguintes fornecedores não foram encontrados: " + idsNaoEncontrados);
        }

        // Atualizar os dados da empresa
        empresa.setCnpj(dto.getCnpj());
        empresa.setNomeFantasia(dto.getNomeFantasia());
        empresa.setCep(dto.getCep());
        empresa.getFornecedores().addAll(fornecedores);
        empresa.setFornecedores(fornecedores);
        return empresaRepository.save(empresa);
    }

  // Deletar Empresa
  public void deletarEmpresa(Long id) {
    Empresa empresa = buscarPorId(id);
    empresaRepository.delete(empresa);
  }



}