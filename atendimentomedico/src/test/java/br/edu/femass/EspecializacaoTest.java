package br.edu.femass;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import br.edu.femass.model.Especializacao;

public class EspecializacaoTest {

    @Test
    public void atribuicaoCorretaNosParametros() {
    Especializacao e = new Especializacao("Neurologista");
    assertEquals("Neurologista", e.getEspecializacao());
    }

    
    @Test
    public void verificaEspecializacaoNulo() {
        try {
            Especializacao e = new Especializacao(null);
            fail("A especializacao não pode ser null.");
        } catch (IllegalArgumentException e) {
            assertEquals("Especialização inválida", e.getMessage());
        }
    }

    @Test
    public void verificaIncrementacaoId() {
        Especializacao e1 = new Especializacao("Neurologista");
        Especializacao e2 = new Especializacao("Cardiologista");

        assertEquals(1L, e1.getId().intValue());
        assertEquals(2L, e2.getId().intValue());
    }

    
}
