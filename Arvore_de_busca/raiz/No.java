package Arvore_de_busca.raiz;

public class No {

   private No pai;

    private No esquerda;

    private No direita;

    private Integer dado;

    public No(Integer dado) {
        this.dado = dado;
    }

    public No getPai() {
        return pai;
    }

    public void setPai(No pai) {
        this.pai = pai;
    }

    public No getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(No esquerda) {
        this.esquerda = esquerda;
    }

    public No getDireita() {
        return direita;
    }

    public void setDireita(No direita) {
        this.direita = direita;
    }

    public Integer getDado() {
        return dado;
    }

    public void setDado(Integer dado) {
        this.dado = dado;
    }

    @Override
    public String toString() {
        return " " +dado;
    }
}
