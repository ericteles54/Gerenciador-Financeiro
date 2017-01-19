package br.com.qualidadeintegrada.gerenciador.financeiro.controllers;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.qualidadeintegrada.gerenciador.financeiro.model.Conta;
import br.com.qualidadeintegrada.gerenciador.financeiro.model.Usuario;
import br.com.qualidadeintegrada.gerenciador.financeiro.services.ContaService;
import br.com.qualidadeintegrada.gerenciador.financeiro.services.UsuarioService;

@RestController
@RequestMapping("/gerenciaContas")
public class ContaController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private ContaService contaService;
	
	@RequestMapping(value = "/contas", method = RequestMethod.GET)
	public ModelAndView listar(Model model) {
		
		// Associa usuário (username) à Conta
		Usuario usuarioTmp = this.usuarioService.getUsuarioLogado();
		
		model.addAttribute("contas", contaService.buscaContasPorUsuario(usuarioTmp));
		model.addAttribute("conta", new Conta());
		
		return new ModelAndView("gerenciaContas");
	}
	
	@RequestMapping(value = "/salva", method = RequestMethod.POST)
	public String salvar(Conta conta) {
		
		// Associa usuário (username) à Conta
		Usuario usuarioTmp = this.usuarioService.getUsuarioLogado();
		conta.setUsuario(usuarioTmp);
			
		if(conta.getSaldo() == null) {
			conta.setSaldo(BigDecimal.ZERO);
		}
		
		this.contaService.salva(conta);
		
		return "redirect:/gerenciaContas";
	}
	
	@RequestMapping("/edita/{id}")
	public String editarConta(@PathVariable Long id, Model model) {
		
		model.addAttribute("conta", contaService.buscarContaPorId(id));
		
		return "editarConta";
	}
	
	@RequestMapping("/deleta/{id}")
	public String deletar(@PathVariable Long id) {
		
		this.contaService.deleta(id);
		
		return "redirect:/gerenciaContas";
	}
	
	

}
