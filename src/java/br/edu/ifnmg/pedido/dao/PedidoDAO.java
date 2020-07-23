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
    
   
    public void salvar(Pedido pedido) throws ErroSistema, SQLException{
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps;
            if(pedido.getId() == null){
                ps = conexao.prepareStatement("INSERT INTO `pedido` (`hamburguer`,`suco`,,`batata`) VALUES (?,?,?)");
            } else {
                ps = conexao.prepareStatement("update pedido set hamburguer=?, suco=?, batata=?, ?");
                ps.setInt(5, pedido.getId());
            }
            ps.setString(1, pedido.getHamburguer());
            ps.setString(2, pedido.getSuco());
            ps.setString(3, pedido.getBatata());
            ps.execute();
            FabricaConexao.fecharConexao();
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao tentar salvar!", ex);
        }
    }
    
    @Override
    public void deletar(Pedido pedido) throws ErroSistema{
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps  = conexao.prepareStatement("delete from pedido where id = ?");
            ps.setInt(1, pedido.getId());
            ps.execute();
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao deletar o pedido!", ex);
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
                
                Pedido pedido = new Pedido();
                pedido.setId(resultSet.getInt("id"));
                pedido.setHamburguer(resultSet.getString("hamburguer"));
                pedido.setSuco(resultSet.getString("suco"));
                pedido.setBatata(resultSet.getString("batata"));
                pedido.add((Pedido) pedido);
            }
            FabricaConexao.fecharConexao();
            List<Pedido> Pedido = null;
            return Pedido;
            
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao buscar os pedido!",ex);
        }
    }

  
   
}
