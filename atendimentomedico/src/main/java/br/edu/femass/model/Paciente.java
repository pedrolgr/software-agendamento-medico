package br.edu.femass.model;

public class Paciente extends Pessoa{
    private PlanoDeSaude plano;

    public Paciente(String nome, String cpf, int idade, String telefone, PlanoDeSaude plano){
        super(nome, cpf, idade, telefone);
        this.plano = plano;
    }

    public String getNomePlano(){
        return this.plano.getNome();
    }

    public String getTipoPlano(){
        return this.plano.getTipo();
    }

}
