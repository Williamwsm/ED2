package ArvoreB_Builder.ArvoreB;

import ArvoreB_Builder.No.NoBuilder;
import lombok.*;

@Getter
@Setter

public class Arvore {
    private NoBuilder raiz;

    public void add(NoBuilder no) throws Exception {
        if (no == null) {
            throw new IllegalArgumentException("Elemento invalido");
        }
        this.raiz = inserir(getRaiz(), no);


    }

    private NoBuilder inserir(NoBuilder raiz, NoBuilder no) {
        if (getRaiz() == null) {
            setRaiz(no);
        } else {
            if (no.getElemento() > raiz.getElemento()) {
                if (raiz.getDireito() == null) {
                    raiz.setDireito(no);
                    no.setPai(raiz);
                } else {
                    inserir(raiz.getDireito(), no);
                }
            } else {
                if (raiz.getEsquerdo() == null) {
                    raiz.setEsquerdo(no);
                    no.setPai(raiz);
                } else {
                    inserir(raiz.getEsquerdo(), no);
                }
            }
        }
        return getRaiz();
    }

    public boolean buscar(NoBuilder no) throws Exception {
        return buscar(getRaiz(), no);
    }

    private boolean buscar(NoBuilder raiz, NoBuilder no) {
        if (raiz == null) {
            return false;

        }
        if (raiz.getElemento().equals(no.getElemento())) {
            return true;
        }else if (raiz.getElemento() > no.getElemento()) {
           return buscar(raiz.getEsquerdo(), no);
        } else {
           return buscar(raiz.getDireito(), no);
        }
    }

    public void remover(NoBuilder no) throws Exception {
        if (no == null) {
            throw new IllegalArgumentException("elemento invalido");
        } else {
            if (buscar(no)) {
                remover(getRaiz(), no);
            } else {
                throw new Exception("Elemento nao encontrado");
            }
        }
    }

    private NoBuilder remover(NoBuilder raiz, NoBuilder no) {
        if (raiz.getElemento() > no.getElemento()) {
           raiz.setEsquerdo( remover(raiz.getEsquerdo(), no));
        } else if (raiz.getElemento() < no.getElemento()) {
            raiz.setDireito(remover(raiz.getDireito(), no));
        } else {
            if (raiz.getEsquerdo() == null && raiz.getDireito() == null) {
                return null;
            } else if (raiz.getDireito() == null) {
                return raiz.getEsquerdo();
            } else if (raiz.getEsquerdo() == null) {
                return raiz.getDireito();
            } else {
                raiz.setElemento(menor(raiz.getDireito()));
                raiz.setDireito(remover(raiz.getDireito(), raiz));
            }
        }
        return raiz;
    }

    private Integer menor(NoBuilder raiz) {
        if (raiz.getElemento() == null) {
            throw new IllegalArgumentException("Elemento invalido");
        } else if (raiz.getEsquerdo() == null) {
            return raiz.getElemento();
        }
        return menor(raiz.getEsquerdo());

    }

    public void preOrdem() { // raiz -> esquerdo -> direito
        rotaPreOrdem(this.getRaiz());
    }

    private void rotaPreOrdem(NoBuilder raiz) {
        if (raiz != null) {
            System.out.println(raiz.getElemento());
            rotaPreOrdem(raiz.getEsquerdo());
            rotaPreOrdem(raiz.getDireito());
        }
    }

    public void emOrdem() {// esquerdo -> raiz -> direito
        rotaEmOrdem(this.getRaiz());
    }

    private void rotaEmOrdem(NoBuilder raiz) {
        if (raiz != null) {
            rotaEmOrdem(raiz.getEsquerdo());
            System.out.println(raiz.getElemento());
            rotaEmOrdem(raiz.getDireito());
        }
    }

    public void posOrdem() {// esquerdo -> direito -> raiz
        rotaPosOrdem(this.getRaiz());
    }

    private void rotaPosOrdem(NoBuilder raiz) {
        if (raiz != null) {
            rotaPosOrdem(raiz.getEsquerdo());
            rotaPosOrdem(raiz.getDireito());
            System.out.println(raiz.getElemento());
        }
    }
}
