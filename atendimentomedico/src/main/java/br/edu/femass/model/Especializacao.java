package br.edu.femass.model;

import java.util.Set;

public class Especializacao {
    private long id;
    private String especializacao;
    private String faculdade;

    private static Long ultimoId = 0L;

    public Especializacao() {

    }

    public Especializacao(String especializacao, String faculdade) {
        this.especializacao = especializacao;
        this.faculdade = faculdade;

        this.id = ultimoId+1;
        ultimoId++;
    }

    public Long getId(){
        return this.id;
    }

    public String getEspecializacao() {
        return this.especializacao;
    }

    public void setEspecializacao(String especializacao) {
        this.especializacao = especializacao;
    }

    public String getfaculdade() {
        return this.faculdade;
    }

    public void setfaculdade(String faculdade) {
        this.faculdade = faculdade;
    }

    @Override
    public String toString() {
        return this.especializacao;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((especializacao == null) ? 0 : especializacao.hashCode());
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
        Especializacao other = (Especializacao) obj;
        if (especializacao == null) {
            if (other.especializacao != null)
                return false;
        } else if (!especializacao.equals(other.especializacao))
            return false;
        return true;
    }

    public static void atualizarUltimoId(Set<Especializacao> especializacoes) {
        for (Especializacao especializacao: especializacoes) {
            if (especializacao.getId().longValue() > ultimoId) {
                ultimoId = especializacao.getId();
            }
        }
    }
}
