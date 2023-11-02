package benicio.soluces.pesroapp.models;

public class Motorista {
    String nome, rg, cpf, whatsapp, endereco, empresa_or_particular;

    public Motorista() {
    }

    public Motorista(String nome, String rg, String cpf, String whatsapp, String endereco, String empresa_or_particular) {
        this.nome = nome;
        this.rg = rg;
        this.cpf = cpf;
        this.whatsapp = whatsapp;
        this.endereco = endereco;
        this.empresa_or_particular = empresa_or_particular;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmpresa_or_particular() {
        return empresa_or_particular;
    }

    public void setEmpresa_or_particular(String empresa_or_particular) {
        this.empresa_or_particular = empresa_or_particular;
    }

    @Override
    public String toString() {
        return  "Nome: " + nome + '\n' +
                "RG: " + rg + '\n' +
                "CPF: " + cpf + '\n' +
                "Whatsapp: " + whatsapp + '\n' +
                "Endereco: " + endereco + '\n' +
                "Empresa: " + empresa_or_particular;
    }
}
