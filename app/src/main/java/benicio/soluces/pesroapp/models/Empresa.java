package benicio.soluces.pesroapp.models;

public class Empresa {
    public Empresa() {
    }

    public Empresa(String nome, String cnpj, String funcionarios, String whatsapp, String endereco) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.funcionarios = funcionarios;
        this.whatsapp = whatsapp;
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(String funcionarios) {
        this.funcionarios = funcionarios;
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

    String nome, cnpj, funcionarios, whatsapp, endereco;

    @Override
    public String toString() {
        return  "Nome: " + nome + '\n' +
                "CNPJ: " + cnpj + '\n' +
                "Funcionários: \n" + funcionarios + '\n' +
                "Whatsapp: " + whatsapp + '\n' +
                "Endereço: " + endereco ;
    }
}
