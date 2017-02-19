package br.com.qualidadeintegrada.gerenciador.financeiro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.qualidadeintegrada.gerenciador.financeiro.model.Usuario;
import br.com.qualidadeintegrada.gerenciador.financeiro.services.UsuarioService;


@Controller
@RequestMapping("/usuarios")
public class UsuariosController {
	
	private static boolean mensagemSuccessAtiva = false;
	private static boolean mensagemWarningAtiva = false;
	private static String mensagemAlert = new String();
		
	@Autowired
	private UsuarioService usuarioService;
	

	@RequestMapping
	@ResponseBody
	public ModelAndView verPagina() {
		
		
		Usuario usuarioTmp = this.usuarioService.getUsuarioLogado();
		
		List<Usuario> usuariosCadastrados = this.usuarioService.buscaTodos();
		
		String olaUsuario = "Olá " + usuarioTmp.getUsername() + "!";
		
		ModelAndView mv = new ModelAndView("usuarios");
		mv.addObject("olaUsuario", olaUsuario);
		mv.addObject("mensagemSuccessAtiva", UsuariosController.mensagemSuccessAtiva);
		mv.addObject("mensagemWarningAtiva", UsuariosController.mensagemWarningAtiva);
		mv.addObject("mensagemAlert", UsuariosController.mensagemAlert);
		mv.addObject("usuarios", usuariosCadastrados);
		mv.addObject(new Usuario());
		
		// Limpa variaveis de mensagem após serem colocadas no objeto ModelAndView
		UsuariosController.mensagemSuccessAtiva = false;
		UsuariosController.mensagemWarningAtiva = false;
		UsuariosController.mensagemAlert = new String();
		
		return mv;
	}
	
	
	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public String salvaNovoUsuario(Usuario usuario) {
		
		this.usuarioService.salvaNovoUsuario(usuario);
		
		UsuariosController.mensagemSuccessAtiva = true;
		UsuariosController.mensagemAlert = "Usuário " + usuario.getUsername() + " criado com sucesso!";
		
		return "redirect:/usuarios";
		
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salva(Usuario usuario) {
		
		this.usuarioService.salva(usuario);
		
		UsuariosController.mensagemSuccessAtiva = true;
		UsuariosController.mensagemAlert = "Usuário " + usuario.getUsername() + " salvo com sucesso!";
		
		return "redirect:/usuarios";
		
	}
	
	@RequestMapping(value = "/deleta", method = RequestMethod.POST)
	public String deleta(@RequestParam("username")String username) {
				
		
		
		if(this.usuarioService.deleta(username)) {
			
			// Usuário deletado com sucesso
			UsuariosController.mensagemSuccessAtiva = true;
			UsuariosController.mensagemAlert = "Usuário " + username + " deletado com sucesso!";
			return "redirect:/usuarios";
			
		} else {
			
			// Erro ao deletar usuário
			UsuariosController.mensagemWarningAtiva = true;
			UsuariosController.mensagemAlert = "Erro - O usuário " + username + " possui contas! Remova as contas antes.";
			return "redirect:/usuarios";
			
		}			
		
	}
	
}