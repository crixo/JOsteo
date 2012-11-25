/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package josteo.model.paziente;

import josteo.application.*;
import java.util.List;
import josteo.infrastructure.DomainBase.LookUpItem;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author cristiano
 */
public class PazienteService01Test {

    public PazienteService01Test() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of GetAllPazienti method, of class PazienteService.
     */
//    @Test
//    public void testGetAllPazienti() {
//        System.out.println("GetAllPazienti");
//        PazienteService instance = null;
//        List expResult = null;
//        List result = instance.GetAllPazienti();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of SavePaziente method, of class PazienteService.
     */
    @Test
    public void testSavePaziente() {
        System.out.println("SavePaziente");

        String expected;
        String actual;

        //-------------------------------------
        expected = "mancanza piede UPD";
        Object pazienteKey = 19;
        IPazienteService instance = (IPazienteService) josteo.infrastructure.helpers.ContainerHelper.getContainer().getBean("PazienteService");
        Paziente paziente = instance.GetPaziente(pazienteKey);
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.set(java.util.Calendar.YEAR, 2000);
        cal.set(java.util.Calendar.MONTH, 1);
        cal.set(java.util.Calendar.DATE, 1);

        //Create instance of java.util.Date
        java.util.Date dataDiNascita = cal.getTime();

        paziente.setDataNascita(dataDiNascita);
        paziente.setProfessione("my professione");

        paziente.get_Consulti().get(1).set_ProblemaIniziale(expected);

        instance.SavePaziente(paziente);

        ///////////

        expected = "maniscalco";
        pazienteKey = 31;
        
        paziente = instance.GetPaziente(pazienteKey);
        paziente.setProfessione(expected);

        instance.SavePaziente(paziente);

        paziente = instance.GetPaziente(pazienteKey);
        actual = paziente.getProfessione();

        assertEquals(expected, actual);
    }

    /**
     * Test of SavePaziente method, of class PazienteService.
     */
    //@Test
    public void testDeletePaziente() {
        System.out.println("SavePaziente - Add");
        Object pazienteKey = 23;
        PazienteService instance = new josteo.application.PazienteService(new josteo.infrastructure.repositories.Pazienti.PazienteRepository());
        Paziente paziente = instance.GetPaziente(pazienteKey);
        instance.DeletePaziente(paziente);

        Paziente expected = null;
        Paziente actual = instance.GetPaziente(pazienteKey);

        assertEquals(expected, actual);
    }


    /**
     * Test of SavePaziente method, of class PazienteService.
     */
    //@Test
    public void testAddPaziente() {
        System.out.println("AddPaziente");
        PazienteService instance = new josteo.application.PazienteService(new josteo.infrastructure.repositories.Pazienti.PazienteRepository());
        Paziente paziente = new Paziente();
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.set(java.util.Calendar.YEAR, 1972);
        cal.set(java.util.Calendar.MONTH, 10-1);
        cal.set(java.util.Calendar.DATE, 5);

        //Create instance of java.util.Date
        java.util.Date dataDiNascita = cal.getTime();

        paziente.setNome("Cristiano");
        //paziente.setCognome("Degiorgis");
        paziente.setDataNascita(dataDiNascita);
        paziente.setProfessione("developer");

        paziente.setIndirizzo("42 keeler st.");
        paziente.setCitta("Springfield");
        paziente.setTelefono("9086054174");
        paziente.setProfessione("TO");
        paziente.setCap("07081");

        List<josteo.infrastructure.DomainBase.BrokenRule> brokenRules = paziente.GetBrokenRules();
        if(brokenRules.isEmpty()){
            instance.SavePaziente(paziente);
        }else{
            for(josteo.infrastructure.DomainBase.BrokenRule br : brokenRules  )
                System.out.println(br.get_Message() );
        }

        Object initKey = 0;
        assertFalse(paziente.get_Key()==initKey);
    }

