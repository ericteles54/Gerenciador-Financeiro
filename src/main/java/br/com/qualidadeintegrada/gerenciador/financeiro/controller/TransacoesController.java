package br.com.qualidadeintegrada.gerenciador.financeiro.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

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
	private UsuarioService usuarioService;
	
	@Autowired
	private TransacaoService transacaoService;

	@Autowired
	private ContaService contaService;

	@RequestMapping
	public ModelAndView lista() {
		
		// Recebe usuário logado e cria mensagem de boas vindas
		Usuario usuarioTmp = this.usuarioService.getUsuarioLogado();		
		String olaUsuario = "Olá " + usuarioTmp.getUsername() + "!";
		
		// Busca todas as contas do usuário logado
		List<Conta> contasUsuario = new ArrayList<Conta>();
		contasUsuario = this.contaService.buscaContasPorUsuario(usuarioTmp);
				
		// Busca todas as transações do usuário
		List<Transacao> transacoesUsuarioPorMes = new ArrayList<Transacao>();
		List<Transacao> transacoesUsuarioTodas = new ArrayList<Transacao>();
		for(Conta conta : contasUsuario) {
			transacoesUsuarioTodas.addAll(this.transacaoService.buscaTransacoesPorConta(conta));
			transacoesUsuarioPorMes.addAll(this.transacaoService.buscaTransacoesPorMesAnoConta(0, 2017, conta));
		}
		
		
		HashMap<Integer, String> mesesHashMap = new HashMap<Integer,String>();
		mesesHashMap = this.getMesDeTransacoes(transacoesUsuarioTodas);
		
		
		List<String> meses = new ArrayList<String>();
		for (Integer key : mesesHashMap.keySet()) {
            
            //Capturamos o valor a partir da chave
            String value = mesesHashMap.get(key);
            System.out.println(key + " = " + value);
            meses.add(key + " = " + value);
		}

		
		ModelAndView mv = new ModelAndView("ListaTransacoes");
		mv.addObject("olaUsuario", olaUsuario);
		mv.addObject("transacoes", transacoesUsuarioPorMes);
		mv.addObject("contas", contasUsuario);
		mv.addObject("tiposTransacao", TipoTransacao.values());
		mv.addObject("mesSelect", meses);
				
		mv.addObject(new Transacao());
		mv.addObject("mes", new String());
		
		return mv;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salva(Transacao transacao) {
					
		this.transacaoService.salva(transacao);

		return "redirect:/transacoes";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String deleta(@RequestParam("id")String id) {
		
		this.transacaoService.deleta(Long.parseLong(id));
		
		return "redirect:/transacoes";
	}
	
	
	
	
	
	
	private HashMap<Integer,String> getMesDeTransacoes(List<Transacao> transacoes) {
		
		HashMap<Integer,String> meses = new HashMap<Integer,String>();
		
		for(Transacao transacao : transacoes) {
						
			Locale localeBR = new Locale("pt", "BR");
			DateFormat fmt = new SimpleDateFormat("MMMM yyyy", localeBR);			
			String mesString = fmt.format(transacao.getData());
						
			Calendar cal = Calendar.getInstance();
			cal.setTime(transacao.getData());
			int mesInt = cal.get(Calendar.MONTH);	
						
			meses.put(mesInt,mesString);
		}	
		
		//List<String> deduped = meses.stream().distinct().collect(Collectors.toList());
		
		//return deduped;
		return meses;
	}	
	
}
