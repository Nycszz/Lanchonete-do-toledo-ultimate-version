<%@page import="java.util.List"%>
<%@page import="model.Produto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>🍔 Lanchonete do Toledo</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <style>
        body {
            margin: 0;
            font-family: 'Segoe UI';
            background: url('https://images.unsplash.com/photo-1550547660-d9450f859349') no-repeat center center fixed;
            background-size: cover;
        }

        .overlay {
            background: rgba(0,0,0,0.75);
            height: 100vh;
            padding: 30px;
            color: white;
        }

        h1 {
            text-align: center;
            margin-bottom: 30px;
        }

.tabs {
    display: flex;
    justify-content: center;
    gap: 20px;
    margin-bottom: 30px;
    position: relative;
    z-index: 1000;
}
      .tab {
    padding: 10px 20px;
    cursor: pointer;
    border-radius: 8px;
    background: #444;
    color: white;
    user-select: none;
    position: relative;
    z-index: 1001;
}

        .tab.active {
            background: #e67e22;
        }

.content {
    display: none;
    background: white;
    color: black;
    padding: 20px;
    border-radius: 10px;
    max-width: 600px;
    margin: auto;
    position: relative;
    z-index: 1;
}
        .content.active {
            display: block;
        }

        input {
            width: 90%;
            padding: 10px;
            margin: 8px 0;
        }

        .btn {
            padding: 10px;
            margin: 5px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        .btn-primary { background: #27ae60; color: white; }
        .btn-danger { background: #c0392b; color: white; }
        .btn-info { background: #2980b9; color: white; }
        
 .campo-select {
    width: 100%;
    padding: 10px;
    margin: 8px 0;
    border: 1px solid #999;
    border-radius: 4px;
    font-size: 16px;
    box-sizing: border-box;
    background: white;
    color: #555;
    
}

.campo {
    width: 90%;
    margin: 8px 0;
}

.campo label {
    display: block;
    margin-bottom: 6px;
    color: black;
}

.adicionais {
    margin-top: 10px;
    padding: 12px;
    background: #f2f2f2;
    border-radius: 8px;
}

.adicionais label {
    display: flex;
    align-items: center;
    gap: 8px;
    margin: 8px 0;
    color: black;
}

.adicionais input[type="checkbox"] {
    width: auto;
    margin: 0;
    padding: 0;
}

.input-produto {
    width: 90%;
}
    </style>

    <script>
        function showTab(tabId) {
            document.querySelectorAll('.content').forEach(c => c.classList.remove('active'));
            document.querySelectorAll('.tab').forEach(t => t.classList.remove('active'));

            document.getElementById(tabId).classList.add('active');
            document.getElementById('tab-' + tabId).classList.add('active');
        }
    </script>
</head>

<body>
<div class="overlay">

    <h1>🍔 Lanchonete do Toledo</h1>

    <!-- ABAS -->
<%
    String abaAtiva = (String) request.getAttribute("abaAtiva");
    if (abaAtiva == null) {
        abaAtiva = "clientes";
    }
%>

<div class="tabs">
    <div id="tab-clientes" class="tab <%= "clientes".equals(abaAtiva) ? "active" : "" %>" onclick="showTab('clientes')">👤 Clientes</div>
    <div id="tab-produtos" class="tab <%= "produtos".equals(abaAtiva) ? "active" : "" %>" onclick="showTab('produtos')">🍟 Menu</div>
    <div id="tab-pedidos" class="tab <%= "pedidos".equals(abaAtiva) ? "active" : "" %>" onclick="window.location='controle_pedido?op=Abrir'">🍔 Pedidos</div>
</div>

    <!-- CLIENTES -->
    <div id="clientes" class="content <%= "clientes".equals(abaAtiva) ? "active" : "" %>">
        <h2>Cadastro de Clientes</h2>
        <form action="controle_cliente" method="GET">
        <input type="hidden" name="id"
       value="<%= request.getAttribute("idEditar") != null ? request.getAttribute("idEditar") : "" %>">

        <input type="text" name="nome" placeholder="Nome"
       value="<%= request.getAttribute("nomeEditar") != null ? request.getAttribute("nomeEditar") : "" %>">

        <input type="email" name="email" placeholder="Email"
       value="<%= request.getAttribute("emailEditar") != null ? request.getAttribute("emailEditar") : "" %>">

        <input type="text" name="telefone" placeholder="Telefone"
       value="<%= request.getAttribute("telefoneEditar") != null ? request.getAttribute("telefoneEditar") : "" %>">

        <% if(request.getAttribute("idEditar") != null){ %>
            <input class="btn btn-primary" type="submit" name="op" value="Atualizar">
        <% } else { %>
             <input class="btn btn-primary" type="submit" name="op" value="Cadastrar">
        <% } %>

<input class="btn btn-info" type="submit" name="op" value="ConsultarTodos">
            
        </form>
    </div>

    <!-- PRODUTOS -->
   <div id="produtos" class="content <%= "produtos".equals(abaAtiva) ? "active" : "" %>">
        <h2>Itens do Menu</h2>
        <form action="controle_produto" method="GET">
           <input type="text" name="txtid" placeholder="ID"
       value="<%= request.getParameter("txtid") != null ? request.getParameter("txtid") : "" %>">

<input type="text" name="txtdescricao" placeholder="Nome do Lanche"
       value="<%= request.getParameter("txtdescricao") != null ? request.getParameter("txtdescricao") : "" %>">

<input type="text" name="txtpreco" placeholder="Preço"
       value="<%= request.getParameter("txtpreco") != null ? request.getParameter("txtpreco") : "" %>">
            

<select name="txtcategoria"
        <select name="txtcategoria" class="campo-select input-produto">
>
    <option value="">Categoria</option>
    <option value="Lanche" <%= "Lanche".equals(request.getParameter("txtcategoria")) ? "selected" : "" %>>Lanche</option>
    <option value="Acompanhamento" <%= "Acompanhamento".equals(request.getParameter("txtcategoria")) ? "selected" : "" %>>Acompanhamento</option>
    <option value="Bebida" <%= "Bebida".equals(request.getParameter("txtcategoria")) ? "selected" : "" %>>Bebida</option>
    <option value="Sobremesa" <%= "Sobremesa".equals(request.getParameter("txtcategoria")) ? "selected" : "" %>>Sobremesa</option>
</select>
<br>
<br><br>

           <%
    boolean editando = request.getParameter("txtid") != null
                    && !request.getParameter("txtid").isEmpty();
%>

<% if(editando){ %>
    <input class="btn btn-primary"
           type="submit"
           name="op"
           value="AtualizarProduto">
<% } else { %>
    <input class="btn btn-primary"
           type="submit"
           name="op"
           value="CadastrarProduto">
<% } %>

<input class="btn btn-info"
       type="submit"
       name="op"
       value="ConsultarTodosProduto">
        </form>
    </div>

<!-- PEDIDOS -->
<div id="pedidos" class="content <%= "pedidos".equals(abaAtiva) ? "active" : "" %>">
    <h2>Pedidos</h2>

    <form action="controle_pedido" method="GET">

        <label>Cliente</label><br>
        <select name="clienteId" class="campo-select" required>
            <option value="">Selecione o cliente</option>
            <%
                java.util.List clientes = (java.util.List) request.getAttribute("clientes");
                if (clientes != null) {
                    for (Object obj : clientes) {
                        model.Cliente c = (model.Cliente) obj;
            %>
                <option value="<%= c.getId() %>"><%= c.getNome() %></option>
            <%
                    }
                }
            %>
        </select>

        <br><br>

        <div id="itensPedido">
            <div class="bloco-item">
                <label>Categoria</label><br>
                <select class="campo-select categoria-select" onchange="filtrarProdutos(this)">
                    <option value="">Selecione a categoria</option>
                    <option value="Lanche">Lanche</option>
                    <option value="Acompanhamento">Acompanhamento</option>
                    <option value="Bebida">Bebida</option>
                    <option value="Sobremesa">Sobremesa</option>
                </select>

                <br><br>

<label>Produto</label><br>
<select name="produtoId" class="campo-select produto-select" onchange="verificarAdicionais(this); calcularTotalGeral();">
    <option value="">Selecione um produto</option>
    <%
        java.util.List produtos = (java.util.List) request.getAttribute("produtos");
        if (produtos != null) {
            for (Object obj : produtos) {
                model.Produto p = (model.Produto) obj;
    %>
        <option value="<%= p.getId() %>"
                data-preco="<%= p.getPreco() %>"
                data-categoria="<%= p.getCategoria() %>">
            <%= p.getDescricao() %> - R$ <%= p.getPreco() %>
        </option>
    <%
            }
        }
    %>
</select>

<br><br>

<div class="adicionais" style="display:none;">
    <strong>Adicionais</strong>

    <label>
        <input type="checkbox" name="adicional" value="bacon" onchange="calcularTotalGeral()">
        Bacon (+ R$ 3,50)
    </label>

    <label>
        <input type="checkbox" name="adicional" value="queijo" onchange="calcularTotalGeral()">
        Queijo (+ R$ 2,00)
    </label>

    <label>
        <input type="checkbox" name="adicional" value="ovo" onchange="calcularTotalGeral()">
        Ovo (+ R$ 1,50)
    </label>
</div>
</div>
</div>
        <br>

        <button type="button" class="btn btn-info" onclick="adicionarItem()">
            + Adicionar outro item
        </button>

        <br><br>

        <textarea name="observacao"
                  class="campo-select"
                  style="height:120px; resize:none;"
                  placeholder="Observações do pedido (ex: sem cebola, sem gelo...)"></textarea>
        
        <div class="campo">
    <label>Forma de pagamento</label>
    <select name="formaPagamento" class="campo-select" required>
        <option value="">Selecione</option>
        <option value="Pix">Pix</option>
        <option value="Cartão">Cartão</option>
        <option value="Dinheiro">Dinheiro</option>
    </select>
</div>
        <br><br>

        <div id="totalPedido" style="font-size:32px; color:#27ae60; font-weight:bold;">
            Total do Pedido: R$ 0,00
        </div>

        <br><br>

        <input type="submit" class="btn btn-primary" name="op" value="Finalizar">
        
    </form>
                
    <form action="controle_pedido" method="GET">
    <input type="submit"
           class="btn btn-info"
           name="op"
           value="ConsultarTodos">
    </form>
</div>

<script>
function filtrarProdutos(selectCategoria) {
    const categoria = selectCategoria.value;
    const selectProduto = document.getElementById("produtoSelect");

    for (let option of selectProduto.options) {

        if (option.value === "") {
            option.style.display = "block";
            continue;
        }

        if (option.getAttribute("data-categoria") === categoria) {
            option.style.display = "block";
        } else {
            option.style.display = "none";
        }
    }

    selectProduto.value = "";
    calcularTotal();
}

function calcularTotal() {
    const selectProduto = document.getElementById("produtoSelect");
    const option = selectProduto.options[selectProduto.selectedIndex];

    let total = 0;

    if (option && option.getAttribute("data-preco")) {
        total = parseFloat(option.getAttribute("data-preco"));
    }

    document.getElementById("totalPedido").innerHTML =
        "Total do Pedido: R$ " + total.toFixed(2).replace(".", ",");
}
</script>

<script>
function filtrarProdutos(categoriaSelect) {
    const bloco = categoriaSelect.closest(".bloco-item");
    const produtoSelect = bloco.querySelector(".produto-select");
    const categoria = categoriaSelect.value;

    for (let i = 0; i < produtoSelect.options.length; i++) {
        const option = produtoSelect.options[i];

        if (option.value === "") {
            option.style.display = "block";
            continue;
        }

        if (option.getAttribute("data-categoria") === categoria) {
            option.style.display = "block";
        } else {
            option.style.display = "none";
        }
    }

    produtoSelect.value = "";
    calcularTotalGeral();
}

function adicionarItem() {
    const container = document.getElementById("itensPedido");
    const primeiroBloco = container.querySelector(".bloco-item");
    const novoBloco = primeiroBloco.cloneNode(true);

    const categoriaSelect = novoBloco.querySelector(".categoria-select");
    const produtoSelect = novoBloco.querySelector(".produto-select");

    categoriaSelect.selectedIndex = 0;
    produtoSelect.selectedIndex = 0;

    for (let i = 0; i < produtoSelect.options.length; i++) {
        produtoSelect.options[i].style.display = "block";
    }

    container.appendChild(document.createElement("br"));
    container.appendChild(novoBloco);
}

function calcularTotalGeral() {
    let total = 0;

    const blocos = document.querySelectorAll(".bloco-item");

    blocos.forEach(function(bloco) {
        const select = bloco.querySelector(".produto-select");
        const option = select.options[select.selectedIndex];

        if (option && option.getAttribute("data-preco")) {
            total += parseFloat(option.getAttribute("data-preco"));
        }

        const adicionais = bloco.querySelectorAll('.adicionais input[type="checkbox"]:checked');

        adicionais.forEach(function(check) {
            if (check.value === "bacon") {
                total += 3.50;
            }

            if (check.value === "queijo") {
                total += 2.00;
            }

            if (check.value === "ovo") {
                total += 1.50;
            }
        });
    });

    document.getElementById("totalPedido").innerHTML =
        "Total do Pedido: R$ " + total.toFixed(2).replace(".", ",");
}

function verificarAdicionais(produtoSelect) {
    const bloco = produtoSelect.closest(".bloco-item");
    const adicionais = bloco.querySelector(".adicionais");
    const option = produtoSelect.options[produtoSelect.selectedIndex];

    if (option && option.getAttribute("data-categoria") === "Lanche") {
        adicionais.style.display = "block";
    } else {
        adicionais.style.display = "none";

        const checks = adicionais.querySelectorAll('input[type="checkbox"]');
        checks.forEach(function(check) {
            check.checked = false;
        });
    }
}
</script>
</body>
</html>