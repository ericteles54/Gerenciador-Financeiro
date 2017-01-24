package br.com.qualidadeintegrada.gerenciador.financeiro.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.qualidadeintegrada.gerenciador.financeiro.model.AnoMes;
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
		}
						
		
		List<AnoMes> meses = new ArrayList<AnoMes>();
		meses = this.getMesDeTransacoes(transacoesUsuarioTodas);
						
		
		ModelAndView mv = new ModelAndView("ListaTransacoes");
		mv.addObject("olaUsuario", olaUsuario);
		mv.addObject("transacoes", transacoesUsuarioPorMes);
		mv.addObject("contas", contasUsuario);
		mv.addObject("tiposTransacao", TipoTransacao.values());
		mv.addObject("meses", meses);
				
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
	
	
	@RequestMapping(value = "/mesAno", method = RequestMethod.GET)
	@ResponseBody	
	public ModelAndView retornaTransacoesMesAno(HttpServletRequest request) {	
		
		AnoMes anoMes = new AnoMes();
		
		String mesAnoString = request.getParameter("mesAnoString");		
		
		String[] mesAnoArray = mesAnoString.split(Pattern.quote(","));
		anoMes.setMes(Integer.parseInt(mesAnoArray[0]));
		anoMes.setAno(Integer.parseInt(mesAnoArray[1]));
				
		// Recebe usuário logado e cria mensagem de boas vindas
		Usuario usuarioTmp = this.usuarioService.getUsuarioLogado();		
						
		// Busca todas as contas do usuário logado
		List<Conta> contasUsuario = new ArrayList<Conta>();
		contasUsuario = this.contaService.buscaContasPorUsuario(usuarioTmp);
						
		// Busca todas as transações do usuário
		List<Transacao> transacoesUsuarioPorMes = new ArrayList<Transacao>();
		List<Transacao> transacoesUsuarioTodas = new ArrayList<Transacao>();
		for(Conta conta : contasUsuario) {
			transacoesUsuarioTodas.addAll(this.transacaoService.buscaTransacoesPorConta(conta));			
			transacoesUsuarioPorMes.addAll(this.transacaoService.buscaTransacoesPorMesAnoConta(anoMes.getMes()+1, anoMes.getAno(), conta));
		}
		
		
		Locale localeBR = new Locale("pt", "BR");
		DateFormat fmtMesNome = new SimpleDateFormat("MMMM yyyy", localeBR);
		String mesAnoSelecionado = fmtMesNome.format(transacoesUsuarioPorMes.get(0).getData());
		
		
		ModelAndView mv = new ModelAndView("TabelaTransacoes");
		mv.addObject("transacoes", transacoesUsuarioPorMes);
		mv.addObject("mesAnoSelecionado", mesAnoSelecionado);
		return mv;
	}
	
	
	
	private List<AnoMes> getMesDeTransacoes(List<Transacao> transacoes) {
		
		List<AnoMes> meses = new ArrayList<AnoMes>();		
		AnoMes anoMes;
		
		Locale localeBR = new Locale("pt", "BR");
		DateFormat fmtMesNome = new SimpleDateFormat("MMMM yyyy", localeBR);
		
		for(Transacao transacao : transacoes) {			
								
			String mesString = fmtMesNome.format(transacao.getData());
									
			Calendar cal = Calendar.getInstance();
			cal.setTime(transacao.getData());
			int mesInt = cal.get(Calendar.MONTH);
			int anoInt = cal.get(Calendar.YEAR);
					
			anoMes = new AnoMes();
			anoMes.setMes(mesInt);
			anoMes.setAno(anoInt);
			anoMes.setMesNome(mesString);
			anoMes.setMesAnoString(mesInt + "," + anoInt);
			
			meses.add(anoMes);			
		}	
		
		// Retira objetos duplicados da lista
		List<AnoMes> deduped = new ArrayList<AnoMes>(new LinkedHashSet<AnoMes>(meses));
		
		// Ordena a lista baseado na funcao compareTo do objeto AnoMes
		Collections.sort(deduped);
	
		// Coloca um objeto AnoMes atual no topo da lista		
		anoMes = new AnoMes();
		anoMes.setMes(Calendar.getInstance().get(Calendar.MONTH));
		anoMes.setAno(Calendar.getInstance().get(Calendar.YEAR));
		anoMes.setMesNome("*** " + fmtMesNome.format(Calendar.getInstance().getTime()) + " *** - Mês Atual");
		anoMes.setMesAnoString(Calendar.getInstance().get(Calendar.MONTH) + "," + Calendar.getInstance().get(Calendar.YEAR));
		deduped.add(0, anoMes);		
		
		
		return deduped;
		
	}	

}
