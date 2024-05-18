package edu.javeriana.ingenieria.social.rol.controlador;

import edu.javeriana.ingenieria.social.rol.dominio.Rol;
import edu.javeriana.ingenieria.social.rol.servicio.ServicioRol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("api/roles")
@CrossOrigin(origins="http://localhost:4200")
public class ControladorRol {

    @Autowired
    private ServicioRol servicio;

    @GetMapping("listar")
    public List<Rol> obtenerRoles(){
        return servicio.obtenerRoles();
    }

    @GetMapping("obtener")
    public ResponseEntity<Rol> obtenerRol(@RequestParam Integer id){
        if(id == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(servicio.obtenerRol(id) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(servicio.obtenerRol(id), HttpStatus.OK);
    }

    @GetMapping("obtenerDescripcion")
    public ResponseEntity<Rol> obtenerROl(@RequestParam String descripcion){
        if(descripcion == null || descripcion.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(!servicio.rolExsite(descripcion)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(servicio.obtenerRol(descripcion), HttpStatus.OK);
    }

    @PostMapping("crear")
    public ResponseEntity<Rol> crearRol(@RequestBody Rol rol){
        if(rol == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(servicio.rolExiste(rol.getId()) || servicio.rolExsite(rol.getDescripcion())){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(servicio.crearRol(rol), HttpStatus.CREATED);
    }

    @PutMapping("actualizar")
    public ResponseEntity<Rol> actualizarRol(@RequestParam Integer id, @RequestBody Rol rol){
        if(id == null || rol == null || !Objects.equals(rol.getId(), id)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(servicio.actualizarRol(id, rol) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(rol, HttpStatus.OK);
    }

    @DeleteMapping("eliminar")
    public ResponseEntity<HttpStatus> borrarRol(@RequestParam Integer id){
        if(id == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(servicio.borrarRol(id) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
