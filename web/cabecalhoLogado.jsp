<%@page import="br.uff.ic.Conta"%>
<%@page import="br.uff.ic.Cliente"%>
<%@page import="br.uff.ic.ContaCliente"%>
<% ContaCliente cc = (ContaCliente) session.getAttribute("contaCliente");
    Cliente cliente = Cliente.getCliente(cc.getCpfCliente());
    Conta conta = Conta.getConta(cc.getCodigoConta());%>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <a class="navbar-brand" href="home.jsp">E L E V E N</a>
        </div>

        <div>
            <form class="navbar-form navbar-right" role="search" action="ServletSair" method="post">                
                <button type="submit" class="btn btn-default">Sair</button>
            </form>
        </div><!-- /.navbar-collapse -->

    </div><!-- /.container-fluid -->
</nav>
<br><br>



