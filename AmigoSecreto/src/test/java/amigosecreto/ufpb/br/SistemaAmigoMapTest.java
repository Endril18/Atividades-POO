package amigosecreto.ufpb.br;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SistemaAmigoMapTest {
    SistemaAmigoMap sistema;

    @BeforeEach
    void setUp() {
        this.sistema = new SistemaAmigoMap();
    }

    @Test
    void testSistemaAmigo() {
        assertTrue(sistema.pesquisaTodasAsMensagens().isEmpty());
        assertThrows(AmigoInexistenteException.class,
                () -> sistema.pesquisaAmigoSecretoDe("endril@teste.com"));
    }

    @Test
    void testPesquisaECadastraAmigo() {
        try {
            sistema.pesquisaAmigoSecretoDe("endril@teste.com");
            fail("Deveria falhar pois não existe ainda");
        } catch (AmigoInexistenteException | AmigoNaoSorteadoException e) {
            //Ok
        }
        try {
            sistema.adicionarAmigo("Endril", "endril@dcx.ufpn.br");
            Amigo amg = sistema.pesquisaAmigoSecretoDe("endril@teste.com");
            assertEquals("Endril", amg.getNome());
            assertEquals("endril@teste.com", amg.getEmail());
        } catch (AmigoInexistenteException | AmigoNaoSorteadoException e) {
            fail("Não deveria lançar exceção");
        }
    }

    @Test
    void testEnviarMensagemParaTodos() {
        assertTrue(sistema.pesquisaTodasAsMensagens().isEmpty());
        sistema.enviarMensagemParaTodos("texto", "endril@dcx.ufpb.br", true);
        List<Mensagem> mensagensAchadas = sistema.pesquisaTodasAsMensagens();
        assertTrue(mensagensAchadas.size() == 1);
        assertTrue(mensagensAchadas.get(0).getEmailRemetente().equals("endril@dcx.ufpb.br"));
    }

    @Test
    void testEnviarMensagemParaAlguem() {
        assertTrue(sistema.pesquisaTodasAsMensagens().isEmpty());
        sistema.enviarMensagemParaAlguem("texto", "endril@dcx.ufpb.br", false, "ayla@dcx.ufpb.br");
        List<Mensagem> mensagensAchadas = sistema.pesquisaTodasAsMensagens();
        assertEquals(1, mensagensAchadas.size());
        assertTrue(mensagensAchadas.get(0) instanceof MensagemParaAlguem);
        assertTrue(mensagensAchadas.get(0).getTexto().equals("texto"));
    }

    @Test
    void testPesquisaMensagensAnonimas() {
        assertTrue(sistema.pesquisaTodasAsMensagens().isEmpty());
        sistema.enviarMensagemParaAlguem("texto 1", "ayla@dcx.ufpb.br", false, "rodrigo@dcx.ufpb.br");
        assertTrue(sistema.pesquisaMensagensAnonimas().isEmpty());
        sistema.enviarMensagemParaAlguem("texto 2", "ayla@dcx.ufpb.br", true, "rodrigo@dcx.ufpb.br");
        assertTrue(sistema.pesquisaMensagensAnonimas().size() == 1);
    }

    @Test
    void testPesquisaTodasAsMensagens() {
        assertTrue(sistema.pesquisaTodasAsMensagens().isEmpty());
        sistema.enviarMensagemParaAlguem("texto 1", "ayla@dcx.ufpb.br", false, "rodrigor@dcx.ufpb.br");
        assertTrue(sistema.pesquisaTodasAsMensagens().size() == 1);
        sistema.enviarMensagemParaAlguem("texto 2", "ayla@dcx.ufpb.br", false, "rodrigor@dcx.ufpb.br");
        assertTrue(sistema.pesquisaTodasAsMensagens().size() == 2);
    }

    @Test
    void testPesquisaAmigoEConfiguraAmigoSecretoDe() {
        assertThrows(AmigoInexistenteException.class,
                () -> sistema.pesquisaAmigoSecretoDe("ayla@dcx.ufpb.br"));
        try {
            sistema.adicionarAmigo("Ayla", "ayla@dcx.ufpb.br");
            sistema.adicionarAmigo("Ana", "ana@dcx.ufpb.br");
            sistema.configurarAmigoSecretoDe("ayla@dcx.ufpb.br", "ana@dcx.ufpb.br");
            sistema.configurarAmigoSecretoDe("ana@dcx.ufpb.br", "ayla@dcx.ufpb.br");
            assertEquals("ana@dcx.ufpb.br", sistema.pesquisaAmigoSecretoDe("ayla@dcx.ufpb.br"));
            assertEquals("ayla@dcx.ufpb.br", sistema.pesquisaAmigoSecretoDe("ana@dcx.ufpb.br"));
        } catch (AmigoInexistenteException | AmigoNaoSorteadoException e) {
            fail("Não deveria lançar exceção");
        }
    }
}
