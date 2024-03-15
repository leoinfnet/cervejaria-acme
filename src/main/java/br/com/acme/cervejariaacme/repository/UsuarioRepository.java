package br.com.acme.cervejariaacme.repository;

import br.com.acme.cervejariaacme.model.Role;
import br.com.acme.cervejariaacme.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    @Query("SELECT u FROM Usuario u WHERE u.status = 1")
    List<Usuario> findAllAtivos();
    @Query("SELECT u from Usuario u WHERE u.email=?1 OR u.nome=?2 ORDER BY u.nome asc limit 5")
    List<Usuario> findAllBy(String email, String nome);
    @Query("SELECT u from Usuario u WHERE u.email=:email OR u.nome=:nome ORDER BY u.nome asc limit 5")
    List<Usuario> findAllByWithNamedParam(@Param("email") String email,

                                          @Param("nome") String nome);
    @Query("SELECT u from Usuario u where u.status IN :statusList")
    List<Usuario> findAllByStatusIn(@Param("statusList") List<Integer> status);

    @Query("SELECT u from Usuario u inner join u.roles roles where roles in :roles")
    List<Usuario> findAllByRole(@Param("roles") List<Role> roles );

    Optional<Usuario> findByEmail(String email);

}
