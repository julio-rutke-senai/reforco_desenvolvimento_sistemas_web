package org.example.controller;

import org.example.model.Aluno;
import org.example.model.Curso;
import org.example.model.Matricula;
import org.example.service.MatriculasService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class MatriculasController extends Controller{

    private final MatriculasService matriculasService;

    public MatriculasController(Scanner scannerIn, MatriculasService matriculasService) {
        super(scannerIn);
        this.matriculasService = matriculasService;
    }

    public void menuMatriculas() {
        while (true) {
            println("\n--- MATRÍCULAS ---");
            println("1) Matricular aluno em curso");
            println("2) Listar matrículas");
            println("3) Listar matrículas por aluno (e-mail)");
            println("4) Listar matrículas por curso (título)");
            println("5) Cancelar matrícula (ID)");
            println("6) Resumo por curso");
            println("0) Voltar");
            print("> ");
            String opc = scannerIn.nextLine().trim();
            switch (opc) {
                case "1": matricular(); break;
                case "2": listarMatriculas(); break;
                case "3": listarPorAluno(); break;
                case "4": listarPorCurso(); break;
                case "5": cancelarMatricula(); break;
                case "6": println(matriculasService.toString()); break;
                case "0": return;
                default: println("Opção inválida!");
            }
        }
    }

    private void matricular() {
        print("E-mail do aluno: "); String email = scannerIn.nextLine();
        Optional<Aluno> aluno = matriculasService.buscarAlunoPorEmail(email);
        if (aluno.isEmpty()) { println("Aluno não encontrado."); return; }

        print("Título exato do curso: "); String titulo = scannerIn.nextLine();
        Optional<Curso> curso = matriculasService.listarCursos().stream()
                .filter(c -> c.getTitulo().equalsIgnoreCase(titulo)).findFirst();
        if (curso.isEmpty()) { println("Curso não encontrado."); return; }

        print("Data (YYYY-MM-DD) ou vazio = hoje: ");
        String d = scannerIn.nextLine().trim();
        LocalDate data = d.isEmpty() ? null : LocalDate.parse(d);

        try {
            Matricula m = matriculasService.matricular(aluno.get(), curso.get(), data);
            println("OK: matriculado -> " + m);
        } catch (Exception e) {
            println("Erro: " + e.getMessage());
        }
    }

    private void listarMatriculas() {
        List<Matricula> lista = matriculasService.listar();
        if (lista.isEmpty()) { println("Sem matrículas."); return; }
        lista.forEach(m -> println("- " + m));
    }

    private void listarPorAluno() {
        print("E-mail do aluno: "); String email = scannerIn.nextLine();
        Optional<Aluno> aluno = matriculasService.buscarAlunoPorEmail(email);
        if (aluno.isEmpty()) { println("Aluno não encontrado."); return; }
        List<Matricula> lista = matriculasService.listarPorAluno(aluno.get());
        if (lista.isEmpty()) { println("Sem matrículas para este aluno."); return; }
        lista.forEach(m -> println("- " + m));
    }

    private void listarPorCurso() {
        print("Título exato do curso: "); String titulo = scannerIn.nextLine();
        Optional<Curso> curso = matriculasService.listarCursos().stream()
                .filter(c -> c.getTitulo().equalsIgnoreCase(titulo)).findFirst();
        if (curso.isEmpty()) { println("Curso não encontrado."); return; }
        List<Matricula> lista = matriculasService.listarPorCurso(curso.get());
        if (lista.isEmpty()) { println("Sem matrículas para este curso."); return; }
        lista.forEach(m -> println("- " + m));
    }

    private void cancelarMatricula() {
        print("ID da matrícula: "); Long id = lerLong();
        boolean ok = matriculasService.cancelar(id);
        println(ok ? "OK: cancelada." : "Matrícula não encontrada.");
    }
}
