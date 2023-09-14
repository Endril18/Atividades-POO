package br.dcx.ufpb.sistemaComercial;

import br.dcx.ufpb.sistemaComercial.*;

import java.util.Collection;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class SistemaComercialMap implements SistemaComercial {
    private Map<String, Cliente> clientes;
    private Map<String, Produto> produtos;
    public SistemaComercialMap () {
        this.clientes = new HashMap<String, Cliente>();
        this.produtos = new HashMap<String, Produto>();
    }
    @Override
    public boolean existeCliente(Cliente c){
        return this.clientes.containsKey(c.getID());
    }
    public Cliente pesquisaCliente(String id) throws ClienteNaoExisteException {
        if(this.clientes.containsKey(id)) {
            return this.clientes.get(id);
        }
        throw new ClienteNaoExisteException("Não foi encontrado o cliente");

    }
    @Override
    public boolean existeProduto(Produto p) {
        return this.produtos.containsKey(p.getCodigo());
    }

    @Override
    public Produto pesquisaProduto(String codigoProduto) throws ProdutoNaoExisteException {
        if (this.produtos.containsKey(codigoProduto)) {
            return this.produtos.get(codigoProduto);
        }
        throw new ProdutoNaoExisteException("Não foi encontrado produto"
                +" com o código "+codigoProduto);

    }

    public Collection<Produto> pesquisaProdutosDaCategoria(CategoriaProduto categoria){
            List<Produto> produtosDaCategoria = new ArrayList<>();

            for (Produto p : this.produtos.values()) {
                 if (p.getCategoria().equals(categoria)){
                     produtosDaCategoria.add(p);
                 }
            }
            return produtosDaCategoria;
    }

    @Override
    public boolean cadastraProduto(Produto p) {
        if (existeProduto(p)) {
            return false;
        } else {
            this.produtos.put(p.getCodigo(), p);
            return true;
        }
    }
}