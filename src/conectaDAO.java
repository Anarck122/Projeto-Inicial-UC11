



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class conectaDAO {
    
    public Connection connectDB() {
        Connection conn = null;
        
        try {
            System.out.println("Tentando conectar ao banco de dados..."); // Debug
            conn = DriverManager.getConnection("jdbc:mysql://localhost/uc11", "root", "petrick2015"); 
            
            if (conn != null) {
                System.out.println("Conexão bem-sucedida!"); // Debug
            }

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ConectaDAO: " + erro.getMessage());
            System.out.println("Erro ao conectar ao banco: " + erro.getMessage()); // Debug
        }
        return conn;
    }
}

