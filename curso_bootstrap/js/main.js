/* Scripts personalizados para o site */

// Função para tratar e abrir o modal na abertura da página
$(function(){
        // equivalente ao load da pagina

        $("#modal01").modal({backdrop:false, keyboard:false}); // Impede fechamento da janela com ESC

        $("#modal01").modal("show");

        setTimeout(function(){
                $("#modal01").modal("hide");
        }, 3000);

});


