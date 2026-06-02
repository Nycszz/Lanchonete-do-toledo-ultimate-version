<%@page import="java.util.List"%>
<%@page import="model.Produto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>🍔 Cardápio Gourmet</title>

    <style>
        body {
            margin: 0;
            font-family: 'Segoe UI';
            background: url('https://images.unsplash.com/photo-1550547660-d9450f859349') no-repeat center center fixed;
            background-size: cover;
        }

        .overlay {
            background: rgba(0,0,0,0.8);
            min-height: 100vh;
            padding: 30px;
            color: white;
        }

        h1 {
            text-align: center;
            margin-bottom: 30px;
        }

        .container {
            max-width: 950px;
            margin: auto;
            background: white;
            color: black;
            padding: 20px;
            border-radius: 10px;
        }

        .msg {
            background: #2ecc71;
            color: white;
            padding: 10px;
            margin-bottom: 15px;
            border-radius: 5px;
            text-align: center;
            font-weight: bold;
        }

        .filtro {
            margin-bottom: 20px;
            text-align: center;
        }

        .filtro select {
            padding: 10px;
            width: 220px;
            border-radius: 6px;
            border: 1px solid #ccc;
        }

        .filtro button {
            padding: 10px 15px;
            border: none;
            border-radius: 6px;
            background: #e67e22;
            color: white;
            cursor: pointer;
            font-weight: bold;
            margin-left: 8px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        th {
            background: #e67e22;
            color: white;
            padding: 10px;
        }

        td {
            padding: 10px;
            text-align: center;
        }

        tr:nth-child(even) {
            background: #f2f2f2;
        }

        .btn-excluir,
        .btn-editar {
            padding: 6px 12px;
            border-radius: 5px;
            text-decoration: none;
            color: white;
            font-weight: bold;
        }

        .btn-excluir {
            background: #c0392b;
        }

        .btn-editar {
            background: #2980b9;
        }

        .voltar {
            display: block;
            margin-top: 20px;
            text-align: center;
        }

        .voltar a {
            background: #27ae60;
            padding: 10px 20px;
            color: white;
            border-radius: 8px;
            text-decoration: none;
        }
    </style>
</head>

<body>

<div class="overlay">

    <h1>🍔 Cardápio da Lanchonete</h1>

    <div class="container">

        <%
            String msg = request.getParameter("msg");
            if (msg != null) {
        %>
            <div class="msg"><%= msg %></div>
        <%
            }

            List<Produto> lprod = (List<Produto>) request.getAttribute("prods");
            if (lprod == null) {
                lprod = (List<Produto>) request.getAttribute("produtos");
            }

            String categoriaSelecionada = (String) request.getAttribute("categoriaSelecionada");
            if (categoriaSelecionada == null) {
                categoriaSelecionada = "Todos";
            }
        %>

        <div class="filtro">
            <form action="controle_produto" method="GET">
                <input type="hidden" name="op" value="ConsultarCategoriaProduto">

                <select name="categoria">
                    <option value="Todos" <%= "Todos".equals(categoriaSelecionada) ? "selected" : "" %>>Todos</option>
                    <option value="Lanche" <%= "Lanche".equals(categoriaSelecionada) ? "selected" : "" %>>Lanche</option>
                    <option value="Acompanhamento" <%= "Acompanhamento".equals(categoriaSelecionada) ? "selected" : "" %>>Acompanhamento</option>
                    <option value="Bebida" <%= "Bebida".equals(categoriaSelecionada) ? "selected" : "" %>>Bebida</option>
                    <option value="Sobremesa" <%= "Sobremesa".equals(categoriaSelecionada) ? "selected" : "" %>>Sobremesa</option>
                </select>

                <button type="submit">Filtrar</button>
            </form>
        </div>

        <table>
            <tr>
                <th>ID</th>
                <th>Categoria</th>
                <th>Produto</th>
                <th>Preço (R$)</th>
                <th>Remover</th>
                <th>Editar</th>
            </tr>

            <% if (lprod != null) {
                   for (Produto p : lprod) { %>
            <tr>
                <td><%= p.getId() %></td>
                <td><%= p.getCategoria() %></td>
                <td><%= p.getDescricao() %></td>
                <td>R$ <%= p.getPreco() %></td>

                <td>
                    <a class="btn-excluir"
                       href="controle_produto?op=ExcluirProduto&txtid=<%=p.getId()%>"
                       onclick="return confirm('Tem certeza que deseja excluir este produto?');">
                        Excluir
                    </a>
                </td>

                <td>
                    <a class="btn-editar"
                       href="controle_produto?op=EditarProduto&txtid=<%=p.getId()%>&txtdescricao=<%=p.getDescricao()%>&txtpreco=<%=p.getPreco()%>&txtcategoria=<%=p.getCategoria()%>">
                        Editar
                    </a>
                </td>
            </tr>
            <%   }
               } %>

        </table>

        <div class="voltar">
            <a href="index.jsp">⬅ Voltar</a>
        </div>

    </div>

</div>

</body>
</html>