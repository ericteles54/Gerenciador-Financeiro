package br.com.qualidadeintegrada.gerenciador.financeiro.controller;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import br.com.qualidadeintegrada.gerenciador.financeiro.model.ContaInfoOnline;
import br.com.qualidadeintegrada.gerenciador.financeiro.model.TipoTransacao;
import br.com.qualidadeintegrada.gerenciador.financeiro.model.Transacao;
import br.com.qualidadeintegrada.gerenciador.financeiro.model.Usuario;
import br.com.qualidadeintegrada.gerenciador.financeiro.services.ContaService;
import br.com.qualidadeintegrada.gerenciador.financeiro.services.TransacaoService;
import br.com.qualidadeintegrada.gerenciador.financeiro.services.UsuarioService;

@Controller
@RequestMapping("/contas")
public class ContasController {
	
	private static boolean mensagemSuccessAtiva = false;
	private static boolean mensagemWarningAtiva = false;
	private static String mensagemAlert = new String();
	
	
	@Autowired
	private ContaService contaService;
	
	@Autowired
	private TransacaoService transacaoService;
			
	@Autowired
	private UsuarioService usuarioService;

	@RequestMapping
	@ResponseBody
	public ModelAndView lista() {
		
		Usuario usuarioTmp = this.usuarioService.getUsuarioLogado();
		
		String olaUsuario = "Olá " + usuarioTmp.getUsername() + "!";
		
		List<Conta> contasUsuario = new ArrayList<Conta>();
		contasUsuario = this.contaService.buscaContasPorUsuario(usuarioTmp);
		
		
		ModelAndView mv = new ModelAndView("contas");
		mv.addObject("olaUsuario", olaUsuario);
		mv.addObject("mensagemSuccessAtiva", ContasController.mensagemSuccessAtiva);
		mv.addObject("mensagemWarningAtiva", ContasController.mensagemWarningAtiva);
		mv.addObject("mensagemAlert", ContasController.mensagemAlert);
		mv.addObject("contas", contasUsuario);		
		mv.addObject(new Conta());
		
		
		// Limpa variaveis de mensagem após serem colocadas no objeto ModelAndView
		ContasController.mensagemSuccessAtiva = false;
		ContasController.mensagemWarningAtiva = false;
		ContasController.mensagemAlert = new String();
		
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salva(Conta conta) {
						
		// Associa usuário (username) à Conta
		Usuario usuarioTmp = this.usuarioService.getUsuarioLogado();
		conta.setUsuario(usuarioTmp);
		
		if(conta.getSaldo() == null) {
			conta.setSaldo(BigDecimal.ZERO);
		}
				
		this.contaService.salva(conta);
		
		
		ContasController.mensagemSuccessAtiva = true;
		ContasController.mensagemAlert = "Conta " + conta.getNome() + " criada com sucesso!";
		
		return "redirect:/contas";
	}
	
	@RequestMapping(value = "/deleta", method = RequestMethod.POST)
	public String deleta(@RequestParam("id")String id) {		
		
		if(this.contaService.deleta(Long.parseLong(id))) {
			
			// Conta deletada com sucesso
			ContasController.mensagemSuccessAtiva = true;
			ContasController.mensagemAlert = "Conta deletada com sucesso!";
			return "redirect:/contas";
			
		} else {
			
			// Erro ao deletar conta
			ContasController.mensagemWarningAtiva = true;
			ContasController.mensagemAlert = "Erro ao deletar conta. Entre em contato com o administrador.";
			return "redirect:/contas";
			
		}	
		
	}
	
	@RequestMapping(value = "/infoContasUsuario", method = RequestMethod.GET)
	@ResponseBody	
	public ModelAndView retornaTransacoesMesAno(HttpServletRequest request) {	
		
		AnoMes anoMes = new AnoMes();
		
		String mesAnoString = request.getParameter("mesAnoString");		
		
		String[] mesAnoArray = mesAnoString.split(Pattern.quote(","));
		anoMes.setMes(Integer.parseInt(mesAnoArray[0]));
		anoMes.setAno(Integer.parseInt(mesAnoArray[1]));
				
		// Recebe usuário logado
		Usuario usuarioTmp = this.usuarioService.getUsuarioLogado();		
						
		// Busca todas as contas do usuário logado
		List<Conta> contasUsuario = new ArrayList<Conta>();
		contasUsuario = this.contaService.buscaContasPorUsuario(usuarioTmp);
						
		// Calcula informações de contas do usuário
		List<Transacao> transacoesContaPorMes = new ArrayList<Transacao>();
		ContaInfoOnline contaInfoOnline;
		List<ContaInfoOnline> contasInfoOnline = new ArrayList<ContaInfoOnline>();
		for(Conta conta : contasUsuario) {	
			
			contaInfoOnline = new ContaInfoOnline();
			transacoesContaPorMes = new ArrayList<Transacao>();
								
			// Busca Transacoes relacionadas a conta no mes selecionado
			transacoesContaPorMes.addAll(this.transacaoService.buscaTransacoesPorMesAnoConta(anoMes.getMes()+1, anoMes.getAno(), conta));
			
			
			// Efetua calculos e coloca no objeto ContaInfoOnline
			BigDecimal despesa = new BigDecimal(0);
			BigDecimal receita = new BigDecimal(0);			
			BigDecimal saldo = new BigDecimal(0);
			for(Transacao transacao : transacoesContaPorMes) {
												
				if(transacao.getTipoTransacao().equals(TipoTransacao.DESPESA)) {
					
					despesa = despesa.add(transacao.getValor());					
					
				} else if (transacao.getTipoTransacao().equals(TipoTransacao.RECEITA)) {
					
					receita = receita.add(transacao.getValor());
				}
				
			}
			
			saldo = receita.subtract(despesa);
			
			
			contaInfoOnline.setConta(conta);
			contaInfoOnline.setDespesasPeriodo(despesa);
			contaInfoOnline.setReceitasPeriodo(receita);
			contaInfoOnline.setSaldoPeriodo(saldo);
			
			contasInfoOnline.add(contaInfoOnline);
			
		}
		
		// Cria uma conta temporaria para calcular as receitas e despesas totais juntando todas as contas		
		BigDecimal despesaPeriodo = new BigDecimal(0);
		BigDecimal receitaPeriodo = new BigDecimal(0);			
		BigDecimal saldoPeriodo = new BigDecimal(0);
		BigDecimal saldoAtual = new BigDecimal(0);
		for(ContaInfoOnline contaInfoOnlineTmp : contasInfoOnline) {
			
			despesaPeriodo = despesaPeriodo.add(contaInfoOnlineTmp.getDespesasPeriodo());
			receitaPeriodo = receitaPeriodo.add(contaInfoOnlineTmp.getReceitasPeriodo());
			saldoAtual = saldoAtual.add(contaInfoOnlineTmp.getConta().getSaldo());
			
		}
		saldoPeriodo = receitaPeriodo.subtract(despesaPeriodo);
		
		Conta contaTotal = new Conta();
		contaTotal.setId(0);
		contaTotal.setUsuario(usuarioTmp);
		contaTotal.setSaldo(saldoAtual);
		contaTotal.setNome("Todas as Contas");
		
		ContaInfoOnline contaInfoOnlineTmp = new ContaInfoOnline();
		contaInfoOnlineTmp.setConta(contaTotal);
		contaInfoOnlineTmp.setDespesasPeriodo(despesaPeriodo);
		contaInfoOnlineTmp.setReceitasPeriodo(receitaPeriodo);
		contaInfoOnlineTmp.setSaldoPeriodo(saldoPeriodo);
		
		contasInfoOnline.add(contaInfoOnlineTmp);
		
		
		
		
		Locale localeBR = new Locale("pt", "BR");
		DateFormat fmtMesNome = new SimpleDateFormat("MMMM yyyy", localeBR);
		String mesAnoSelecionado = fmtMesNome.format(transacoesContaPorMes.get(0).getData());
		
		
		ModelAndView mv = new ModelAndView("InformacoesContas");
		mv.addObject("contasInfoOnline", contasInfoOnline);
		mv.addObject("mesAnoSelecionado", mesAnoSelecionado);
		return mv;
	}
	
	
	/*
	 * Retorna um array para ser usado no gráfico de contas com o total
	 * de despesas e receitas de um período de um ano 
	 * 6 meses antes do atual e 6 meses depois do atual
	 */
	@RequestMapping(value = "/graficoRecDesp", method = RequestMethod.GET)
	@ResponseBody	
	public ModelAndView geraInfoGraficoReceitasDespesas(HttpServletRequest request) {	
	
		// Recebe usuário logado
		Usuario usuarioTmp = this.usuarioService.getUsuarioLogado();		
						
		// Busca todas as contas do usuário logado
		List<Conta> contasUsuario = new ArrayList<Conta>();
		contasUsuario = this.contaService.buscaContasPorUsuario(usuarioTmp);
						
		// Calcula informações de contas do usuário
		List<Transacao> transacoesContaPorMes = new ArrayList<Transacao>();
		ContaInfoOnline contaInfoOnline;
		List<ContaInfoOnline> contasInfoOnline = new ArrayList<ContaInfoOnline>();
		List<ContaInfoOnline> totaisMesesGrafico = new ArrayList<ContaInfoOnline>();
		
				
		/*
		 * Faz um loop com 6 meses antes até 6 meses depois do anoMes atual
		 */
		
		Locale localeBR = new Locale("pt", "BR");
		DateFormat fmtMesNome = new SimpleDateFormat("MMM yyyy", localeBR);
				
		contasInfoOnline = new ArrayList<ContaInfoOnline>();
		
		Calendar data = Calendar.getInstance();
		data.add(Calendar.MONTH, -6);
		
		AnoMes anoMes = new AnoMes();
		anoMes.setMes(data.get(Calendar.MONTH));
		anoMes.setAno(data.get(Calendar.YEAR));
		anoMes.setMesAnoString(fmtMesNome.format(data.getTime()));
		
		for(int i = 1; i <= 12; i++) {
				
			for(Conta conta : contasUsuario) {	
				
				
				
				contaInfoOnline = new ContaInfoOnline();
				transacoesContaPorMes = new ArrayList<Transacao>();				
				
				// Busca Transacoes relacionadas a conta no mes selecionado
				transacoesContaPorMes.addAll(this.transacaoService.buscaTransacoesPorMesAnoConta(anoMes.getMes(), anoMes.getAno(), conta));
				
				
				// Efetua calculos e coloca no objeto ContaInfoOnline
				BigDecimal despesa = new BigDecimal(0);
				BigDecimal receita = new BigDecimal(0);			
				BigDecimal saldo = new BigDecimal(0);
				BigDecimal aplicacao = new BigDecimal(0);
				for(Transacao transacao : transacoesContaPorMes) {
					
					// Se a transacao não for transferencia entra na conta
					if(!transacao.isTransferencia()) {
						if(transacao.getTipoTransacao().equals(TipoTransacao.DESPESA)) {
							
							despesa = despesa.add(transacao.getValor());					
							
						} else if (transacao.getTipoTransacao().equals(TipoTransacao.RECEITA)) {
							
							receita = receita.add(transacao.getValor());
						}
					}
					if(transacao.isAplicacao()) {
						aplicacao = aplicacao.add(transacao.getValor());
					}
					
				}
				
				saldo = receita.subtract(despesa);
				
				
				contaInfoOnline.setConta(conta);
				contaInfoOnline.setDespesasPeriodo(despesa);
				contaInfoOnline.setReceitasPeriodo(receita);
				contaInfoOnline.setAplicacaoPeriodo(aplicacao);
				contaInfoOnline.setSaldoPeriodo(saldo);
				
				contasInfoOnline.add(contaInfoOnline);
				
			}
			
			// Cria uma conta temporaria para calcular as receitas e despesas totais do mes 
			// juntando todas as contas 		
			BigDecimal despesaPeriodo = new BigDecimal(0);
			BigDecimal receitaPeriodo = new BigDecimal(0);
			BigDecimal aplicacaoPeriodo = new BigDecimal(0);
			BigDecimal saldoPeriodo = new BigDecimal(0);
			BigDecimal saldoAtual = new BigDecimal(0);
			for(ContaInfoOnline contaInfoOnlineTmp : contasInfoOnline) {
				
				despesaPeriodo = despesaPeriodo.add(contaInfoOnlineTmp.getDespesasPeriodo());
				receitaPeriodo = receitaPeriodo.add(contaInfoOnlineTmp.getReceitasPeriodo());
				aplicacaoPeriodo = aplicacaoPeriodo.add(contaInfoOnlineTmp.getAplicacaoPeriodo());
				saldoAtual = saldoAtual.add(contaInfoOnlineTmp.getConta().getSaldo());
				
			}
		
			saldoPeriodo = receitaPeriodo.subtract(despesaPeriodo);
		
			Conta contaTotal = new Conta();
			contaTotal.setId(0);
			contaTotal.setUsuario(usuarioTmp);
			contaTotal.setSaldo(saldoAtual);
			contaTotal.setNome("Todas as Contas");
			
			ContaInfoOnline contaInfoOnlineTmp = new ContaInfoOnline();
			contaInfoOnlineTmp.setMesAno(anoMes.getMesAnoString());
			contaInfoOnlineTmp.setConta(contaTotal);
			contaInfoOnlineTmp.setDespesasPeriodo(despesaPeriodo);
			contaInfoOnlineTmp.setReceitasPeriodo(receitaPeriodo);
			contaInfoOnlineTmp.setAplicacaoPeriodo(aplicacaoPeriodo);
			contaInfoOnlineTmp.setSaldoPeriodo(saldoPeriodo);
			
			totaisMesesGrafico.add(contaInfoOnlineTmp);
			
			
			// Incrementa um mês ao mesAno verificado
			data.add(Calendar.MONTH, +1);					
			anoMes.setMes(data.get(Calendar.MONTH) + 1);
			anoMes.setAno(data.get(Calendar.YEAR));						
			anoMes.setMesAnoString(fmtMesNome.format(data.getTime()));
			
			contasInfoOnline = new ArrayList<ContaInfoOnline>();
		}		
		
		
		ModelAndView mv = new ModelAndView("graficoReceitasDespesas");
		mv.addObject("totaisMesesGrafico", totaisMesesGrafico);		
		return mv;

	}
	
}
