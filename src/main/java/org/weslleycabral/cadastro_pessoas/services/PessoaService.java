package org.weslleycabral.cadastro_pessoas.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.weslleycabral.cadastro_pessoas.entities.Pessoa;
import org.weslleycabral.cadastro_pessoas.entities.dto.PessoaDTO;
import org.weslleycabral.cadastro_pessoas.repositories.PessoaRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository repository;

    public PessoaDTO findById(Integer id) {
        var pessoa = repository.findById(id).get();
        var pessoaDTO = new PessoaDTO(pessoa.getId(), pessoa.getNome(), pessoa.getDataNascimento());
        return pessoaDTO;
    }

    public List<PessoaDTO> findAll() {
        var listPessoas = repository.findAll();
        return listPessoas.stream()
                .map(pessoa -> {
                    var dto = new PessoaDTO(pessoa.getId(), pessoa.getNome(), pessoa.getDataNascimento());
                    return dto;
                }).collect(Collectors.toList());
    }

    public PessoaDTO save(PessoaDTO pessoaDTO) {
        var pessoa = new Pessoa(null, pessoaDTO.name(), pessoaDTO.dataNascimento());
        repository.save(pessoa);
        return pessoaDTO;
    }

    public PessoaDTO update(PessoaDTO pessoaDTO){
        var pessoa = repository.findById(pessoaDTO.id());

        if(pessoa.isPresent()){
            var pessoaUpdate = pessoa.get();
            pessoaUpdate.setNome(pessoaDTO.name());
            pessoaUpdate.setDataNascimento(pessoaDTO.dataNascimento());
            repository.save(pessoaUpdate);

            return pessoaDTO;
        } else {
            throw new EntityNotFoundException("Pessoa não encontrada com o ID: " + pessoaDTO.id());
        }
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
