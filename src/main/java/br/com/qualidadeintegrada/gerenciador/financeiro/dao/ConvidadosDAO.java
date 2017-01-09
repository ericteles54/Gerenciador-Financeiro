package br.com.qualidadeintegrada.gerenciador.financeiro.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.qualidadeintegrada.gerenciador.financeiro.model.Convidado;

@Transactional
public interface ConvidadosDAO extends CrudRepository<Convidado, Long>{
	
	@Query("select c from Convidado c")
	List<Convidado> findAll(); 
}
