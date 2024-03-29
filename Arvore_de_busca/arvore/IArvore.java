package Arvore_de_busca.arvore;

import Arvore_de_busca.raiz.No;

public interface IArvore {

    No inserir(No atual, No valor);

    void remover(No no) throws Exception;

    Boolean buscar(No no) throws Exception;

    void preOrdem();

    void posOrdem();

    void emOrdem();

}
