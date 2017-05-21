/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloConection;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author INFOSUL
 */
public class ConexaoBD {
    
    public Statement stm;
    public ResultSet rs;
    private String drive = "org.postgresql.Driver";
    private String caminho = "jdbc:postgresql://localhost:5432/projetoclinica";
    private String usuario = "postgres";
    private String senha= "123";
    public Connection com;
    
    public void conexao(){
        System.setProperty("jdbc.Drivers", drive);
        try {
            com = DriverManager.getConnection(caminho, usuario, senha);
            JOptionPane.showMessageDialog(null, "Conexão Efetuada com Sucesso!");
                    } catch (SQLException ex) {
            Logger.getLogger(ConexaoBD.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showInternalMessageDialog(null, "Erro ao se conectar com o Banco de Dados:\n"+ex.getMessage());
        }
        
    }
    
    public void execultaSql(String sql){
        try {
            stm = com.createStatement(rs.TYPE_SCROLL_INSENSITIVE,rs.CONCUR_READ_ONLY);
            rs = stm.executeQuery(sql);
        } catch (SQLException ex) {
            JOptionPane.showInternalMessageDialog(null, "Erro ExecultaSql!:\n"+ex.getMessage());
        }
    }
    public void desconecta(){
        try {
            com.close();
            JOptionPane.showMessageDialog(null, "BD Desconectado com sucesso!");
        } catch (SQLException ex) {
            Logger.getLogger(ConexaoBD.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erros ao Feichar conexão com BD:\n"+ex.getMessage());
        }
    }
    
}
