package amigosecreto.ufpb.br;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class SistemaAmigoMap {
    private List<Mensagem> mensagens;
    private Map<String, Amigo> amigosMap; // mapeando por email

    public SistemaAmigoMap(){
        this.amigosMap = new HashMap<>();
    }

    public void adicionarAmigo(String nome, String email){
        Amigo a = new Amigo(nome, email);
        this.amigosMap.put(email, a);
    }

    public List<Mensagem> pesquisaMensagensAnonimas(){
        List<Mensagem> mensagensAnonimas = new ArrayList<>();
        for (Mensagem m: mensagens){
            if(m.ehAnonima()){
                mensagensAnonimas.add(m);
            }
        }
        return mensagensAnonimas;
    }

    public void configurarAmigoSecretoDe(String emailDaPessoa, String emailAmigoSorteado) throws AmigoInexistenteException {
        Amigo aPessoa = amigosMap.get(emailDaPessoa);
        if(aPessoa == null){
            throw new AmigoInexistenteException("Não encontrado no sistema alguém com esse email");
        } else{
            aPessoa.setEmailAmigoSorteado(emailAmigoSorteado);
        }
    }

    public List<Mensagem> pesquisaTodasAsMensagens(){
        return mensagens;
    }

    public Amigo pesquisaAmigoSecretoDe(String emailDaPessoa) throws AmigoInexistenteException, AmigoNaoSorteadoException {
        Amigo pessoa = amigosMap.get(emailDaPessoa);
        if (pessoa == null) {
            throw new AmigoInexistenteException("Não foi encontrado no sistema alguem com esse email.");
        } else {
            String emailAmigoSorteado = pessoa.getEmailAmigoSorteado();
            if(emailAmigoSorteado == null){
                throw new AmigoNaoSorteadoException("Esse amigo não foi sorteado");
            } else{
                return this.amigosMap.get(emailAmigoSorteado);
            }
        }
    }
    public void enviarMensagemParaAlguem (String texto, String emailRemetente, boolean ehAnonima, String emailDestinatario){
        Mensagem m = new MensagemParaAlguem(texto, emailRemetente, ehAnonima, emailDestinatario) {
        };
        this.mensagens.add(m);
    }

    public void enviarMensagemParaTodos (String texto, String emailRemetente, boolean ehAnonima){
        Mensagem m = new MensagemParaTodos(texto, emailRemetente, ehAnonima);
        this.mensagens.add(m);
    }
}
