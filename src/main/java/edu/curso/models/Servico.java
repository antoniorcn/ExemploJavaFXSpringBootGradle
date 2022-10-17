package edu.curso.models;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Servico {
    private String categoria;
    private long id;
    private int tempo;
    private String titulo;
    private double valor;
}
