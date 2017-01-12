package br.com.qualidadeintegrada.gerenciador.financeiro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.qualidadeintegrada.gerenciador.financeiro.dao.TransacoesDAO;
import br.com.qualidadeintegrada.gerenciador.financeiro.model.Transacao;

@Controller
@RequestMapping("/transacoes")
public class TransacoesController {
	
	@Autowired
	private TransacoesDAO transacoesDAO;
	
	@RequestMapping
	@ResponseBody
	public ModelAndView listar() {
		
		ModelAndView mv = new ModelAndView("ListarTransacoes");
		mv.addObject("transacoes", this.transacoesDAO.findAll());
		mv.addObject(new Transacao());
		
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(Transacao transacao) {
		
		this.transacoesDAO.save(transacao);
		
		return "redirect:/transacoes";
	}

}
