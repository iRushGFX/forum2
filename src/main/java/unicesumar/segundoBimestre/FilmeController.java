package unicesumar.segundoBimestre;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import unicesumar.segundoBimestre.Filme;

@RestController
@RequestMapping("/api/Filme")
public class FilmeController {
    @Autowired
    private FilmeService service;

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping
    public List<Filme> getAll() {
        return service.getAll();
    }

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/{id}")
    public Filme getById(@PathVariable("id") String id) {
        Filme recuperado = service.getById(id);
        return recuperado;
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping
    public String post(@RequestBody Filme novo) {
        return service.save(novo);
    }

    @ResponseStatus(code = HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") String id) {
        service.deleteById(id);
    }

    @ResponseStatus(code = HttpStatus.OK)
    @PutMapping("/{id}")
    public void put(@PathVariable("id") String id, @RequestBody Filme modificado) {
        if (!id.equals(modificado.getId())) {
            throw new RuntimeException("Id do recurso não confere com Id do body!");
        }
        service.save(modificado);
    }
}






