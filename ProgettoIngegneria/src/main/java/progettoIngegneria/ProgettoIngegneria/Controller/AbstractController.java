package it.main.controller;


import it.main.service.Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



@Component
public abstract class AbstractController <Entity>{

    private Service<Entity> service;

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam("id") Integer id) {

        if(service.existsById(id)){
            service.delete(id);
            return new ResponseEntity<String>("Cancellazione andata a buon fine!", HttpStatus.OK);
        }
        return new ResponseEntity<String>("Nessuna occorrenza trovata", HttpStatus.NOT_FOUND);
    }
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody Entity dto){
        try{
            return new ResponseEntity<Entity>((Entity)service.update(dto), HttpStatus.OK);

        }catch(IllegalArgumentException ex){
            return new ResponseEntity<String>(ex.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/insert")
    public ResponseEntity<?> insert (@RequestBody Entity dto) {
        try{
            return new ResponseEntity<Entity>((Entity)service.insert(dto), HttpStatus.OK);

        }catch(IllegalArgumentException ex){
            return new ResponseEntity<String>(ex.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/read")
    public ResponseEntity<?> read(Integer id) {
        if(service.existsById(id))
            return new ResponseEntity<Entity>((Entity)service.read(id), HttpStatus.OK);

        return new ResponseEntity<String>("Nessuna occorrenza trovata", HttpStatus.NOT_FOUND);
    }
}