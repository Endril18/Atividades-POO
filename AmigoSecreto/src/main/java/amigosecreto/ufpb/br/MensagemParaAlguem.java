package amigosecreto.ufpb.br;

public abstract class MensagemParaAlguem extends Mensagem{
    private String emailDestinatario;

    public MensagemParaAlguem(String texto, String emailRemete, boolean anonima, String emailDestinatario){
        super(texto, emailRemete, anonima);
        this.emailDestinatario = emailDestinatario;
    }

    public String getEmailDestinatario(){
        return emailDestinatario;
    }

    @Override
    public String getTextoCompletoAExibir(){
        if (super.ehAnonima()) {
            return "Mensagem para " + this.getEmailDestinatario() + ": " + super.getTexto();
        } else {
            return "Mensagem de: " + super.getEmailRemetente() + " para " + this.getEmailDestinatario() + ". Texto: " + super.getTexto();
        }
    }
}
