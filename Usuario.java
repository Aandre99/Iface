package projeto;

import java.util.ArrayList;

public class Usuario extends Perfil{
    
    private ArrayList<Mensagens> caixaDeMensagemRecebidas;
    private ArrayList<Mensagens> MensagensEnviadas;
    private ArrayList<Usuario> Amigos;
    private ArrayList<Usuario> Solicitacoes;
    private ArrayList<Comunidade> Comunidades;
    private ArrayList<Atributo> ListaAtributos;
    
    public Usuario(){
        
        this.caixaDeMensagemRecebidas = new ArrayList<>();
        this.Solicitacoes = new ArrayList<>();
        this.Amigos = new ArrayList<>();
        this.Comunidades = new ArrayList<>();
        this.MensagensEnviadas = new ArrayList<>();
        this.ListaAtributos = new ArrayList<>();
    }

    public ArrayList<Mensagens> getCaixaDeMensagem() {
        return caixaDeMensagemRecebidas;
    }
    public void setCaixaDeMensagem(Mensagens M){
        this.caixaDeMensagemRecebidas.add(M);
    }
    public ArrayList<Usuario> getAmigos() {
        return Amigos;
    }
    public void setAmigos(Usuario Amigo) {
        this.Amigos.add(Amigo);
    }

    public ArrayList<Usuario> getSolicitacoes() {
        return Solicitacoes;
    }

    public void setSolicitacoes(Usuario Solicitado) {
        this.Solicitacoes.add(Solicitado);
    }
    public void RemoveSolicitacoes(Usuario Removido){
        this.Solicitacoes.remove(Removido);
    }

    public ArrayList<Comunidade> getComunidades() {
        return Comunidades;
    }
    public ArrayList<Mensagens> getMensagensEnviadas() {
        return MensagensEnviadas;
    }

    public void setMensagensEnviadas(Mensagens mensagem) {
        this.MensagensEnviadas.add(mensagem);
    }
    public ArrayList<Atributo> getListaAtributos() {
        return ListaAtributos;
    }
    public void setListaAtributos(Atributo Atributos) {
        this.ListaAtributos.add(Atributos);
    }
    public void setComunidades(Comunidade NovaComunidade) {
        this.Comunidades.add(NovaComunidade);
    }
    
    
    public void MostrarInformacoes(){
        
        System.out.println("Nome: " + super.getNomeUsuario());
        System.out.println("Endereco: " + super.getEndereco());
        System.out.println("Contato: " + super.getContato());
        
        if(this.getListaAtributos().size() > 0)
        {
            for(Atributo Item : this.getListaAtributos()){
                System.out.println(Item.getAtributo() + " : " + Item.getDescricao());
            }
        }
        System.out.println("Amigos: ");
        for(Usuario Item : this.Amigos){
            System.out.println("Nome: " + Item.getNomeUsuario());
        }
        System.out.println("Comunidades: ");
        for(Comunidade Item : this.Comunidades){
            System.out.println("Nome da Comunidade: " + Item.getNomeDaComunidade());
        }
        System.out.println("Mensagens de Usuarios: \n");
        
        System.out.println("Mensagens Recebidas: ");
        
        for(Mensagens Item : this.caixaDeMensagemRecebidas){
            System.out.println("De: " + Item.getRemetente().getNomeUsuario());
            System.out.println("Mensagem: " + Item.getMensagem() + "\n");
        }
        System.out.println("Mensagens Enviadas: ");

        for(Mensagens Item : this.MensagensEnviadas)
        {
            System.out.println("Para: " + Item.getRemetente().getNomeUsuario());
            System.out.println("Mensagem: " + Item.getMensagem());
        }
        System.out.println("\nMensagens de Comunidades: ");
        for(Comunidade Item : this.getComunidades())
        {
            System.out.println("Comunidade : " + Item.getNomeDaComunidade());
            for(Mensagens Item2 : Item.getComunidade())
            {
                System.out.println("De: " + Item2.getRemetente().getNomeUsuario());
                System.out.println("Mensagens: " + Item2.getMensagem());
            }
        }
        System.out.println("\n");
}
}
