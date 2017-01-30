package br.com.qualidadeintegrada.gerenciador.financeiro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/template")
public class ClasseTesteTemplate {

	@RequestMapping
	@ResponseBody
	public ModelAndView verPagina() {
		
		ModelAndView mv = new ModelAndView("template");
		mv.addObject("olaUsuario", "UsuárioTeste");
		
		return mv;
	}
	
}
