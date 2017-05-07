package servlet;

import model.Deposito;
import model.Conta;
import model.Cliente;
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

@WebServlet(name = "ServletDeposito", urlPatterns = {"/ServletDeposito"})
public class ServletDeposito extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String erro = null;
            double valor = Double.parseDouble(request.getParameter("valor"));
            int codigoContaDestino = Integer.parseInt(request.getParameter("codigoContaDestino"));

            ContaCliente cc = (ContaCliente) request.getSession().getAttribute("contaCliente");
            Cliente cliente = Cliente.getCliente(cc.getCpfCliente());
            Conta contaOrigem = Conta.getConta(cc.getCodigoConta());
            Conta contaDestino = Conta.getConta(codigoContaDestino);

            if (contaDestino != null) {
                Deposito deposito = new Deposito(0, cliente.getCpf(), valor, contaOrigem.getSaldo(), contaOrigem.getCodigo(), new Date(), contaDestino.getCodigo(), contaDestino.getSaldo());
                int codigoTransacao = deposito.addTransacao(2, deposito.getCodigoContaDestino(), deposito.getSaldoInicialDestino());
                if (codigoTransacao != 0) {
                    deposito.doDeposito();

                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/home.jsp");
                    if (dispatcher != null) {
                        dispatcher.forward(request, response);
                    }
                } else {
                    erro = "Houve algum problema com nosso sistema e sua aplicaçao nao foi computada! Tente novamente, por favor.";
                }
            } else {
                erro = "Essa conta de destino não existe. Tem certeza que este é o código certo?";
            }

            if (erro != null) {
                erro = "Ops... " + erro;
                request.setAttribute("erro", erro);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/deposito.jsp");
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
            Logger.getLogger(ServletDeposito.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ServletDeposito.class.getName()).log(Level.SEVERE, null, ex);
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
