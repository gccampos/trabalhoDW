<%@page import="java.util.List"%>
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
                            <h1>Conta Conjunta</h1>
                        </div> 

                        <% if (!cc.contaIsConjunta()) {%>                        
                        <p>Sua conta ainda não é conjunta.</p>
                        <small><em>Para adicionar outro cliente a esta conta basta preencher o formulário abaixo: </em></small>                        
                        <% } else {%>                        
                        <small>Clientes que usam esta conta: </small>  
                        <table class="table table-bordered table-condensed">
                            <thead>
                                <tr>
                                    <th>Nome</th>
                                    <th>CPF</th>                                    
                                </tr>
                            </thead>
                            <tbody>
                                <%  List<Cliente> clientes = cc.getClientes();
                                    for (Cliente clienteConta : clientes) {%>
                                <tr>
                                    <td><%= clienteConta.getNome()%></td>
                                    <td><%= clienteConta.getCpf()%></td>                                    
                                </tr>
                                <% }%>
                            </tbody>
                        </table>
                        <% }%>
                        <hr>
                        <form method="post" role="form" action="ServletContaConjunta">                            
                            <div class="form-group">
                                <label for="cpf">CPF do novo cliente da conta: </label>                                
                                <input type="text" class="form-control" id="cpf" placeholder="CPF (Apenas números)" name="cpf">
                                <small>(Para adicionar um cliente a esta conta, ele deve antes estar cadastrado em nosso banco.) </small>
                            </div>
                            <div class="form-group">
                                <label for="senha">Senha de acesso do novo cliente da conta: </label>
                                <input type="password" class="form-control" id="senha" placeholder="Senha" name="senha">
                            </div>
                            <button type="submit" class="btn btn-success">Confirmar</button>
                        </form>

                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
