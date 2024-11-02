<%-- 
    Document   : DadosProduto
    Created on : 05/09/2023, 22:36:50
    Author     : ruanf
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Incluir Produto</title>
    
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css">
</head>

<body>
    <form action="CadastroFC" method="post">
        <input type="hidden" name="acao" value="incluirOsProdutos"/>
        <div class="container mt-3">
            <h1 class="mt-3">Dados do Produto</h1>
            <div class="row">
                <div class="col-md-6">
                    <div class="mb-3">
                        <label for="nome" class="form-label">Nome:</label>
                        <input type="text" name="nome" id="nome" class="form-control" required/>
                    </div>
                    <div class="mb-3">
                        <label for="quantidade" class="form-label">Quantidade:</label>
                        <input type="number" name="quantidade" id="quantidade" class="form-control" required/>
                    </div>
                    
                     <div class="mb-3">
                        <label for="precoVenda" class="form-label">Preço de Venda:</label>
                        <input type="number" step="0.01" name="precoVenda" id="precoVenda" class="form-control" required/> 
                    </div>
                    
                    <input type="hidden" name="codProduto" value="4"/> 
                    
                </div>
            </div>
            <div class="mb-3">
                <input type="submit" value="Incluir Produto" class="btn btn-primary"/>                
            </div>
        </div>
    </form>

    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
