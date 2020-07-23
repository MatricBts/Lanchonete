package br.edu.ifnmg.pedido.dao;

import br.edu.ifnmg.pedido.uti.FabricaConexao;
import br.edu.ifnmg.pedido.util.exception.ErroSistema;
import br.edu.ifnmg.pedido.entidade.Pedido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author Danilo Souza Almeida
 */
public class PedidoDAO implements CrudDAO<Pedido>{
    
   
    public void salvar(Pedido carro) throws ErroSistema, SQLException{
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps;
            if(carro.getId() == null){
                ps = conexao.prepareStatement("INSERT INTO `carro` (`modelo`,`fabricante`,`cor`,`ano`) VALUES (?,?,?,?)");
            } else {
                ps = conexao.prepareStatement("update carro set modelo=?, fabricante=?, cor=?, ano=? where id=?");
                ps.setInt(5, carro.getId());
            }
            ps.setString(1, carro.getModelo());
            ps.setString(2, carro.getFabricante());
            ps.setString(3, carro.getCor());
            ps.execute();
            FabricaConexao.fecharConexao();
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao tentar salvar!", ex);
        }
    }
    
    public void deletar(Pedido carro) throws ErroSistema{
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps  = conexao.prepareStatement("delete from carro where id = ?");
            ps.setInt(1, carro.getId());
            ps.execute();
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao deletar o carro!", ex);
        }
    }
    
    /**
     *
     * @return
     * @throws ErroSistema
     */
    @Override
    public List<Pedido> buscar() throws ErroSistema{
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps = conexao.prepareStatement("select * from pedido");
            ResultSet resultSet = ps.executeQuery();
            List<Pedido> pedido = new ArrayList<>();
            while(resultSet.next()){
                
                Pedido carro = new Pedido();
                carro.setId(resultSet.getInt("id"));
                carro.setModelo(resultSet.getString("modelo"));
                carro.setFabricante(resultSet.getString("fabricante"));
                carro.setCor(resultSet.getString("cor"));
                carro.setAno(resultSet.getDate("ano"));
                pedido.add((Pedido) pedido);
            }
            FabricaConexao.fecharConexao();
            List<Pedido> Pedido = null;
            return Pedido;
            
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao buscar os carros!",ex);
        }
    }

    @Override
    public void salvar(Pedido entidade) throws ErroSistema {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
}
