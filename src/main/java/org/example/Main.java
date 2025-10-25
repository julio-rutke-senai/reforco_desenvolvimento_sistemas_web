package org.example;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    private static final Scanner in = new Scanner(System.in);

    private static final CatalogoCursos catalogoCursos = new CatalogoCursos();
    private static final CatalogoAlunos catalogoAlunos = new CatalogoAlunos();
    private static final CatalogoMatriculas catalogoMatriculas = new CatalogoMatriculas();

    public static void main(String[] args) {
        println("Sistema Acadêmico — Aula 1 (Console)");
        loop:
        while (true) {
            println("\n=== MENU PRINCIPAL ===");
            println("1) Cursos");
            println("2) Alunos");
            println("3) Matrículas");
            println("0) Sair");
            print("> ");
            String opc = in.nextLine().trim();

            switch (opc) {
                case "1": menuCursos(); break;
                case "2": menuAlunos(); break;
                case "3": menuMatriculas(); break;
                case "0": break loop;
                default: println("Opção inválida!");
            }
        }
        println("Encerrado.");
    }

    // ======== CURSOS ========
    private static void menuCursos() {
        while (true) {
            println("\n--- CURSOS ---");
            println("1) Adicionar curso");
            println("2) Listar cursos");
            println("3) Buscar por prefixo");
            println("4) Incrementar horas de um curso");
            println("5) Carga horária total");
            println("0) Voltar");
            print("> ");
            String opc = in.nextLine().trim();
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

    private static void adicionarCurso() {
        print("Título: "); String titulo = in.nextLine();
        print("Carga horária (int > 0): "); int ch = lerInt();
        try {
            catalogoCursos.adicionar(new Curso(titulo, ch));
            println("OK: curso adicionado.");
        } catch (Exception e) {
            println("Erro: " + e.getMessage());
        }
    }

    private static void listarCursos() {
        List<Curso> lista = catalogoCursos.listar();
        if (lista.isEmpty()) { println("Sem cursos."); return; }
        lista.forEach(c -> println("- " + c));
    }

    private static void buscarCursoPorPrefixo() {
        print("Prefixo: ");
        String p = in.nextLine();
        List<Curso> res = catalogoCursos.buscarPorPrefixo(p);
        if (res.isEmpty()) { println("Nenhum curso encontrado"); return; }
        res.forEach(c -> println("- " + c));
    }

    private static void incrementarHorasCurso() {
        print("Título exato do curso: "); String titulo = in.nextLine();
        print("Horas a incrementar (int > 0): "); int horas = lerInt();
        List<Curso> lista = catalogoCursos.listar();
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

    private static void cargaTotalCursos() {
        println("Carga total: " + catalogoCursos.cargaTotal() + " horas");
    }

    // ======== ALUNOS ========
    private static void menuAlunos() {
        while (true) {
            println("\n--- ALUNOS ---");
            println("1) Cadastrar aluno");
            println("2) Listar alunos");
            println("3) Buscar por e-mail");
            println("4) Atualizar aluno");
            println("5) Remover aluno");
            println("0) Voltar");
            print("> ");
            String opc = in.nextLine().trim();
            switch (opc) {
                case "1": cadastrarAluno(); break;
                case "2": listarAlunos(); break;
                case "3": buscarAlunoPorEmail(); break;
                case "4": atualizarAluno(); break;
                case "5": removerAluno(); break;
                case "0": return;
                default: println("Opção inválida!");
            }
        }
    }

    private static void cadastrarAluno() {
        print("Nome: "); String nome = in.nextLine();
        print("E-mail: "); String email = in.nextLine();
        try {
            Aluno a = catalogoAlunos.criar(nome, email);
            println("OK: aluno cadastrado -> " + a);
        } catch (Exception e) {
            println("Erro: " + e.getMessage());
        }
    }

    private static void listarAlunos() {
        List<Aluno> lista = catalogoAlunos.listar();
        if (lista.isEmpty()) { println("Sem alunos."); return; }
        lista.forEach(a -> println("- " + a));
    }

    private static void buscarAlunoPorEmail() {
        print("E-mail: "); String email = in.nextLine();
        Optional<Aluno> opt = catalogoAlunos.buscarPorEmail(email);
        println(opt.map(Object::toString).orElse("Não encontrado."));
    }

    private static void atualizarAluno() {
        print("ID do aluno: "); Long id = lerLong();
        print("Novo nome (vazio = manter): "); String nome = in.nextLine();
        print("Novo e-mail (vazio = manter): "); String email = in.nextLine();
        boolean ok = catalogoAlunos.atualizar(id, nome.isBlank()? null : nome, email.isBlank()? null : email);
        println(ok ? "OK: atualizado." : "Aluno não encontrado.");
    }

    private static void removerAluno() {
        print("ID do aluno: "); Long id = lerLong();
        boolean ok = catalogoAlunos.remover(id);
        println(ok ? "OK: removido." : "Aluno não encontrado.");
    }

    // ======== MATRÍCULAS ========
    private static void menuMatriculas() {
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
            String opc = in.nextLine().trim();
            switch (opc) {
                case "1": matricular(); break;
                case "2": listarMatriculas(); break;
                case "3": listarPorAluno(); break;
                case "4": listarPorCurso(); break;
                case "5": cancelarMatricula(); break;
                case "6": println(catalogoMatriculas.toString()); break;
                case "0": return;
                default: println("Opção inválida!");
            }
        }
    }

    private static void matricular() {
        print("E-mail do aluno: "); String email = in.nextLine();
        Optional<Aluno> aluno = catalogoAlunos.buscarPorEmail(email);
        if (aluno.isEmpty()) { println("Aluno não encontrado."); return; }

        print("Título exato do curso: "); String titulo = in.nextLine();
        Optional<Curso> curso = catalogoCursos.listar().stream()
                .filter(c -> c.getTitulo().equalsIgnoreCase(titulo)).findFirst();
        if (curso.isEmpty()) { println("Curso não encontrado."); return; }

        print("Data (YYYY-MM-DD) ou vazio = hoje: ");
        String d = in.nextLine().trim();
        LocalDate data = d.isEmpty() ? null : LocalDate.parse(d);

        try {
            Matricula m = catalogoMatriculas.matricular(aluno.get(), curso.get(), data);
            println("OK: matriculado -> " + m);
        } catch (Exception e) {
            println("Erro: " + e.getMessage());
        }
    }

    private static void listarMatriculas() {
        List<Matricula> lista = catalogoMatriculas.listar();
        if (lista.isEmpty()) { println("Sem matrículas."); return; }
        lista.forEach(m -> println("- " + m));
    }

    private static void listarPorAluno() {
        print("E-mail do aluno: "); String email = in.nextLine();
        Optional<Aluno> aluno = catalogoAlunos.buscarPorEmail(email);
        if (aluno.isEmpty()) { println("Aluno não encontrado."); return; }
        List<Matricula> lista = catalogoMatriculas.listarPorAluno(aluno.get());
        if (lista.isEmpty()) { println("Sem matrículas para este aluno."); return; }
        lista.forEach(m -> println("- " + m));
    }

    private static void listarPorCurso() {
        print("Título exato do curso: "); String titulo = in.nextLine();
        Optional<Curso> curso = catalogoCursos.listar().stream()
                .filter(c -> c.getTitulo().equalsIgnoreCase(titulo)).findFirst();
        if (curso.isEmpty()) { println("Curso não encontrado."); return; }
        List<Matricula> lista = catalogoMatriculas.listarPorCurso(curso.get());
        if (lista.isEmpty()) { println("Sem matrículas para este curso."); return; }
        lista.forEach(m -> println("- " + m));
    }

    private static void cancelarMatricula() {
        print("ID da matrícula: "); Long id = lerLong();
        boolean ok = catalogoMatriculas.cancelar(id);
        println(ok ? "OK: cancelada." : "Matrícula não encontrada.");
    }

    // ======== util ========
    private static int lerInt() {
        while (true) {
            try { return Integer.parseInt(in.nextLine().trim()); }
            catch (Exception e) { print("Valor inválido, tente novamente: "); }
        }
    }
    private static Long lerLong() {
        while (true) {
            try { return Long.parseLong(in.nextLine().trim()); }
            catch (Exception e) { print("Valor inválido, tente novamente: "); }
        }
    }
    private static void println(String s){ System.out.println(s); }
    private static void print(String s){ System.out.print(s); }
}
