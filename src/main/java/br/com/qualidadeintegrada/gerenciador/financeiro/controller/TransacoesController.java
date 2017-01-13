package br.com.qualidadeintegrada.gerenciador.financeiro.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.qualidadeintegrada.gerenciador.financeiro.dao.ContasDAO;
import br.com.qualidadeintegrada.gerenciador.financeiro.dao.TransacoesDAO;
import br.com.qualidadeintegrada.gerenciador.financeiro.dao.UsuariosDAO;
import br.com.qualidadeintegrada.gerenciador.financeiro.model.Conta;
import br.com.qualidadeintegrada.gerenciador.financeiro.model.Transacao;
import br.com.qualidadeintegrada.gerenciador.financeiro.model.Usuario;

@Controller
@RequestMapping("/transacoes")
public class TransacoesController {

	@Autowired
	private UsuariosDAO usuariosDAO;
	
	@Autowired
	private TransacoesDAO transacoesDAO;

	@Autowired
	private ContasDAO contasDAO;

	@RequestMapping
	@ResponseBody
	public ModelAndView listar() {
		
		Usuario usaurioTmp = this.getUsuarioLogado();
		List<Conta> contasUsuario = new ArrayList<Conta>();
		contasUsuario = this.contasDAO.findContasByUsuario(usaurioTmp);
		
		List<Transacao> transacoesUsuario = new ArrayList<Transacao>();
		for(Conta conta : contasUsuario) {
			transacoesUsuario.addAll(this.transacoesDAO.findTransacoesByConta(conta));
		}
		
		ModelAndView mv = new ModelAndView("ListaTransacoes");
		mv.addObject("transacoes", transacoesUsuario);
		mv.addObject("contas", contasUsuario);
				
		mv.addObject(new Transacao());
		
		return mv;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvar(Transacao transacao) {
						
		Date date = new Date();
		transacao.setData(date);
		
		this.transacoesDAO.save(transacao);

		return "redirect:/transacoes";
	}

	private Usuario getUsuarioLogado() {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuarioTmp = usuariosDAO.findOne(auth.getName());

		return usuarioTmp;
	}

}
