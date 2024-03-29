package Arvore_de_busca.arvore;

import Arvore_de_busca.raiz.No;

public class Arvore implements IArvore {

    No raiz;


    public void adicionar(No no) {

        this.raiz = inserir(raiz, no);

    }

    @Override
    public No inserir(No atual, No valor) {
        if (atual == null) {
            atual = valor;
        } else {

            if (valor.getDado() > atual.getDado()) {

                if (atual.getDireita() != null) {
                    inserir(atual.getDireita(), valor);
                } else {
                    atual.setDireita(valor);
                    valor.setPai(atual);
                }

            } else {

                if (atual.getEsquerda() != null) {
                    inserir(atual.getEsquerda(), valor);
                } else {
                    atual.setEsquerda(valor);
                    valor.setPai(atual);
                }

            }

        }


        return atual;

    }


    @Override
    public Boolean buscar(No no) throws Exception {

        No aux = this.raiz;

        while (aux != null) {

            if (aux.getDado() == no.getDado()) {
                return true;
            }

            if (no.getDado() > aux.getDado()) {

                aux = aux.getDireita();


            } else {
                aux = aux.getEsquerda();
            }

        }

        return false;


    }


    @Override
    public void preOrdem() { // raiz ->  esqueda -> direita
        rotaPreOrdem(this.raiz);


    }

    private void rotaPreOrdem(No no) {
        if (no != null) {
            System.out.println(no.getDado() + " ");
            rotaPreOrdem(no.getEsquerda());
            rotaPreOrdem(no.getDireita());
        }
    }

    @Override
    public void posOrdem() { // esquerda -> direita -> raiz
        rotaPosOrdem(this.raiz);

    }

    private void rotaPosOrdem(No no) {
        if (no != null) {
            rotaPosOrdem(no.getEsquerda());
            rotaPosOrdem(no.getDireita());
            System.out.println(no.getDado() + " ");
        }

    }

    @Override
    public void emOrdem() {
        rotaEmOrdem(this.raiz);
    }

    private void rotaEmOrdem(No no) { //esquerda -> raiz ->  direira
        if (no != null) {
            rotaEmOrdem(no.getEsquerda());
            System.out.println(no.getDado() + " ");
            rotaEmOrdem(no.getDireita());
        }

    }

    public void remover(No no) throws Exception {
        if (no == null) {
            throw new RuntimeException("Elemento nao encontrado");

        }
        removerRecursivo(this.raiz, no);

    }


    private No removerRecursivo(No raiz, No valor) {


        if (raiz == null) {
            return raiz;
        }
        if (raiz.getDado() > valor.getDado()) { // verifica se o no é menor q a raiz
            raiz.setEsquerda(removerRecursivo(raiz.getEsquerda(), valor));
        } else if (raiz.getDado() < valor.getDado()) { // verifica se o no é maior q a raiz
            raiz.setDireita(removerRecursivo(raiz.getDireita(), valor));
        }else {
            if (raiz.getDireita() == null && raiz.getEsquerda() == null){// se o no nao tiver filhos retorna nulo
                return null;
            } else if (raiz.getEsquerda() == null) { // se tiver apenas um filho a direita retorna o filho esquerdo
                return  raiz.getDireita();

            } else if (raiz.getDireita() == null) { // se tiver apenas um filho a esquerda retorna o filho direito
                return  raiz.getEsquerda();

            }else {// se o no tiver dois filhos vai retornar o menor filho da subArvore diteita
                raiz.setDado(menor(raiz.getDireita()));
                raiz.setDireita(removerRecursivo(raiz.getDireita(), raiz));

            }


    }
        return raiz;
    }

    private Integer menor(No raiz){
        if (raiz.getEsquerda() == null){
            return raiz.getDado();
        }
        return menor(raiz.getEsquerda());

    }
    private Integer maior(No raiz){
        if (raiz.getDireita() == null){
            return raiz.getDado();
        }
        return maior(raiz.getDireita());



    }
}

