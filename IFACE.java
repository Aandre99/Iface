
package iface;

import java.util.ArrayList;
import java.util.Scanner;

public class IFACE {

    public static Scanner ler = new Scanner(System.in);
    
    public static void main(String[] args) {
        
        ArrayList<Usuario> ListaUsuarios = new ArrayList<>();
        ArrayList<Comunidade> ListaComunidade = new ArrayList<>();
        
        SistemaIface Iface = new SistemaIface();

        int opcao;
        String getchar;

        while(true){
             Iface.MenuPrincipal();
            opcao = ler.nextInt();
            getchar = ler.nextLine();
            
            if(opcao == 0){
                break;
            }
            switch(opcao){
                
                case 1:
                    Iface.CriarConta(ListaUsuarios);
                    break;
                case 2:
                    ListaUsuarios = Iface.UsuarioLogado(ListaUsuarios,ListaComunidade);
                    break;
                case 3:
                    Iface.RemoverConta(ListaUsuarios);
                    break;
            }
        }
   }
    
}
