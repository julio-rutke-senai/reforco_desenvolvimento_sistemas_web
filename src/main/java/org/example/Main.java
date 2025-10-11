package org.example;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner in = new Scanner(System.in);
    private static final CatalogoCursos catalogo = new CatalogoCursos();

    public static void main(String[] args) {
        println("Catálogo de Cursos — Aula 1");
        loop:
        while (true) {
            println("\n1) Adicionar curso");
            println("2) Listar cursos");
            println("3) Buscar por prefixo");
            println("4) Carga total");
            println("0) Sair");
            print("Escolha: ");
            String op = in.nextLine().trim();
            switch (op) {
                case "1": adicionar(); break;
                case "2": listar(); break;
                case "3": buscar(); break;
                case "4": cargaTotal(); break;
                case "0": break loop;
                default: println("Opção inválida");
            }
        }
        println("Fim.");
    }

    private static void adicionar() {
        print("Título: ");
        String titulo = in.nextLine();
        print("Carga horária (h): ");
        int carga = Integer.parseInt(in.nextLine());
        try {
            catalogo.adicionar(new Curso(titulo, carga));
            println("OK: curso adicionado");
        } catch (Exception e) {
            println("Erro: " + e.getMessage());
        }
    }

    private static void listar() {
        List<Curso> lista = catalogo.listar();
        if (lista.isEmpty()) { println("Sem cursos cadastrados"); return; }
        for (int i = 0; i < lista.size(); i++) {
            println((i+1) + ") " + lista.get(i));
        }
    }

    private static void buscar() {
        print("Prefixo: ");
        String p = in.nextLine();
        List<Curso> res = catalogo.buscarPorPrefixo(p);
        if (res.isEmpty()) { println("Nenhum curso encontrado"); return; }
        res.forEach(c -> println("- " + c));
    }

    private static void cargaTotal() {
        println("Carga total: " + catalogo.cargaTotal() + " horas");
    }

    private static void println(String s){ System.out.println(s); }
    private static void print(String s){ System.out.print(s); }
}
