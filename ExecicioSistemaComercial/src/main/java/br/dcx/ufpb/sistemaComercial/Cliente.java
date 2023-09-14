package br.dcx.ufpb.sistemaComercial;

public abstract class Cliente {
    private String nome;
    private String endereco;
    private String email;

    public abstract String getID();

    public Cliente (String nome, String endereco, String email){
        this.nome = nome;
        this.endereco = endereco;
        this.email = email;
    }
}
