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
                <h1>Criação de Conta
                    <small>Preencha os campos abaixo para criar sua nova conta.</small>
                </h1>
            </div> 
            <%@ include file="erros.jsp" %>
            <form method="post" role="form" action="ServletCadastroConta">                
                <div class="form-group">
                    <label for="cpf">CPF: </label>
                    <small>(Apenas números)</small>
                    <input type="text" class="form-control" id="cpf" placeholder="CPF" name="cpf">
                </div>                
                <div class="form-group">
                    <label for="senha">Senha: </label>
                    <input type="password" class="form-control" id="senha" placeholder="Senha" name="senha">
                </div>    
                <div class="form-group">
                    <label for="senhaConfirma">Confirme sua senha: </label>
                    <input type="password" class="form-control" id="senhaconfirma" placeholder="Digite sua senha novamente" name="senhaConfirma">
                </div>     
                <button type="submit" class="btn btn-primary">Cadastrar</button>
            </form>
        </div>
    </body>
</html>
