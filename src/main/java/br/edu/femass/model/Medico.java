package br.edu.femass.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Medico extends Pessoa{
    private Long id;
    private String crm;
    protected List<Especializacao> especializacoes = new ArrayList<Especializacao>();

    public static Long ultimoId = 0L;

    public Medico() {

    }

    public Medico(String nome, 
    String cpf, 
    String idade, 
    String telefone, 
    String crm) {
        super(nome, cpf, idade, telefone);
        this.crm = crm;

        this.id = ultimoId+1;
    }

    public Long getId(){
        return this.id;
    }

    public List<Especializacao> getEspecializacao() {
        return especializacoes;
    }

    public void addEspecializacao(Especializacao especializacao){
        this.especializacoes.add(especializacao);
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public static void atualizarUltimoId(Set<Medico> medicos) {
        for (Medico medico: medicos) {
            if (medico.getId().longValue() > ultimoId) {
                ultimoId = medico.getId();
            }
        }
    }

    @Override
    public String toString() {
        return this.nome;
    }
    
}
