package br.com.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.beans.Loja;
import br.com.springboot.dao.LojaDAO;

@RestController
public class LojaController {

	@Autowired
	private LojaDAO dao;
	
	@GetMapping("/produtos")
    public ResponseEntity<List<Loja>> getAll(){
    	List<Loja> lojinha = (List<Loja>) dao.findAll();
    	if(lojinha.size() == 0)
    		return ResponseEntity.status(404).build();
    	else
    		return ResponseEntity.ok(lojinha);
    }
}
