package ArvoreB_Builder.Main;

import ArvoreB_Builder.ArvoreB.Arvore;
import ArvoreB_Builder.No.NoBuilder;

public class Main {
    public static void main(String[] args) throws Exception {
        Arvore arvore = new Arvore();
        NoBuilder no = new NoBuilder<>();
        arvore.add(no.builder().elemento(9).build());
        arvore.add(no.builder().elemento(25).build());
        arvore.add(no.builder().elemento(17).build());
        arvore.add(no.builder().elemento(77).build());
        arvore.add(no.builder().elemento(65).build());
        arvore.add(no.builder().elemento(4).build());
        arvore.add(no.builder().elemento(1).build());
        arvore.add(no.builder().elemento(37).build());

       // arvore.remover();
        arvore.preOrdem();

    }
}