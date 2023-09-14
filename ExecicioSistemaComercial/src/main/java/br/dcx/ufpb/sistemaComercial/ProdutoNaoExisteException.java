package br.dcx.ufpb.sistemaComercial;

public class ProdutoNaoExisteException extends Exception{
    public ProdutoNaoExisteException (String msg){
        super(msg);
    }
}
