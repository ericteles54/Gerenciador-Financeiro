$(document).ready(function() {
	
	// Datatable
	var table = $('#contas').DataTable();
	new $.fn.dataTable.FixedHeader(table, {
		alwaysCloneTop : true
	});
});	
	
	

$(document).ready(function() {	
	// DataPicker
	$('#idData').datepicker({
        format: "yyyy/mm/dd",
        language: "pt-BR",
        autoclose: true,
    }).on('changeDate', function (ev) {
    	$(this).datepicker('hide');
    });
});





//Funcao Ajax para carregar mês selecionado pelo usuário
function mostrarInformacoesContasUsuario(mesAnoString) {
					
	var valorMesAnoString = mesAnoString;
				
	$.ajax({
        type: "GET",
        url: "/contas/infoContasUsuario",
        data: {"mesAnoString": valorMesAnoString},         
        success: function(response) {	            	
            $("#subViewDivInformacoesContas").html( response );	                
        }
    });
}


//Funcao Ajax para carregar mês selecionado pelo usuário
function mostrarMesAnoSelecionado(mesAnoString) {
		    				
	var index = mesAnoString.selectedIndex;
	var valorMesAnoString = mesAnoString.options[index].value;		
	
	$.ajax({
        type: "GET",
        url: "/transacoes/mesAno",
        data: {"mesAnoString": valorMesAnoString},            
        success: function(response) {	    	            	
            $("#subViewDivTabelaTransacoes").html( response );
            mostrarInformacoesContasUsuario(valorMesAnoString);
        }
    });			
}	   



//Carrega Tabela de transacao com mês/ano atual
function mostrarMesAnoSelecionadoInicializacaoPagina() {
				
	var today = new Date();
	var mm = today.getMonth();
	var yyyy = today.getFullYear();
	
	var valorMesAnoString = mm+","+yyyy;				
	 
	$.ajax({
		type: "GET",
        url: "/transacoes/mesAno",
        data: {"mesAnoString": valorMesAnoString},            
        success: function(response) {	            	
            $("#subViewDivTabelaTransacoes").html( response );
            mostrarInformacoesContasUsuario(valorMesAnoString);
        }
	});
}	
