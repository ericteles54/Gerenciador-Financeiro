package br.com.qualidadeintegrada.gerenciador.financeiro.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.qualidadeintegrada.gerenciador.financeiro.model.Transacao;

@Transactional
public interface TransacoesDAO extends JpaRepository<Transacao, Long> {

}
