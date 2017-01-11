package br.com.qualidadeintegrada.gerenciador.financeiro.dao;

import org.springframework.data.repository.CrudRepository;

import br.com.qualidadeintegrada.gerenciador.financeiro.model.Transacao;

public interface TransacoesDAO extends CrudRepository<Transacao, Long> {

}
