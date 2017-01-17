package br.com.qualidadeintegrada.gerenciador.financeiro.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.qualidadeintegrada.gerenciador.financeiro.model.Conta;
import br.com.qualidadeintegrada.gerenciador.financeiro.model.Usuario;
import br.com.qualidadeintegrada.gerenciador.financeiro.utils.ContaUtility;
import br.com.qualidadeintegrada.gerenciador.financeiro.utils.UsuarioUtility;

@Controller
@RequestMapping("/contas")
public class ContasController {
	
	@Autowired
	private ContaUtility contaUtility;
			
	@Autowired
	private UsuarioUtility usuarioUtility;

	@RequestMapping
	@ResponseBody
	public ModelAndView listar() {
		
		Usuario usuarioTmp = this.usuarioUtility.getUsuarioLogado();
		
		String olaUsuario = "Olá " + usuarioTmp.getUsername() + "!";
		
		List<Conta> contasUsuario = new ArrayList<Conta>();
		contasUsuario = this.contaUtility.buscarContasPorUsuario(usuarioTmp);
		contasUsuario = this.contaUtility.atualizaSaldoContas(contasUsuario);
		
		ModelAndView mv = new ModelAndView("ListaContas");
		mv.addObject("olaUsuario", olaUsuario);
		mv.addObject("contas", contasUsuario);
		mv.addObject(new Conta());
		
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(Conta conta) {
		
		// Associa usuário (username) à Conta
		Usuario usuarioTmp = this.usuarioUtility.getUsuarioLogado();
		conta.setUsuario(usuarioTmp);
		
		if(conta.getSaldo() == null) {
			conta.setSaldo(BigDecimal.ZERO);
		}
		
		this.contaUtility.salvar(conta);
		
		return "redirect:/contas";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String deletar(@RequestParam("id")String id) {
		
		this.contaUtility.deletar(Long.parseLong(id));
		
		return "redirect:/contas";
	}
	
	@RequestMapping(value = "/alterar", method = RequestMethod.POST)
	public String alterar(@RequestParam("id")String id, HttpServletRequest request) {
		
		Usuario usuarioTmp = this.usuarioUtility.getUsuarioLogado();
		
		String olaUsuario = "Olá " + usuarioTmp.getUsername() + "!";
		
		Conta conta = contaUtility.buscarContaPorId(Long.parseLong(id));		
		
		request.setAttribute("olaUsuario", olaUsuario);
		request.setAttribute("conta", conta);
		
		
		return "forward:/alterarConta";
	}
	
}
