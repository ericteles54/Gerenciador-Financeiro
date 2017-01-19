package br.com.qualidadeintegrada.gerenciador.financeiro.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.qualidadeintegrada.gerenciador.financeiro.model.Conta;
import br.com.qualidadeintegrada.gerenciador.financeiro.model.Usuario;
import br.com.qualidadeintegrada.gerenciador.financeiro.services.ContaService;
import br.com.qualidadeintegrada.gerenciador.financeiro.services.UsuarioService;

@Controller
@RequestMapping("/editaConta")
public class AlteraContasController {
	
	@Autowired
	private ContaService contaService;
			
	@Autowired
	private UsuarioService usuarioService;
	
	@RequestMapping
	public ModelAndView altera(@RequestParam("id")String id) {
		
		Usuario usuarioTmp = this.usuarioService.getUsuarioLogado();
		
		String olaUsuario = "Olá " + usuarioTmp.getUsername() + "!";
		
		Conta conta = contaService.buscarContaPorId(Long.parseLong(id));
		
		ModelAndView mv = new ModelAndView("editaConta");
		mv.addObject("olaUsuario", olaUsuario);
		mv.addObject("conta", conta);
			
		
		return mv;
	}
/*
	@RequestMapping
	//@ResponseBody
	public ModelAndView alterar(HttpServletRequest request) {
	
	
		
		//String olaUsuario = (String)request.getAttribute("olaUsuario");
		Conta conta = (Conta)request.getAttribute("conta");
		//Model model = (Model)request.getAttribute("model");
		
		//model.addAttribute("conta", conta);
		
		//ModelAndView mv = new ModelAndView("AlteraConta");
		//mv.addObject("olaUsuario", olaUsuario);		
		//mv.addObject("contaId", conta.getId());
		//mv.addObject("contaNome", conta.getNome());
		//mv.addObject("contaSaldo", conta.getSaldo());
		//mv.addObject("contaUsuario", conta.getUsuario().getUsername());
		//mv.addObject("conta", conta);
		//mv.addObject(new Conta());
		
		//return mv;
		return new ModelAndView("AlteraConta", "conta", conta);
	}
*/
}
