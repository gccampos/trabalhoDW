package servlet;

import model.Saque;
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

@WebServlet(name = "ServletSaque", urlPatterns = {"/ServletSaque"})
public class ServletSaque extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            double valor = Double.parseDouble(request.getParameter("valor"));

            ContaCliente cc = (ContaCliente) request.getSession().getAttribute("contaCliente");
            Cliente cliente = Cliente.getCliente(cc.getCpfCliente());
            Conta contaOrigem = Conta.getConta(cc.getCodigoConta());

            Saque saque = new Saque(0, cliente.getCpf(), valor, contaOrigem.getSaldo(), contaOrigem.getCodigo(), new Date());
            if (saque.verificaSaldoFinalOrigem()) {
                int codigoTransacao = saque.addTransacao(1, 0, 0.0);
                if (codigoTransacao != 0) {
                    saque.doSaque();
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/home.jsp");
                    if (dispatcher != null) {
                        dispatcher.forward(request, response);
                    }
                }
            } else {
                String erro = "Saldo insuficiente!";
                request.setAttribute("erro", erro);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/saque.jsp");
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
            Logger.getLogger(ServletSaque.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ServletSaque.class.getName()).log(Level.SEVERE, null, ex);
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
