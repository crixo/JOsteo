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
import java.util.*;
import josteo.infrastructure.helpers.EntityHelper;

/**
 *
 * @author cristiano
 */
public class PazienteServiceTest {

    private static Paziente paziente;
    private static int CONSULTI = 1;
    private static int ANAMNESI_REMOTE = 2;
    private static int ANAMNESI_PROSSIME = 3;
    private static int ESAMI = 4;
    private static int TRATTAMENTI = 5;
    private static int VALUTAZIONI = 7;
    
    public PazienteServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        paziente = new Paziente();
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.set(java.util.Calendar.YEAR, 1972);
        cal.set(java.util.Calendar.MONTH, 10-1);
        cal.set(java.util.Calendar.DATE, 5);

        //Create instance of java.util.Date
        java.util.Date dataDiNascita = cal.getTime();

        paziente.setNome("Cristiano");
        paziente.setCognome("DegiorgisT" + (new Date()).getTime());
        paziente.setDataNascita(dataDiNascita);
        paziente.setProfessione("developer");

        paziente.setIndirizzo("42 keeler st.");
        paziente.setCitta("Springfield");
        paziente.setTelefono("9086054174");
        paziente.setProv("TO");
        paziente.setCap("07081");
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
//        List current = instance.GetAllPazienti();
//        assertEquals(expResult, current);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }






    /**
     * Test of SavePaziente method, of class PazienteService.
     */
    @Test
    public void testAddPaziente() {        
        System.out.println("AddPaziente");
        IPazienteService instance = (IPazienteService) josteo.infrastructure.helpers.ContainerHelper.getContainer().getBean("PazienteService");
        
        int brokenrules = paziente.GetBrokenRules().size();
        assertFalse(brokenrules>0);
        
        instance.SavePaziente(paziente);

        Object initKey = 0;
        assertFalse(paziente.get_Key()==initKey);

        System.out.println(paziente.get_Key());
    }
    
    
    /**
     * Test of SavePaziente method, of class PazienteService.
     */
    @Test
    public void testSavePaziente() {
        System.out.println("SavePaziente");

        //-------------------------------------
        IPazienteService instance = (IPazienteService) josteo.infrastructure.helpers.ContainerHelper.getContainer().getBean("PazienteService");
        Paziente paziente1 = instance.GetPaziente(paziente.get_Key());

        //Create instance of java.util.Date
        java.util.Date dataDiNascita = createDateNoTime(2000, 2, 1);
        String sProfessione = "my professione";

        paziente1.setDataNascita(dataDiNascita);
        paziente1.setProfessione(sProfessione);
        instance.SavePaziente(paziente1);
        
        paziente = instance.GetPaziente(paziente.get_Key());
        
        assertEquals(paziente.getNome(), paziente1.getNome());
        assertEquals(paziente.getCognome(), paziente1.getCognome());
        assertEquals(paziente.getDataNascita(), paziente1.getDataNascita());
        assertEquals(paziente.getDataNascita(), dataDiNascita);
        assertEquals(paziente.getProfessione(), sProfessione);
    }

