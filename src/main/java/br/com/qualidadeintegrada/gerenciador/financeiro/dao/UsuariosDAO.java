package br.com.qualidadeintegrada.gerenciador.financeiro.dao;

import org.springframework.data.repository.CrudRepository;

import br.com.qualidadeintegrada.gerenciador.financeiro.model.Usuario;

public interface UsuariosDAO extends CrudRepository<Usuario, String> {
	
	

}
