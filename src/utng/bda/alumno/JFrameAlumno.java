/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utng.bda.alumno;
 import com.db4o.Db4o;
 import com.db4o.ObjectContainer;
 import com.db4o.ObjectSet;
 import javax.swing.JOptionPane;
 import javax.swing.table.DefaultTableModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
 import javax.swing.JComboBox;
 
/**
 *
 * @author benru
 */
public class JFrameAlumno extends javax.swing.JFrame {
    DefaultTableModel modelo;
    List<Integer> ids;
    int id = 1;
   
    /**
     * Creates new form JFrameAlumno
     */
    
    
    
    public JFrameAlumno() {
        initComponents();
        
        this.setLocationRelativeTo(null);
        //JFrameAlumno Carrera = new JFrameAlumno();
        //jCcarrera = new JComboBox();
        //jCcarrera.addItem("Administracion");
        //jCcarrera.addItem("Redes");
        //jCcarrera.addItem("Sistema Informaticos");
        //Carrera.add(jCcarrera);
        //add(Carrera, Alumno.class);

        conexion = new Conexion();
        alumno = new Alumno();
        modelo = listarAlumnos();
        this.jTableAlum.setModel(modelo);
        
        txtIdAlumno.enable(false);
        txtIdAlumno.setText("" + id);
     //   cargarDatos();
    }
    
    
   /*void cargarDatos(String valor){
         this.txtidAlumno.setText(Integer.toString(alum.getIdAlumno()));
        this.txtnombreAlum.setText(alum.getNombreAlum());
        this.txtapPaterno.setText(alum.getApPaterno());
        this.txtapMaterno.setText(alum.getApMaterno());
        this.txtsexo.setText(alum.getSexo());
        this.txtfecNacimiento.setText(alum.getFecNacimiento());
        this.txtcalif1.setText(Double.toString(alum.getCalif1()));
        this.txtcalif2.setText(Double.toString(alum.getCalif2()));
        this.txtcalif3.setText(Double.toString(alum.getCalif3()));
        this.txtcalif4.setText(Double.toString(alum.getCalif4()));
        this.txtcalif5.setText(Double.toString(alum.getCalif5()));
        this.txtcuatrimestre.setText(Integer.toString(alum.getCuatrimestre()));
    }*/
    /*public  void  buscar(String bus){
       while(modelo.getRowCount()>0){
           if(!bus.equals(modelo.getValueAt(0, 0))){
              modelo.removeRow(0);  
           }
           
       }
        String []Datos = new String[13];
        Datos[0]=txtidAlumno.getText();    //insertar 
        txtidAlumno.setText(null);        ///al tiempo de insertar los campos se queden vacios   
        Datos[1]=txtnombreAlum.getText();
        txtnombreAlum.setText(null);
        Datos[2]=txtapPaterno.getText();
        txtapPaterno.setText(null);
        Datos[3]=txtapMaterno.getText();
        txtapMaterno.setText(null);
        Datos[4]=txtsexo.getText();
        txtsexo.setText(null);
        Datos[5]=txtfecNacimiento.getText();
        txtfecNacimiento.setText(null);
        Datos[6]=txtcalif1.getText();
        txtcalif1.setText(null);
        Datos[7]=txtcalif2.getText();
        txtcalif2.setText(null);
        Datos[8]=txtcalif3.getText();
        txtcalif3.setText(null);
        Datos[9]=txtcalif4.getText();
        txtcalif4.setText(null);
        Datos[10]=txtcalif5.getText();
        txtcalif5.setText(null);
        Datos[11]=txtcuatrimestre.getText();
        txtcuatrimestre.setText(null);
        Datos[12]= this.jCcarrera.getSelectedItem().toString();
        modelo.addRow(Datos);  
    }*/
  
    
   
