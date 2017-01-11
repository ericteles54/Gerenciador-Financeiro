package br.com.qualidadeintegrada.gerenciador.financeiro.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.qualidadeintegrada.gerenciador.financeiro.model.Conta;

@Transactional
public interface ContasDAO extends CrudRepository<Conta, Long> {
	
	@Query("select c from Conta c")
	List<Conta> findAllContas();

}
