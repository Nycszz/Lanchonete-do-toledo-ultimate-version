package command;

import DAO.PedidoDAO;
import DAO.ProdutoDAO;
import model.Pedido;
import model.Produto;
import DAO.PagamentoDAO;
import model.Pagamento;

import decorator.ItemLanche;
import decorator.LancheBase;
import decorator.Bacon;
import decorator.Queijo;
import decorator.Ovo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;

public class FinalizarPedidoCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse rep)
            throws ServletException, IOException {

        try {
            int clienteId = Integer.parseInt(req.getParameter("clienteId"));
            String observacao = req.getParameter("observacao");

            String[] produtosSelecionados = req.getParameterValues("produtoId");
            String[] adicionaisSelecionados = req.getParameterValues("adicional");

            if (produtosSelecionados == null || produtosSelecionados.length == 0) {
                req.setAttribute("msg", "Selecione pelo menos um item para o pedido.");
                return "erro.jsp";
            }

            ProdutoDAO produtoDAO = new ProdutoDAO();
            PedidoDAO pedidoDAO = new PedidoDAO();

            double total = 0.0;

            for (String produtoIdStr : produtosSelecionados) {
                if (produtoIdStr != null && !produtoIdStr.trim().isEmpty()) {
                    int produtoId = Integer.parseInt(produtoIdStr);

                    Produto produto = new Produto();
                    produto.setId(produtoId);

                    Produto produtoBanco = produtoDAO.consultarById(produto);

                    if (produtoBanco != null) {
                        ItemLanche itemDecorado = aplicarAdicionais(produtoBanco, adicionaisSelecionados);
                        total += itemDecorado.custo();
                    }
                }
            }

            Pedido pedido = new Pedido();
            pedido.setClienteId(clienteId);
            pedido.setValorTotal(total);
            pedido.setObservacao(observacao);
            pedido.setStatus("Pedido Recebido");

            int pedidoId = pedidoDAO.cadastrarPedido(pedido);

            for (String produtoIdStr : produtosSelecionados) {
                if (produtoIdStr != null && !produtoIdStr.trim().isEmpty()) {
                    int produtoId = Integer.parseInt(produtoIdStr);

                    Produto produto = new Produto();
                    produto.setId(produtoId);

                    Produto produtoBanco = produtoDAO.consultarById(produto);

                    if (produtoBanco != null) {
                        ItemLanche itemDecorado = aplicarAdicionais(produtoBanco, adicionaisSelecionados);

                        pedidoDAO.cadastrarItemPedido(
                            pedidoId,
                            produtoId,
                            1,
                            itemDecorado.custo()
                        );
                    }
                }
            }

            String formaPagamento = req.getParameter("formaPagamento");

            Pagamento pagamento = new Pagamento();
            pagamento.setPedidoId(pedidoId);
            pagamento.setFormaPagamento(formaPagamento);
            pagamento.setValor(total);
            pagamento.setStatusPagamento("Não pago");

            PagamentoDAO pagamentoDAO = new PagamentoDAO();
            pagamentoDAO.cadastrar(pagamento);

            rep.sendRedirect("controle_pedido?op=ConsultarTodos&msg=Pedido realizado com sucesso!");
            return null;

        } catch (Exception e) {
            req.setAttribute("msg", "Erro ao cadastrar pedido: " + e.getMessage());
            return "erro.jsp";
        }
    }

    private ItemLanche aplicarAdicionais(Produto produto, String[] adicionaisSelecionados) {

        ItemLanche item = new LancheBase(produto);

        if (produto.getCategoria() == null ||
            !produto.getCategoria().equalsIgnoreCase("Lanche")) {
            return item;
        }

        if (adicionaisSelecionados != null) {
            for (String adicional : adicionaisSelecionados) {

                if ("bacon".equalsIgnoreCase(adicional)) {
                    item = new Bacon(item);
                }

                if ("queijo".equalsIgnoreCase(adicional)) {
                    item = new Queijo(item);
                }

                if ("ovo".equalsIgnoreCase(adicional)) {
                    item = new Ovo(item);
                }
            }
        }

        return item;
    }
}