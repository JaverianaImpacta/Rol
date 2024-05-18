package edu.javeriana.ingenieria.social.rol.servicio;

import edu.javeriana.ingenieria.social.rol.dominio.Rol;
import edu.javeriana.ingenieria.social.rol.repositorio.RepositorioRol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ServicioRol {

    @Autowired
    private RepositorioRol repositorio;

    public List<Rol> obtenerRoles() {
        return repositorio.findAll();
    }

    public Rol obtenerRol(Integer id) {
        return repositorio.findById(id).orElse(null);
    }

    public Rol obtenerRol(String descripcion) {
        return repositorio.findOneByDescripcion(descripcion);
    }

    public Rol crearRol(Rol rol) {
        return repositorio.save(rol);
    }

    public Rol actualizarRol(Integer id, Rol rol) {
        if(repositorio.findById(id).orElse(null) == null) {
            return null;
        }
        rol.setId(id);
        return repositorio.save(rol);
    }

    public Rol borrarRol(Integer id) {
        Rol aux = repositorio.findById(id).orElse(null);
        if(aux == null){
            return null;
        }
        repositorio.delete(aux);
        return aux;
    }

    public boolean rolExiste(Integer id){
        return repositorio.existsById(id);
    }
    public boolean rolExsite(String descripcion){
        return repositorio.existsByDescripcion(descripcion);
    }
}
