package org.weslleycabral.cadastro_pessoas.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.weslleycabral.cadastro_pessoas.entities.dto.PessoaDTO;
import org.weslleycabral.cadastro_pessoas.services.PessoaService;

import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {

    @Autowired
    private PessoaService service;

    @GetMapping("/{id}")
    public ResponseEntity<PessoaDTO> getById(@PathVariable Integer id) {
        var pessoa = service.findById(id);
        return ResponseEntity.ok().body(pessoa);
    }

    @GetMapping()
    public ResponseEntity<List<PessoaDTO>> getAll() {
        var listaPessoas = service.findAll();
        return ResponseEntity.ok().body(listaPessoas);
    }

    @PostMapping
    public ResponseEntity<PessoaDTO> save(@RequestBody PessoaDTO pessoaDTO) {
        return ResponseEntity.ok().body(service.save(pessoaDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PessoaDTO> update(@RequestBody PessoaDTO pessoaDTO) {
        var pessoaUpdate = service.update(pessoaDTO);
        return ResponseEntity.ok(pessoaUpdate);
    }
}
