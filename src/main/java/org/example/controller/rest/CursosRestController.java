package org.example.controller.rest;

import org.example.model.Curso;
import org.example.service.CursosService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cursos")
public class CursosRestController {

    private CursosService cursosService;

    public CursosRestController(CursosService cursosService){
        this.cursosService = cursosService;
    }

    @PostMapping("/add")
    private ResponseEntity adicionarCurso(@RequestBody Curso curso) {
        try {
            cursosService.adicionar(curso);
            System.out.println("OK: curso adicionado.");
            return ResponseEntity.ok().body("Criado");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/listar")
    private ResponseEntity<List<Curso>> listarCursos() {
        List<Curso> lista = cursosService.listar();
        if (lista.isEmpty()) {
            System.out.println("Sem cursos.");
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/listar-prefixo")
    private ResponseEntity<List<Curso>> buscarCursoPorPrefixo(@RequestParam("prefixo") String prefixo) {
        List<Curso> lista = cursosService.buscarPorPrefixo(prefixo);
        if (lista.isEmpty()) {
            System.out.println("Nenhum curso encontrado");
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    @PatchMapping("/incrementar-horas")
    private ResponseEntity incrementarHorasCurso(@RequestParam("curso") String titulo, @RequestParam("horas") int horas) {
        Optional<Curso> opt = cursosService.incrementarHoras(titulo);
        if (opt.isEmpty()) {
            System.out.println("Curso não encontrado.");
            return ResponseEntity.noContent().build();
        }
        try {
            opt.get().incrementarHoras(horas);
            System.out.println("OK: horas incrementadas.");
            return ResponseEntity.ok("OK: horas incrementadas.");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/carga-total")
    private ResponseEntity cargaTotalCursos() {
        String cargaHoraria = "Carga total: " + cursosService.cargaTotal() + " horas";
        System.out.println(cargaHoraria);
        return ResponseEntity.ok(cargaHoraria);
    }

    @DeleteMapping("/excluir/{codigo}")
    private ResponseEntity excluir(@RequestParam("titulo") String titulo){
        try {
            System.out.println("Excluindo curso "+titulo);
            cursosService.excluir(titulo);

            return ResponseEntity.ok("Curso excluído.");
        }catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

}
