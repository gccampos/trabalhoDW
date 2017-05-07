package servlet;

import model.ContaCliente;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ServletValidaLogin", urlPatterns = {"/ServletValidaLogin"})
public class ServletValidaLogin extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String cpfCliente = request.getParameter("cpf");
            String senha = request.getParameter("senha");
            int codigoConta = Integer.parseInt(request.getParameter("codigoConta"));

            //busca na tabela ContaCliente pelo cpf e codigoConta
            //se existir, valida senha
            ContaCliente cc = ContaCliente.getContaCliente(cpfCliente, codigoConta, senha);
            if (cc != null) {
                request.getSession().setAttribute("contaCliente", cc);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/home.jsp");
                if (dispatcher != null) {
                    dispatcher.forward(request, response);
                }
            } else {
                String erro = "Ops! Login incorreto. Tente de novo... ";
                request.setAttribute("erro", erro);

                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
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
            Logger.getLogger(ServletValidaLogin.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ServletValidaLogin.class.getName()).log(Level.SEVERE, null, ex);
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
