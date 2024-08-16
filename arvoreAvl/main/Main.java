package com.example.arvoreAvl.main;


import com.example.arvoreAvl.arvore.ArvoreAVL;
import com.example.arvoreAvl.no.No;

public class Main {
    public static void main(String[] args) {
        ArvoreAVL arvore = new ArvoreAVL();

        arvore.add(new No(10));
        arvore.add(new No(20));
        arvore.add(new No(5));
        arvore.add(new No(6));
        arvore.add(new No(3));
        arvore.add(new No(1));

        System.out.println(" em ordem:");
        arvore.emOrdem();

        System.out.println(" pré-ordem:");
        arvore.preOrdem();

        System.out.println("pós-ordem:");
        arvore.posOrdem();

        System.out.println("Removendo o nó ");
        arvore.remover(new No(10));

        System.out.println(" em ordem:");
        arvore.emOrdem();
    }
}