    public boolean fillFromForm(){
        if((((((((((((!txtIdAlumno.getText().equals("") && !txtnombreAlum.getText().equals("")) && !txtapPaterno.getText().equals("")) && !txtapMaterno.getText().equals("")) && !txtfecNacimiento.getText().equals("")) && !txtcalif1.getText().equals("")) && !txtcalif2.getText().equals("")) && !txtcalif3.getText().equals("")) && !txtcalif3.getText().equals("")) && !txtcalif4.getText().equals("")) && !txtcalif5.getText().equals("")) && !txtcuatrimestre.getText().equals(""))  && !jCcarrera.getSelectedItem().equals("")){
            alumno.setIdAlumno(Integer.parseInt(txtIdAlumno.getText()));
            alumno.setNombreAlum(txtnombreAlum.getText());
            alumno.setApPaterno(txtapPaterno.getText());
            alumno.setApMaterno(txtapMaterno.getText());
            alumno.setSexo(txtsexo.getText());
            alumno.setFecNacimiento(txtfecNacimiento.getText());
            alumno.setCalif1(Double.parseDouble(txtcalif1.getText()));
            alumno.setCalif2(Double.parseDouble(txtcalif2.getText()));
            alumno.setCalif3(Double.parseDouble(txtcalif3.getText()));
            alumno.setCalif4(Double.parseDouble(txtcalif4.getText()));
            alumno.setCalif5(Double.parseDouble(txtcalif5.getText()));
            alumno.setCuatrimestre(Integer.parseInt(txtcuatrimestre.getText()));
            this.jCcarrera.setSelectedIndex(0);
            return true;
        }else {
            JOptionPane.showMessageDialog(null, "error", "ERROR", JOptionPane.ERROR_MESSAGE);  //faltaron algunos campos de llenar
            return false;
        }
   
    } 
    
    private DefaultTableModel listarAlumnos() {
    	
        modelo = new DefaultTableModel();
        modelo.addColumn("Clave");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido Paterno");
        modelo.addColumn("Apellido Materno");
        modelo.addColumn("Sexo");
        modelo.addColumn("Fecha de nacimiento");
        modelo.addColumn("Calificacion 1");
        modelo.addColumn("Calificacion 2");
        modelo.addColumn("Calificacion 3");
        modelo.addColumn("Calificacion 4");
        modelo.addColumn("Calificacion 5");
        modelo.addColumn("Cuatrimestre");
        modelo.addColumn("Carrera");
        
        Alumno[] p = conexion.Consultar();
        if (p != null) {
        	ids.clear();
            for (Alumno per : p) {
                Object[] cli = new Object[15];
                cli[0] = per.getIdAlumno();
                ids.add(per.getIdAlumno());
                cli[1] = per.getNombreAlum();
                cli[2] = per.getApPaterno();
                cli[3] = per.getApMaterno();
                cli[4] = per.getSexo();
                cli[5] = per.getFecNacimiento();
                cli[6] = per.getCalif1();
                cli[7] = per.getCalif2();
                cli[8] = per.getCalif3();
                cli[9] = per.getCalif4();
                cli[10] = per.getCalif5();
                cli[11] = per.getCuatrimestre();
                cli[12] = per.getCarrera();
                modelo.addRow(cli);
            }
        }
        this.id = Collections.max(ids)+1;
        return modelo;
    } 
    
