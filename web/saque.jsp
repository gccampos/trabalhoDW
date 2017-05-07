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
                            <h1>Saque</h1>
                        </div>                
                        <form method="post" role="form" action="ServletSaque">
                            <div class="form-group">
                                <label for="valor">Valor do Saque: </label>
                                <input type="text" class="form-control" id="valor" placeholder="Valor do Saque" name="valor">
                            </div>                                         
                            <button type="submit" class="btn btn-primary">Confirmar</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