    @Test
    public void testConsulti() {
        System.out.println("Consulti");
        IPazienteService instance = (IPazienteService) josteo.infrastructure.helpers.ContainerHelper.getContainer().getBean("PazienteService");

        int toDel = 1;
        int toCreate = CONSULTI+toDel+1;

        Consulto e;
        Consulto eTest;
        List<Consulto> list = new ArrayList<Consulto>();

        for(int i=1; i<toCreate; i++){
            e = new Consulto();
            e.set_Data(createDateNoTime(2000,1,i));
            e.set_ProblemaIniziale("Consulto "+i+" - Problema iniziale1");
            paziente.get_Consulti().add(e);
            list.add(e);
            instance.SavePaziente(paziente);
            System.out.println("Consulto"+i+".get_Key()="+list.get(i-1).get_Key());
        }

        paziente = instance.GetPaziente(paziente.get_Key());
        assertEquals(paziente.get_Consulti().size(), list.size());

        for(int i=0; i<list.size(); i++){
            eTest = list.get(i);
            e = EntityHelper.GetItemByKey(paziente.get_Consulti(), eTest.get_Key());
            assertEquals(eTest.get_Data(),e.get_Data());
            assertEquals(eTest.get_ProblemaIniziale(),e.get_ProblemaIniziale());
        }

        eTest = list.get(0);
        e = EntityHelper.GetItemByKey(paziente.get_Consulti(), eTest.get_Key());
        int delKey = -((Integer)eTest.get_Key());
        System.out.println("delKey="+delKey);
        e.set_Key(delKey);

        eTest = list.get(1);
        e = EntityHelper.GetItemByKey(paziente.get_Consulti(), eTest.get_Key());
        e.set_Data(createDateNoTime(2002,2,2));
        e.set_ProblemaIniziale("Consulto 2 - Problema iniziale1 UPD");
        eTest.set_Data(createDateNoTime(2002,2,2));
        eTest.set_ProblemaIniziale("Consulto 2 - Problema iniziale1 UPD");

        instance.SavePaziente(paziente);

        paziente = instance.GetPaziente(paziente.get_Key());
        for(int i=0; i<list.size(); i++){
            eTest = list.get(i);
            e = EntityHelper.GetItemByKey(paziente.get_Consulti(), eTest.get_Key());

            if(i==0){
                assertTrue(e==null);
            }else{
                assertEquals(eTest.get_Data(),e.get_Data());
                assertEquals(eTest.get_ProblemaIniziale(),e.get_ProblemaIniziale());
            }
        }

    }

    @Test
    public void testAnamnesiRemote() {
        System.out.println("AnamnesiRemote");
        IPazienteService instance = (IPazienteService) josteo.infrastructure.helpers.ContainerHelper.getContainer().getBean("PazienteService");
        ITipoAnamnesiService tipoAnamnesiSvc = (ITipoAnamnesiService) josteo.infrastructure.helpers.ContainerHelper.getContainer().getBean("TipoAnamnesiService");
        josteo.model.tipoAnamnesi.TipoAnamnesi tipo1 = tipoAnamnesiSvc.GetAllAnamnesi().get(0);
        josteo.model.tipoAnamnesi.TipoAnamnesi tipo2 = tipoAnamnesiSvc.GetAllAnamnesi().get(1);

        int toDel = 1;
        int toCreate = ANAMNESI_REMOTE+toDel+1;

        AnamnesiRemota e;
        AnamnesiRemota eTest;
        List<AnamnesiRemota> list = new ArrayList<AnamnesiRemota>();

        for(int i=1; i<toCreate; i++){
            e = new AnamnesiRemota();
            e.set_Note(new Note("AR "+i+" - Nota1", createDateNoTime(2001,2,i)));
            e.set_Tipo(tipo1);
            list.add(e);
            paziente.get_AnamnesiRemote().add(e);
            instance.SavePaziente(paziente);
            System.out.println("AR"+i+".get_Key()="+list.get(i-1).get_Key());
        }

        paziente = instance.GetPaziente(paziente.get_Key());
        assertEquals(paziente.get_AnamnesiRemote().size(), list.size());

        for(int i=0; i<list.size(); i++){
            eTest = list.get(i);
            e = EntityHelper.GetItemByKey(paziente.get_AnamnesiRemote(), eTest.get_Key());
            assertEquals(eTest.get_Note().get_Data(),e.get_Note().get_Data());
            assertEquals(eTest.get_Note().get_Descrizione(),e.get_Note().get_Descrizione());
            assertEquals(eTest.get_Tipo().get_Key(),e.get_Tipo().get_Key());
        }

        eTest = list.get(0);
        e = EntityHelper.GetItemByKey(paziente.get_AnamnesiRemote(), eTest.get_Key());
        int delKey = -((Integer)eTest.get_Key());
        System.out.println("delKey="+delKey);
        e.set_Key(delKey);

        eTest = list.get(1);
        e = EntityHelper.GetItemByKey(paziente.get_AnamnesiRemote(), eTest.get_Key());
        e.set_Note(new Note("Nota2 UPD", createDateNoTime(2002,2,2)));
        eTest.set_Note(new Note("Nota2 UPD", createDateNoTime(2002,2,2)));
        
        instance.SavePaziente(paziente);

        paziente = instance.GetPaziente(paziente.get_Key());
        for(int i=0; i<list.size(); i++){
            eTest = list.get(i);
            e = EntityHelper.GetItemByKey(paziente.get_AnamnesiRemote(), eTest.get_Key());

            if(i==0){
                assertTrue(e==null);
            }else{
                assertEquals(eTest.get_Note().get_Data(),e.get_Note().get_Data());
                assertEquals(eTest.get_Note().get_Descrizione(),e.get_Note().get_Descrizione());
                assertEquals(eTest.get_Tipo().get_Key(),e.get_Tipo().get_Key());
            }
        }


    }

