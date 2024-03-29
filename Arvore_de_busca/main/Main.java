package Arvore_de_busca.main;

import Arvore_de_busca.arvore.Arvore;
import Arvore_de_busca.raiz.No;

public class Main {

    public static void main(String[] args) throws Exception {

        Arvore arvore=new Arvore();

        arvore.adicionar(new No(5));
        arvore.adicionar(new No(7));
        arvore.adicionar(new No(6));
        arvore.adicionar(new No(8));
        arvore.adicionar(new No(4));
        arvore.adicionar(new No(3));
       arvore.emOrdem();
        System.out.println();
       arvore.remover(new No(4));
       arvore.emOrdem();



        System.out.println(arvore.buscar(new No(5)));

        System.out.println(arvore.buscar(new No(3)));

        System.out.println(arvore.buscar(new No(25)));
        ;




    }

}
