package br.com.qualidadeintegrada.gerenciador.financeiro.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.qualidadeintegrada.gerenciador.financeiro.model.Conta;
import br.com.qualidadeintegrada.gerenciador.financeiro.model.Usuario;
import br.com.qualidadeintegrada.gerenciador.financeiro.utils.ContaUtility;
import br.com.qualidadeintegrada.gerenciador.financeiro.utils.UsuarioUtility;

@Controller
@RequestMapping("/alterarConta")
public class AlteraContasController {
	
	@Autowired
	private ContaUtility contaUtility;
			
	@Autowired
	private UsuarioUtility usuarioUtility;

	@RequestMapping	
	public ModelAndView alterar(HttpServletRequest request) {
		
		String olaUsuario = (String)request.getAttribute("olaUsuario");
		Conta conta = (Conta)request.getAttribute("conta");
				
		ModelAndView mv = new ModelAndView("AlteraConta");
		mv.addObject("olaUsuario", olaUsuario);		
		mv.addObject("contaId", conta.getId());
		mv.addObject("contaNome", conta.getNome());
		mv.addObject("contaSaldo", conta.getSaldo());
		mv.addObject("contaUsuario", conta.getUsuario().getUsername());
		mv.addObject("conta", conta);
		mv.addObject(new Conta());
		
		return mv;
	}	
}
