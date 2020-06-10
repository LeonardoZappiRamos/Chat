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
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
        <title>Contatos</title>
        
        <style>
            *{
            margin: 0;
            padding: 0;
            background-color: #f0f0f5;
        }
        #page_home{
            height: 100vh;
        }
        
        #page-home .content{
            width: 90%;
            max-width: 1100px;
            height: 100%;
            margin: 0 auto;
            display: flex;
            flex-direction: column;
        }
        
        #page-home .header{
            display: flex;
            justify-content: space-between;
            align-items: center;
            height: 80px;
        }
        
        #page-home .header a{
            text-decoration: none;
        }
        
        #page_home main{
            max-width: 560px;   
            flex: 1;
            display: flex;
            flex-direction: column;
            justify-content: center;
        }
        </style>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    </head>
    <body>
        <div id="page-home">
            <div class="content">
                <div class="header">
                    <h1>Contatos</h1>
                    <a href="ContatoServlet">
                        Home
                    </a>
                </div>
                <main>
                    <table class="table">
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
                                    <td><a href="ContatoServlet?acao=atualizar&id=${p.id}&nome=${p.nome}&telefone=${p.telefone}">Editar</a></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    
                    <a href="ContatoServlet?acao=abreCadastro">Novo Contato</a>
                </main>
            </div>
        </div>
    </body>
</html>