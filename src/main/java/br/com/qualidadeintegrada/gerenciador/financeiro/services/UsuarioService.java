package br.com.qualidadeintegrada.gerenciador.financeiro.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.qualidadeintegrada.gerenciador.financeiro.dao.UsuariosDAO;
import br.com.qualidadeintegrada.gerenciador.financeiro.model.Usuario;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuariosDAO usuariosDAO;
				
	/*
	 * OPERACOES PADRAO OBJETO CONTA
	 */
	
	public void salvaNovoUsuario(Usuario usuario) {
			
		this.usuariosDAO.save(usuario);
	}
	
	public void salva(Usuario usuario) {
				
		this.usuariosDAO.save(usuario);
	}
	
	public void deleta(String username) {
		this.usuariosDAO.delete(username);
	}
	
	
	
	
	
	/*
	 *  BUSCAS COM OBJETO USUARIO
	 */
	
	public List<Usuario> buscaTodos() {
		
		return (List<Usuario>) this.usuariosDAO.findAll(); 
		
	}
	
	public Usuario buscarUsuarioUsername(String username) {
		
		return this.usuariosDAO.findOne(username);
	}
	
	public Usuario getUsuarioLogado() {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuario = usuariosDAO.findOne(auth.getName());

		return usuario;
	}
}
