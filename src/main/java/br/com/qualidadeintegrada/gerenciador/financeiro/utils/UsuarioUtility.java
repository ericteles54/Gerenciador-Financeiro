package br.com.qualidadeintegrada.gerenciador.financeiro.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.qualidadeintegrada.gerenciador.financeiro.dao.UsuariosDAO;
import br.com.qualidadeintegrada.gerenciador.financeiro.model.Usuario;

@Service
public class UsuarioUtility {
	
	@Autowired
	private UsuariosDAO usuariosDAO;
	
	
	/*
	 *  BUSCAS COM OBJETO USUARIO
	 */
	
	public Usuario getUsuarioLogado() {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuario = usuariosDAO.findOne(auth.getName());

		return usuario;
	}
}
