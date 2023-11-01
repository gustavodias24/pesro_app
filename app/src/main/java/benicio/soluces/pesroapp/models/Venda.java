package benicio.soluces.pesroapp.models;

import java.util.List;

public class Venda {
    String dataVenda, dataRetirada, localRetirada;
    List<Produto> listaProdutos;

    public Venda() {
    }

    public Venda(String dataVenda, String dataRetirada, String localRetirada, List<Produto> listaProdutos) {
        this.dataVenda = dataVenda;
        this.dataRetirada = dataRetirada;
        this.localRetirada = localRetirada;
        this.listaProdutos = listaProdutos;
    }

    public String getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(String dataVenda) {
        this.dataVenda = dataVenda;
    }

    public String getDataRetirada() {
        return dataRetirada;
    }

    public void setDataRetirada(String dataRetirada) {
        this.dataRetirada = dataRetirada;
    }

    public String getLocalRetirada() {
        return localRetirada;
    }

    public void setLocalRetirada(String localRetirada) {
        this.localRetirada = localRetirada;
    }

    public List<Produto> getListaProdutos() {
        return listaProdutos;
    }

    public void setListaProdutos(List<Produto> listaProdutos) {
        this.listaProdutos = listaProdutos;
    }
}
