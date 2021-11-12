package br.com.springboot.controller;

import java.util.List;
import java.util.Optional;

import org.hibernate.boot.model.source.internal.hbm.RootEntitySourceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.beans.Loja;
import br.com.springboot.dao.LojaDAO;

@RestController
public class LojaController {

	@Autowired
	private LojaDAO dao;

	@GetMapping("/produtos")
	public ResponseEntity<List<Loja>> getAll() {
		List<Loja> lojinha = (List<Loja>) dao.findAll();
		if (lojinha.size() == 0)
			return ResponseEntity.status(404).build();
		else
			return ResponseEntity.ok(lojinha);
	}

	@GetMapping("/produtos/{id}")
	public ResponseEntity<Loja> getUsuario(@PathVariable int id){
		
		Loja resposta = dao.findById(id).orElse(null);
		
		if(resposta == null) {
			return ResponseEntity.status(404).build();
		}
		
		return ResponseEntity.ok(resposta);
		
	}
	
	@PostMapping("/produtos/login")
	public ResponseEntity<Loja> login(@RequestBody Loja objeto){
		
		Loja resposta = dao.findByNomeAndCodigo(objeto.getNome(), objeto.getCodigo());
		
		if(resposta == null) {
			return ResponseEntity.status(404).build();
		}
		
		return ResponseEntity.ok(resposta);
		
	}
	
	@PostMapping("/novoprodutos")
	public ResponseEntity<Loja> addProdutos(@RequestBody Loja objeto){
		try {
			dao.save(objeto);
			return ResponseEntity.ok(objeto);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(404).build();
			// TODO: handle exception
		}
	}
}