    /**
     * Test of SavePaziente method, of class PazienteService.
     */
    //@Test
    public void testSavePaziente_AddChilds() {
        System.out.println("SavePaziente");
        Object pazienteKey = 19;
        PazienteService instance = new josteo.application.PazienteService(new josteo.infrastructure.repositories.Pazienti.PazienteRepository());
        Paziente paziente = instance.GetPaziente(pazienteKey);
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.set(java.util.Calendar.YEAR, 2000);
        cal.set(java.util.Calendar.MONTH, 1);
        cal.set(java.util.Calendar.DATE, 1);

        //Create instance of java.util.Date
        java.util.Date dataDiNascita = cal.getTime();

        paziente.setProfessione("my professione");

        paziente.get_Consulti().get(1).set_ProblemaIniziale("mancanza piede");

        AnamnesiProssima ap = new AnamnesiProssima(0);
        ap.set_AltreTerapie("molti salassi");
        paziente.get_Consulti().get(1).get_AnamesiProssime().add(ap);

//        AnamnesiRemota ar = new AnamnesiRemota(0, new Note("anam rem", dataDiNascita), new LookUpItem(1, "", ""));
//        paziente.get_AnamnesiRemote().add(ar);

        instance.SavePaziente(paziente);

        Paziente pazienteSaved = instance.GetPaziente(pazienteKey);

        assertEquals("molti salassi", pazienteSaved.get_Consulti().get(1).get_AnamesiProssime().get(1).get_AltreTerapie());
    }

    //@Test
    public void testSavePaziente_AddCosultoChilds() {
        System.out.println("SavePaziente");
        Object pazienteKey = 19;
        PazienteService instance = new josteo.application.PazienteService(new josteo.infrastructure.repositories.Pazienti.PazienteRepository());
        Paziente paziente = instance.GetPaziente(pazienteKey);

        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.set(java.util.Calendar.YEAR, 2000);
        cal.set(java.util.Calendar.MONTH, 1);
        cal.set(java.util.Calendar.DATE, 1);

        //Create instance of java.util.Date
        java.util.Date myDate = cal.getTime();

        paziente.get_Consulti().get(1).set_ProblemaIniziale("mancanza piede");

//        AnamnesiProssima ap = new AnamnesiProssima(0);
//        ap.set_AltreTerapie("molti salassi2");
//        paziente.get_Consulti().get(1).get_AnamesiProssime().add(ap);

        Valutazione valutazione = new Valutazione(0,"strutturale","cranio sacrale","ak ortodontica");
        paziente.get_Consulti().get(1).get_Valutazioni().add(valutazione);

        Trattamento trattamento = new Trattamento(0,new Note("primo trattamento per consulto 2", myDate));
        paziente.get_Consulti().get(1).get_Trattamenti().add(trattamento);

        instance.SavePaziente(paziente);

        Paziente pazienteSaved = instance.GetPaziente(pazienteKey);

        //assertEquals("molti salassi2", pazienteSaved.get_Consulti().get(1).get_AnamesiProssime().get(1).get_AltreTerapie());
        assertEquals("primo trattamento per consulto 2", pazienteSaved.get_Consulti().get(1).get_Trattamenti().get(0).get_Note().get_Descrizione());
        assertEquals("strutturale", pazienteSaved.get_Consulti().get(1).get_Valutazioni().get(0).get_Strutturale());
    }

    /**
     * Test of GetPaziente method, of class PazienteService.
     */
    @Test
    public void testGetPaziente() {
        System.out.println("GetPaziente");
        Object pazienteKey = 19;
        IPazienteService instance = (IPazienteService) josteo.infrastructure.helpers.ContainerHelper.getContainer().getBean("PazienteService");//new josteo.application.PazienteService();
        Paziente expResult = null;
        Paziente result = instance.GetPaziente(pazienteKey);
        assertEquals("Washington", result.getCognome());
        assertEquals(3, result.get_Consulti().size());
        Consulto cosulto = result.get_Consulti().get(1);
        assertEquals("molti salassi", cosulto.get_AnamesiProssime().get(0).get_AltreTerapie());
        assertEquals("primo trattamento per consulto 2", cosulto.get_Trattamenti().get(0).get_Note().get_Descrizione());
        assertEquals("strutturale", cosulto.get_Valutazioni().get(0).get_Strutturale());
    }

    @Test
    public void testGetAllPazienti() {
        System.out.println("GetAllPazienti");
        IPazienteService instance = (IPazienteService) josteo.infrastructure.helpers.ContainerHelper.getContainer().getBean("PazienteService");//PazienteService instance = new josteo.application.PazienteService();
        Paziente expResult = null;
        List<Paziente> result = instance.GetAllPazienti();
        assertEquals(22, result.size());
    }

}