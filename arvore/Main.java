package arvore;

public class Main {
    public static void main(String[] args) {
        // Criação da árvore rubro-negra
        RedBlackTree<Integer> rn = new RedBlackTree<>();

        rn.insert(10);
        rn.insert(20);
        rn.insert(30);
        rn.insert(15);
        rn.insert(25);
        System.out.println(rn.search(10));
        System.out.println(rn.search(15));
        System.out.println(rn.search(5));
        rn.delete(20);

        System.out.println(rn.search(20));
    }
}

