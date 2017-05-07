<%@page import="br.uff.ic.Investimento"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet">
        <title>Banco Eleven</title>
    </head>
    <body>
    <body>
        <%@ include file="cabecalhoLogado.jsp" %>
        <div class="jumbotron">
            <div class="container">
                <%@ include file="erros.jsp" %>
                <div class="row">

                    <div class="col-md-3">
                        <%@ include file="menu.jsp" %>
                    </div>

                    <div class="col-md-9">
                        <div class="page-header">
                            <h1>Investimento</h1>                
                        </div> 

                        <% Investimento investimento = conta.getInvestimento();
                            if (investimento == null) {%>                        
                        <p>Você ainda não inicializou nosso serviço de investimentos.</p>
                        <small>Para começar a investir basta fazer uma aplicação: </small>                        
                        <% } else {%>                        
                        <p>Seu rendimento atual é: R$ <%= investimento.getRentabilidadeMinutos()%></p>  
                        <% } %>

                        <form method="post" role="form" action="ServletInvestimento">

                            <label class="radio-inline"><input type="radio" placeholder="Nome" name="opcao" value="aplicacao">Aplicação</label>

                            <% if (investimento != null) {%>  
                            <label class="radio-inline"><input type="radio" placeholder="Nome" name="opcao" value="resgate">Resgate</label>
                                <% }%>
                            <div class="form-group">
                                <label for="valor">Valor: </label>
                                <input type="text" class="form-control" id="valor" placeholder="Nome" name="valor">
                            </div>
                            <button type="submit" class="btn btn-success">Confirmar</button>
                        </form>

                    </div>
                </div>
            </div>
        </div>
    </body>
</body>
</html>
