package br.com.qualidadeintegrada.gerenciador.financeiro.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.qualidadeintegrada.gerenciador.financeiro.dao.UsuariosDAO;
import br.com.qualidadeintegrada.gerenciador.financeiro.dao.UsuariosRolesDAO;
import br.com.qualidadeintegrada.gerenciador.financeiro.model.Usuario;
import br.com.qualidadeintegrada.gerenciador.financeiro.model.UsuarioRoles;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuariosDAO usuariosDAO;
	
	@Autowired
	private UsuariosRolesDAO usuariosRolesDAO;
	
	@Autowired
	private ContaService contaService;
	
				
	/*
	 * OPERACOES PADRAO OBJETO CONTA
	 */
	
	public void salvaNovoUsuario(Usuario usuario) {
				
		usuario.setPassword(new BCryptPasswordEncoder().encode(usuario.getPassword()));
		this.usuariosDAO.save(usuario);
		
		
		UsuarioRoles usuarioRoles = new UsuarioRoles();
		usuarioRoles.setRole("ROLE_USER");
		usuarioRoles.setUsuario(usuario);
		
		usuariosRolesDAO.save(usuarioRoles);
	}
	
	public void salva(Usuario usuario) {
		
		usuario.setPassword(new BCryptPasswordEncoder().encode(usuario.getPassword()));		
		this.usuariosDAO.save(usuario);		
	}
	
	public boolean deleta(String username) {
		
		// Busca Usuario
		Usuario usuario = new Usuario();
		usuario = this.usuariosDAO.findOne(username);
		
		
		// Verifica se usuário possui contas e deleta somente se não tiver contas
		// cadastradas
		if(this.contaService.buscaContasPorUsuario(usuario).isEmpty()) {
		
			// Busca e deleta ROLES do usuário
			UsuarioRoles usuarioRoles = new UsuarioRoles();
			usuarioRoles = this.usuariosRolesDAO.findUsuarioRoleByUsuario(usuario);
			this.usuariosRolesDAO.delete(usuarioRoles);
					
			// Deleta usuario
			this.usuariosDAO.delete(usuario);
			
			return true;
			
			
		} else {		
		
			return false;
		}		
		
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
