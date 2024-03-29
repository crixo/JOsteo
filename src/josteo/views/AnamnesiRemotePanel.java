/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * AnamnesiRemotePanel.java
 *
 * Created on Nov 12, 2010, 7:28:50 PM
 */

package josteo.views;

import josteo.views.UI.AnamnesiRemotaAdvancedTableFormat;
import josteo.infrastructure.UI.PanelListBase;
import josteo.application.IPazienteSelectedListner;
import java.util.*;
import javax.swing.event.*;
import ca.odell.glazedlists.swing.*;
import ca.odell.glazedlists.*;
import ca.odell.glazedlists.gui.*;

import josteo.viewmodels.*;
import josteo.model.paziente.*;
import josteo.application.*;
import josteo.infrastructure.helpers.*;
import josteo.infrastructure.DomainBase.*;
import josteo.infrastructure.UI.*;

import org.jdesktop.swingbinding.*;
import org.jdesktop.beansbinding.*;
import org.jdesktop.swingbinding.SwingBindings;
import javax.swing.*;
import java.awt.event.*;
import josteo.model.tipoAnamnesi.TipoAnamnesi;

/**
 *
 * @author cristiano
 */
public class AnamnesiRemotePanel extends PanelListBase<AnamnesiRemota> implements IPazienteSelectedListner{

    private List<TipoAnamnesi> _anamnesi;

    /** Creates new form AnamnesiRemotePanel */
    public AnamnesiRemotePanel() {
        super();
        initComponents();

        this._presenter = new josteo.viewmodels.AnamnesiRemotePresenter();


        ApplicationState.getInstance().addPazienteSelectedListener(this);

        this._anamnesi = ((ITipoAnamnesiService)ContainerHelper.getContainer().getBean("TipoAnamnesiService")).GetAllAnamnesi();

        // create the binding from List to JComboBox
        JComboBoxBinding cb = SwingBindings.createJComboBoxBinding(AutoBinding.UpdateStrategy.READ, _anamnesi, this.cbTipoAnamnesi);
        // realize the binding
        cb.bind();

        this.jTable1.setRowHeight(32);

        this.taDescrizione.getDocument().addDocumentListener(new UpdateListener());
        this.dcData.addPropertyChangeListener(new MyPropertyChangeListener());
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        pnEditing = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        dcData = new com.toedter.calendar.JDateChooser();
        lblData = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        taDescrizione = new javax.swing.JTextArea();
        lblDescrizione = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        cbTipoAnamnesi = new javax.swing.JComboBox();
        lblTipoAnamnesi = new javax.swing.JLabel();
        btnStore = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        pnEditing.setBorder(javax.swing.BorderFactory.createTitledBorder("Anamnesi Remota"));

        lblData.setText("Data");

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(lblData)
                    .add(dcData, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 170, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(lblData)
                .add(0, 0, 0)
                .add(dcData, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(0, Short.MAX_VALUE))
        );

        taDescrizione.setColumns(20);
        taDescrizione.setRows(5);

        lblDescrizione.setText("Descrizione");

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(0, 0, 0)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(taDescrizione, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 362, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(lblDescrizione))
                .add(91, 91, 91))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(0, 0, 0)
                .add(lblDescrizione)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(taDescrizione, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 180, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        lblTipoAnamnesi.setText("Tipo Anamesi");

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .add(0, 0, 0)
                .add(cbTipoAnamnesi, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 314, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
            .add(jPanel3Layout.createSequentialGroup()
                .add(8, 8, 8)
                .add(lblTipoAnamnesi))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel3Layout.createSequentialGroup()
                .add(lblTipoAnamnesi)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 0, Short.MAX_VALUE)
                .add(cbTipoAnamnesi, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(0, 0, 0))
        );

