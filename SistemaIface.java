package projeto;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SistemaIface implements ValidarNome, LoginUsuario {
    
    public static Scanner ler = new Scanner(System.in);
    ArrayList<Usuario> ListaUsuarios;
    ArrayList<Comunidade> ListaComunidade;
    
    public SistemaIface(){
        ListaUsuarios = new ArrayList<>();
        ListaComunidade = new ArrayList<>();
    }
    public void MenuPrincipal(){
        
        System.out.println("\n- - - - Menu - - - -");
        System.out.println("Selecione a opção desejada: \n");
        System.out.println("1 - Criar Conta;");
        System.out.println("2 - Fazer Login;");
        System.out.println("3 - Remover conta;");
        System.out.println("0 - Sair;");
         System.out.println(" - - - - - - - - - - - \n");
    }
    public void MenuEdicoes(){
        
        System.out.println("- - - - Menu Edições - - - -");
        System.out.println("\n Informe a opção que deseja editar: ");
        System.out.println("1 - Login;");
        System.out.println("2 - Nome de usuário;");
        System.out.println("3 - Senha;");
        System.out.println("4 - Endereco;");
        System.out.println("5 - Contato;");
        System.out.println("0 - Sair;");
        System.out.println(" - - - - - - - - - - - - - - \n");
    }
    public void MenuUsuarioLogado(){
        
        System.out.println("\n - - - - Menu Usuario - - - -\n ");
        System.out.println("1 - Editar Perfil; ");
        System.out.println("2 - Enviar Solicitação; ");
        System.out.println("3 - Verificar Solicitações;");
        System.out.println("4 - Enviar mensages;");
        System.out.println("5 - Ler mensagens;");
        System.out.println("6 - Criar Comunidade;");
        System.out.println("7 - Gerenciar Comunidade (Apenas para Administrador);");
        System.out.println("8 - Ver informações do  Perfil;");
        System.out.println("0 - Sair");
        
         System.out.println(" - - - - - - - - - - - - - - -\n");
     }
    public void MenuComunidade()
    {
        System.out.println("\n- - - Menu Comunidade - - -\n");
        System.out.println("1 - Adicionar Membro;");
        System.out.println("2 - Remover Membro;");
        System.out.println("3 - Mostrar todos os Membros da Comunidade;");
        System.out.println("4 - Ver Informações de um membro;");
        System.out.println("0 - Sair;");
        System.out.println("\n- - - - - - - - - - - - - -\n");
    }
    
    // Funções Principais
    
    public void CriarConta(){
        
        Usuario novoUsuario = new Usuario();
        String auxiliar = null;
        
        System.out.println("Digite as seguintes informações para cadastrar-se no Iface: ");
        System.out.println("Nome de Usuario: ");
        
        while(true){
            auxiliar = ler.nextLine();
            if(!VerificaNome(auxiliar, this.ListaUsuarios)){
                break;
            }else{
                System.out.println("\nNome de usuario ja existe, informe um nome diferente!\n");
            }
        }
        novoUsuario.setNomeUsuario(auxiliar);
        
        System.out.println("Login: ");
        auxiliar = ler.nextLine();
        novoUsuario.setLogin(auxiliar);
        
        System.out.println("Senha: ");
        auxiliar = ler.nextLine();
        novoUsuario.setSenha(auxiliar);
        
        this.ListaUsuarios.add(novoUsuario);
        
        System.out.println("\n-> Conta criada com sucesso!");
    }
    public void RemoverConta(){
        if(this.ListaUsuarios.size() > 0)
        {
            String auxiliar = null;
            int opcao;
            System.out.println("Deseja realmente excluir sua conta permanentemente?");
            System.out.println("1 - Sim | 0 - Não\n");
            opcao = ler.nextInt();
            auxiliar = ler.nextLine();

            if(opcao == 1){
                Usuario Aux = Login(this.ListaUsuarios);
                for(Usuario Item :  Aux.getAmigos()){
                    Item.getAmigos().remove(Aux);
                }
                this.ListaUsuarios.remove(Aux);
                System.out.println("Conta removida com sucesso!");   
            }else{
                System.out.println("Remoção abortada com sucesso!");
            }
        }else{
            System.out.println("Não ha contas cadastradas!");
        }
    }
    public void UsuarioLogado(){
        
        if(this.ListaUsuarios.size() < 1){
            System.out.println("Não ha contas cadastradas!");
        }else{
            
            Usuario Atual = Login(this.ListaUsuarios);
            int opcao;
            String auxiliar = null;
            
            try
            {
                while(true)
                {
                    MenuUsuarioLogado();
                    opcao = ler.nextInt();
                    auxiliar = ler.nextLine();

                    if(opcao == 0){break;}

                    switch(opcao){
                        case 1:
                            EditarInformacao(Atual);
                            break;
                        case 2:
                            AdicionarAmigos(Atual);
                            break;
                        case 3:
                            Atual = VerificarSolicitacoes(Atual);
                            break;
                        case 4: 
                            EnviarMensagem(Atual);
                            break;
                        case 5:
                            LerMensagem(Atual);
                            break;
                        case 6:
                            this.ListaComunidade.add(CriarComunidade(Atual));
                            break;
                        case 7:
                            GerenciarComunidade(Atual,this.ListaUsuarios);
                            break;
                        case 8 :
                            MostrarUsuario(Atual);
                            break;
                        case 9 : 
                            SolicitarComunidade(Atual, this.ListaComunidade);
                            break;
                    }
               }
            }catch(InputMismatchException II){
                System.out.println("\n-> Formato da Entrada diferente do esperado!");
                System.out.println("-> Desconectando de sua conta...");
            }
                
                for(Usuario Item : this.ListaUsuarios)
                {
                    if(Item.getNomeUsuario().equals(Atual.getNomeUsuario())){
                        Item = Atual;
                        break;
                    }
                }
            }
        }
    
    // Funções especificas de um Usuario
    
    public void MostrarUsuario(Usuario Atual){
        
            System.out.println("Informacoes do Usuario: ");
            Atual.MostrarInformacoes();
            System.out.println("\n");
    }
    public Usuario EditarInformacao(Usuario Atual){
       
        String novaInformacao = null;
        String auxiliar = null;
        int opcao1;
        
        System.out.println("Escolha a opção desejada: \n");
        System.out.println("1 - Editar Informações;");
        System.out.println("2 - Criar novos Atributos;");
        
        opcao1 = ler.nextInt();
        auxiliar = ler.nextLine();
        
        if(opcao1 == 1)
        {
            while(true)
            {
                MenuEdicoes();
                int opcao;
                opcao = ler.nextInt();
                auxiliar = ler.nextLine();

                if(opcao == 0){break;}

                 switch(opcao)
                 {
                    case 1:

                        System.out.println("\nInforme o novo Login: ");
                        novaInformacao = ler.nextLine();

                        if(Atual.getComunidades().size() > 0){
                            for(Comunidade Item : Atual.getComunidades())
                            {
                                if(Item.getAdministrador().getLogin().equals(Atual.getLogin()))
                                {
                                    Item.getAdministrador().setLogin(novaInformacao);
                                }
                            }
                        }
                        Atual.setLogin(novaInformacao);
                        break;

                    case 2:
                        System.out.println("Informe o novo nome de usuário: ");
                        novaInformacao = ler.nextLine();
                        Atual.setNomeUsuario(novaInformacao);
                        break;
                    case 3:
                        System.out.println("Informe a nova senha: ");
                        novaInformacao = ler.nextLine();
                        if(Atual.getComunidades().size() > 0){
                            for(Comunidade Item : Atual.getComunidades())
                            {
                                if(Item.getAdministrador().getLogin().equals(Atual.getLogin()))
                                {
                                    Item.getAdministrador().setSenha(novaInformacao);
                                }
                            }
                        }
                        Atual.setSenha(novaInformacao);
                        break;
                    case 4:
                        System.out.println("Informe o novo Endereco: ");
                        novaInformacao = ler.nextLine();
                        Atual.setEndereco(novaInformacao);
                        break;
                    case 5:
                        System.out.println("Informe o novo Contato: ");
                        novaInformacao = ler.nextLine();
                        Atual.setContato(novaInformacao);
                        break;
                    case 6:
                        System.out.println("Informe o nome do atributo que deseja Editar: ");
                        auxiliar = ler.nextLine();
                        for(Atributo Item : Atual.getListaAtributos())
                        {
                            if(Item.getAtributo().equals(auxiliar))
                            {
                                System.out.println("Escolha a opção que deseja editar: ");
                                System.out.println("1 - Nome do Atributo;");
                                System.out.println("2 - Descricao do atributo;");
                                opcao = ler.nextInt();
                                auxiliar = ler.nextLine();
                                
                                if(opcao == 1){
                                    System.out.println("Informe o novo nome para o atributo: ");
                                    auxiliar  = ler.nextLine();
                                    Item.setAtributo(auxiliar);
                                }else{
                                    System.out.println("Informe a nova descrição para o atributo: ");
                                    auxiliar = ler.nextLine();
                                    Item.setDescricao(auxiliar);
                                    
                                }
                                break;
                            }
                        }
                }
            }
            System.out.println("\n-> Informação alterada com sucesso!\n");
        }else{
            Atributo novoAtributo = new Atributo();
            System.out.println("Informe o nome do Atributo: ");
            auxiliar = ler.nextLine();
            novoAtributo.setAtributo(auxiliar);
            System.out.println("Informe uma descricao para o novo atributo: ");
            auxiliar = ler.nextLine();
            novoAtributo.setDescricao(auxiliar);
            Atual.setListaAtributos(novoAtributo);
            System.out.println("Atributo criado com sucesso!");
        }
       
          return Atual;
    }
    public void AdicionarAmigos( Usuario Atual){
        
        String auxiliar = null;
        int opcao;
        
        System.out.println("    Informe a opção desejada: \n");
        System.out.println("1 - Enviar solicitação para usuário;");
        System.out.println("2 - Enviar solicitação para comunidade;");
        
        opcao = ler.nextInt();
        auxiliar = ler.nextLine();
        
        if(opcao == 1)
        {
            if(this.ListaUsuarios.size() > 1)
            {
                System.out.println("Usuarios Disponiveis: ");
                for(Usuario Item : this.ListaUsuarios)
                {
                    if(Item != Atual && !Atual.getAmigos().contains(Item))
                    {
                        System.out.println("Nome: " + Item.getNomeUsuario());
                    }
                }

                System.out.println("Informe o nome do usuario a ser solicitado: ");
                auxiliar = ler.nextLine();

                for(Usuario Item : this.ListaUsuarios)
                {
                    if(Item.getNomeUsuario().equals(auxiliar))
                    {
                        Item.getSolicitacoes().add(Atual);
                        System.out.println("\n-> Solicitacao enviada com sucesso!\n");
                        break;
                    }
                }
            }else{
                System.out.println("Não ha usuários disponíveis!\n");
            }
        }else{
            
            if(this.ListaComunidade.size() > 0)
            {
                System.out.println("Comunidades Disponiveis: ");
                for(Comunidade Item : this.ListaComunidade){
                    System.out.println("Nome: " + Item.getNomeDaComunidade());
                }
                
                System.out.println("Informe a comunidade a qual deseja enviar uma solicitação: ");
                auxiliar = ler.nextLine();
                for(Comunidade Item : this.ListaComunidade)
                {
                    if(Item.getNomeDaComunidade().equals(auxiliar))
                    {
                        Item.setSolicitacoesComunidade(Atual);
                        System.out.println("-> Solicitação enviada com sucesso!\n");
                    }
                }
            }
        }
    }
    public Usuario VerificarSolicitacoes(Usuario Atual){    
        int opcao;
        String auxiliar = null;
        
        System.out.println("    Informe a opção desejada: \n");
        System.out.println("1 - Ver solicitacões de Usuarios;");
        System.out.println("2 - Ver solicitações de Comunidade;");
        opcao = ler.nextInt();
        auxiliar = ler.nextLine();
        
        if(opcao == 1)
        {
            if(Atual.getSolicitacoes().size() > 0)
            {
                for(Usuario Item : Atual.getSolicitacoes())
                {
                    System.out.println("Nome: " + Item.getNomeUsuario());
                    System.out.println("1 - Aceitar | 0 - Rejeitar;");

                    opcao = ler.nextInt();
                    if(opcao == 1)
                    {
                        Atual.setAmigos(Item);
                        Item.setAmigos(Atual);
                        System.out.println("Agora vocês são amigos!");
                    }
                    Atual.RemoveSolicitacoes(Item);
                    if(Atual.getSolicitacoes().size() <= 0){
                        break;
                    }
                }   
            }else{
                System.out.println("Você nao possui solicitações!");
            }
        }else{
            
            if(Atual.getComunidades().size() > 0)
            {
                System.out.println("Suas comunidades: ");
                for(Comunidade Item : Atual.getComunidades()){
                    System.out.println("Nome: " + Item.getNomeDaComunidade());
                }
                System.out.println("Informe o nome da comunidade a qual deseja verificar as solicitacoes: ");
                auxiliar = ler.nextLine();
                for(Comunidade Item : Atual.getComunidades())
                {
                    if(Item.getNomeDaComunidade().equals(auxiliar)){
                        Item.AdicionarMembros(Item);
                    }
                }
            }else{
                System.out.println("Você não possui comunidades!");
            }
        }
        return Atual;
    }
    public void EnviarMensagem(Usuario Atual){
        
        int opcao;
        String limpar = null;
        System.out.println("    Informe a opcao desejada: \n");
        System.out.println("1 - Enviar mensagem a amigo;");
        System.out.println("2 - Enviar mensagem a comunidade: ");
        
        opcao = ler.nextInt();
        limpar = ler.nextLine();
        
        if(opcao == 1 )
        {
            if( Atual.getAmigos().size() > 0)
            {
                String auxiliar = null;
                String mensagem  = null;

                System.out.println("Lista de Amigos: \n");
                for(Usuario Item : Atual.getAmigos()){
                    System.out.println("Nome: " + Item.getNomeUsuario());
                }

                System.out.println("Informe para qual amigo deseja enviar uma mensagem: ");
                auxiliar = ler.nextLine();
                System.out.println("Digite a mensagem: ");
                mensagem = ler.nextLine();

                for(Usuario Item : Atual.getAmigos()){
                    if(Item.getNomeUsuario().equals(auxiliar)){

                        Mensagens enviarMensagem = new Mensagens();
                        Mensagens mensagemEnviada = new Mensagens();
                        
                        enviarMensagem.setMensagem(mensagem);
                        enviarMensagem.setRemetente(Atual);
                        Item.setCaixaDeMensagem(enviarMensagem);
                        mensagemEnviada.setMensagem(mensagem);
                        mensagemEnviada.setRemetente(Item);
                        Atual.setMensagensEnviadas(enviarMensagem);
                        
                        System.out.println("Mensagem enviada!");
                        break;
                    }
                }
            }else{
                System.out.println("Você não possui amigos!");
            }
        }else{
            
            if(Atual.getComunidades().size() > 0)
            {
                String auxiliar = null;
            
                System.out.println("Comunidades Disponiveis: ");
                for(Comunidade Item : Atual.getComunidades()){
                    System.out.println("Nome: " + Item.getNomeDaComunidade());
                }

                System.out.println("Informe o nome da comunidade a qual deseja enviar uma mensagem: ");
                auxiliar = ler.nextLine();

                for(Comunidade Item : Atual.getComunidades())
                {
                    if(Item.getNomeDaComunidade().equals(auxiliar)){
                        Item.EnviarMensagens(Atual);
                        break;
                    }
                }
            }else{
                System.out.println("Você não está cadastrado em nenhuma comunidade!");
            }
        }
    }
    public void LerMensagem(Usuario Atual){
        
        int opcao;
        String limpar = null;
        
        System.out.println("Infome a opção deseja: \n");
        System.out.println("1 - Ler mensagens de amigos;");
        System.out.println("2 - Ler mensagens de comunidades;");
        
        opcao = ler.nextInt();
        limpar = ler.nextLine();
        
        if(opcao == 1)
        {
            if(Atual.getCaixaDeMensagem().size() > 0)
            {

                System.out.println("Caixa de mensagens: \n");
                for(Mensagens Item : Atual.getCaixaDeMensagem()){
                    System.out.println("De: " + Item.getRemetente().getNomeUsuario());
                    System.out.println("Mensagem: " + Item.getMensagem() + "\n");
                }
            }else{
                System.out.println("Você não possui mensagens!");
            }
        }else{
            
            if(Atual.getComunidades().size() > 0)
            {
                String auxiliar = null;
                
                System.out.println("Comunidades Disponiveis: ");
                for(Comunidade Item : Atual.getComunidades()){
                    System.out.println("Nome: " + Item.getNomeDaComunidade());
                }
                System.out.println("Informe a comunidade desejada: ");
                auxiliar = ler.nextLine();
                for(Comunidade  Item : Atual.getComunidades())
                {
                    if(Item.getNomeDaComunidade().equals(auxiliar)){
                        Item.LerMensagens();
                        break;
                    }
                }
            }
        }
    }
    public Comunidade CriarComunidade(Usuario Atual){
        String auxiliar = null;
        Comunidade NovaComunidade = new Comunidade();
        
        System.out.println("Informe o nome da Comunidade: ");
        auxiliar = ler.nextLine();
        NovaComunidade.setNomeDaComunidade(auxiliar);
        System.out.println("Descreva a comunidade: ");
        auxiliar = ler.nextLine();
        NovaComunidade.setDescricao(auxiliar);
        NovaComunidade.setAdministrador(Atual);
        NovaComunidade.setMembros(Atual);
        Atual.setComunidades(NovaComunidade);
        System.out.println("Comunidade Criada com sucesso!");
        
        return NovaComunidade;
    }
    public void GerenciarComunidade(Usuario Atual, ArrayList<Usuario> Lista){
        
        String auxiliar = null;
        int opcao;
  
        if(Atual.getComunidades().size() > 0)
        {
            System.out.println("Suas Comunidades:\n");
            for(Comunidade Item : Atual.getComunidades())
            {
                System.out.println("Nome da Comunidade: " + Item.getNomeDaComunidade());
            }

            System.out.println("\nInforme o nome da Comunidade a qual deseja gerenciar: ");
            auxiliar = ler.nextLine();

            System.out.println("Prosseguir requer acessor ao Administrador: \n");

                for(Comunidade Item : Atual.getComunidades()){

                    if(Item.getNomeDaComunidade().equals(auxiliar))
                    {
                        while(true)
                        {
                            if((Login(Lista)).equals(Item.getAdministrador())){
                                System.out.println("Acesso ao Administrador permitido");
                                break;
                            }else{
                                System.out.println("Usuario não é o administrador da comunidade, Informe os dados corretamente!");
                            }
                        }

                        try
                        {
                            
                      
                            while(true)
                            {
                                MenuComunidade();
                                opcao = ler.nextInt();

                                if(opcao == 0){break;}

                                switch(opcao)
                                {
                                    case 1:
                                        Item.AdicionarMembros(Item);
                                        break;
                                    case 2:
                                        Item.RemoverMembros();
                                        break;
                                    case 3:
                                        Item.MostrarMembros();
                                        break;
                                    case 4:
                                        Item.VerMembro(Item);
                                        break;
                                    default:
                                        System.out.println("Opção Inexistente!\n");
                                }
                            }
                        }catch(InputMismatchException IV){
                            System.out.println("Entrada informada difere do tipo de dado esperado!");
                            System.out.println("Retornando ao menu de edições....");
                        }
                        
                      }
                }
        }else{
            System.out.println("Você Não possui Comunidades!");
        }
    }
    public void SolicitarComunidade(Usuario Atual, ArrayList<Comunidade> Lista){
        String auxiliar = null;
        System.out.println("Comunidades Disponiveis: ");
        for(Comunidade Item : Lista){
            if(!Atual.getComunidades().contains(Item)){
                System.out.println("Nome Da comunidade: " + Item.getNomeDaComunidade());
            }
        }
        
        System.out.println("Informe o nome da comunidade a qual deseja solicitar ingresso: ");
        auxiliar = ler.nextLine();
        
        for(Comunidade Item : Lista )
        {
            if(Item.getNomeDaComunidade().equals(auxiliar))
            {
                Item.setSolicitacoesComunidade(Atual);
                System.out.println("Solicitação enviada ao administrador da comunidade!");
                break;
            }
        }
    }
    
    // Interfaces
    
    @Override
    public boolean VerificaNome(String nome, ArrayList<Usuario> listaUsuarios) {
        
        for(Usuario usuario : listaUsuarios){
            if(usuario.getNomeUsuario().equals(nome)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Usuario Login(ArrayList<Usuario> Lista) {
        
         String auxiliar = null;
            boolean verificaLogin = false;
            boolean verificaSenha = false;
            Usuario Atual = null;
            
            System.out.println("Digite as seguintes informações: ");
            System.out.println("Login: ");
            
                while(true)
                {
                    auxiliar = ler.nextLine();
                    for(Usuario Item : Lista)
                    {
                        if(Item.getLogin().equals(auxiliar))
                        {  
                            verificaLogin = true;
                            System.out.println("Senha: ");
                            while(true)
                            {
                                auxiliar = ler.nextLine();
                                if(Item.getSenha().equals(auxiliar)){
                                    verificaSenha = true;
                                    Atual = Item;
                                }
                                if(verificaSenha){
                                    break;
                                }else{
                                    System.out.println("Senha Incorreta, verifique sua senha e tente novamente!");
                                }
                            }
                        }
                    }
                    if(verificaLogin)
                    {
                        return Atual;
                    }else{
                        System.out.println("Login informado nao encontrado! Informe-o novamente!");
                    }
                }
    }
   
}
