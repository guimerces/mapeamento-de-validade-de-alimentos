package com.despensa.service;

import com.despensa.dto.request.PessoaRequestDTO;
import com.despensa.dto.response.PessoaResponseDTO;
import com.despensa.dto.request.LoginRequestDTO;

import com.despensa.mapper.PessoaMapper;

import com.despensa.model.Pessoa;

import com.despensa.repository.PessoaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public PessoaResponseDTO cadastrar(PessoaRequestDTO dto) {
        Pessoa novaPessoa = new Pessoa(
            dto.getLogin(),
            dto.getSenha(),
            dto.getChatIdTelegram()
        );
        Pessoa pessoaSalva = pessoaRepository.save(novaPessoa);
        return PessoaMapper.transformarParaDTO(pessoaSalva);
    }

    public PessoaResponseDTO buscarPorId(Long id) {
        Pessoa pessoa = pessoaRepository.findById(id).orElse(null);
        return PessoaMapper.transformarParaDTO(pessoa);
    }

    // para o bot telegram
    public PessoaResponseDTO buscarPorLogin(String login) {
        Pessoa pessoa = pessoaRepository.findByLogin(login).orElse(null);
        return PessoaMapper.transformarParaDTO(pessoa);
    }

    public PessoaResponseDTO autenticar(LoginRequestDTO dto) {
        Pessoa pessoa = pessoaRepository.findByLogin(dto.getLogin())
            .orElseThrow(() -> new RuntimeException("Usuário ou senha inválidos"));

        if (!pessoa.getSenha().equals(dto.getSenha())) {
            throw new RuntimeException("Usuário ou senha inválidos");
        }

        return PessoaMapper.transformarParaDTO(pessoa);
}

}