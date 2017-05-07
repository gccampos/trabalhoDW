package servlet;

import valida.ValidaEmail;
import valida.ValidaCpf;
import model.Cliente;
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

@WebServlet(name = "ServletCadastroCliente", urlPatterns = {"/ServletCadastroCliente"})
public class ServletCadastroCliente extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String nome = request.getParameter("nome");
            String cpf = request.getParameter("cpf");
            String email = request.getParameter("email");
            String endereco = request.getParameter("endereco");
            String telefone = request.getParameter("telefone");

            //validação dos dados inseridos
            boolean cpfIsValid = ((cpf != null) && ValidaCpf.isValidCPF(cpf));
            boolean nomeIsValid = (nome != null);
            boolean telefoneIsValid = (telefone != null);
            boolean emailIsValid = (email != null);
            boolean enderecoIsValid = (endereco != null);

            if (emailIsValid) {
                emailIsValid = new ValidaEmail().validate(email);
            }

            boolean cadastroIsValid = cpfIsValid && nomeIsValid && telefoneIsValid && emailIsValid && enderecoIsValid;

            //se os dados forem validados, cria cadastro  
            if (cadastroIsValid) {
                Cliente cliente = new Cliente(cpf, nome, email, endereco, telefone);
                cliente.addCliente();
                response.sendRedirect("sucessoCadastroCliente.jsp");
            } else {
                //se os dados não forem validados, retorna para a página anterior
                String erro = "Houve algum problema com seu cadastro! Por favor, preencha o formulário abaixo novamente conforme as recomendações em cada campo.";
                request.setAttribute("erro", erro);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/cadastroCliente.jsp");
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
            Logger.getLogger(ServletCadastroCliente.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ServletCadastroCliente.class.getName()).log(Level.SEVERE, null, ex);
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
