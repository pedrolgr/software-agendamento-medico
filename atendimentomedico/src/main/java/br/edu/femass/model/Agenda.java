package br.edu.femass.model;

import java.util.Set;

public class Agenda {
    private Long id;
    private Paciente paciente;
    private Medico medico;
    private Especializacao especializacao;
    private String data;
    private String hora;

    public static Long ultimoId = 0L;

    public Agenda() {

    }

    public Agenda(Paciente paciente,
    Medico medico,
    String data,
    String hora,
    Especializacao especializacao) {
        this.paciente = paciente;
        this.medico = medico;
        this.data = data;
        this.hora = hora;
        this.especializacao = especializacao;

        this.id = ultimoId+1;
    }

    public Long getId(){
        return this.id;
    }

    public Especializacao getEspecializacao() {
        return especializacao;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((data == null) ? 0 : data.hashCode());
        result = prime * result + ((hora == null) ? 0 : hora.hashCode());
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
        Agenda other = (Agenda) obj;
        if (data == null) {
            if (other.data != null)
                return false;
        } else if (!data.equals(other.data))
            return false;
        if (hora == null) {
            if (other.hora != null)
                return false;
        } else if (!hora.equals(other.hora))
            return false;
        return true;
    }

    public static void atualizarUltimoId(Set<Agenda> agendas) {
        for (Agenda agenda: agendas) {
            if (agenda.getId().longValue() > ultimoId) {
                ultimoId = agenda.getId();
            }
        }
    }

    
}