    private void limpiar(String mensaje) {
    	 txtIdAlumno.setText(""+id);        ///al tiempo de insertar los campos se queden vacios   
         //Datos[1]=txtnombreAlum.getText();
         txtnombreAlum.setText("");
         //Datos[2]=txtapPaterno.getText();
         txtapPaterno.setText("");
         //Datos[3]=txtapMaterno.getText();
         txtapMaterno.setText("");
         //Datos[4]=txtsexo.getText();
         txtsexo.setText("");
         //Datos[5]=txtfecNacimiento.getText();
         txtfecNacimiento.setText("");
         //Datos[6]=txtcalif1.getText();
         txtcalif1.setText("");
         //Datos[7]=txtcalif2.getText();
         txtcalif2.setText("");
         //Datos[8]=txtcalif3.getText();
         txtcalif3.setText("");
         //Datos[9]=txtcalif4.getText();
         txtcalif4.setText("");
         //Datos[10]=txtcalif5.getText();
         txtcalif5.setText("");
         //Datos[11]=txtcuatrimestre.getText();
         txtcuatrimestre.setText("");
         //Datos[12]= this.jCcarrera.getSelectedItem().toString();
         //modelo.addRow(Datos);
         //alumno = null;
         modelo = listarAlumnos();
         
         this.jTableAlum.setModel(modelo);
         if(!mensaje.equals(""))
         JOptionPane.showMessageDialog(this, mensaje); //cuando se hace el registro

    }
    
    

    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    
 
    
        
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
    	ids = new ArrayList<Integer>();
        label1 = new java.awt.Label();
        txtIdAlumno = new javax.swing.JTextField();
        txtnombreAlum = new javax.swing.JTextField();
        txtapPaterno = new javax.swing.JTextField();
        txtapMaterno = new javax.swing.JTextField();
        txtsexo = new javax.swing.JTextField();
        txtfecNacimiento = new javax.swing.JTextField();
        txtcalif1 = new javax.swing.JTextField();
        txtcalif2 = new javax.swing.JTextField();
        txtcalif3 = new javax.swing.JTextField();
        txtcalif4 = new javax.swing.JTextField();
        txtcalif5 = new javax.swing.JTextField();
        txtcuatrimestre = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jCcarrera = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableAlum = new javax.swing.JTable();
        btnInsertar = new javax.swing.JButton();
        btnConsultar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jlbCrudAlumno = new javax.swing.JLabel();
        txtConsultar = new javax.swing.JTextField();
        btnMostrarTodo = new javax.swing.JButton();

