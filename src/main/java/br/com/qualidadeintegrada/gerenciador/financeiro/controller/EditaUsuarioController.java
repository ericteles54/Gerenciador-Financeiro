package br.com.qualidadeintegrada.gerenciador.financeiro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.qualidadeintegrada.gerenciador.financeiro.model.Usuario;
import br.com.qualidadeintegrada.gerenciador.financeiro.services.UsuarioService;

@Controller
@RequestMapping("/editaUsuario")
public class EditaUsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
			
	@RequestMapping
	public ModelAndView mostraUsuario(@RequestParam("username")String username) {
		
		Usuario usuarioTmp = this.usuarioService.getUsuarioLogado();
		
		String olaUsuario = "Olá " + usuarioTmp.getUsername() + "!";
				
		Usuario usuario = usuarioService.buscarUsuarioUsername(username);
		
		ModelAndView mv = new ModelAndView("editaUsuario");
		mv.addObject("olaUsuario", olaUsuario);
		mv.addObject("usuario", usuario);
			
		
		return mv;
	}
}
