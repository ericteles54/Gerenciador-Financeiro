package br.com.qualidadeintegrada.gerenciador.financeiro.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.qualidadeintegrada.gerenciador.financeiro.model.Conta;
import br.com.qualidadeintegrada.gerenciador.financeiro.model.Usuario;

@Transactional
public interface ContasDAO extends JpaRepository<Conta, Long> {
	
	@Query("select c from Conta c")
	List<Conta> findAll();
	
	@Query("select c from Conta c where c.usuario = :usuario")
	List<Conta> findContasByUsuario(@Param("usuario") Usuario usuario);

}
