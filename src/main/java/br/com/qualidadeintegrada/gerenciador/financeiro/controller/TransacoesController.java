package br.com.qualidadeintegrada.gerenciador.financeiro.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.qualidadeintegrada.gerenciador.financeiro.model.Conta;
import br.com.qualidadeintegrada.gerenciador.financeiro.model.TipoTransacao;
import br.com.qualidadeintegrada.gerenciador.financeiro.model.Transacao;
import br.com.qualidadeintegrada.gerenciador.financeiro.model.Usuario;
import br.com.qualidadeintegrada.gerenciador.financeiro.services.ContaService;
import br.com.qualidadeintegrada.gerenciador.financeiro.services.TransacaoService;
import br.com.qualidadeintegrada.gerenciador.financeiro.services.UsuarioService;

@Controller
@RequestMapping("/transacoes")
public class TransacoesController {


	@Autowired
	private UsuarioService usuarioUtility;
	
	@Autowired
	private TransacaoService transacaoUtility;

	@Autowired
	private ContaService contaUtility;

	@RequestMapping
	public ModelAndView listar() {
		
		Usuario usuarioTmp = this.usuarioUtility.getUsuarioLogado();
		
		String olaUsuario = "Olá " + usuarioTmp.getUsername() + "!";
		
		List<Conta> contasUsuario = new ArrayList<Conta>();
		contasUsuario = this.contaUtility.buscaContasPorUsuario(usuarioTmp);
				
		List<Transacao> transacoesUsuario = new ArrayList<Transacao>();
		for(Conta conta : contasUsuario) {
			transacoesUsuario.addAll(this.transacaoUtility.buscarTransacoesPorConta(conta));
		}
		
		ModelAndView mv = new ModelAndView("ListaTransacoes");
		mv.addObject("olaUsuario", olaUsuario);
		mv.addObject("transacoes", transacoesUsuario);
		mv.addObject("contas", contasUsuario);
		mv.addObject("tiposTransacao", TipoTransacao.values());
				
		mv.addObject(new Transacao());
		
		return mv;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvar(Transacao transacao) {
					
		this.transacaoUtility.salvar(transacao);

		return "redirect:/transacoes";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String deletar(@RequestParam("id")String id) {
		
		this.transacaoUtility.deletar(Long.parseLong(id));
		
		return "redirect:/transacoes";
	}
}
