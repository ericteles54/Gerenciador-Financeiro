package br.com.qualidadeintegrada.gerenciador.financeiro.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.qualidadeintegrada.gerenciador.financeiro.model.Conta;
import br.com.qualidadeintegrada.gerenciador.financeiro.model.Usuario;
import br.com.qualidadeintegrada.gerenciador.financeiro.services.ContaService;
import br.com.qualidadeintegrada.gerenciador.financeiro.services.UsuarioService;

@Controller
@RequestMapping("/alterarConta")
public class AlteraContasController {
	
	@Autowired
	private ContaService contaUtility;
			
	@Autowired
	private UsuarioService usuarioUtility;
	
	@RequestMapping
	public ModelAndView alterar() {
		
		Usuario usuarioTmp = this.usuarioUtility.getUsuarioLogado();		
		String olaUsuario = "Olá " + usuarioTmp.getUsername() + "!";
		
		ModelAndView mv = new ModelAndView("AlteraConta");
		mv.addObject("olaUsuario", olaUsuario);		
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
