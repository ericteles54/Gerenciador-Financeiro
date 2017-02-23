//Carrega Gráfico de Receitas e despesas período
//function mostrarGraficoReceitasDespesasInicializacaoPagina() {

$(document).ready(function() {	
	
	var today = new Date();
	var mm = today.getMonth();
	var yyyy = today.getFullYear();
	
	var valorMesAnoString = mm+","+yyyy;				
	 
	$.ajax({			
		type: "GET",
        url: "/contas/graficoRecDesp",
        data: {"mesAnoString": valorMesAnoString},                
        success: function(response) {	            	
            $("#subViewDivGraficoContas").html( response );   
        },
        
	});
	
	
});