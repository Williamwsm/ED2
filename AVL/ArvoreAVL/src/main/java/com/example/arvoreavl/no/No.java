package com.example.arvoreavl.no;

import lombok.*;

@Data
public class No<T> implements Comparable<No> {
    private T elemento;
    private No pai;
    private No esquerdo;
    private No direito;
    private  int altura;

    public  No(T elemento){
        this.elemento= elemento;
        this.altura = 0;
    }

    @Override
    public int compareTo(No novoElemento) {
        return Integer.compare((Integer) this.elemento,(Integer) novoElemento.elemento);
    }
}
