package br.edu.femass.model;

import java.util.Set;

public class PlanoDeSaude {
    private Long id;
    private String nome;

    public static Long ultimoId = 0L;

    public PlanoDeSaude () {

    }

    public PlanoDeSaude (String nome) {
        this.nome = nome;

        this.id = ultimoId+1;
    }

    public Long getId(){
        return this.id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return this.nome;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PlanoDeSaude other = (PlanoDeSaude) obj;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        return true;
    }

    public static void atualizarUltimoId(Set<PlanoDeSaude> planos) {
        for (PlanoDeSaude plano: planos) {
            if (plano.getId().longValue() > ultimoId) {
                ultimoId = plano.getId();
            }
        }
    }


        
}
