package br.com.springboot.controller;

import java.util.List;
import java.util.Optional;

import org.hibernate.boot.model.source.internal.hbm.RootEntitySourceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.springboot.beans.Loja;
import br.com.springboot.dao.LojaDAO;

@Controller
public class LojaController {

	@Autowired
	private LojaDAO dao;

	//HomePage
	@RequestMapping("/")
	public ModelAndView index() {
		return new ModelAndView("homepage");
	}
	
	//HomePage
	@RequestMapping("/createproduto")
	public ModelAndView createProduto() {
		return new ModelAndView("index");
	}
	
	//Tabela 
	@RequestMapping("/viewtabela")
	public ModelAndView verTabela(Model model) {
		List<Loja> lojinha = (List<Loja>) dao.findAll();
		System.out.println(lojinha);
		model.addAttribute(lojinha);
		return new ModelAndView("Tabela");
	}
	
	//Lista todo os produto do banco de dados
	@PostMapping("/viewtabela1")
	public ResponseEntity<List<Loja>> getAll() {
		List<Loja> lojinha = (List<Loja>) dao.findAll();
		if (lojinha.size() == 0)
			return ResponseEntity.status(404).build();
		else
			return ResponseEntity.ok(lojinha);
	}

	@GetMapping("/login")
	public String home() {
		return "login";
	}
	
	@PostMapping("/login")
	public ResponseEntity<Loja> getAllByNomeAndCodigo(@RequestBody Loja loja) {
		System.out.println(loja);
		Loja resposta = dao.findByNomeAndCodigo(loja.getNome(), loja.getCodigo());

//		if(resposta == null) { return ResponseEntity.status(404).build();}
		System.out.println(resposta);
		return ResponseEntity.ok(resposta);
	}

	//lista um produto especifico
	@GetMapping("/produtos/{id}")
	public ResponseEntity<Loja> getUsuario(@PathVariable int id){
		
		Loja resposta = dao.findById(id).orElse(null);
		
		if(resposta == null) {
			return ResponseEntity.status(404).build();
		}
		
		return ResponseEntity.ok(resposta);
		
	}
	

    //login
	@PostMapping("/produtos/login")
	public ResponseEntity<Loja> login(@RequestBody Loja objeto){
		Loja resposta = dao.findByNomeAndCodigo(objeto.getNome(), objeto.getCodigo());
		if(resposta == null) {
			return ResponseEntity.status(404).build();
		}
		
		return ResponseEntity.ok(resposta);
		
	}
	
	//Criar novo produto
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
	
	@PostMapping("/deletarprodutos")
	public ResponseEntity<Loja> deletarProdutos(@RequestBody Loja objeto){
		Loja resposta = dao.findByNomeAndCodigo(objeto.getNome(), objeto.getCodigo());
		if(resposta == null) {
			return ResponseEntity.status(404).build();
		}
		try {
			dao.delete(resposta);
			return ResponseEntity.ok(resposta);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(404).build();
			// TODO: handle exception
		}
	}
}
