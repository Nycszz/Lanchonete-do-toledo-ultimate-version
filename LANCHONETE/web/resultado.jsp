<%-- 
    Document   : resultado
    Created on : 8 de set. de 2025, 17:30:04
    Author     : proft
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Página Resultado</title>
    </head>
    <body>
        <%String msg = (String) request.getAttribute("message");%>
        
        <h1><%=msg%> realizado com sucesso.</h1>
    </body>
</html>
