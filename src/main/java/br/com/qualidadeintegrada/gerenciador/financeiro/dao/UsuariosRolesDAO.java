package br.com.qualidadeintegrada.gerenciador.financeiro.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.qualidadeintegrada.gerenciador.financeiro.model.Usuario;
import br.com.qualidadeintegrada.gerenciador.financeiro.model.UsuarioRoles;

public interface UsuariosRolesDAO extends CrudRepository<UsuarioRoles, Long> {
	
	@Query("select u from UsuarioRoles u where u.usuario = :usuario")
	UsuarioRoles findUsuarioRoleByUsuario(@Param("usuario") Usuario usuario);

}
