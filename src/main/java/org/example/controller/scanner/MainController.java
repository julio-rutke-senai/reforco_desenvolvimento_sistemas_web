package org.example.controller.scanner;

import java.util.Scanner;

public class MainController extends Controller {

    private final CursosController cursosController;
    private final AlunosController alunosController;
    private final MatriculasController matriculasController;

    public MainController(Scanner scannerIn, CursosController cursosController, AlunosController alunosController, MatriculasController matriculasController) {
        super(scannerIn);
        this.cursosController = cursosController;
        this.alunosController = alunosController;
        this.matriculasController = matriculasController;
    }

    public void execute(){
        println("Sistema Acadêmico — Aula 1 (Console)");
        loop:
        while (true) {
            println("\n=== MENU PRINCIPAL ===");
            println("1) Cursos");
            println("2) Alunos");
            println("3) Matrículas");
            println("0) Sair");
            print("> ");
            String opc = this.scannerIn.nextLine().trim();

            switch (opc) {
                case "1": cursosController.menuCursos(); break;
                case "2": alunosController.menuAlunos(); break;
                case "3": matriculasController.menuMatriculas(); break;
                case "0": break loop;
                default: println("Opção inválida!");
            }
        }
        println("Encerrado.");
    }

}
