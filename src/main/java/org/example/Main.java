package org.example;

import org.example.controller.AlunosController;
import org.example.controller.CursosController;
import org.example.controller.MainController;
import org.example.controller.MatriculasController;
import org.example.data.DataBase;
import org.example.service.AlunosService;
import org.example.service.CursosService;
import org.example.service.MatriculasService;

import java.util.Scanner;

public class Main {
    private static final Scanner in = new Scanner(System.in);

    private static final DataBase dataBase = new DataBase();
    private static CursosService cursosService = new CursosService(dataBase);
    private static CursosController cursosController = new CursosController(in, cursosService);
    private static AlunosService alunosService = new AlunosService(dataBase);
    private static AlunosController alunosController = new AlunosController(in, alunosService);
    private static MatriculasService matriculasService = new MatriculasService(dataBase, alunosService, cursosService);
    private static MatriculasController matriculasController = new MatriculasController(in, matriculasService);
    private static MainController mainController = new MainController(in, cursosController, alunosController, matriculasController);


    public static void main(String[] args) {
        mainController.execute();
    }

}
