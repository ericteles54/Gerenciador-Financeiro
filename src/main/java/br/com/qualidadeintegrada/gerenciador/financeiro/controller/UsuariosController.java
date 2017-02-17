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
		mv.addObject("usuarios", usuariosCadastrados);
		mv.addObject(new Usuario());
		
		return mv;
	}
	
	
	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public String salvaNovoUsuario(Usuario usuario) {
		
		this.usuarioService.salvaNovoUsuario(usuario);
		
		return "redirect:/usuarios";
		
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salva(Usuario usuario) {
		
		this.usuarioService.salva(usuario);
		
		return "redirect:/usuarios";
		
	}
	
	@RequestMapping(value = "/deleta", method = RequestMethod.POST)
	public String deleta(@RequestParam("username")String username) {
		
		
		this.usuarioService.deleta(username);
		
		
		return "redirect:/usuarios";
	}
	
}