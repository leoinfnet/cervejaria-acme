package br.com.acme.cervejariaacme.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.converter.json.GsonBuilderUtils;

import java.time.LocalDate;
import java.util.UUID;

@Data@AllArgsConstructor@NoArgsConstructor@Builder
@Entity
@Table(name = "MARCAS")
public class Marca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String nome;
    @NotNull
    @Size(min = 2, max = 30,message = "O nome do pais deve ter entre 2 e 30")
    private String pais;
    //@Column(name = "emailFornecedor")
    @Email(message = "O email deve ser um email valido")
    private String email;
    @Past(message = "A Data é invalida")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataFundacao;
}
