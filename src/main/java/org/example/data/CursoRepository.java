package org.example.data;

import org.example.dto.CursoDTOResponse;
import org.example.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

    List<Curso> findByTituloStartingWith(String prefixo);

    @Query("SELECT sum(c.cargaHoraria) from Curso c")
    int retornarSomaCargaHoraria();

    Curso findByTituloEqualsIgnoreCase(String titulo);

    @Query("select new org.example.dto.CursoDTOResponse(c.id, c.titulo, c.cargaHoraria) from Curso c")
    List<CursoDTOResponse> retornarDTOCurso();

}
