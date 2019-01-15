
package iface;

public class Atributo {
    private String atributo;
    private String descricao;
    
    public Atributo(){
        this.atributo = null;
        this.descricao = null;
    } 

    public String getAtributo() {
        return atributo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setAtributo(String atributo) {
        this.atributo = atributo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
}
