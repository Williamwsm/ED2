package com.example.arvoreAvl.arvore;

import com.example.arvoreAvl.no.No;
import lombok.Data;

@Data
public class ArvoreAVL {
    private No raiz;

    public void add(No no) {
        if (no == null) {
            throw new IllegalArgumentException("O nó está vazio");
        }
        this.raiz = inserir(this.raiz, no);
    }

    private No inserir(No raiz, No no) {
        if (raiz == null) {
            return no;
        }

        if (no.compareTo(raiz) > 0) {
            raiz.setDireito(inserir(raiz.getDireito(), no));
        } else {
            raiz.setEsquerdo(inserir(raiz.getEsquerdo(), no));
        }

        // Atualizar altura
        raiz.setAltura(1 + Math.max(altura(raiz.getEsquerdo()), altura(raiz.getDireito())));

        // Balancear o nó
        return balancear(raiz);
    }

    public void remover(No no) {
        if (no.getElemento() == null) {
            throw new IllegalArgumentException("Nó vazio");
        } else {
            if (buscar(no)) {
                this.raiz = remover(this.raiz, no);
            }
        }
    }

    private No remover(No raiz, No no) {


        if (no.compareTo(raiz) > 0) {
            raiz.setDireito(remover(raiz.getDireito(), no));
        } else if (no.compareTo(raiz) < 0) {
            raiz.setEsquerdo(remover(raiz.getEsquerdo(), no));
        } else {
            if (raiz.getEsquerdo() == null || raiz.getDireito() == null) {
                raiz = (raiz.getEsquerdo() != null) ? raiz.getEsquerdo() : raiz.getDireito();
            } else {
                No novoNo = maior(raiz.getEsquerdo());
                raiz.setElemento(novoNo.getElemento());
                raiz.setEsquerdo(remover(raiz.getEsquerdo(), novoNo));
            }
        }

        if (raiz == null) {
            return raiz;
        }

        // Atualizar altura
        raiz.setAltura(1 + Math.max(altura(raiz.getEsquerdo()), altura(raiz.getDireito())));

        // Balancear o nó
        return balancear(raiz);
    }

    public boolean buscar(No no) {
        return buscar(this.raiz, no);
    }

    private boolean buscar(No raiz, No no) {
        if (raiz == null) {
            return false;
        }
        if (no.getElemento().equals(raiz.getElemento())) {
            return true;
        } else {
            if (no.compareTo(raiz) > 0) {
                return buscar(raiz.getDireito(), no);
            } else {
                return buscar(raiz.getEsquerdo(), no);
            }
        }
    }

    public void emOrdem() {
        rotaEmOrdem(this.raiz);
    }

    public void preOrdem() {
        rotaPreOrdem(this.raiz);
    }

    public void posOrdem() {
        rotaPosOrdem(this.raiz);
    }

    private void rotaPosOrdem(No raiz) {
        if (raiz != null) {
            rotaPosOrdem(raiz.getEsquerdo());
            rotaPosOrdem(raiz.getDireito());
            System.out.println(raiz.getElemento());
        }
    }

    private void rotaPreOrdem(No raiz) {
        if (raiz != null) {
            System.out.println(raiz.getElemento());
            rotaPreOrdem(raiz.getEsquerdo());
            rotaPreOrdem(raiz.getDireito());
        }
    }

    private void rotaEmOrdem(No raiz) {
        if (raiz != null) {
            rotaEmOrdem(raiz.getEsquerdo());
            System.out.println(raiz.getElemento());
            rotaEmOrdem(raiz.getDireito());
        }
    }

    private No maior(No raiz) {
        while (raiz.getDireito() != null) {
            raiz = raiz.getDireito();
        }
        return raiz;
    }

    private int altura(No no) {
        return (no == null) ? 0 : no.getAltura();
    }

    private int getBalanceamento(No no) {
        return (no == null) ? 0 : altura(no.getEsquerdo()) - altura(no.getDireito());
    }

    private No balancear(No no) {
        int balanceamento = getBalanceamento(no);

        if (balanceamento > 1) {
            if (getBalanceamento(no.getEsquerdo()) < 0) {
                no.setEsquerdo(rotacaoEsquerda(no.getEsquerdo()));
            }
            return rotacaoDireita(no);
        }

        if (balanceamento < -1) {
            if (getBalanceamento(no.getDireito()) > 0) {
                no.setDireito(rotacaoDireita(no.getDireito()));
            }
            return rotacaoEsquerda(no);
        }

        return no;
    }

    private No rotacaoEsquerda(No no) {
        No novoRaiz = no.getDireito();
        No neto = novoRaiz.getEsquerdo();

        novoRaiz.setEsquerdo(no);
        no.setDireito(neto);

        // Atualizar alturas
        no.setAltura(1 + Math.max(altura(no.getEsquerdo()), altura(no.getDireito())));
        novoRaiz.setAltura(1 + Math.max(altura(novoRaiz.getEsquerdo()), altura(novoRaiz.getDireito())));

        return novoRaiz;
    }

    private No rotacaoDireita(No no) {
        No novoRaiz = no.getEsquerdo();
        No neto = novoRaiz.getDireito();

        novoRaiz.setDireito(no);
        no.setEsquerdo(neto);

        // Atualizar alturas
        no.setAltura(1 + Math.max(altura(no.getEsquerdo()), altura(no.getDireito())));
        novoRaiz.setAltura(1 + Math.max(altura(novoRaiz.getEsquerdo()), altura(novoRaiz.getDireito())));

        return novoRaiz;
    }
}