        label1.setText("label1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtIdAlumno.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txtIdAlumno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidAlumnoActionPerformed(evt);
            }
        });
        getContentPane().add(txtIdAlumno, new org.netbeans.lib.awtextra.AbsoluteConstraints(246, 36, 164, -1));

        txtnombreAlum.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(txtnombreAlum, new org.netbeans.lib.awtextra.AbsoluteConstraints(246, 74, 164, -1));

        txtapPaterno.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(txtapPaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(246, 108, 164, -1));

        txtapMaterno.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txtapMaterno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtapMaternoActionPerformed(evt);
            }
        });
        getContentPane().add(txtapMaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(246, 147, 164, -1));

        txtsexo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txtsexo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsexoActionPerformed(evt);
            }
        });
        getContentPane().add(txtsexo, new org.netbeans.lib.awtextra.AbsoluteConstraints(246, 185, 164, -1));

        txtfecNacimiento.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(txtfecNacimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(246, 227, 164, -1));

        txtcalif1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(txtcalif1, new org.netbeans.lib.awtextra.AbsoluteConstraints(246, 271, 164, -1));

        txtcalif2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(txtcalif2, new org.netbeans.lib.awtextra.AbsoluteConstraints(246, 312, 164, -1));

        txtcalif3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(txtcalif3, new org.netbeans.lib.awtextra.AbsoluteConstraints(246, 346, 164, -1));

        txtcalif4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(txtcalif4, new org.netbeans.lib.awtextra.AbsoluteConstraints(246, 377, 164, -1));

        txtcalif5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(txtcalif5, new org.netbeans.lib.awtextra.AbsoluteConstraints(246, 408, 163, -1));

        txtcuatrimestre.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(txtcuatrimestre, new org.netbeans.lib.awtextra.AbsoluteConstraints(246, 434, 164, -1));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel1.setText("Clave");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(84, 39, -1, -1));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel2.setText("Nombre ");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(84, 72, -1, -1));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel3.setText("Apellido Paterno");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(84, 105, -1, -1));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel4.setText("Apellido Materno");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(84, 147, -1, -1));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel5.setText("Sexo");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(84, 187, -1, -1));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel6.setText("Fecha de Nacimiento");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(84, 227, -1, -1));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel7.setText("Calificación 1");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(84, 268, -1, -1));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel8.setText("Calificación 2");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(84, 309, -1, -1));

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel9.setText("Calificación 3");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(84, 343, -1, -1));

        jLabel10.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel10.setText("Calificación 4");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(84, 372, 122, 27));

        jLabel11.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel11.setText("Calificación 5");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(84, 405, -1, -1));

        jLabel12.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel12.setText("Cuatrimestre");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(84, 433, -1, -1));

        jLabel13.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel13.setText("Carrera");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(84, 462, -1, -1));

        jCcarrera.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "REdes" }));
        jCcarrera.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jCcarrera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCcarreraActionPerformed(evt);
            }
        });
        getContentPane().add(jCcarrera, new org.netbeans.lib.awtextra.AbsoluteConstraints(246, 461, 164, 29));

        jTableAlum.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTableAlum.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTableAlum.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableAlumMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableAlum);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 512, 691, 110));

        btnInsertar.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnInsertar.setText("Insertar");
        btnInsertar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnInsertar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertarActionPerformed(evt);
            }
        });
        getContentPane().add(btnInsertar, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 190, 121, -1));

        btnConsultar.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnConsultar.setText("Consultar");
        btnConsultar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarActionPerformed(evt);
            }
        });
        getContentPane().add(btnConsultar, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 480, 121, -1));

        btnActualizar.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnActualizar.setText("Actualizar");
        btnActualizar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        getContentPane().add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 240, 121, -1));

        btnEliminar.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 300, 121, -1));

        jLabel14.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel14.setText("Registro");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 490, 120, 23));

        jlbCrudAlumno.setFont(new java.awt.Font("Times New Roman", 0, 36)); // NOI18N
        jlbCrudAlumno.setText("CRUD ALUMNO");
        getContentPane().add(jlbCrudAlumno, new org.netbeans.lib.awtextra.AbsoluteConstraints(194, 0, 404, 30));

        txtConsultar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txtConsultar.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtConsultarCaretUpdate(evt);
            }
        });
        txtConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtConsultarActionPerformed(evt);
            }
        });
        getContentPane().add(txtConsultar, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 460, 120, 20));

        btnMostrarTodo.setText("Mostrar todo");
        btnMostrarTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarTodoActionPerformed(evt);
            }
        });
        getContentPane().add(btnMostrarTodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 480, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents
        
    private void jCcarreraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCcarreraActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jCcarreraActionPerformed

    private void txtsexoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsexoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsexoActionPerformed

    private void txtapMaternoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtapMaternoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtapMaternoActionPerformed

    private void btnInsertarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertarActionPerformed
        // TODO add your handling code here:
        if (!fillFromForm()){
            return;
        }
        if(conexion.insertar(alumno)){
           limpiar("Insertado correctamente");
        } 
    }//GEN-LAST:event_btnInsertarActionPerformed
    
    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
      // String texto = txtConsultar.getText();
      //  buscar(texto);
      //  txtConsultar.setText("");
    
   
        
    
        
        
    }//GEN-LAST:event_btnConsultarActionPerformed
   
    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        // TODO add your handling code here:
        if (!fillFromForm()){
            return;
        }
       
        if(conexion.actualizar(original, alumno)){ 
           limpiar("Actualizado correctamente");
       }
        
        
    }//GEN-LAST:event_btnActualizarActionPerformed
        
    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
    	if (!fillFromForm()){
            return;
        }
    	if(conexion.eliminar(alumno)){
            limpiar("Eliminado correctamente");

        }
       }
             //no se inserta porque fallo algo

    private void jTableAlumMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableAlumMouseClicked
         // TODO add your handling code here:
        int filaselecionada = jTableAlum.getSelectedRow();
        txtIdAlumno.setText(jTableAlum.getValueAt(filaselecionada, 0).toString());
        txtnombreAlum.setText(jTableAlum.getValueAt(filaselecionada, 1).toString());
        txtapPaterno.setText(jTableAlum.getValueAt(filaselecionada, 2).toString());
        txtapMaterno.setText(jTableAlum.getValueAt(filaselecionada, 3).toString());
        txtsexo.setText(jTableAlum.getValueAt(filaselecionada, 4).toString());
        txtfecNacimiento.setText(jTableAlum.getValueAt(filaselecionada, 5).toString());
        txtcalif1.setText(jTableAlum.getValueAt(filaselecionada, 6).toString());
        txtcalif2.setText(jTableAlum.getValueAt(filaselecionada, 7).toString());
        txtcalif3.setText(jTableAlum.getValueAt(filaselecionada, 8).toString());
        txtcalif4.setText(jTableAlum.getValueAt(filaselecionada, 9).toString());
        txtcalif5.setText(jTableAlum.getValueAt(filaselecionada, 10).toString());
        txtcuatrimestre.setText(jTableAlum.getValueAt(filaselecionada, 11).toString());
        original = new Alumno(
        		Integer.parseInt(txtIdAlumno.getText()),
        		txtnombreAlum.getText(),
        		txtapPaterno.getText(),
        		txtapMaterno.getText(), 
        		txtsexo.getText(), 
        		txtfecNacimiento.getText(),
        		Double.parseDouble(txtcalif1.getText()), 
        		Double.parseDouble(txtcalif2.getText()),
        		Double.parseDouble(txtcalif3.getText()),
        		Double.parseDouble(txtcalif4.getText()),
        		Double.parseDouble(txtcalif5.getText()), 
        		Integer.parseInt(txtcuatrimestre.getText()),
        		null);
        //jCcarrera.setSelectedItem(jTableAlum.getValueAt(filaselecionada, 12).toString());
        //txtidAlumno.enable(true);
        
    }//GEN-LAST:event_jTableAlumMouseClicked

    private void txtidAlumnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidAlumnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidAlumnoActionPerformed

    private void txtConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtConsultarActionPerformed
        
    }//GEN-LAST:event_txtConsultarActionPerformed

    private void txtConsultarCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtConsultarCaretUpdate
       //* String valor = txtConsultar.getText();
      //  if(valor.isEmpty()){
       //     jTableAlum.clearSelection();
        //}else{
          //  for(int i=0; 1<jTableAlum.getRowCount(); i++){
            //    if(jTableAlum.getValueAt(i, 0).equals(valor)){
              //      jTableAlum.changeSelection(i, 0, false, false);
                    
    //            }
      //      }
       // }
            
        
    }//GEN-LAST:event_txtConsultarCaretUpdate

    private void btnMostrarTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarTodoActionPerformed
        
    }//GEN-LAST:event_btnMostrarTodoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFrameAlumno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrameAlumno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrameAlumno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrameAlumno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFrameAlumno().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnInsertar;
    private javax.swing.JButton btnMostrarTodo;
    private javax.swing.JComboBox jCcarrera;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableAlum;
    private javax.swing.JLabel jlbCrudAlumno;
    private java.awt.Label label1;
    private javax.swing.JTextField txtConsultar;
    private javax.swing.JTextField txtapMaterno;
    private javax.swing.JTextField txtapPaterno;
    private javax.swing.JTextField txtcalif1;
    private javax.swing.JTextField txtcalif2;
    private javax.swing.JTextField txtcalif3;
    private javax.swing.JTextField txtcalif4;
    private javax.swing.JTextField txtcalif5;
    private javax.swing.JTextField txtcuatrimestre;
    private javax.swing.JTextField txtfecNacimiento;
    private javax.swing.JTextField txtIdAlumno;
    private javax.swing.JTextField txtnombreAlum;
    private javax.swing.JTextField txtsexo;
    // End of variables declaration//GEN-END:variables
    private Conexion conexion;
    private Alumno alumno;
    private Alumno original;
    
}
