package edu.curso.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class MenuInfo {

    private String id;
    private String titulo;
    private String descricao;
    private String imagemNome;
    private int imageWidth;
    private int imageHeight;
    private String style;
    private String somNome;
    private double preco;
    private Object payLoad;

}
