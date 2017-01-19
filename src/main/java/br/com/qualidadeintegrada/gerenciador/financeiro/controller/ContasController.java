package br.com.qualidadeintegrada.gerenciador.financeiro.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.qualidadeintegrada.gerenciador.financeiro.model.Conta;
import br.com.qualidadeintegrada.gerenciador.financeiro.model.Usuario;
import br.com.qualidadeintegrada.gerenciador.financeiro.services.ContaService;
import br.com.qualidadeintegrada.gerenciador.financeiro.services.UsuarioService;

@Controller
@RequestMapping("/contas")
public class ContasController {
	
	@Autowired
	private ContaService contaService;
			
	@Autowired
	private UsuarioService usuarioService;

	@RequestMapping
	@ResponseBody
	public ModelAndView lista() {
		
		Usuario usuarioTmp = this.usuarioService.getUsuarioLogado();
		
		String olaUsuario = "Olá " + usuarioTmp.getUsername() + "!";
		
		List<Conta> contasUsuario = new ArrayList<Conta>();
		contasUsuario = this.contaService.buscaContasPorUsuario(usuarioTmp);
		contasUsuario = this.contaService.atualizaSaldoContas(contasUsuario);
		
		ModelAndView mv = new ModelAndView("ListaContas");
		mv.addObject("olaUsuario", olaUsuario);
		mv.addObject("contas", contasUsuario);
		mv.addObject(new Conta());
		
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salva(Conta conta) {
		
		// Associa usuário (username) à Conta
		Usuario usuarioTmp = this.usuarioService.getUsuarioLogado();
		conta.setUsuario(usuarioTmp);
		
		if(conta.getSaldo() == null) {
			conta.setSaldo(BigDecimal.ZERO);
		}
		
		this.contaService.salva(conta);
		
		return "redirect:/contas";
	}
	
	@RequestMapping(value = "/deleta", method = RequestMethod.POST)
	public String deleta(@RequestParam("id")String id) {
		
		this.contaService.deleta(Long.parseLong(id));
		
		return "redirect:/contas";
	}
	
	@RequestMapping(value = "/altera/{id}", method = RequestMethod.GET)
	@ResponseBody
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
	@RequestMapping(value = "/altera", method = RequestMethod.POST)
	public String alterar(@RequestParam("id")String id, HttpServletRequest request, Model model) {
		
		Usuario usuarioTmp = this.usuarioUtility.getUsuarioLogado();
		
		String olaUsuario = "Olá " + usuarioTmp.getUsername() + "!";
		
		Conta conta = contaUtility.buscarContaPorId(Long.parseLong(id));		
		
		request.setAttribute("olaUsuario", olaUsuario);
		request.setAttribute("conta", conta);
		request.setAttribute("model", model);
		
		
		return "forward:/alterarConta";
	}
	*/
	
}
