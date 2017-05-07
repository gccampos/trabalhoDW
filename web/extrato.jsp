<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="br.uff.ic.Transacao"%>
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
                <div class="row">

                    <div class="col-md-3">
                        <%@ include file="menu.jsp" %>
                    </div>

                    <div class="col-md-9">
                        <div class="page-header">
                            <h1>Extrato</h1>
                        </div> 
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>Data</th>
                                    <% if (cc.contaIsConjunta()){ %>
                                    <th>Cliente</th>
                                    <% } %>
                                    <th>Operação</th>
                                    <th>Valor</th>
                                    <th>Saldo</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% List<Transacao> 
                                    transacoes = conta.getTransacoes();
                                    String classe = null;
                                    for (Transacao transacao : transacoes) {
                                        classe = transacao.defineCor(conta.getCodigo());
                                %>
                                <tr class="<%= classe%>">
                                    <td><%= new SimpleDateFormat("dd-MM-yyyy").format(transacao.getCreatedAt())%></td>
                                    <% if (cc.contaIsConjunta()){ %>
                                    <td><%= Cliente.getCliente(transacao.getCpfCliente()).getNome() %></td>
                                    <% } %>
                                    <td><%= transacao.getTipoTransacao()%></td>
                                    <td><%= transacao.getValor()%></td>
                                    <td><%= transacao.getSaldoFinal(conta.getCodigo())%></td>
                                </tr>
                                <% }%>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
