<%-- 
    Document   : atualiza
    Created on : 10/06/2020, 12:50:09
    Author     : leoza
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Atualiza</title>
        <style>
            *{
            margin: 0;
            padding: 0;
            background-color: #f0f0f5;
        }
        #cadastra{
            height: 100vh;
        }
        
        #cadastra .content{
            width: 90%;
            max-width: 1100px;
            height: 100%;
            margin: 0 auto;
            display: flex;
            flex-direction: column;
        }
        
        #cadastra .header{
            display: flex;
            justify-content: space-between;
            align-items: center;
            height: 80px;
        }
        
        #cadastra .header a{
            text-decoration: none;
        }
        
        #cadastra form{
            margin-top: 40px;
            display: flex;
            flex-direction: column;
            width: 400px;
        }
        </style>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    
    </head>
    <body>
        <div id="cadastra">
        <div class="content">
            <div class="header">
            <h1>Altera Contato</h1>
            <a href="ContatoServlet?acao=lista">Lista</a>
            </div>
            <form action="ContatoServlet" method="post">
                <div class="form-group">
                    <label for="Nome">nome: </label>
                    <input type="text" name="nome" class="form-control" value="${contato.nome}" />
                </div>
                <div class="form-group">
                    <label for="Telefone">telefone: </label>
                    <input type="text" name="telefone" class="form-control" value="${contato.telefone}" />
                </div>
                <button type="submit" class="btn btn-secondary">Salvar</button>
                <input type="hidden" name="id" value="${contato.id}" />
                <input type="hidden" name="acao" value="acaoAtualizar" />
            </form>
        </div>
        </div>
    </body>
</html>
