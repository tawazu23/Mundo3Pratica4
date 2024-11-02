
package fcontroller;


/**
 *
 * @author ruanf
 */
import cadastroee.controller.ProdutoFacadeLocal;
import cadastroee.model.Produto;
import fcontroller.Strategy;
import java.util.List;
import javax.servlet.http.HttpServletRequest;


public class ServletProdutoFC extends Strategy<ProdutoFacadeLocal> {

    public ServletProdutoFC(ProdutoFacadeLocal facade) {
        super(facade);
    }

    @Override
    public String executar(String acao, HttpServletRequest request) {
        String paginaDestino = "ProdutoLista.jsp";

        switch (acao) {
            case "listarOsProdutos":
                listar(request);
                break;
            case "excluirOsProdutos":
                excluir(request);
                listar(request);
                break;
            case "exibirEditar":
                exibirPaginaEditar(request);
                paginaDestino = "EditarProduto.jsp";
                break;
            case "editarOsProdutos":
                alterar(request);
                listar(request);
                break;
            case "incluirOsProdutos":
                incluir(request);
                listar(request);
                break;
            case "exibirIncluir":
                paginaDestino = "ProdutoDados.jsp";
                request.setAttribute("produto", new Produto());
                break;
        }

        return paginaDestino;
    }

    private void listar(HttpServletRequest request) {
        List<Produto> produtos = facade.findAll();
        request.setAttribute("lista", produtos);
    }

    private void excluir(HttpServletRequest request) {
        int codigo = Integer.parseInt(request.getParameter("cod"));
        Produto produto = facade.find(codigo);
        if (produto != null) {
            facade.remove(produto);
        }
    }

    private void exibirPaginaEditar(HttpServletRequest request) {
        int codigo = Integer.parseInt(request.getParameter("codProduto"));
        Produto produtoEdit = facade.find(codigo);
        if (produtoEdit != null) {
            request.setAttribute("produtoEdit", produtoEdit);
        }
    }

    private void alterar(HttpServletRequest request) {
        int codProdutoEdit = Integer.parseInt(request.getParameter("codProduto")); 

        Produto produtoEdit = facade.find(codProdutoEdit);
        if (produtoEdit != null) {
            String nome = request.getParameter("nome");
            int quantidade = Integer.parseInt(request.getParameter("quantidade"));
            float precoVenda = Float.parseFloat(request.getParameter("precoVenda"));
            produtoEdit.setNome(nome);
            produtoEdit.setQuantidade(quantidade);
            produtoEdit.setPrecoVenda(precoVenda);
            facade.edit(produtoEdit);
        }
    }

    private void incluir(HttpServletRequest request) {
        String nome = request.getParameter("nome");
        int quantidade = Integer.parseInt(request.getParameter("quantidade"));
        float precoVenda = Float.parseFloat(request.getParameter("precoVenda"));
        List<Produto> produtos = facade.findAll();
        int novoCodProduto = 1;
        if (!produtos.isEmpty()) {
            int maxCodProduto = produtos.stream()
                    .mapToInt(Produto::getIdProduto)
                    .max()
                    .getAsInt();
            novoCodProduto = maxCodProduto + 1;
        }

        Produto produto = new Produto();
        produto.setNome(nome);
        produto.setQuantidade(quantidade);
        produto.setIdProduto(novoCodProduto);
        produto.setPrecoVenda(precoVenda);
        facade.create(produto);
    }
}
