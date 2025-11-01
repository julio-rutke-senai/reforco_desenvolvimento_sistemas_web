package org.example.controller.scanner;

import org.example.model.Curso;
import org.example.service.CursosService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class CursosController extends Controller {

    private CursosService cursosService;

    public CursosController(Scanner scannerIn, CursosService cursosService) {
        super(scannerIn);
        this.cursosService = cursosService;
    }

    public void menuCursos() {
        while (true) {
            println("\n--- CURSOS ---");
            println("1) Adicionar curso");
            println("2) Listar cursos");
            println("3) Buscar por prefixo");
            println("4) Incrementar horas de um curso");
            println("5) Carga horária total");
            println("0) Voltar");
            print("> ");
            String opc = scannerIn.nextLine().trim();
            switch (opc) {
                case "1": adicionarCurso(); break;
                case "2": listarCursos(); break;
                case "3": buscarCursoPorPrefixo(); break;
                case "4": incrementarHorasCurso(); break;
                case "5": cargaTotalCursos(); break;
                case "0": return;
                default: println("Opção inválida!");
            }
        }
    }

    private void adicionarCurso() {
        print("Título: "); String titulo = scannerIn.nextLine();
        print("Carga horária (int > 0): "); int ch = lerInt();
        try {
            cursosService.adicionar(new Curso(titulo, ch));
            println("OK: curso adicionado.");
        } catch (Exception e) {
            println("Erro: " + e.getMessage());
        }
    }

    private void listarCursos() {
        List<Curso> lista = cursosService.listar();
        if (lista.isEmpty()) { println("Sem cursos."); return; }
        lista.forEach(c -> println("- " + c));
    }

    private void buscarCursoPorPrefixo() {
        print("Prefixo: ");
        String p = scannerIn.nextLine();
        List<Curso> res = cursosService.buscarPorPrefixo(p);
        if (res.isEmpty()) { println("Nenhum curso encontrado"); return; }
        res.forEach(c -> println("- " + c));
    }

    private void incrementarHorasCurso() {
        print("Título exato do curso: "); String titulo = scannerIn.nextLine();
        print("Horas a incrementar (int > 0): "); int horas = lerInt();
        List<Curso> lista = cursosService.listar();
        Optional<Curso> opt = lista.stream()
                .filter(c -> c.getTitulo().equalsIgnoreCase(titulo))
                .findFirst();
        if (opt.isEmpty()) { println("Curso não encontrado."); return; }
        try {
            opt.get().incrementarHoras(horas);
            println("OK: horas incrementadas.");
        } catch (Exception e) {
            println("Erro: " + e.getMessage());
        }
    }

    private void cargaTotalCursos() {
        println("Carga total: " + cursosService.cargaTotal() + " horas");
    }

}
