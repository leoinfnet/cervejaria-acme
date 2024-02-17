package br.com.acme.cervejariaacme.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Lupulo {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Size(max = 5000, message = "O tamanho excede o limite do campo")
    private  String descricao;
    @ManyToMany(fetch = FetchType.EAGER)
    List<Cerveja> cervejas;

    @Override
    public String toString() {
        return "Lupulo{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cervejas=" + cervejas +
                '}';
    }
}