        btnStore.setText("Store");
        btnStore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStoreActionPerformed(evt);
            }
        });

        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout pnEditingLayout = new org.jdesktop.layout.GroupLayout(pnEditing);
        pnEditing.setLayout(pnEditingLayout);
        pnEditingLayout.setHorizontalGroup(
            pnEditingLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(pnEditingLayout.createSequentialGroup()
                .add(5, 5, 5)
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
            .add(pnEditingLayout.createSequentialGroup()
                .add(79, 79, 79)
                .add(btnReset)
                .add(49, 49, 49)
                .add(btnStore))
            .add(pnEditingLayout.createSequentialGroup()
                .add(8, 8, 8)
                .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 366, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
            .add(jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        );
        pnEditingLayout.setVerticalGroup(
            pnEditingLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(pnEditingLayout.createSequentialGroup()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(7, 7, 7)
                .add(jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 206, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(18, 18, 18)
                .add(pnEditingLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(btnReset)
                    .add(btnStore))
                .addContainerGap())
        );

        jSplitPane1.setRightComponent(pnEditing);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jSplitPane1.setLeftComponent(jScrollPane1);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jSplitPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jSplitPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(70, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnStoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStoreActionPerformed
        // TODO add your handling code here:
        store();
}//GEN-LAST:event_btnStoreActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        reset();
}//GEN-LAST:event_btnResetActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnStore;
    private javax.swing.JComboBox cbTipoAnamnesi;
    private com.toedter.calendar.JDateChooser dcData;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblData;
    private javax.swing.JLabel lblDescrizione;
    private javax.swing.JLabel lblTipoAnamnesi;
    private javax.swing.JPanel pnEditing;
    private javax.swing.JTextArea taDescrizione;
    // End of variables declaration//GEN-END:variables


    public void PazienteSelected( Paziente paziente ){
        System.out.println("AnamnesiRemotePanel->PazienteSelected: "+ (paziente==null? "null" : paziente.getCognome()));
        this.Init();
    }
    
    class DateComparator implements Comparator{
        public int compare(Object obj1, Object obj2){
            //parameter are of type Object, so we have to downcast it to Employee objects
            Date dt1 = ( (AnamnesiRemota) obj1 ).get_Note().get_Data();
            Date dt2 = ( (AnamnesiRemota) obj2 ).get_Note().get_Data();
            //uses compareTo method of String class to compare names of the employee
            return dt1.compareTo(dt2);
        }
    }

    public void Init(){
        this._items = new SortedList( this._presenter.getItems(), new DateComparator());
        gridConfig(new AnamnesiRemotaAdvancedTableFormat());

        this._presenter.Load();

        this.addTabLisntener();

        bindUI();
    }



    @Override
    protected JTable getTable(){
        return this.jTable1;
    }

    @Override
    protected void bindUI(){
        AnamnesiRemota item = this._presenter.getSelected();
        if(item==null)
            item = this._presenter.getNewItem();

        System.out.println("bindUI -> selItem:"+item.get_Key().toString());
        if(item==null) return;
        boolean bBind = true;
        if(this._isDirty){
            int i = JOptionPane.showConfirmDialog(null,"Vuoi sovrascrivere i dati nel form con la nuova selezione?","Dati non salvati", JOptionPane.YES_NO_OPTION);
            bBind = (i==0);
        }
        if(bBind){
            this.taDescrizione.setText((this._presenter.getSelected()!=null)? item.get_Note().get_Descrizione() : "");
            this.dcData.setDate((this._presenter.getSelected()!=null)? item.get_Note().get_Data() : null);

            this.cbTipoAnamnesi.setSelectedIndex(-1);
            if(this._presenter.getSelected()!=null)
            for(TipoAnamnesi i : this._anamnesi){
                if(i.get_Key().equals(this._presenter.getSelected().get_Tipo().get_Key())){
                    this.cbTipoAnamnesi.setSelectedItem(i);
                    break;
                }
            }

            this._isDirty = false;
            System.out.println("bindUI done");
        }

    }

    @Override
    protected boolean bindToPresenter(){
        AnamnesiRemota itemTmp = new AnamnesiRemota(0
                , new josteo.model.paziente.Note(this.taDescrizione.getText(),this.dcData.getDate())
                , (TipoAnamnesi)this.cbTipoAnamnesi.getSelectedItem());
        List<BrokenRule> brokenRules = itemTmp.GetBrokenRules();
        if(brokenRules.size()>0){
            List<String> errors = new ArrayList<String>();
            for(BrokenRule br : brokenRules)
                errors.add(br.get_Message());

            JOptionPane.showMessageDialog(this, josteo.infrastructure.helpers.StringHelper.join(errors,"\n"));
            return false;
        }


        AnamnesiRemota item = this._presenter.getSelected()==null? this._presenter.getNewItem() : this._presenter.getSelected();
        System.out.println("bindToPresenter:" + item.get_Key());
        item.set_Tipo(itemTmp.get_Tipo());
        item.set_Note(itemTmp.get_Note());
        return true;
    }
}
