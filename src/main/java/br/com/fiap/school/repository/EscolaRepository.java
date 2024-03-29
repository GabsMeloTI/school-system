package br.com.fiap.school.repository;
import br.com.fiap.school.model.Escola;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EscolaRepository extends JpaRepository<Escola, Long> {
}
