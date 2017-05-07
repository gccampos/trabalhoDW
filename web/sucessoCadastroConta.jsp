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
            <div class="page-header">
                <h1>Sucesso!</h1>                
            </div> 
            <p>Parabéns! Sua conta foi criada.</p>
            <p>O código da sua conta é: <%= request.getAttribute("codigoConta")%></p>                
            <div class="alert alert-warning">
                <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                <small>Para logar em nosso ATM, você precisará do seu CPF, a senha escolhida e o código da conta! Guarde bem essas informações.</small>
            </div>
        </div>
    </body>
</html>
