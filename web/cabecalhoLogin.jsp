<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <a class="navbar-brand" href="index.jsp">E L E V E N</a>
        </div>

        <div>
            <form class="navbar-form navbar-right" role="search" action="ServletValidaLogin" method="post">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="CPF" name="cpf">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Código da Conta" name="codigoConta">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" placeholder="Senha" name="senha">
                </div> 
                <button type="submit" class="btn btn-primary">Login</button>
            </form>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
<br><br>
