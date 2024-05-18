package edu.javeriana.ingenieria.social.rol.repositorio;

import edu.javeriana.ingenieria.social.rol.dominio.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioRol extends JpaRepository<Rol, Integer> {
    boolean existsByDescripcion(String descripcion);

    Rol findOneByDescripcion(String descripcion);
}
