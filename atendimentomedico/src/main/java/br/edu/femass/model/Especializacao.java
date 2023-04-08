package br.edu.femass.model;

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
}
