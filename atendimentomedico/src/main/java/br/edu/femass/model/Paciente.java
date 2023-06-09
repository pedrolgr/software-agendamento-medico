package br.edu.femass.model;

import java.util.Set;

public class Paciente extends Pessoa{
    private PlanoDeSaude plano;
    private Long id;

    public static Long ultimoId = 0L;

    public Paciente() {

    }

    public Paciente(String nome, String cpf, String idade, String telefone, PlanoDeSaude plano){
        super(nome, cpf, idade, telefone);
        this.plano = plano;

        // if(this.plano == null) throw new IllegalArgumentException("Plano inválido");

        this.id = ultimoId+1;
    }

    public Long getId(){
        return this.id;
    }

    public PlanoDeSaude getPlano(){
        return plano;
    }

    @Override
    public String toString() {
        return this.nome;
    }

    public static void atualizarUltimoId(Set<Paciente> pacientes) {
        for (Paciente paciente: pacientes) {
            if (paciente.getId().longValue() > ultimoId) {
                ultimoId = paciente.getId();
            }
        }
    }

}
