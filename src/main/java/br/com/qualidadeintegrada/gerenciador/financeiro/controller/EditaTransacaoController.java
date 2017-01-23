package br.com.qualidadeintegrada.gerenciador.financeiro.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("/editaTransacao")
public class EditaTransacaoController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private TransacaoService transacaoService;
	
	@Autowired
	private ContaService contaService;
	
	@RequestMapping
	public ModelAndView mostraTransacao(@RequestParam("id")String id) {
		
		Usuario usuarioTmp = this.usuarioService.getUsuarioLogado();
		
		String olaUsuario = "Olá " + usuarioTmp.getUsername() + "!";
		
		List<Conta> contasUsuario = new ArrayList<Conta>();
		contasUsuario = this.contaService.buscaContasPorUsuario(usuarioTmp);
		
		Transacao transacao = transacaoService.buscaTransacaoPorId(Long.parseLong(id));
		transacao.setRepeticoes(0);
		
		ModelAndView mv = new ModelAndView("editaTransacao");
		mv.addObject("olaUsuario", olaUsuario);
		mv.addObject("contas", contasUsuario);
		mv.addObject("transacao", transacao);		
		mv.addObject("tiposTransacao", TipoTransacao.values());
		
		return mv;
	}

}
