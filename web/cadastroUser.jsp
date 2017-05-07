<%-- 
    Document   : cadastroUser
    Created on : May 7, 2017, 12:14:05 AM
    Author     : Salle
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet">
        <title>Trabalho-DW</title>
    </head>
    <body>
        <%@ include file="cabecalhoLogin.jsp" %>
        <div class="jumbotron">  
            <div class="page-header">
                <h1>Cadastro de Usuário 
                    <small>Preencha os campos abaixo para criar seu cadastro.</small>
                </h1>
            </div> 
            <%@ include file="erros.jsp" %>
            <form method="post" role="form" action="ServletCadastroCliente">
                <div class="form-group">
                    <label for="nome">Nome: </label>
                    <input type="text" class="form-control" id="nome" placeholder="Nome" name="nome">
                </div>
                <div class="form-group">
                    <label for="email">E-mail </label>
                    <small><em>(nome@exemplo.com)</em>:</small>
                    <input type="text" class="form-control" id="email" placeholder="E-mail" name="email">
                </div>
                <div class="form-group">
                    <label for="cpf">CPF </label>
                    <small><em>(Apenas números)</em>:</small>
                    <input type="text" class="form-control" id="cpf" placeholder="CPF" name="cpf">
                </div>
                <div class="form-group">
                    <label for="endereco">Endereço: </label>
                    <input type="text" class="form-control" id="endereco" placeholder="Endereço" name="endereco">
                </div>
                <div class="form-group">
                    <label for="telefone">Telefone Residencial: </label>
                    <input type="text" class="form-control" id="telefone" placeholder="Telefone Residencial" name="telefone">
                </div>                            
                <button type="submit" class="btn btn-primary">Cadastrar</button>
            </form>
        </div>
    </body>
</html>
