package iface;

import java.util.ArrayList;
import java.util.Scanner;

public class Comunidade {
    
    public static Scanner ler = new Scanner(System.in);
    
    private String NomeDaComunidade;
    private String Descricao;
    private Usuario Administrador;
    private ArrayList <Usuario> Membros;
    private ArrayList <Mensagens> MensagensComunidade;
    private ArrayList <Usuario> SolicitacoesComunidade;
    
    public Comunidade(){
        this.NomeDaComunidade = null;
        this.MensagensComunidade = new ArrayList<>();
        this.Membros = new ArrayList<>();
        this.Administrador = new Usuario();
        this.Descricao = null;
        this.SolicitacoesComunidade = new ArrayList<>();
    }

    public String getNomeDaComunidade() {
        return NomeDaComunidade;
    }
    public String getDescricao() {
        return Descricao;
    }
    public Usuario getAdministrador() {
        return Administrador;
    }
    public ArrayList <Usuario> getMembros() {
        return Membros;
    }
    public ArrayList <Mensagens> getComunidade() {
        return MensagensComunidade;
    }
    public void setNomeDaComunidade(String NomeDaComunidade) {
        this.NomeDaComunidade = NomeDaComunidade;
    }
    public void setDescricao(String Descricao) {
        this.Descricao = Descricao;
    }
    public void setAdministrador(Usuario Administrador) {
        this.Administrador = Administrador;
    }
    public void setMembros(Usuario NovoMembro) {
        this.Membros.add(NovoMembro);
    }
    public void setComunidade(Mensagens novaMensagem) {
        this.MensagensComunidade.add(novaMensagem);
    }

    public ArrayList <Usuario> getSolicitacoesComunidade() {
        return SolicitacoesComunidade;
    }
    public void setSolicitacoesComunidade(Usuario Solicitado) {
            this.SolicitacoesComunidade.add(Solicitado);
    }
    
    public void AdicionarMembros(Comunidade Atual){
        
        String auxiliar = null;
        boolean checkLogin = false;
        int opcao;
       
        if(this.getSolicitacoesComunidade().size() > 0)
        {
            System.out.println("Usuarios que desejam fazer parte da comunidade: \n");
            for(Usuario Item : this.SolicitacoesComunidade)
            {
                System.out.println("Nome: " + Item.getMeuPerfil().getNomeUsuario());
                System.out.println("1 - Adicionar | 2 - Rejeitar");
                opcao = ler.nextInt();
                auxiliar = ler.nextLine();

                if(opcao == 1){
                    this.setMembros(Item);
                    Item.setComunidades(Atual);
                    System.out.println("Usuario Adicionado");
                }
                this.SolicitacoesComunidade.remove(Item);

                if(this.SolicitacoesComunidade.size() == 0){
                    break;
                }
            }
        }else{
            System.out.println("Não ha solicitações para esta comunidade!");
        }
    }

    public void MostrarMembros(){
        
        if(this.Membros.size() > 1)
        {
            System.out.println("Membros da comunidade:");
            for(Usuario Item : this.Membros)
            {
                System.out.println("Nome: " + Item.getMeuPerfil().getNomeUsuario());
            }
        }else{
            System.out.println("Não ha membros alem de você na comunidade!");
        }
    }
    
    public void RemoverMembros()
    {
        String auxiliar = null;
        
        if(this.Membros.size() > 1)
        {
            
            System.out.println("Informe o nome do membro que deseja remover da Comunidade: ");
            auxiliar = ler.nextLine();

            for(Usuario Item : this.Membros)
            {
                if(Item.getMeuPerfil().getNomeUsuario().equals(auxiliar))
                {
                    this.Membros.remove(Item);
                    System.out.println("Usuario removido com sucesso!");
                    if(this.Membros.size() == 0){
                        break;
                    }
                }
            }
        }else{
            System.out.println("Sua comunidade não possui membros alem de você!");
        }
    }
    public void EnviarMensagens(Usuario Atual){
        
        String mensagem = null;
        Mensagens novaM = new Mensagens();
        
        System.out.println("Informe a mensagens a ser compartilhada: ");
        mensagem = ler.nextLine();
        novaM.setRemetente(Atual);
        novaM.setMensagem(mensagem);
        this.MensagensComunidade.add(novaM);
        System.out.println("Mensagem enviada com sucesso!\n");
    }
    public void LerMensagens()
    {
        if(this.MensagensComunidade.size() > 0)
        {
            System.out.println("Mensagens: ");
            for(Mensagens Item : this.MensagensComunidade)
            {
                System.out.println("De: " + Item.getRemetente().getMeuPerfil().getNomeUsuario());
                System.out.println("Mensagem: " + Item.getMensagem()+ "\n");
            }
            System.out.println("\n");
        }else{
            System.out.println("A comunidade não possui mensagens!");
        }
    }
    public void VerMembro(Comunidade Atual)
    {
        String auxiliar = null;
        System.out.println("Informe o nome do Usuario que deseja ver suas Informações: ");
        auxiliar = ler.nextLine();
        
        for(Usuario Item : Atual.getMembros()){
            if(Item.getMeuPerfil().getNomeUsuario().equals(auxiliar)){
                Item.MostrarInformacoes();
            }
        }
    }
}
