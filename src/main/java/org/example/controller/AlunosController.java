package org.example.controller;

import org.example.model.Aluno;
import org.example.service.AlunosService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class AlunosController extends Controller{

    private final AlunosService alunosService;

    public AlunosController(Scanner scannerIn, AlunosService alunosService) {
        super(scannerIn);
        this.alunosService = alunosService;
    }

    public void menuAlunos() {
        while (true) {
            println("\n--- ALUNOS ---");
            println("1) Cadastrar aluno");
            println("2) Listar alunos");
            println("3) Buscar por e-mail");
            println("4) Atualizar aluno");
            println("5) Remover aluno");
            println("0) Voltar");
            print("> ");
            String opc = scannerIn.nextLine().trim();
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

    private void cadastrarAluno() {
        print("Nome: "); String nome = scannerIn.nextLine();
        print("E-mail: "); String email = scannerIn.nextLine();
        try {
            Aluno a = this.alunosService.criar(nome, email);
            println("OK: aluno cadastrado -> " + a);
        } catch (Exception e) {
            println("Erro: " + e.getMessage());
        }
    }

    private void listarAlunos() {
        List<Aluno> lista = this.alunosService.listar();
        if (lista.isEmpty()) { println("Sem alunos."); return; }
        lista.forEach(a -> println("- " + a));
    }

    private void buscarAlunoPorEmail() {
        print("E-mail: "); String email = scannerIn.nextLine();
        Optional<Aluno> opt = this.alunosService.buscarPorEmail(email);
        println(opt.map(Object::toString).orElse("Não encontrado."));
    }

    private void atualizarAluno() {
        print("ID do aluno: "); Long id = lerLong();
        print("Novo nome (vazio = manter): "); String nome = scannerIn.nextLine();
        print("Novo e-mail (vazio = manter): "); String email = scannerIn.nextLine();
        boolean ok = this.alunosService.atualizar(id, nome.isBlank()? null : nome, email.isBlank()? null : email);
        println(ok ? "OK: atualizado." : "Aluno não encontrado.");
    }

    private void removerAluno() {
        print("ID do aluno: "); Long id = lerLong();
        boolean ok = this.alunosService.remover(id);
        println(ok ? "OK: removido." : "Aluno não encontrado.");
    }

}
