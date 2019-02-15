package projeto;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class IFace {

    public static Scanner ler = new Scanner(System.in);
    
    public static void main(String[] args) {
        
        SistemaIface Iface = new SistemaIface();

        int opcao;
        String getchar = null;

        try
        {
            while(true)
            {
                Iface.MenuPrincipal();
                opcao = ler.nextInt();
                getchar = ler.nextLine();

                if(opcao == 0){
                    break;
                }
                switch(opcao){

                    case 1:
                        Iface.CriarConta();
                        break;
                    case 2:
                        Iface.UsuarioLogado();
                        break;
                    case 3:
                        Iface.RemoverConta();
                        break;
                }
            }
            }catch(InputMismatchException I){
                    System.out.println("-> Entrada informada possui o tipo de dado incompativel com o esperado!");
                    System.out.println("-> Não é possivel prosseguir, Encerrando o sistema...\n");
            }
   }
    
}

