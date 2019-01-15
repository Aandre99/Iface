package iface;

public class Mensagens {

    private String mensagem;
    private Usuario amigo;
    
    public Mensagens(){
        this.amigo = null;
        this.mensagem = null;
    }

    public String getMensagem() {
        return mensagem;
    }

    public Usuario getRemetente() {
        return amigo;
    }
    public void setRemetente(Usuario Remetente){
        this.amigo = Remetente;
    }
    public void setMensagem(String mensagem){
        this.mensagem = mensagem;
    }
    
}
