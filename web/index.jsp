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
        <%@ include file="cabecalhoLogin.jsp" %>
        <div class="jumbotron">
            <%@ include file="erros.jsp" %>
            <div class="page-header">
                <h1>Bem-vindo ao Banco Eleven.</h1>                
            </div>             
            <p>Seus problemas financeiros são a nossa alegria. <span class="glyphicon glyphicon-heart" aria-hidden="true"></span></p>
            <p>Comece hoje a investir seu dinheiro e fique cada vez mais pobre, enquanto ficamos cada vez mais ricos.</p> 
            <small>Atendemos somente pessoa física. Mais fácil de enganar e não ser processado.</small>
            <br>
            <h2>Faça já o seu cadastro! Basta clicar <a href="cadastroCliente.jsp">aqui</a>.</h2> 
            <h3>Já possui cadastro e deseja criar outra conta?! Clique <a href="cadastroConta.jsp">aqui</a>.</h3> 
        </div>
    </body>
</html>
