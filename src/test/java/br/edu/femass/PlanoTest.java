package br.edu.femass;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import br.edu.femass.model.PlanoDeSaude;

public class PlanoTest {

    @Test
    public void testeGetters() {
        PlanoDeSaude plano = new PlanoDeSaude("Plano Básico");
        assertEquals("Plano Básico", plano.getNome());
        assertNotNull(plano.getId());

    }

    @Test 
    public void testeSetters() {
        PlanoDeSaude plano = new PlanoDeSaude("Plano A");
        assertEquals("Plano A", plano.toString());

    }


}
