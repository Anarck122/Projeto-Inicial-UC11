/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.ResultSet;
import java.util.ArrayList;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ProdutosDAO {
    
    public void cadastrarProduto(ProdutosDTO produto) {
        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
        
        try (Connection conn = new conectaDAO().connectDB();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, produto.getNome());
            stmt.setInt(2, produto.getValor());
            stmt.setString(3, produto.getStatus());

            System.out.println("Executando SQL: " + sql); // Debug
            System.out.println("Com valores: Nome=" + produto.getNome() + ", Valor=" + produto.getValor() + ", Status=" + produto.getStatus());

            int resultado = stmt.executeUpdate();
            
            if (resultado > 0) {
                JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar: " + erro.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            System.out.println("Erro SQL: " + erro.getMessage()); // Debug
        }
    }
     public ArrayList<ProdutosDTO> listarProdutos() {
        ArrayList<ProdutosDTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM produtos";

        try (Connection conn = new conectaDAO().connectDB();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getInt("valor"));
                produto.setStatus(rs.getString("status"));

                lista.add(produto);
            }

        } catch (Exception e) {
            System.out.println("Erro ao listar produtos: " + e.getMessage());
        }
        return lista;
    }

  public void venderProduto(int id) {
    String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?"; 
    
    try (Connection conn = new conectaDAO().connectDB();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        System.out.println("Conexão estabelecida com sucesso.");
        System.out.println("Query a ser executada: " + sql);
        System.out.println("ID recebido para venda: " + id);
        
        stmt.setInt(1, id);
        int resultado = stmt.executeUpdate();
        
        if (resultado > 0) {
            JOptionPane.showMessageDialog(null, "Produto vendido com sucesso!");
            System.out.println("Produto com ID " + id + " vendido com sucesso.");
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao vender produto. Produto não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            System.out.println("Produto com ID " + id + " não foi encontrado no banco.");
        }
        
    } catch (SQLException erro) {
        System.out.println("Erro SQL: " + erro.getMessage());
        erro.printStackTrace();
        JOptionPane.showMessageDialog(null, "Erro ao vender produto: " + erro.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
    }
}
}
