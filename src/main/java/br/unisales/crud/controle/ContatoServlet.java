/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unisales.crud.controle;

import br.unisales.crud.modelo.Contato;
import br.unisales.crud.persistencia.MySqlFactory;
import br.unisales.crud.repositorio.ContatoDAO;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
@WebServlet(name = "ContatoServlet", urlPatterns = {"/ContatoServlet"})
public class ContatoServlet extends HttpServlet {
 
//dao para operacionalizar as ações
protected ContatoDAO dao;
//lista a ser usada nas ações
protected List<Contato> lista;
//entidade a ser usada nas ações
protected Contato t;
//diretório das páginas
protected String diretorio = "contato";
//página padrão para o crud
protected String pagina = "index.html";
 
//cria a tabela contato
static {
    Connection conexao = MySqlFactory.getCon();
    PreparedStatement ps = null;
    try {
        String comando = "CREATE TABLE if not exists contato (\n"
                        + " id INT NOT NULL AUTO_INCREMENT,\n"
                        + " nome VARCHAR(250) NOT NULL,\n"
                        + " telefone VARCHAR(12) NOT NULL,\n"
                        + " PRIMARY KEY (id));";
        ps = conexao.prepareStatement(comando);
        ps.executeUpdate();
        conexao.commit();

    } catch (Exception ex) {
        try {
            conexao.rollback();
        } catch (Exception e) {
              
            }
        
            throw new RuntimeException(ex);
    } finally {
        try {
            if (ps != null) {
                ps.close();
            }
            if (conexao != null) {
                conexao.close();
            }
            } catch (Exception e) {

        }
    }
}
 
/**
* método construtor
*/
public ContatoServlet() {
this.dao = new ContatoDAO(MySqlFactory.getInstancia());
this.diretorio = "/contato/";
}

protected void processaAcao(HttpServletRequest request, 
                            HttpServletResponse response) throws ServletException, 
                                                                 IOException {
    try {
        request.setCharacterEncoding("UTF8");
        this.t = new Contato();
        this.t.setId(Integer.parseInt((request.getParameter("id") != null) ? request.getParameter("id") : "-1"));
        this.t.setNome(request.getParameter("nome"));
        this.t.setTelefone(request.getParameter("telefone"));
    } catch (Exception ex) {

    }
    
    try {
    //captura a ação a ser efetivada
    String acao = (request.getParameter("acao") != null) ? request.getParameter("acao") : "home";
        try {
            //faz o tratamento da ação padrão
            switch (acao) {
                case "abreCadastro":
                    pagina = "Cadastro.html";
                break;

                case "cadastra":
                    dao.insere(this.t);
                    this.lista = this.dao.selecionaVarios();
                    request.setAttribute("lista", this.lista);
                    pagina = "lista.jsp";
                break;

                case "exclui":
                    dao.exclui(this.t);
                    this.lista = this.dao.selecionaVarios();
                    request.setAttribute("lista", this.lista);
                    pagina = "lista.jsp";
                break;        

                case "lista":
                    this.lista = this.dao.selecionaVarios();
                    request.setAttribute("lista", this.lista);
                    pagina = "lista.jsp";
                break;

                case "seleciona":
                    this.t = this.dao.seleciona(Integer.parseInt(request.getParameter("id")));
                    pagina = "home.html";
                break;

                default:
                pagina = "home.html";
            }
        } catch (Exception ex) {
            request.setAttribute("erro", ex);
            pagina = "erro.jsp";
        }
    //efetua o despacho da requisição para a página correta com os atributos devidamente setados
    RequestDispatcher dispatcher = request.getRequestDispatcher(diretorio + pagina);
    
    dispatcher.forward(request, response);
    
    } catch (Exception ex) {

    }
}
 
@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
this.processaAcao(request, response);
}
 
@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
this.processaAcao(request, response);
}
}
