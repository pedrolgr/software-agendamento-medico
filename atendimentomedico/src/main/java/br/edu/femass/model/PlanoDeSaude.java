package br.edu.femass.model;

import java.util.List;
import java.util.Set;

public class PlanoDeSaude {
    private Long id;
    private String nome;
    private String tipo;

    private static Long ultimoId = 0L;

    public PlanoDeSaude () {

    }

    public PlanoDeSaude (String nome, String tipo) {
        this.nome = nome;
        this.tipo = tipo;

        this.id = ultimoId+1;
        ultimoId++;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return this.nome;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
        if (tipo == null) {
            if (other.tipo != null)
                return false;
        } else if (!tipo.equals(other.tipo))
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
