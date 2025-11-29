package org.example.data;

import org.example.model.Curso;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursoDAO {

    private static String URL = "jdbc:postgres://127.0.0.1:5432/aula_web";
    private static String USER = "postgres";
    private static String PASS = "postgres";

    public void salvar(Curso curso) {
        String sql = "INSERT INTO curso (titulo, carga_horaria) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement stmt =
                     conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, curso.getTitulo());
            stmt.setInt(2, curso.getCargaHoraria());

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    long idGerado = rs.getLong(1);
                    curso.setId(idGerado);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Curso> listar() {
        List<Curso> cursos = new ArrayList<>();
        String sql = "SELECT id, titulo, carga_horaria FROM curso";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Curso c = new Curso(
                        rs.getLong("id"),
                        rs.getString("titulo"),
                        rs.getInt("carga_horaria")
                );
                cursos.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cursos;
    }

    public Curso buscarPorId(Long id) {
        String sql = "SELECT id, titulo, carga_horaria FROM curso WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Curso(
                            rs.getLong("id"),
                            rs.getString("titulo"),
                            rs.getInt("carga_horaria")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // ou Optional
    }


}