    @Test
    public void testEsami() {
        System.out.println("Esami");
        IPazienteService instance = (IPazienteService) josteo.infrastructure.helpers.ContainerHelper.getContainer().getBean("PazienteService");
        ITipoEsameService tipoEsameSvc = (ITipoEsameService) josteo.infrastructure.helpers.ContainerHelper.getContainer().getBean("TipoEsameService");
        josteo.model.tipoEsame.TipoEsame tipo1 = tipoEsameSvc.GetAllEsami().get(0);
        josteo.model.tipoEsame.TipoEsame tipo2 = tipoEsameSvc.GetAllEsami().get(1);
        Consulto consulto = paziente.get_Consulti().get(0);

        int toDel = 1;
        int toCreate = ESAMI+toDel+1;

        Esame e;
        Esame eTest;
        List<Esame> list = new ArrayList<Esame>();

        for(int i=1; i<toCreate; i++){
            e = new Esame();
            e.set_Note(new Note("Esame "+i+" - Note1", createDateNoTime(2001,3,i)));
            e.set_TipoEsame(tipo1);
            list.add(e);
            consulto.get_Esami().add(e);
            assertTrue(e.GetBrokenRules().size()==0);
            instance.SavePaziente(paziente);
            System.out.println("esame"+i+".get_Key()="+list.get(i-1).get_Key());
        }


        paziente = instance.GetPaziente(paziente.get_Key());
        consulto = paziente.get_Consulti().get(0);
        assertEquals(consulto.get_Esami().size(), list.size());

        for(int i=0; i<list.size(); i++){
            eTest = list.get(i);
            e = EntityHelper.GetItemByKey(consulto.get_Esami(), eTest.get_Key());
            assertEquals(eTest.get_Note().get_Data(),e.get_Note().get_Data());
            assertEquals(eTest.get_Note().get_Descrizione(),e.get_Note().get_Descrizione());
            assertEquals(eTest.get_TipoEsame().get_Key(),e.get_TipoEsame().get_Key());
        }

        eTest = list.get(0);
        e = EntityHelper.GetItemByKey(consulto.get_Esami(), eTest.get_Key());
        e.set_Note(new Note("Esame 1 - Nota1 UPD", createDateNoTime(2002,2,2)));
        eTest.set_Note(new Note("Esame 1 - Nota1 UPD", createDateNoTime(2002,2,2)));
        assertTrue(e.GetBrokenRules().size()==0);

        eTest = list.get(1);
        e = EntityHelper.GetItemByKey(consulto.get_Esami(), eTest.get_Key());
        int delKey = -((Integer)eTest.get_Key());
        System.out.println("delKey="+delKey);
        e.set_Key(delKey);

        instance.SavePaziente(paziente);

        paziente = instance.GetPaziente(paziente.get_Key());
        consulto = paziente.get_Consulti().get(0);
        for(int i=0; i<list.size(); i++){
            eTest = list.get(i);
            e = EntityHelper.GetItemByKey(consulto.get_Esami(), eTest.get_Key());

            if(i==1){
                assertTrue(e==null);
            }else{
                assertEquals(eTest.get_Note().get_Data(),e.get_Note().get_Data());
                assertEquals(eTest.get_Note().get_Descrizione(),e.get_Note().get_Descrizione());
                assertEquals(eTest.get_TipoEsame().get_Key(),e.get_TipoEsame().get_Key());
            }
        }
    }
    
