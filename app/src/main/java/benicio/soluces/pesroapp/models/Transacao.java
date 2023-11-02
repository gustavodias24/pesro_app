package benicio.soluces.pesroapp.models;

import android.annotation.SuppressLint;

public class Transacao {
    String data, descricao;
    float valor;
    int tipo; // 0 despesa 1 receita

    public Transacao() {
    }

    public Transacao(String data, String descricao, float valor, int tipo) {
        this.data = data;
        this.descricao = descricao;
        this.valor = valor;
        this.tipo = tipo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    @SuppressLint("DefaultLocale")
    @Override
    public String toString() {

        String tipoTransacao = tipo == 0 ? "Despesa" : "Receita";

        return  "data: " + data + '\n' +
                "descricao: " + descricao + '\n' +
                String.format( "valor: R$%.2f",valor) + "\n" +
                "tipo: " + tipoTransacao;
    }
}
