package br.edu.femass;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import br.edu.femass.model.Especializacao;
import br.edu.femass.model.Medico;

public class MedicoTest {

    @Test
    public void atribuicaoCorretaNosParametros() {
        Especializacao esp = new Especializacao("Cardiologia");
        Medico medico = new Medico("João", "40113965087", "35", "999999999", "12345");

        assertEquals("João", medico.getNome());
        assertEquals("40113965087", medico.getCpf());
        assertEquals("35", medico.getIdade());
        assertTrue(medico.getTelefones().contains("999999999"));
    }
    
    @Test
    public void verificaIncrementacaoId() {
        Medico medico1 = new Medico("João", "40113965087", "35", "999999999", "12345");

        Medico medico2 = new Medico("Marcio", "40113965087", "35", "999999999", "12345");
        

        assertEquals(1L, medico1.getId().intValue());
        assertEquals(1L, medico2.getId().intValue());
    }
}
