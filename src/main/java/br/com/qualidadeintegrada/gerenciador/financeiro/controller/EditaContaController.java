package br.com.qualidadeintegrada.gerenciador.financeiro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.qualidadeintegrada.gerenciador.financeiro.model.Conta;
import br.com.qualidadeintegrada.gerenciador.financeiro.model.Usuario;
import br.com.qualidadeintegrada.gerenciador.financeiro.services.ContaService;
import br.com.qualidadeintegrada.gerenciador.financeiro.services.UsuarioService;

@Controller
@RequestMapping("/editaConta")
public class EditaContaController {
	
	@Autowired
	private ContaService contaService;
			
	@Autowired
	private UsuarioService usuarioService;
	
	@RequestMapping
	public ModelAndView mostraConta(@RequestParam("id")String id) {
		
		Usuario usuarioTmp = this.usuarioService.getUsuarioLogado();
		
		String olaUsuario = "Olá " + usuarioTmp.getUsername() + "!";
		
		Conta conta = contaService.buscarContaPorId(Long.parseLong(id));
		
		ModelAndView mv = new ModelAndView("editaConta");
		mv.addObject("olaUsuario", olaUsuario);
		mv.addObject("conta", conta);
			
		
		return mv;
	}
}
