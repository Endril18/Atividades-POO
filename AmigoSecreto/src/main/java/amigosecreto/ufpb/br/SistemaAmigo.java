package amigosecreto.ufpb.br;

import java.util.List;

public class SistemaAmigo {
    private List<Mensagem> mensagens;
    private List<Amigo> amigos;

    public String pesquisaAmigoSecretoDe(String emailDaPessoa) throws AmigoInexistenteException, AmigoNaoSorteadoException {
        for (Amigo c : this.amigos) {
            if (c.getEmail().equals(emailDaPessoa)) {
                String emailAmigoSorteado = c.getEmailAmigoSorteado();
                if (emailAmigoSorteado == null) {
                    throw new AmigoNaoSorteadoException("Esse amigo não sorteado: " + emailDaPessoa);
                } else {
                    return emailAmigoSorteado;
                }
            }
        }
        throw new AmigoInexistenteException("Não foi encontrado no sistema ninguém com o email " + emailDaPessoa);
    }

    public void enviarMensagemParaTodos (String texto, String emailRemetente, boolean ehAnonima){
        Mensagem m = new MensagemParaTodos(texto, emailRemetente, ehAnonima);
        this.mensagens.add(m);
    }

    //public List<Mensagem> pesquisaMensagensAnonimas(){
      //TODO
    //}
}
