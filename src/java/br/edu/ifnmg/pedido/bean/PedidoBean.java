
import br.edu.ifnmg.pedido.bean.CrudBean;
import br.edu.ifnmg.pedido.dao.PedidoDAO;
import br.edu.ifnmg.pedido.entidade.Pedido;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class PedidoBean extends CrudBean<Pedido, PedidoDAO> {

    private PedidoDAO pedidoDAO;
    
    @Override
    public PedidoDAO getDao() {
        if(pedidoDAO == null){
            pedidoDAO = new PedidoDAO();
        }
        return pedidoDAO;
    }

    @Override
    public Pedido criarNovaEntidade() {
        return new Pedido();
    }

}
