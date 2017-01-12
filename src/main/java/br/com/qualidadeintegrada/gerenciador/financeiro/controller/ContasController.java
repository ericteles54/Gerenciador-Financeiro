package br.com.qualidadeintegrada.gerenciador.financeiro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.qualidadeintegrada.gerenciador.financeiro.dao.ContasDAO;
import br.com.qualidadeintegrada.gerenciador.financeiro.dao.UsuariosDAO;
import br.com.qualidadeintegrada.gerenciador.financeiro.model.Conta;
import br.com.qualidadeintegrada.gerenciador.financeiro.model.Usuario;

@Controller
@RequestMapping("/contas")
public class ContasController {
	
	@Autowired
	private ContasDAO contasDAO;
	
	@Autowired
	private UsuariosDAO usuariosDAO;

	@RequestMapping
	@ResponseBody
	public ModelAndView listar() {
		
		Usuario usuarioTmp = this.getUsuarioLogado();
		
		ModelAndView mv = new ModelAndView("ListaContas");		
		mv.addObject("contas", this.contasDAO.findContasByUsuario(usuarioTmp));
		mv.addObject(new Conta());
		
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(Conta conta) {
		
		// Associa usuário (username) à Conta
		Usuario usuarioTmp = this.getUsuarioLogado();
		conta.setUsuario(usuarioTmp);
		
		this.contasDAO.save(conta);
		
		return "redirect:/contas";
	}
	
	private Usuario getUsuarioLogado() {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuarioTmp = usuariosDAO.findOne(auth.getName());
		
		return usuarioTmp;
	}
}
