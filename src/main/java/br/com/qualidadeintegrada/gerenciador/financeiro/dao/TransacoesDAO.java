package br.com.qualidadeintegrada.gerenciador.financeiro.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.qualidadeintegrada.gerenciador.financeiro.model.Conta;
import br.com.qualidadeintegrada.gerenciador.financeiro.model.Transacao;

@Transactional
public interface TransacoesDAO extends JpaRepository<Transacao, Long> {
	
	@Query("select t from Transacao t where t.conta = :conta")
	List<Transacao> findTransacoesByConta(@Param("conta") Conta conta);
	
	@Query("select t from Transacao t where t.consolidada = true and t.conta = :conta")
	List<Transacao> findValorTransacoesConsolidadasByConta(@Param("conta") Conta conta);

}