    @Test
    public void testAnamnesiProssime() {
        System.out.println("AnamnesiProssime");
        IPazienteService instance = (IPazienteService) josteo.infrastructure.helpers.ContainerHelper.getContainer().getBean("PazienteService");
        Consulto consulto = paziente.get_Consulti().get(0);

        int toDel = 2;
        int toCreate = ANAMNESI_PROSSIME+toDel+1;

        AnamnesiProssima e;
        AnamnesiProssima eTest;
        List<AnamnesiProssima> list = new ArrayList<AnamnesiProssima>();

        for(int i=1; i<toCreate; i++){
            e = new AnamnesiProssima();
            e.set_AltreTerapie("AltreTerapie " + i);
            e.set_Durata("Durata " + i);
            e.set_Familiarita("Familiarita " + i);
            e.set_Irradiazione("Irradiazione " + i);
            e.set_Localizzazione("Localizzazione " + i);
            e.set_PeriodoInsorgenza("PeriodoInsorgenza " + i);
            e.set_PrimaVolta("1");
            e.set_Tipologia("Tipologia " + i);
            e.set_Varie("Varie " + i);
            list.add(e);
            consulto.get_AnamesiProssime().add(e);
            assertTrue(e.GetBrokenRules().size()==0);
            instance.SavePaziente(paziente);
            System.out.println("AnamnesiProssima"+i+".get_Key()="+list.get(i-1).get_Key());
        }

        paziente = instance.GetPaziente(paziente.get_Key());
        consulto = paziente.get_Consulti().get(0);
        assertEquals(consulto.get_AnamesiProssime().size(), list.size());

        for(int i=0; i<list.size(); i++){
            eTest = list.get(i);
            e = EntityHelper.GetItemByKey(consulto.get_AnamesiProssime(), eTest.get_Key());
            assertEquals(eTest.get_AltreTerapie(),e.get_AltreTerapie());
            assertEquals(eTest.get_Durata(),e.get_Durata());
            assertEquals(eTest.get_Familiarita(),e.get_Familiarita());
            assertEquals(eTest.get_Irradiazione(),e.get_Irradiazione());
            assertEquals(eTest.get_PeriodoInsorgenza(),e.get_PeriodoInsorgenza());
            assertEquals(eTest.get_PrimaVolta(),e.get_PrimaVolta());
            assertEquals(eTest.get_Tipologia(),e.get_Tipologia());
            assertEquals(eTest.get_Varie(),e.get_Varie());
        }


        eTest = list.get(0);
        e = EntityHelper.GetItemByKey(consulto.get_AnamesiProssime(), eTest.get_Key());
        int delKey = -((Integer)eTest.get_Key());
        System.out.println("delKey="+delKey);
        e.set_Key(delKey);

        eTest = list.get(1);
        e = EntityHelper.GetItemByKey(consulto.get_AnamesiProssime(), eTest.get_Key());
        e.set_AltreTerapie("AltreTerapie UPD");
        e.set_Durata("Durata UPD");
        e.set_Familiarita("Familiarita UPD");
        e.set_Irradiazione("Irradiazione UPD");
        e.set_Localizzazione("Localizzazione UPD");
        e.set_PeriodoInsorgenza("PeriodoInsorgenza UPD");
        e.set_PrimaVolta("0");
        e.set_Tipologia("Tipologia UPD");
        e.set_Varie("Varie UPD");
        eTest.set_AltreTerapie("AltreTerapie UPD");
        eTest.set_Durata("Durata UPD");
        eTest.set_Familiarita("Familiarita UPD");
        eTest.set_Irradiazione("Irradiazione UPD");
        eTest.set_Localizzazione("Localizzazione UPD");
        eTest.set_PeriodoInsorgenza("PeriodoInsorgenza UPD");
        eTest.set_PrimaVolta("0");
        eTest.set_Tipologia("Tipologia UPD");
        eTest.set_Varie("Varie UPD");
        assertTrue(e.GetBrokenRules().size()==0);

        eTest = list.get(2);
        e = EntityHelper.GetItemByKey(consulto.get_AnamesiProssime(), eTest.get_Key());
        delKey = -((Integer)eTest.get_Key());
        System.out.println("delKey="+delKey);
        e.set_Key(delKey);

        instance.SavePaziente(paziente);

        paziente = instance.GetPaziente(paziente.get_Key());
        consulto = paziente.get_Consulti().get(0);
        for(int i=0; i<list.size(); i++){
            eTest = list.get(i);
            e = EntityHelper.GetItemByKey(consulto.get_AnamesiProssime(), eTest.get_Key());

            if(i==0 || i==2){
                assertTrue(e==null);
            }else{
                assertEquals(eTest.get_AltreTerapie(),e.get_AltreTerapie());
                assertEquals(eTest.get_Durata(),e.get_Durata());
                assertEquals(eTest.get_Familiarita(),e.get_Familiarita());
                assertEquals(eTest.get_Irradiazione(),e.get_Irradiazione());
                assertEquals(eTest.get_PeriodoInsorgenza(),e.get_PeriodoInsorgenza());
                assertEquals(eTest.get_PrimaVolta(),e.get_PrimaVolta());
                assertEquals(eTest.get_Tipologia(),e.get_Tipologia());
                assertEquals(eTest.get_Varie(),e.get_Varie());
            }
        }
    }

