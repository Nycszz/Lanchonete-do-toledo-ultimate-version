<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="model.Cliente"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Clientes</title>

    <style>
        body {
            margin: 0;
            font-family: 'Segoe UI';
            background: url('https://images.unsplash.com/photo-1550547660-d9450f859349') no-repeat center center fixed;
            background-size: cover;
        }

        .overlay {
            background: rgba(0,0,0,0.78);
            min-height: 100vh;
            padding: 30px;
            color: white;
        }

        h1 {
            text-align: center;
            margin-bottom: 25px;
        }

        .container {
            max-width: 900px;
            margin: auto;
            background: white;
            color: black;
            padding: 25px;
            border-radius: 14px;
            box-shadow: 0 6px 18px rgba(0,0,0,0.35);
        }

        .msg {
            background: #27ae60;
            color: white;
            padding: 12px;
            border-radius: 8px;
            margin-bottom: 18px;
            font-weight: bold;
            text-align: center;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        th {
            background: #e67e22;
            color: white;
            padding: 12px;
        }

        td {
            padding: 12px;
            text-align: center;
        }

        tr:nth-child(even) {
            background: #f2f2f2;
        }

        tr:nth-child(odd) {
            background: #ffffff;
        }

        .acoes {
            text-align: center;
            margin-top: 25px;
        }

        .btn {
            display: inline-block;
            padding: 10px 18px;
            border-radius: 8px;
            text-decoration: none;
            color: white;
            font-weight: bold;
            margin: 5px;
        }
        
        td .btn {
            min-width: 90px;
            text-align: center;
            padding: 10px 0;
}

        td:last-child,
        td:nth-last-child(2) {
            width: 120px;
}

        .btn-voltar {
            background: #27ae60;
        }

        .btn-novo {
            background: #2980b9;
        }
        
        .btn-editar {
            background: #2980b9;
        }

        .btn-excluir {
            background: #c0392b;
        }
    </style>
</head>
<body>

<div class="overlay">
    <h1>👤 Clientes da Lanchonete</h1>

    <div class="container">

        <%
            String msg = request.getParameter("msg");
            if (msg != null) {
        %>
            <div class="msg"><%= msg %></div>
        <%
            }

            List<Cliente> lista = (List<Cliente>) request.getAttribute("clientes");
        %>

<table>
    <tr>
        <th>Nome</th>
        <th>Email</th>
        <th>Telefone</th>
        <th>Editar</th>
        <th>Excluir</th>
    </tr>

    <% if (lista != null) {
           for (Cliente c : lista) { %>
        <tr>
            <td><%= c.getNome() %></td>
            <td><%= c.getEmail() %></td>
            <td><%= c.getTelefone() %></td>
            <td style="text-align:center;">
                
    <a class="btn btn-editar"
       href="controle_cliente?op=Editar&id=<%= c.getId() %>&nome=<%= c.getNome() %>&email=<%= c.getEmail() %>&telefone=<%= c.getTelefone() %>">
        Editar
    </a>
</td>

<td style="text-align:center;">
    <a class="btn btn-excluir"
       href="controle_cliente?op=Excluir&id=<%= c.getId() %>"
       onclick="return confirm('Tem certeza que deseja excluir este cliente?');">
        Excluir
    </a>
</td>
        </tr>
    <%   }
       } %>
       
</table>

        <div class="acoes">
            <a class="btn btn-voltar" href="index.jsp">⬅ Voltar</a>
            <a class="btn btn-novo" href="index.jsp">+ Novo cliente</a>
        </div>

    </div>
</div>

</body>
</html>