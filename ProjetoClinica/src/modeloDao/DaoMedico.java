/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloDao;

import modeloConection.ConexaoBD;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modeloBeans.*;

public class DaoMedico {
    ConexaoBD  conex = new ConexaoBD();
    BeansMedicos mod = new BeansMedicos();
    
    public void salvar(BeansMedicos mod){
        conex.conexao();
        try {
            PreparedStatement pst = conex.com.prepareStatement("insert into medicos(nome_medico,especialidade,crm) values(?,?,?)");
            pst.setString(1,mod.getNome());
            pst.setString(2, mod.getEspecialidade());
            pst.setInt(3,mod.getCrm());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Dados inseridos com Sucesso!");
        } catch (SQLException ex) {
            Logger.getLogger(DaoMedico.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao inserir dados!\n" + ex);
        }
        
        conex.desconecta();
        
    }



    public BeansMedicos buscaMedico(BeansMedicos mod){
        conex.conexao();
        conex.execultaSql("Select *from medicos where nome_medico like'%"+mod.getPesquisa()+"%'");
        try {
            conex.rs.first();
            mod.setCodigo(conex.rs.getInt("cod_medico"));
            mod.setNome(conex.rs.getString("nome_medico"));
            mod.setCrm(conex.rs.getInt("crm"));
            mod.setEspecialidade(conex.rs.getString("especialidade"));
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Buscar Medico!\n" + ex);
        }
        
        conex.desconecta();
        return mod;
    }
}

