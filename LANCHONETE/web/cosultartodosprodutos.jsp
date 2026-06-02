<%-- 
    Document   : resultadocosultaratualizar
    Created on : 8 de set. de 2025, 17:30:04
    Author     : proft
--%>

<%@page import="model.Produto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Resultado Cosultar By ID</title>
    </head>
    <body>
        <h1>Resultado Consultar By ID</h1>
        <%Produto p = (Produto) request.getAttribute("prod");%>
        <%if (p.getDescricao() != null) {%>

        <form name="f1" action="controle_produto" method="GET">
            ID..........: <%=p.getId()%> <input type="hidden" name="txtid" value="<%=p.getId()%>"> <BR>
            Descrição...: <input type="text" name="txtdescricao" value="<%=p.getDescricao()%>">  <BR>
            Preço.......: <input type="text" name="txtpreco" value="<%=p.getPreco()%>">  <BR>
            <input type="submit" name="op" value="EfetivarAtualizar">
        </form>
        <%} else {%>
        <h2>Produto não encontrado.</h2>
        <%}%>
    </body>
</html>
