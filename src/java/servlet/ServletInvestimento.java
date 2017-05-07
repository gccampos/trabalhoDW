package servlet;

import model.Investimento;
import model.Resgate;
import model.Aplicacao;
import model.Cliente;
import model.Conta;
import model.ContaCliente;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ServletInvestimento", urlPatterns = {"/ServletInvestimento"})
public class ServletInvestimento extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            Double valor = Double.parseDouble(request.getParameter("valor"));
            String opcao = request.getParameter("opcao"), erro = null;

            ContaCliente cc = (ContaCliente) request.getSession().getAttribute("contaCliente");
            Conta contaOrigem = Conta.getConta(cc.getCodigoConta());
            Cliente cliente = Cliente.getCliente(cc.getCpfCliente());
            Investimento investimento = contaOrigem.getInvestimento();

            if (investimento == null) {
                investimento = new Investimento(0.0, contaOrigem.getCodigo());
                investimento.addInvestimento();
            }

            if ("aplicacao".equals(opcao)) {
                Aplicacao aplicacao = new Aplicacao(0, cliente.getCpf(), valor, contaOrigem.getSaldo(), contaOrigem.getCodigo(), new Date());
                if (aplicacao.verificaSaldoFinalOrigem()) {
                    int codigoTransacao = aplicacao.addTransacao(4, 0, 0.0);
                    if (codigoTransacao != 0) {
                        contaOrigem.updateSaldoAplicacao(aplicacao.getValor());
                        aplicacao.doAplicacao();
                    } else {
                        erro = "Houve algum problema com nosso sistema e sua aplicaçao nao foi computada! Tente novamente, por favor.";
                    }
                } else {
                    erro = "Saldo insuficiente!";
                }
            } else if ("resgate".equals(opcao)) {
                Resgate resgate = new Resgate(0, cliente.getCpf(), valor, contaOrigem.getSaldo(), contaOrigem.getCodigo(), new Date());
                if (resgate.verificaValorFinalInvestimento(investimento)) {
                    int codigoTransacao = resgate.addTransacao(5, 0, 0.0);
                    if (codigoTransacao != 0) {
                        contaOrigem.updateSaldoResgate(resgate.getValor());
                        resgate.doResgate();
                    } else {
                        erro = "Houve algum problema com nosso sistema e sua aplicaçao nao foi computada! Tente novamente, por favor.";
                    }
                } else {
                    erro = "Você não possui esse valor em investimentos para resgatar!";
                }
            }

            if (erro == null) {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/home.jsp");
                if (dispatcher != null) {
                    dispatcher.forward(request, response);
                }
            } else {
                erro = "Ops... " + erro;
                request.setAttribute("erro", erro);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/investimento.jsp");
                if (dispatcher != null) {
                    dispatcher.forward(request, response);
                }
            }

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ServletInvestimento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ServletInvestimento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