    @Test
    public void testTrattamenti() {
        System.out.println("Trattamenti");
        IPazienteService instance = (IPazienteService) josteo.infrastructure.helpers.ContainerHelper.getContainer().getBean("PazienteService");
        Consulto consulto = paziente.get_Consulti().get(0);

        int toDel = 2;
        int toCreate = TRATTAMENTI+toDel+1;

        Trattamento e;
        Trattamento eTest;
        List<Trattamento> list = new ArrayList<Trattamento>();

        for(int i=1; i<toCreate; i++){
            e = new Trattamento();
            e.set_Note(new Note("Trattamento"+i+" - Nota1", createDateNoTime(2001,4,i)));
            list.add(e);
            consulto.get_Trattamenti().add(e);
            assertTrue(e.GetBrokenRules().size()==0);
            instance.SavePaziente(paziente);
            System.out.println("Trattamenti"+i+".get_Key()="+list.get(i-1).get_Key());
        }

        paziente = instance.GetPaziente(paziente.get_Key());
        consulto = paziente.get_Consulti().get(0);
        assertEquals(consulto.get_Trattamenti().size(), list.size());


        for(int i=0; i<list.size(); i++){
            eTest = list.get(i);
            e = EntityHelper.GetItemByKey(consulto.get_Trattamenti(), eTest.get_Key());
            assertEquals(eTest.get_Note().get_Data(),e.get_Note().get_Data());
            assertEquals(eTest.get_Note().get_Descrizione(),e.get_Note().get_Descrizione());
        }

        eTest = list.get(0);
        e = EntityHelper.GetItemByKey(consulto.get_Trattamenti(), eTest.get_Key());
        int delKey = -((Integer)eTest.get_Key());
        System.out.println("delKey="+delKey);
        e.set_Key(delKey);

        eTest = list.get(1);
        e = EntityHelper.GetItemByKey(consulto.get_Trattamenti(), eTest.get_Key());
        e.set_Note(new Note("Trattamento2 - Nota1 - UPD", createDateNoTime(2002,4,2)));
        eTest.set_Note(new Note("Trattamento2 - Nota1 - UPD", createDateNoTime(2002,4,2)));
        assertTrue(e.GetBrokenRules().size()==0);

        eTest = list.get(2);
        e = EntityHelper.GetItemByKey(consulto.get_Trattamenti(), eTest.get_Key());
        e.set_Note(new Note("Trattamento3 - Nota1 - UPD", createDateNoTime(2002,4,3)));
        eTest.set_Note(new Note("Trattamento3 - Nota1 - UPD", createDateNoTime(2002,4,3)));
        assertTrue(e.GetBrokenRules().size()==0);

        eTest = list.get(3);
        e = EntityHelper.GetItemByKey(consulto.get_Trattamenti(), eTest.get_Key());
        delKey = -((Integer)eTest.get_Key());
        System.out.println("delKey="+delKey);
        e.set_Key(delKey);

        instance.SavePaziente(paziente);

        paziente = instance.GetPaziente(paziente.get_Key());
        consulto = paziente.get_Consulti().get(0);
        for(int i=0; i<list.size(); i++){
            eTest = list.get(i);
            e = EntityHelper.GetItemByKey(consulto.get_Trattamenti(), eTest.get_Key());

            if(i==0 || i==3){
                assertTrue(e==null);
            }else{
                assertEquals(eTest.get_Note().get_Data(),e.get_Note().get_Data());
                assertEquals(eTest.get_Note().get_Descrizione(),e.get_Note().get_Descrizione());
            }
        }
    }

