package br.com.springboot.dao;

import org.springframework.data.repository.CrudRepository;

import br.com.springboot.beans.Loja;

public interface LojaDAO extends CrudRepository<Loja, Integer> {

}
