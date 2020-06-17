package unicesumar.segundoBimestre;

import unicesumar.segundoBimestre.Filme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmeRepository extends JpaRepository<Filme, String> {
}
