package br.com.qualidadeintegrada.gerenciador.financeiro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.qualidadeintegrada.gerenciador.financeiro.dao.ContasDAO;
import br.com.qualidadeintegrada.gerenciador.financeiro.model.Conta;

@Controller
@RequestMapping("/contas")
public class ContasController {
	
	@Autowired
	private ContasDAO contasDAO;
	
	@RequestMapping
	@ResponseBody
	public ModelAndView listarTodas() {
		
		ModelAndView modelAndView = new ModelAndView("contas");
		modelAndView.addObject("contas", this.contasDAO.findAllContas());
		modelAndView.addObject(new Conta());
		
		return modelAndView;		
		
	}

	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(Conta conta) {
		
		this.contasDAO.save(conta);
		
		return "redirect:/contas";
	}
}
