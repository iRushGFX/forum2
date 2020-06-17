package unicesumar.segundoBimestre;

import unicesumar.segundoBimestre.Filme;
import unicesumar.segundoBimestre.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class FilmeService {
    @Autowired
    private FilmeRepository repo;

    public List<Filme> getAll() {
        return repo.findAll();
    }

    public Filme getById(String id) {
        return repo.findById(id).orElseThrow(RuntimeException::new);
    }

    public String save(Filme filme) {
        return this.repo.save(filme).getId();
    }

    public void deleteById(String id) {
        repo.deleteById(id);
    }
}
