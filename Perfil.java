package iface;

import java.util.ArrayList;
import java.util.Scanner;

public class Perfil 
{
     
    Scanner leitor = new Scanner(System.in);
    
    private String Login;
    private String Senha;
    private String nomeUsuario;
    private String endereco;
    private String contato;
    
    public Perfil(){
        
        this.Login = null;
        this.Senha = null;
        this.nomeUsuario = null;
        this.endereco = "vazio";
        this.contato = "vazio";
    }

    public String getLogin() {
        return Login;
    }

    public String getSenha() {
        return Senha;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setLogin(String Login) {
        this.Login = Login;
    }

    public void setSenha(String Senha) {
        this.Senha = Senha;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getContato() {
        return contato;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }
}