    @Test
    public void testValutazioni() {
        System.out.println("Valutazioni");
        IPazienteService instance = (IPazienteService) josteo.infrastructure.helpers.ContainerHelper.getContainer().getBean("PazienteService");
        Consulto consulto = paziente.get_Consulti().get(0);

        Valutazione e;
        Valutazione eTest;
        List<Valutazione> list = new ArrayList<Valutazione>();

        for(int i=1; i<10; i++){
            e = new Valutazione();
            e.set_Strutturale("Strutturale "+i);
            e.set_CranioSacrale("CranioSacrale "+i);
            e.set_AkOrtodontica("AkOrtodontica"+i);
            list.add(e);
            consulto.get_Valutazioni().add(e);
            assertTrue(e.GetBrokenRules().size()==0);
            instance.SavePaziente(paziente);
            System.out.println("Valutazione"+i+".get_Key()="+list.get(i-1).get_Key());
        }

        paziente = instance.GetPaziente(paziente.get_Key());
        consulto = paziente.get_Consulti().get(0);
        assertEquals(consulto.get_Valutazioni().size(), list.size());


        for(int i=0; i<list.size(); i++){
            eTest = list.get(i);
            e = EntityHelper.GetItemByKey(consulto.get_Valutazioni(), eTest.get_Key());
            assertEquals(eTest.get_Strutturale(),e.get_Strutturale());
            assertEquals(eTest.get_CranioSacrale(),e.get_CranioSacrale());
            assertEquals(eTest.get_AkOrtodontica(),e.get_AkOrtodontica());
        }

        eTest = list.get(0);
        e = EntityHelper.GetItemByKey(consulto.get_Valutazioni(), eTest.get_Key());
        int delKey = -((Integer)eTest.get_Key());
        System.out.println("delKey="+delKey);
        e.set_Key(delKey);

        eTest = list.get(1);
        e = EntityHelper.GetItemByKey(consulto.get_Valutazioni(), eTest.get_Key());
        delKey = -((Integer)eTest.get_Key());
        System.out.println("delKey="+delKey);
        e.set_Key(delKey);

        eTest = list.get(2);
        e = EntityHelper.GetItemByKey(consulto.get_Valutazioni(), eTest.get_Key());
        e.set_Strutturale("Strutturale 3 UPD");
        e.set_CranioSacrale("CranioSacrale 3 UPD");
        e.set_AkOrtodontica("AkOrtodontica 3 UPD");
        eTest.set_Strutturale("Strutturale 3 UPD");
        eTest.set_CranioSacrale("CranioSacrale 3 UPD");
        eTest.set_AkOrtodontica("AkOrtodontica 3 UPD");
        assertTrue(e.GetBrokenRules().size()==0);

        eTest = list.get(3);
        e = EntityHelper.GetItemByKey(consulto.get_Valutazioni(), eTest.get_Key());
        e.set_Strutturale("Strutturale UPD");
        e.set_CranioSacrale("CranioSacrale UPD");
        e.set_AkOrtodontica("AkOrtodontica UPD");
        eTest.set_Strutturale("Strutturale UPD");
        eTest.set_CranioSacrale("CranioSacrale UPD");
        eTest.set_AkOrtodontica("AkOrtodontica UPD");
        assertTrue(e.GetBrokenRules().size()==0);

        instance.SavePaziente(paziente);

        paziente = instance.GetPaziente(paziente.get_Key());
        consulto = paziente.get_Consulti().get(0);
        for(int i=0; i<list.size(); i++){
            eTest = list.get(i);
            e = EntityHelper.GetItemByKey(consulto.get_Valutazioni(), eTest.get_Key());

            if(i==0 || i==1){
                assertTrue(e==null);
            }else{
                assertEquals(eTest.get_Strutturale(),e.get_Strutturale());
                assertEquals(eTest.get_CranioSacrale(),e.get_CranioSacrale());
                assertEquals(eTest.get_AkOrtodontica(),e.get_AkOrtodontica());
            }
        }
    }


