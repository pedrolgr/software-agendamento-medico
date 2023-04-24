package br.edu.femass;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import br.edu.femass.model.Paciente;
import br.edu.femass.model.PlanoDeSaude;

public class PacienteTest {
    
    @Test
    public void atribuicaoCorretaNosParametros() {
        PlanoDeSaude plano = new PlanoDeSaude("Unimed Nacional");
        Paciente paciente = new Paciente("Pedro Guimaraes", "40113965087", "22", "22111333", plano);
        assertEquals("Pedro Guimaraes", paciente.getNome());
        assertEquals("40113965087", paciente.getCpf());
        assertEquals("22", paciente.getIdade());
        assertTrue(paciente.getTelefones().contains("22111333"));
        assertEquals(plano, paciente.getPlano());
    }

    @Test
    public void verificaIncrementacaoId() {
        PlanoDeSaude plano1 = new PlanoDeSaude("Unimed Nacional");
        Paciente paciente1 = new Paciente("Pedro Guimaraes", "40113965087", "22", "22111333", plano1);

        PlanoDeSaude plano2 = new PlanoDeSaude("Bradesco");
        Paciente paciente2 = new Paciente("Pedro Lucio", "29124573000", "32", "11000333", plano2);

        assertEquals(1L, paciente1.getId().intValue());
        assertEquals(1L, paciente2.getId().intValue());
    }


}

