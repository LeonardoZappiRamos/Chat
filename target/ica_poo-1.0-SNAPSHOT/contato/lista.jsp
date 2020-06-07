<%-- 
    Document   : lista
    Created on : 03/06/2020, 18:04:13
    Author     : leoza
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Contatos</title>
    </head>
    <body>
        <a href="ContatoServlet?acao=abreCadastro">Cadastro</a>
        
        <h1>Contatos</h1>
        
        <table>
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Nome</th>
                    <th>Telefone</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${lista}" var="p">
                    <tr>
                        <td>${p.id}</td>
                            <td>${p.nome}</td>
                            <td>${p.telefone}</td>
                        <td><a href="ContatoServlet?acao=exclui&id=${p.id}">Excluir</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>