    /**
     * Test of GetPaziente method, of class PazienteService.
     */
    @Test
    public void testGetPaziente() {
        System.out.println("GetPaziente");
        IPazienteService instance = (IPazienteService) josteo.infrastructure.helpers.ContainerHelper.getContainer().getBean("PazienteService");//new josteo.application.PazienteService();

        Paziente current = instance.GetPaziente(paziente.get_Key());
        assertEquals(paziente.getNome(), current.getNome());
        assertEquals(paziente.getCognome(), current.getCognome());
        assertEquals(paziente.getIndirizzo(), current.getIndirizzo());
        assertEquals(paziente.getCap(), current.getCap());
        assertEquals(paziente.getCellulare(), current.getCellulare());
        assertEquals(paziente.getTelefono(), current.getTelefono());
        assertEquals(paziente.getCitta(), current.getCitta());
        assertEquals(paziente.getEmail(), current.getEmail());
        assertEquals(paziente.getProfessione(), current.getProfessione());
        assertEquals(paziente.getProv(), current.getProv());


        assertEquals(ANAMNESI_REMOTE, current.get_AnamnesiRemote().size());
        
        assertEquals(CONSULTI, current.get_Consulti().size());
        assertEquals(ESAMI, current.get_Consulti().get(0).get_Esami().size());
        assertEquals(ANAMNESI_PROSSIME, current.get_Consulti().get(0).get_AnamesiProssime().size());
        assertEquals(TRATTAMENTI, current.get_Consulti().get(0).get_Trattamenti().size());
        assertEquals(VALUTAZIONI, current.get_Consulti().get(0).get_Valutazioni().size());

    }


    /**
     * Test of SavePaziente method, of class PazienteService.
     */
    @Test
    public void testDeletePaziente() {
        System.out.println("SavePaziente - Add");
        PazienteService instance = new josteo.application.PazienteService(new josteo.infrastructure.repositories.Pazienti.PazienteRepository());
        Paziente paziente1 = instance.GetPaziente(paziente.get_Key());
        paziente1.set_Key(-((Integer)paziente1.get_Key()));
        instance.DeletePaziente(paziente1);

        Paziente expected = null;
        Paziente actual = instance.GetPaziente(paziente.get_Key());

        assertEquals(expected, actual);
    }


    public Date createDateNoTime(int yr, int month, int day){
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.set(java.util.Calendar.YEAR, yr);
        cal.set(java.util.Calendar.MONTH, month-1);
        cal.set(java.util.Calendar.DATE, day);
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.AM_PM, Calendar.AM);
        return cal.getTime();
    }

}