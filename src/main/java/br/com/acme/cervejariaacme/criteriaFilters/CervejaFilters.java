package br.com.acme.cervejariaacme.criteriaFilters;

import br.com.acme.cervejariaacme.model.Estilo;
import br.com.acme.cervejariaacme.model.Lupulo;
import br.com.acme.cervejariaacme.model.Marca;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@Data @AllArgsConstructor @Builder
public class CervejaFilters {
    private Optional<String> nome;
    private Optional<Estilo> estilo;
    private Optional<Marca> marca;
    private Optional<String> pais;
    private Optional<List<Lupulo>> lupulos;

    public CervejaFilters() {
        this.nome = Optional.empty();
        this.estilo = Optional.empty();
        this.marca = Optional.empty();
        this.pais = Optional.empty();
        this.lupulos = Optional.empty();
    }
}
