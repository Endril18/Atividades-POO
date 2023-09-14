package br.dcx.ufpb.sistemaComercial;

import org.junit.Test;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SistemaComercialMapTest {
    @Test
    public void testaCadastroProdutos() {
        SistemaComercialMap sistema = new SistemaComercialMap();
        Collection<Produto> alimentos = sistema.pesquisaProdutosDaCategoria(CategoriaProduto.ALIMENTO);
        assertTrue(alimentos.size()==0);

        Produto meuP = new Produto("265494", "Pãezinhos de Queijo", 10.00,42, CategoriaProduto.ALIMENTO);
        assertTrue(sistema.cadastraProduto(meuP));
        assertTrue(sistema.existeProduto(meuP));
        assertEquals(1, meuP.getQuantidadeEmEstoque());
    }
}
