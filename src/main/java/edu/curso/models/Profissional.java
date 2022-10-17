package edu.curso.models;

import lombok.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Profissional {
    private String id;
    private String nome;
    private String apelido;
    private String cargo;
    private String email;
    private String foto;
    private List<Servico> servicos = new ArrayList<>();
    private Map<Integer, Boolean> agenda = new HashMap<>();
}
