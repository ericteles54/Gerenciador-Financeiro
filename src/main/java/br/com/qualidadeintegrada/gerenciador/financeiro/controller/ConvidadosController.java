package br.com.qualidadeintegrada.gerenciador.financeiro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.qualidadeintegrada.gerenciador.financeiro.dao.ConvidadosDAO;
import br.com.qualidadeintegrada.gerenciador.financeiro.model.Convidado;

@Controller
@RequestMapping("/convidados")
public class ConvidadosController {
	
	@Autowired
	private ConvidadosDAO convidados;
	//private ConvidadosRepository convidados;

	@RequestMapping
	@ResponseBody
	public ModelAndView listar() {
		
		ModelAndView mv = new ModelAndView("ListaConvidados");
		mv.addObject("convidados", this.convidados.findAll());
		
		mv.addObject(new Convidado());
		
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	//@ResponseBody
	public String salvar(Convidado convidado) {
		
		this.convidados.save(convidado);
		
		return "redirect:/convidados";
	}
}
