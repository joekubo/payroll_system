
package payroll_system;

import java.awt.event.KeyEvent;
import javax.swing.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;

public class PanelDepartment_Designation extends javax.swing.JPanel {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
    public PanelDepartment_Designation() {
        initComponents();
        conn = javaconnect.ConnecrDb();
        update_tableDepartment();
        update_tableDesignation();
        resetDepartment();
        resetDesignation();
        jPanel3.setVisible(false);
    }
    //--------------------------------Department -----------------------------------------------------------
    private void resetDepartment(){
        txtDepartment.setText("");
        txtDepartment.requestFocus();
        buttonSaveDepartment.setEnabled(true);
        buttonUpdateDepartment.setEnabled(false);
        buttonDeleteDepartment.setEnabled(false);
        jPanel3.setVisible(false);
    }
    private void displayPreviousDepartmentId(){
        try{ 
            String sql = "SELECT id FROM departmenttable ORDER BY id DESC";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if(rs.next()){
                String Id = rs.getString("id");
                txtDepartmentId.setText(Id);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }finally{
            try{
                rs.close();
                pst.close();
            }catch(Exception e){
                
            }
        }
    }
    private void update_tableDepartment(){
        try{
            String sql = "SELECT id AS '', department AS 'Department' FROM departmenttable WHERE status = '1'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            tableDepartment.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }finally{
            try{
                rs.close();
                pst.close();
            }catch(Exception e){
                
            }
        }
    }
    private void selectedRowDepartment(){
        try{
            int row = tableDepartment.getSelectedRow();
            String table_click = tableDepartment.getValueAt(row, 0).toString();
            
            String sql = "SELECT * FROM departmenttable WHERE id = '"+table_click+"'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if(rs.next()){
                String Id = rs.getString("id");
                txtDepartmentId.setText(Id);
                
                String Department = rs.getString("department");
                txtDepartment.setText(Department);
                
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }finally{
            try{
                rs.close();
                pst.close();
            }catch(Exception e){
                
            }
        }
         update_tableDesignation();
    }
    private void saveDepartment(){
        try{
            String Status = "1";
            if(txtDepartment.getText().equals("")){
                txtException.setText("Please Enter Department");
                txtDepartment.requestFocus();
            }else{
                String sql = "INSERT INTO departmenttable(department,status) VALUES (?,?)";
                pst = conn.prepareStatement(sql);
                
                pst.setString(1, txtDepartment.getText());
                pst.setString(2, Status);
                pst.execute();
                txtException.setText("***** SAVED *****");
                jPanel3.setVisible(true);
                //resetDepartment();
                displayPreviousDepartmentId();
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }finally{
            try{
                rs.close();
                pst.close();
            }catch(Exception e){
                
            }
        }
        update_tableDepartment();
    }
    private void updateDepartment(){
         try{
            String sql = "UPDATE departmenttable SET department = '"+txtDepartment.getText()+"' WHERE id = '"+txtDepartmentId.getText()+"'";
            pst = conn.prepareStatement(sql);
            pst.executeQuery();
            txtException.setText("***** UPDATED *****");
            resetDepartment();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }finally{
            try{
                rs.close();
                pst.close();
            }catch(Exception e){
                
            }
        }
        update_tableDepartment();
        
    }
    private void deleteDepartment(){
         try{
            String sql = "UPDATE departmenttable SET status = '0' WHERE id = '"+txtDepartmentId.getText()+"'";
            pst = conn.prepareStatement(sql);
            pst.execute();
            txtException.setText("***** DELETED *****");
            resetDepartment();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }finally{
            try{
                rs.close();
                pst.close();
            }catch(Exception e){
                
            }
        }
         try{
            String sql = "UPDATE designationtable SET status = '1' WHERE departmentId = '"+txtDepartmentId.getText()+"'";
            pst = conn.prepareStatement(sql);
            pst.execute();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }finally{
            try{
                rs.close();
                pst.close();
            }catch(Exception e){
                
            }
        }
        update_tableDepartment();
    }
    //-----------------------------------------Designation-------------------------------------------------------------------
     private void resetDesignation(){
        txtDesignation.setText("");
        txtDesignation.requestFocus();
        buttonSaveDesignation.setEnabled(true);
        buttonUpdateDesignation.setEnabled(false);
        buttonDeleteDesignation.setEnabled(false);
    }
    private void update_tableDesignation(){
        try{
            String sql = "SELECT id AS '', designation AS 'Designation' FROM designationtable WHERE departmentId = "
                    + "'"+txtDepartmentId.getText()+"' AND status = '1'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            tableDesignation.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }finally{
            try{
                rs.close();
                pst.close();
            }catch(Exception e){
                
            }
        }
    }
    private void selectedRowDesignation(){
        try{
            int row = tableDesignation.getSelectedRow();
            String table_click = tableDesignation.getValueAt(row,0).toString();
            
            String sql = "SELECT * FROM designationtable WHERE id = '"+table_click+"'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if(rs.next()){
                String Id = rs.getString("id");
                txtDesignationId.setText(Id);
                
                String DeptId = rs.getString("departmentId");
                txtDepartmentId.setText(DeptId);
                
                String Designation = rs.getString("designation");
                txtDesignation.setText(Designation);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }finally{
            try{
                rs.close();
                pst.close();
            }catch(Exception e){
                
            }
        }
    }
    private void saveDesignation(){
        try{
            String Status = "1";
            if(txtDesignation.getText().equals("")){
                txtException.setText("Please Enter Designation");
                txtDesignation.requestFocus();
            }else{
                String sql = "INSERT INTO designationtable(departmentId,designation,status) VALUES (?,?,?)";
                pst = conn.prepareStatement(sql);
                
                pst.setString(1, txtDepartmentId.getText());
                pst.setString(2, txtDesignation.getText());
                pst.setString(3, Status);
                pst.execute();
                txtException.setText("***** SAVED *****");
                resetDesignation();
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }finally{
            try{
                rs.close();
                pst.close();
            }catch(Exception e){
                
            }
        }
        update_tableDesignation();
    }
    private void updateDesignation(){
         try{
            String sql = "UPDATE designationtable SET designation = '"+txtDesignation.getText()+"' "
                    + "WHERE id = '"+txtDesignationId.getText()+"'";
            pst = conn.prepareStatement(sql);
            pst.executeQuery();
            txtException.setText("***** UPDATED *****");
            resetDesignation();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }finally{
            try{
                rs.close();
                pst.close();
            }catch(Exception e){
                
            }
        }
        update_tableDesignation();
        
    }
    private void deleteDesignation(){
         try{
            String sql = "UPDATE designationtable SET status = '0' WHERE id = '"+txtDesignationId.getText()+"'";
            pst = conn.prepareStatement(sql);
            pst.execute();
            txtException.setText("***** DELETED *****");
            resetDesignation();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }finally{
            try{
                rs.close();
                pst.close();
            }catch(Exception e){
                
            }
        }
        update_tableDesignation();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtDepartmentId = new javax.swing.JTextField();
        txtDesignationId = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtDepartment = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableDepartment = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        buttonAddDepartment = new javax.swing.JButton();
        buttonUpdateDepartment = new javax.swing.JButton();
        buttonSaveDepartment = new javax.swing.JButton();
        buttonDeleteDepartment = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtDesignation = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableDesignation = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        buttonAddDesignation = new javax.swing.JButton();
        buttonUpdateDesignation = new javax.swing.JButton();
        buttonSaveDesignation = new javax.swing.JButton();
        buttonDeleteDesignation = new javax.swing.JButton();
        txtException = new javax.swing.JLabel();

        setBackground(java.awt.Color.white);
        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Department & Designation Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 2, 12), java.awt.Color.blue)); // NOI18N

        jPanel1.setBackground(java.awt.Color.lightGray);
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Department", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 2, 12), java.awt.Color.blue)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel1.setForeground(java.awt.Color.blue);
        jLabel1.setText("Department:");

        txtDepartment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDepartmentActionPerformed(evt);
            }
        });
        txtDepartment.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDepartmentKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDepartmentKeyReleased(evt);
            }
        });

        tableDepartment.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tableDepartment.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableDepartmentMouseClicked(evt);
            }
        });
        tableDepartment.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tableDepartmentKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tableDepartment);

        jPanel2.setBackground(java.awt.Color.lightGray);
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, java.awt.Color.white));

        buttonAddDepartment.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        buttonAddDepartment.setText("ADD");
        buttonAddDepartment.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        buttonAddDepartment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddDepartmentActionPerformed(evt);
            }
        });

        buttonUpdateDepartment.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        buttonUpdateDepartment.setText("Update");
        buttonUpdateDepartment.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        buttonUpdateDepartment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonUpdateDepartmentActionPerformed(evt);
            }
        });

        buttonSaveDepartment.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        buttonSaveDepartment.setText("SAVE");
        buttonSaveDepartment.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        buttonSaveDepartment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSaveDepartmentActionPerformed(evt);
            }
        });

        buttonDeleteDepartment.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        buttonDeleteDepartment.setText("DELETE");
        buttonDeleteDepartment.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        buttonDeleteDepartment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDeleteDepartmentActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonAddDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonSaveDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonUpdateDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(buttonDeleteDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonSaveDepartment)
                    .addComponent(buttonAddDepartment)
                    .addComponent(buttonDeleteDepartment)
                    .addComponent(buttonUpdateDepartment))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jPanel3.setBackground(java.awt.Color.lightGray);
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Designation", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 2, 12), java.awt.Color.blue)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel2.setForeground(java.awt.Color.blue);
        jLabel2.setText("Designation:");

        txtDesignation.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDesignationKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDesignationKeyReleased(evt);
            }
        });

        tableDesignation.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tableDesignation.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableDesignationMouseClicked(evt);
            }
        });
        tableDesignation.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tableDesignationKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tableDesignation);

        jPanel4.setBackground(java.awt.Color.lightGray);
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, java.awt.Color.white));

        buttonAddDesignation.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        buttonAddDesignation.setText("ADD");
        buttonAddDesignation.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        buttonAddDesignation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddDesignationActionPerformed(evt);
            }
        });

        buttonUpdateDesignation.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        buttonUpdateDesignation.setText("Update");
        buttonUpdateDesignation.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        buttonUpdateDesignation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonUpdateDesignationActionPerformed(evt);
            }
        });

        buttonSaveDesignation.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        buttonSaveDesignation.setText("SAVE");
        buttonSaveDesignation.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        buttonSaveDesignation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSaveDesignationActionPerformed(evt);
            }
        });

        buttonDeleteDesignation.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        buttonDeleteDesignation.setText("DELETE");
        buttonDeleteDesignation.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        buttonDeleteDesignation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDeleteDesignationActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonAddDesignation, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonSaveDesignation, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonUpdateDesignation, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonDeleteDesignation, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonSaveDesignation)
                    .addComponent(buttonAddDesignation)
                    .addComponent(buttonDeleteDesignation)
                    .addComponent(buttonUpdateDesignation))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDesignation, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtDesignation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtException.setFont(new java.awt.Font("Noto Sans", 3, 12)); // NOI18N
        txtException.setForeground(java.awt.Color.red);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtException, javax.swing.GroupLayout.PREFERRED_SIZE, 608, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(166, 166, 166))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(txtException, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtDepartmentKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDepartmentKeyReleased
        txtDepartment.setText(txtDepartment.getText().toUpperCase());
    }//GEN-LAST:event_txtDepartmentKeyReleased

    private void txtDepartmentKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDepartmentKeyPressed
        txtException.setText("");
    }//GEN-LAST:event_txtDepartmentKeyPressed

    private void buttonAddDepartmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddDepartmentActionPerformed
        resetDepartment();
    }//GEN-LAST:event_buttonAddDepartmentActionPerformed

    private void buttonSaveDepartmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSaveDepartmentActionPerformed
        saveDepartment();  
    }//GEN-LAST:event_buttonSaveDepartmentActionPerformed

    private void buttonUpdateDepartmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUpdateDepartmentActionPerformed
     
       try{
           String sql = "SELECT * FROM employeeregistrationtable WHERE department = ?";
           pst = conn.prepareStatement(sql);
           pst.setString(1, txtDepartment.getText());
           
           rs = pst.executeQuery();
           if(rs.next()){
                txtException.setText("Cannot Update this record. It is bieng used somewhere else");
           }else{
               updateDepartment();
           }
       }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
       }finally{
           try{
               rs.close();
               pst.close();
           }catch(Exception e){
               
           }
       }
    }//GEN-LAST:event_buttonUpdateDepartmentActionPerformed

    private void buttonDeleteDepartmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDeleteDepartmentActionPerformed
        deleteDepartment();
    }//GEN-LAST:event_buttonDeleteDepartmentActionPerformed

    private void txtDesignationKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDesignationKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDesignationKeyPressed

    private void txtDesignationKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDesignationKeyReleased
        txtDesignation.setText(txtDesignation.getText().toUpperCase());
    }//GEN-LAST:event_txtDesignationKeyReleased

    private void buttonAddDesignationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddDesignationActionPerformed
        resetDesignation();
    }//GEN-LAST:event_buttonAddDesignationActionPerformed

    private void buttonUpdateDesignationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUpdateDesignationActionPerformed
        updateDesignation();
    }//GEN-LAST:event_buttonUpdateDesignationActionPerformed

    private void buttonSaveDesignationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSaveDesignationActionPerformed
        saveDesignation();
    }//GEN-LAST:event_buttonSaveDesignationActionPerformed

    private void buttonDeleteDesignationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDeleteDesignationActionPerformed
       deleteDesignation();
    }//GEN-LAST:event_buttonDeleteDesignationActionPerformed

    private void tableDepartmentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDepartmentMouseClicked
       selectedRowDepartment();
       buttonSaveDepartment.setEnabled(false);
       buttonUpdateDepartment.setEnabled(true);
       buttonDeleteDepartment.setEnabled(true);
       txtException.setText("");
       jPanel3.setVisible(true);
    }//GEN-LAST:event_tableDepartmentMouseClicked

    private void tableDepartmentKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableDepartmentKeyReleased
       if (evt.getKeyCode()==KeyEvent.VK_DOWN || evt.getKeyCode()==KeyEvent.VK_UP){
            selectedRowDepartment();
        }
    }//GEN-LAST:event_tableDepartmentKeyReleased

    private void tableDesignationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDesignationMouseClicked
        selectedRowDesignation();
        buttonSaveDesignation.setEnabled(false);
        buttonUpdateDesignation.setEnabled(true);
        buttonDeleteDesignation.setEnabled(true);
    }//GEN-LAST:event_tableDesignationMouseClicked

    private void tableDesignationKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableDesignationKeyReleased
        if (evt.getKeyCode()==KeyEvent.VK_DOWN || evt.getKeyCode()==KeyEvent.VK_UP){
            selectedRowDesignation();
        }
    }//GEN-LAST:event_tableDesignationKeyReleased

    private void txtDepartmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDepartmentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDepartmentActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAddDepartment;
    private javax.swing.JButton buttonAddDesignation;
    private javax.swing.JButton buttonDeleteDepartment;
    private javax.swing.JButton buttonDeleteDesignation;
    private javax.swing.JButton buttonSaveDepartment;
    private javax.swing.JButton buttonSaveDesignation;
    private javax.swing.JButton buttonUpdateDepartment;
    private javax.swing.JButton buttonUpdateDesignation;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tableDepartment;
    private javax.swing.JTable tableDesignation;
    private javax.swing.JTextField txtDepartment;
    private javax.swing.JTextField txtDepartmentId;
    private javax.swing.JTextField txtDesignation;
    private javax.swing.JTextField txtDesignationId;
    private javax.swing.JLabel txtException;
    // End of variables declaration//GEN-END:variables
}
