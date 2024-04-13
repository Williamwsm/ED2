package ArvoreB_Builder.No;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class NoBuilder<T>{
    private NoBuilder<T> pai;
    private Integer elemento;
    private NoBuilder<T> esquerdo;
    private NoBuilder<T> direito;

    @Override
    public String toString() {
        return  " " +elemento;
    }
}