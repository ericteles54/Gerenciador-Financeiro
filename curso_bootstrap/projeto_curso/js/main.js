/* Scripts personalizados para o site */

// equivalente ao load da pagina
$(function(){

        // Função para tratar e abrir o modal na abertura da página
        $("#modal01").modal({backdrop:false, keyboard:false}); // Impede fechamento da janela com ESC

        $("#modal01").modal("show");

        setTimeout(function(){
                $("#modal01").modal("hide");
        }, 3000);




        // Funcao para tratar o tooltip
        $(".ttp").tooltip({
                animation:false,
                delay : { show : 500, hide : 5000 },
                title : "Titulo padrao!",
                trigger : 'click'
        });

        // Funcao para tratar o popover
        $(".ppv").popover({
                title : "Titulo javascript do popover",
                trigger : 'hover focus'
        });


        // Funcao para tratar checkbox button com javascript
        $(".btnjs").button({

        });


        // Funcao para tratar botao com troca de estado
        $("#troca-estado").click(function(){
                var btn = $(this);
                btn.button("loading");

                setTimeout(function(){
                        btn.button("reset");
                }, 3000);
        });

});


