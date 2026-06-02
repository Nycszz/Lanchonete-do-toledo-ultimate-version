<%@page import="java.util.List"%>
<%@page import="model.Pedido"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <title>Pedidos da Lanchonete</title>

    <style>
        body {
            margin: 0;
            font-family: 'Segoe UI';
            background: #1f1f1f;
            color: white;
            padding: 30px;
        }

        h1 {
            text-align: center;
            margin-bottom: 30px;
            color: #f39c12;
        }

        .pedido-card {
            background: #2c2c2c;
            border-radius: 15px;
            padding: 20px;
            margin: 20px auto;
            max-width: 700px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.4);
        }

        .pedido-card h2 {
            margin-top: 0;
            color: #f1c40f;
        }

        .linha {
            margin: 10px 0;
            font-size: 16px;
        }

        .status-container {
            display: flex;
            justify-content: space-between;
            margin-top: 20px;
            position: relative;
        }

        .status-container::before {
            content: "";
            position: absolute;
            top: 15px;
            left: 10%;
            width: 80%;
            height: 4px;
            background: #555;
            z-index: 0;
        }

        .status {
            position: relative;
            z-index: 1;
            text-align: center;
            width: 33%;
        }

        .bolinha {
            width: 30px;
            height: 30px;
            border-radius: 50%;
            background: #555;
            margin: auto;
            margin-bottom: 8px;
        }

        .ativo .bolinha {
            background: #27ae60;
        }

        .status span {
            font-size: 14px;
        }

        .btn-area {
            margin-top: 20px;
            text-align: center;
        }

        .btn {
            padding: 10px 18px;
            border: none;
            border-radius: 8px;
            background: #e67e22;
            color: white;
            cursor: pointer;
            margin: 5px;
            font-size: 14px;
        }

        .btn:hover {
            background: #d35400;
        }

        .voltar {
            display: block;
            width: 220px;
            margin: 30px auto;
            text-align: center;
            text-decoration: none;
            background: #3498db;
            color: white;
            padding: 12px;
            border-radius: 8px;
        }

        .voltar:hover {
            background: #2980b9;
        }
    </style>
</head>
<body>

<h1>🧾 Pedidos da Lanchonete</h1>

<%
    String statusSelecionado = request.getParameter("status");
    if (statusSelecionado == null || statusSelecionado.isEmpty()) {
        statusSelecionado = "Todos";
    }
%>

<div style="max-width: 700px; margin: 0 auto 25px auto; background: #2c2c2c; border-radius: 15px; padding: 20px; box-shadow: 0 4px 12px rgba(0,0,0,0.4);">
    <form action="controle_pedido" method="GET" style="display:flex; gap:10px; justify-content:center; align-items:center; flex-wrap:wrap;">
        <input type="hidden" name="op" value="ConsultarStatus">

        <select name="status" style="padding:10px; border-radius:8px; border:none; min-width:220px;">
            <option value="Todos" <%= "Todos".equals(statusSelecionado) ? "selected" : "" %>>Todos os status</option>
            <option value="Pedido Recebido" <%= "Pedido Recebido".equals(statusSelecionado) ? "selected" : "" %>>Pedido Recebido</option>
            <option value="Em preparo" <%= "Em preparo".equals(statusSelecionado) ? "selected" : "" %>>Em preparo</option>
            <option value="Pronto" <%= "Pronto".equals(statusSelecionado) ? "selected" : "" %>>Pronto</option>
        </select>

        <button class="btn" type="submit">Filtrar</button>

        <a class="btn" href="controle_pedido?op=ConsultarTodos">Mostrar Todos</a>
    </form>
</div>

<%
    List<Pedido> lista = (List<Pedido>) request.getAttribute("pedidos");

    if(lista != null){
        for(Pedido p : lista){
%>

<div class="pedido-card">

    <h2>Pedido #<%= p.getId() %></h2>

    <div class="linha"><strong>Cliente:</strong> <%= p.getClienteNome() %></div>

    <div class="linha">
        <strong>Total:</strong>
        R$ <%= String.format("%.2f", p.getValorTotal()) %>
    </div>

    <div class="linha">
        <strong>Observação:</strong>
        <%= p.getObservacao() == null || p.getObservacao().isEmpty()
                ? "Sem observações"
                : p.getObservacao() %>
    </div>

<div class="linha">
    <strong>Status do pedido:</strong>
    <%= p.getStatus() %>
</div>

<div class="linha">
    <strong>Forma de pagamento:</strong>
    <%= p.getFormaPagamento() == null ? "Não informado" : p.getFormaPagamento() %>
</div>

<div class="linha">
    <strong>Status do pagamento:</strong>
    <%= p.getStatusPagamento() == null ? "Não informado" : p.getStatusPagamento() %>
</div>

<div class="status-container">

    <div class="status <%= ("Pedido Recebido".equals(p.getStatus()) || "Em preparo".equals(p.getStatus()) || "Pronto".equals(p.getStatus())) ? "ativo" : "" %>">
        <div class="bolinha"></div>
        <span>Pedido Recebido</span>
    </div>

    <div class="status <%= ("Em preparo".equals(p.getStatus()) || "Pronto".equals(p.getStatus())) ? "ativo" : "" %>">
        <div class="bolinha"></div>
        <span>Em preparo</span>
    </div>

    <div class="status <%= "Pronto".equals(p.getStatus()) ? "ativo" : "" %>">
        <div class="bolinha"></div>
        <span>Pronto</span>
    </div>

</div>

<div class="btn-area">

    <% if(!"Pago".equals(p.getStatusPagamento())) { %>
        <a href="controle_pedido?op=AtualizarPagamento&id=<%=p.getId()%>&status=Pago">
            <button class="btn">Marcar como Pago</button>
        </a>
    <% } %>

    <% if("Em preparo".equals(p.getStatus())) { %>
        <a href="controle_pedido?op=AtualizarStatus&id=<%=p.getId()%>&status=Pronto">
            <button class="btn">Marcar como Pronto</button>
        </a>
    <% } %>

</div>

</div>

<%
        }
    }
%>

<a class="voltar" href="index.jsp">← Voltar para o início</a>

</body>
</html>