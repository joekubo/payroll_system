
package payroll_system;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.sql.*;
import javax.swing.*;
public class MainInternalFrame extends javax.swing.JInternalFrame {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        GridBagLayout layout = new GridBagLayout();
        PanelCreateUser createuser;
        PanelEmployees employees;
        PanelCompany company;
        PanelEvaluation evaluation;
        BankAccountPanel bankaccount;
        SettingsPanel settings;
        ExportPanel export;
        PanelBenefitNames benefitsname;
        PanelDepartment_Designation department_designation;
        
    public MainInternalFrame() {
        initComponents();
        conn = javaconnect.ConnecrDb();
        DynamicPanel.setLayout(layout);
        createuser = new PanelCreateUser();
        employees = new PanelEmployees();
        company = new PanelCompany();
        evaluation = new PanelEvaluation();
        bankaccount = new BankAccountPanel();
        settings = new SettingsPanel();
        export = new ExportPanel();
        benefitsname = new PanelBenefitNames();
        department_designation = new PanelDepartment_Designation();
        fillCombo();
        
        GridBagConstraints c = new  GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        DynamicPanel.add(createuser,c);
        DynamicPanel.add(employees,c);
        DynamicPanel.add(company,c);
        DynamicPanel.add(evaluation,c);
        DynamicPanel.add(bankaccount,c);
        DynamicPanel.add(settings,c);
        DynamicPanel.add(export, c);
        DynamicPanel.add(benefitsname, c);
        DynamicPanel.add(department_designation, c);
        
        createuser.setVisible(false);
        employees.setVisible(false);
        company.setVisible(false);
        evaluation.setVisible(false);
        bankaccount.setVisible(false);
        settings.setVisible(false);
        export.setVisible(false);
        benefitsname.setVisible(false);
        department_designation.setVisible(false);
    }
    private void showDialogEmailSettings(){
        DialogEmailSettings.setVisible(true);
        DialogEmailSettings.setSize(400,270);
        DialogEmailSettings.setResizable(false);
        DialogEmailSettings.setLocationRelativeTo(null);
        DialogEmailSettings.setAlwaysOnTop(true);
        loading();
    }
    private void loading(){
        try{
            String sql = "SELECT * FROM emailsettingstable";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if(rs.next()){
               String Id = rs.getString("id");
               txtId.setText(Id);
               
               String smtpserver = rs.getString("smtpServer");
               txtSmtpServer.setText(smtpserver);
               
               String port = rs.getString("smtpPort");
               txtSmtpPort.setText(port);
               
               String username = rs.getString("username");
               comboUsername.setSelectedItem(username);
               
               String password = rs.getString("password");
               txtPassword.setText(password);
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
   
    
    
    private void fillCombo(){
        try{
            String sql = "SELECT * FROM companytable";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
             while(rs.next()){
                 String username = rs.getString("email");
                 comboUsername.addItem(username);
             }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }finally{
            try{
                rs.close();
                pst.close();
            }catch(Exception e){
                
            }
        }
    }
    private void update(){
        try{
            String Id = txtId.getText();
            String smtpserver = txtSmtpServer.getText();
            String port = txtSmtpPort.getText();
            String Username = comboUsername.getSelectedItem().toString();
            String Password = txtPassword.getText();
            
            String sql = "UPDATE emailsettingstable SET smtpServer = '"+smtpserver+"',smtpPort = '"+port+"',username = '"+Username+"',password = '"+Password+"'WHERE id = '"+Id+"' ";
            pst = conn.prepareStatement(sql);
            pst.execute(); 
              
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }finally{
            try{
                rs.close();
                pst.close();
            }catch(Exception e){
                
            }
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        DialogEmailSettings = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtSmtpPort = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtSmtpServer = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        buttonExit = new javax.swing.JButton();
        buttonUpdate = new javax.swing.JButton();
        comboUsername = new javax.swing.JComboBox();
        txtPassword = new javax.swing.JPasswordField();
        txtId = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        DynamicPanel = new javax.swing.JPanel();

        jPanel2.setBackground(java.awt.Color.white);

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel2.setForeground(java.awt.Color.red);
        jLabel2.setText("smtp port:");

        jLabel3.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel3.setForeground(java.awt.Color.red);
        jLabel3.setText("Username:");

        jLabel4.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel4.setForeground(java.awt.Color.red);
        jLabel4.setText("Password:");

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel1.setForeground(java.awt.Color.red);
        jLabel1.setText("smtp server:");

        buttonExit.setText("Exit");
        buttonExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonExitActionPerformed(evt);
            }
        });

        buttonUpdate.setText("Update");
        buttonUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonUpdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonExit, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonUpdate)
                    .addComponent(buttonExit))
                .addContainerGap())
        );

        comboUsername.setEditable(true);

        txtPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPasswordActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtSmtpServer, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtSmtpPort, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(comboUsername, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 44, Short.MAX_VALUE)))
                                .addContainerGap())))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtSmtpServer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtSmtpPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(comboUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtId.setText("jTextField1");

        javax.swing.GroupLayout DialogEmailSettingsLayout = new javax.swing.GroupLayout(DialogEmailSettings.getContentPane());
        DialogEmailSettings.getContentPane().setLayout(DialogEmailSettingsLayout);
        DialogEmailSettingsLayout.setHorizontalGroup(
            DialogEmailSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DialogEmailSettingsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(DialogEmailSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(DialogEmailSettingsLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        DialogEmailSettingsLayout.setVerticalGroup(
            DialogEmailSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DialogEmailSettingsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(DialogEmailSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(DialogEmailSettingsLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        setClosable(true);

        jPanel1.setBackground(java.awt.Color.white);
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.white, java.awt.Color.blue));

        jButton7.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jButton7.setForeground(java.awt.Color.blue);
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/payroll_system/Office-Customer-Male-Light-icon32.png"))); // NOI18N
        jButton7.setText("Create User");
        jButton7.setFocusable(false);
        jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jButton4.setForeground(java.awt.Color.blue);
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/payroll_system/images~1.png"))); // NOI18N
        jButton4.setText("Company Details");
        jButton4.setFocusable(false);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jButton5.setForeground(java.awt.Color.blue);
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/payroll_system/Icon three.png"))); // NOI18N
        jButton5.setText("Employees");
        jButton5.setFocusable(false);
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jButton6.setForeground(java.awt.Color.blue);
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/payroll_system/c5eea9b377ba.png"))); // NOI18N
        jButton6.setText("Evaluation");
        jButton6.setFocusable(false);
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton8.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jButton8.setForeground(java.awt.Color.blue);
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/payroll_system/document-icon48.png"))); // NOI18N
        jButton8.setText("Reports/Export");
        jButton8.setFocusable(false);
        jButton8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jButton9.setForeground(java.awt.Color.blue);
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/payroll_system/tool-box-preferences-icon48.png"))); // NOI18N
        jButton9.setText("Settings");
        jButton9.setFocusable(false);
        jButton9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton9.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jButton1.setForeground(java.awt.Color.blue);
        jButton1.setText("Bank Accounts");
        jButton1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jButton2.setForeground(java.awt.Color.blue);
        jButton2.setText("EmailSettings");
        jButton2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton10.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jButton10.setForeground(java.awt.Color.blue);
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/payroll_system/Money Bag Pound-48.png"))); // NOI18N
        jButton10.setText("Benefit Type");
        jButton10.setFocusable(false);
        jButton10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton10.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jButton3.setForeground(java.awt.Color.blue);
        jButton3.setText("DEPT/DESGNATION");
        jButton3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 132, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addContainerGap(89, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.LINE_START);

        DynamicPanel.setBackground(java.awt.Color.white);
        DynamicPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.white, java.awt.Color.blue));

        javax.swing.GroupLayout DynamicPanelLayout = new javax.swing.GroupLayout(DynamicPanel);
        DynamicPanel.setLayout(DynamicPanelLayout);
        DynamicPanelLayout.setHorizontalGroup(
            DynamicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        DynamicPanelLayout.setVerticalGroup(
            DynamicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        getContentPane().add(DynamicPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        createuser.setVisible(true);
        employees.setVisible(false);
        company.setVisible(false);
        evaluation.setVisible(false);
        bankaccount.setVisible(false);
        settings.setVisible(false);
        export.setVisible(false);
        benefitsname.setVisible(false);
        department_designation.setVisible(false);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
       createuser.setVisible(false);
       employees.setVisible(false);
       company.setVisible(true);
       evaluation.setVisible(false);
       bankaccount.setVisible(false);
       settings.setVisible(false);
       export.setVisible(false);
       benefitsname.setVisible(false);
       department_designation.setVisible(false);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
       createuser.setVisible(false);
       employees.setVisible(true);
       company.setVisible(false);
       evaluation.setVisible(false);
       bankaccount.setVisible(false);
       settings.setVisible(false);
       export.setVisible(false);
       benefitsname.setVisible(false);
       department_designation.setVisible(false);
       
        employees.reset();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
       createuser.setVisible(false);
       employees.setVisible(false);
       company.setVisible(false);
       evaluation.setVisible(true);
       bankaccount.setVisible(false);
       settings.setVisible(false);
       evaluation.Update_TablePayment();
       export.setVisible(false);
       evaluation.jPanel9.setEnabled(false);
       benefitsname.setVisible(false);
       department_designation.setVisible(false);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        createuser.setVisible(false);
        employees.setVisible(false);
        company.setVisible(false);
        evaluation.setVisible(false);
        bankaccount.setVisible(true);
        settings.setVisible(false);
        export.setVisible(false);
        benefitsname.setVisible(false);
        department_designation.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed
    public void priviledgeUsers(){
        jButton2.setVisible(false);
    }
    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
       createuser.setVisible(false);
       employees.setVisible(false);
       company.setVisible(false);
       evaluation.setVisible(false);
       bankaccount.setVisible(false);
       settings.setVisible(true);
       export.setVisible(false);
       benefitsname.setVisible(false);
       department_designation.setVisible(false);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        createuser.setVisible(false);
        employees.setVisible(false);
        company.setVisible(false);
        evaluation.setVisible(false);
        bankaccount.setVisible(false);
        settings.setVisible(false);
        export.setVisible(true);
        benefitsname.setVisible(false);
        export.Update_tableEmployees2();
        export.Update_tableKra();
        export.comboBankname.removeAllItems();
        export.comboBankname.addItem("Select Bank");
        export.fillComboBankname();
        department_designation.setVisible(false);
        export.update_tableloans();
        export.comboEmployees.removeAllItems();
        export.comboEmployees.addItem("Select");
        export.fillcomboloans();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        showDialogEmailSettings();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void buttonExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExitActionPerformed
        DialogEmailSettings.dispose();
    }//GEN-LAST:event_buttonExitActionPerformed

    private void buttonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUpdateActionPerformed
        update();
    }//GEN-LAST:event_buttonUpdateActionPerformed

    private void txtPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPasswordActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        createuser.setVisible(false);
        employees.setVisible(false);
        company.setVisible(false);
        evaluation.setVisible(false);
        bankaccount.setVisible(false);
        settings.setVisible(false);
        export.setVisible(false);
        benefitsname.setVisible(true);
        department_designation.setVisible(false);
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        createuser.setVisible(false);
        employees.setVisible(false);
        company.setVisible(false);
        evaluation.setVisible(false);
        bankaccount.setVisible(false);
        settings.setVisible(false);
        export.setVisible(false);
        benefitsname.setVisible(false);
        department_designation.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog DialogEmailSettings;
    private javax.swing.JPanel DynamicPanel;
    private javax.swing.JButton buttonExit;
    private javax.swing.JButton buttonUpdate;
    private javax.swing.JComboBox comboUsername;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    public javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField txtId;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtSmtpPort;
    private javax.swing.JTextField txtSmtpServer;
    // End of variables declaration//GEN-END:variables
}
