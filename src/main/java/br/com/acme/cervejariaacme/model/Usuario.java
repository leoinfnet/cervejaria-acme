package br.com.acme.cervejariaacme.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data@AllArgsConstructor@NoArgsConstructor@Builder
@Entity
public class Usuario {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String nome;
    private Integer status;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    Set<Role> roles;

    @ManyToMany
    private Set<Cerveja> cervejasCurtidas;

}
