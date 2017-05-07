package servlet;

import model.ContaCliente;
import model.Conta;
import model.Cliente;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ServletCadastroConta", urlPatterns = {"/ServletCadastroConta"})
public class ServletCadastroConta extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String cpf = request.getParameter("cpf");
            String senha = request.getParameter("senha");
            String senhaConfirma = request.getParameter("senhaConfirma");

            //validação dos dados inseridos
            boolean senhaIsValid = senha.equals(senhaConfirma);
            boolean cpfIsValid = (Cliente.getCliente(cpf) != null);

            boolean cadastroIsValid = senhaIsValid && cpfIsValid;

            if (cadastroIsValid) {
                Conta conta = new Conta();
                int codigoConta = conta.addConta();
                if (codigoConta != 0) {
                    request.setAttribute("codigoConta", codigoConta);
                    ContaCliente contaCliente = new ContaCliente(cpf, codigoConta, senha);
                    contaCliente.addContaCliente();
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/sucessoCadastroConta.jsp");
                    if (dispatcher != null) {
                        dispatcher.forward(request, response);
                    }
                }
            } else {
                //se os dados não forem validados, retorna para a página anterior
                String erro = "Ops! Houve algum problema com a criação da sua conta... ";
                if (!cpfIsValid) {
                    erro += "Não achamos seu CPF no nosso banco de clientes! Você precisa se cadastrar antes de criar uma conta.";
                } else if (!senhaIsValid) {
                    erro += "Sua senha não foi confirmada corretamente! Tente de novo.";
                }
                request.setAttribute("erro", erro);

                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/cadastroConta.jsp");
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
    }

}
