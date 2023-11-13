/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lojainformatica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataBaseConector {

    static String url = "jdbc:mysql://localhost:3306/basenotafiscal";
    static String login = "root";
    static String senha = "";

    public static boolean salvar(Computador obj) {
        boolean retorno = false;
        Connection conexao = null;
        PreparedStatement comandoSQL = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conexao = DriverManager.getConnection(url, login, senha);

            comandoSQL = conexao.prepareStatement("INSERT INTO Computador ( valorComputador, processador, TipoHD) VALUES (?, ?, ?, ?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            comandoSQL.setDouble(1, obj.getValorComputador());
            comandoSQL.setString(2, obj.getProcessador());
            comandoSQL.setString(3, obj.getHD());

            // Execute the SQL statement
            int linhasAfetadas = comandoSQL.executeUpdate();
            if (linhasAfetadas > 0) {
                // Success! - Data successfully inserted into the database
                rs = comandoSQL.getGeneratedKeys();
                if (rs != null && rs.next()) {
                    int idGerado = rs.getInt(1);
                    // Assign the generated ID to the object
                    obj.setIdComputador(idGerado);
                }
                retorno = true;
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DataBaseConector.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Close resources in the reverse order of their creation
            try {
                if (rs != null) {
                    rs.close();
                }
                if (comandoSQL != null) {
                    comandoSQL.close();
                }
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DataBaseConector.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return retorno;
    }

    public static ArrayList<Computador> listar() {

        Connection conexao = null;
        PreparedStatement comandoSQL = null;
        ResultSet rs = null;
        ArrayList<Computador> lista = new ArrayList<>();

        try {
            // 1 - Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2 - Establish the connection
            conexao = DriverManager.getConnection(url, login, senha);

            // 3 - Prepare the SQL statement
            comandoSQL = conexao.prepareStatement("SELECT * FROM Computador");

            // 4 - Execute the command
            rs = comandoSQL.executeQuery();

            if (rs != null) {

                while (rs.next()) {
                    int id = rs.getInt("idComputador");
                    int numero = rs.getInt("numeroComputador");
                    double valor = rs.getDouble("valorComputador");
                    String processador = rs.getString("processador");
                    String HD = rs.getString("TipoHD");

                    Computador item = new Computador(processador, HD);

                    lista.add(item);

                }
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DataBaseConector.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DataBaseConector.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return lista;
    }
}
