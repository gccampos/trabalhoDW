<% String erro = (String) request.getAttribute("erro");
    if (erro != null) {%>
<div class="alert alert-danger" role="alert">
    <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
    <span class="sr-only">Erro:</span>
    <%= erro%>
</div>
<% }%>
