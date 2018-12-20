
package payroll_system;

import com.toedter.calendar.JCalendar;
import java.io.File;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class ExportPanel extends javax.swing.JPanel {
        Connection conn = null;
        PreparedStatement pst=null;
        ResultSet rs = null;
        DefaultTableModel dm = new DefaultTableModel();
        String attachment_path;
        private String staffno;
        
    public ExportPanel() {
        initComponents();
        conn = javaconnect.ConnecrDb();
        currentMonthNYear();
        monthNyear();
        loadingEmailSettings();
    }
    private void monthNyear(){
    JCalendar calendar = new JCalendar();
    String mymonth;
    SimpleDateFormat sdf1 = new SimpleDateFormat("MMMM");
    java.util.Date date1 = calendar.getDate();
    mymonth = sdf1.format(date1);
    comboMonth.setSelectedItem(mymonth);
   
    SimpleDateFormat sdf2 = new SimpleDateFormat("YYYY");
    java.util.Date date2 = calendar.getDate();
    mymonth = sdf2.format(date2);
    comboYear.setSelectedItem(mymonth);
    }
  //------------------------------------------------DialogEmployeesEmail-----------------------------------------------------------------
    private void showDialogEmployees(){
        DialogEmployees.setVisible(true);
        DialogEmployees.setSize(480,470);
        DialogEmployees.setLocationRelativeTo(null);
        DialogEmployees.setResizable(true);
        DialogEmployees.setAlwaysOnTop(true);
        update_tableEmployees();
    }
    public void loadingEmailSettings(){
        try{
            String sql = "SELECT * FROM emailsettingstable WHERE id = '1'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
                if(rs.next()){
                    String Port = rs.getString("smtpPort");
                    txtPort.setText(Port);
                    
                    String From = rs.getString("username");
                    txtFrom.setText(From);
                    
                    String Password = rs.getString("password");
                    txtPassword.setText(Password);
                    
                    String Server = rs.getString("smtpServer");
                    txtServer.setText(Server);
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
    public void fillComboBankname(){
        try{
            String sql = "SELECT * FROM banktable";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                String Bankname = rs.getString("bankname");
                comboBankname.addItem(Bankname);
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
    private void update_tableEmployees(){
        try{
            String sql = "SELECT staffNo AS 'Staff No', firstName||' '||lastName AS 'Name' FROM"
                    + " employeeregistrationtable WHERE status = 'active'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            tableEmail.setModel(DbUtils.resultSetToTableModel(rs));
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
    private void selectedRowEmail(){
        try{
            int row = tableEmail.getSelectedRow();
            String Table_click = tableEmail.getValueAt(row, 0).toString();
            String sql = "SELECT firstName||' '||lastName,email FROM employeeregistrationtable WHERE staffNo = '"+Table_click+"'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
                if(rs.next()){
                String Name = rs.getString("firstName||' '||lastName");
                txtEmployeeName.setText(Name);
                
                String Email = rs.getString("email");
                txtEmployeeEmail.setText(Email);
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
  //------------------------------------------------------------------------------------------------------------------------------------
    public void fillcomboloans(){
        try{
            String sql = "SELECT staffno,firstName,lastname FROM employeeregistrationtable WHERE status = 'active'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
                while(rs.next()){
                    comboEmployees.addItem(rs.getString("firstName"));
                }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e+" exportpanel.fillcomboloans");
        }finally{
            try{
                rs.close();
                pst.close();
            }catch(Exception e){
                
            }
        }
    }
    public void update_tableloans(){
        try{
            String sql = "SELECT loantable.id AS [id],loantable.month||' '||loantable.year AS [Month - Year],loantable.takenloan "
                    + "AS [Loan Taken],loantable.paidloan AS [Paid Loan],loantable.balance AS [Balance] FROM loantable,employeeregistrationtable "
                    + "WHERE loantable.staffno = employeeregistrationtable.staffno AND employeeregistrationtable.status = 'active' AND "
                    + "loantable.year = '"+comboYear.getSelectedItem().toString()+"' AND loantable.staffno = '"+staffno+"'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            tableloans.setModel(DbUtils.resultSetToTableModel(rs));
            TableColumn idColumn = tableloans.getColumn("id");
            idColumn.setMaxWidth(0);
            idColumn.setMinWidth(0);
            idColumn.setPreferredWidth(0);
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e+" exportpanel.update_tableloans");
        }finally{
            try{
                rs.close();
                pst.close();
            }catch(Exception e){
                
            }
        }
    }
    private void currentMonthNYear() {
        JCalendar calendar = new JCalendar();
        String mymonth;
        SimpleDateFormat sdf1 = new SimpleDateFormat("MMMM");
        java.util.Date date1 = calendar.getDate();
        mymonth = sdf1.format(date1);
        comboMonth.setSelectedItem(mymonth);
        
        SimpleDateFormat sdf2 = new SimpleDateFormat("YYYY");
        java.util.Date date2 = calendar.getDate();
        mymonth = sdf2.format(date2);
        comboYear.setSelectedItem(mymonth);
    }
    public void Update_tableKra(){
        try{
            String Month = comboMonth.getSelectedItem().toString();
            String Year = comboYear.getSelectedItem().toString();
            String sql = "SELECT pin,name,residencestatus,employeeType,basicsalary,houseAll,transportAll,"
                    + "leavePay,overtimeAll,directorsFee,lumpSum,otherAll,totalCashpay,valueOfCB,otherNCB,totalNCPay,globalIncome,typeHousing,"
                    + "rentOfHouse,computedRent,rentRecovered,netValueHousing,totalGrosspay,cashPay,actualContribution,permissibleLimit,mortgageInterest,"
                    + "depositHOSP,amountBenefit,taxablePay,taxPayable,personalRelief,amountIR,payeTax,SAPT "
                    + "FROM kratable WHERE Month = '"+Month+"' AND year = '"+Year+"'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            tableKra.setModel(DbUtils.resultSetToTableModel(rs));
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
    
    public void Update_tableEmployees(){
        try{
            String Month = comboMonth.getSelectedItem().toString();
            String Year = comboYear.getSelectedItem().toString();
            String sql = "SELECT employeeregistrationtable.staffNo AS 'Staff No', paymenttable.Name AS 'Name',idnumber AS 'ID No',"
                    + "employeeregistrationtable.branchSortCode AS 'Branch Sort Code', employeeregistrationtable.accountNumber AS "
                    + "'A/C No',paymenttable.netsalary AS 'Net Salary' FROM paymenttable,banktable,employeeregistrationtable WHERE "
                    + "employeeregistrationtable.staffNo = paymenttable.staffNo AND banktable.branchCode = employeeregistrationtable"
                    + ".branchSortCode AND branchsortCode AND paymenttable.Month = '"+Month+"' AND paymenttable.year = '"+Year+"' "
                    + "AND paymenttable.netsalary > '1000' AND banktable.bankname = '"+comboBankname.getSelectedItem().toString()+"'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            tableEmployees.setModel(DbUtils.resultSetToTableModel(rs));
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
    public void Update_tableEmployees2(){
        try{
            String Month = comboMonth.getSelectedItem().toString();
            String Year = comboYear.getSelectedItem().toString();
            String sql = "SELECT employeeregistrationtable.staffNo AS 'Staff No', paymenttable.Name AS 'Name',idnumber AS 'ID No',"
                    + "employeeregistrationtable.branchSortCode AS 'Branch Sort Code', employeeregistrationtable.accountNumber "
                    + "AS 'A/C No',paymenttable.netsalary AS 'Net Salary' FROM paymenttable,banktable,employeeregistrationtable "
                    + "WHERE employeeregistrationtable.staffNo = paymenttable.staffNo AND banktable.branchCode = "
                    + "employeeregistrationtable.branchSortCode AND branchsortCode AND paymenttable.Month = '"+Month+"'"
                    + " AND paymenttable.year = '"+Year+"' AND paymenttable.netsalary > '1000'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            tableEmployees.setModel(DbUtils.resultSetToTableModel(rs));
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
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        DialogEmployees = new javax.swing.JDialog();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableEmail = new javax.swing.JTable();
        txtFrom = new javax.swing.JTextField();
        txtPassword = new javax.swing.JTextField();
        txtServer = new javax.swing.JTextField();
        txtPort = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        comboYear = new javax.swing.JComboBox();
        comboMonth = new javax.swing.JComboBox();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableloans = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        comboEmployees = new javax.swing.JComboBox<>();
        txtLastname = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        name_attach = new javax.swing.JTextField();
        buttonAttachReport = new javax.swing.JButton();
        txtEmployeeName = new javax.swing.JTextField();
        path = new javax.swing.JTextField();
        buttonSend = new javax.swing.JButton();
        buttonAddemployee = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtEmployeeEmail = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        buttonExport1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableKra = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableEmployees = new javax.swing.JTable();
        buttonExport = new javax.swing.JButton();
        buttonViewPrint = new javax.swing.JButton();
        comboBankname = new javax.swing.JComboBox();

        DialogEmployees.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel5.setBackground(java.awt.Color.white);
        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.lightGray, java.awt.Color.blue));

        tableEmail.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableEmail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableEmailMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tableEmail);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout DialogEmployeesLayout = new javax.swing.GroupLayout(DialogEmployees.getContentPane());
        DialogEmployees.getContentPane().setLayout(DialogEmployeesLayout);
        DialogEmployeesLayout.setHorizontalGroup(
            DialogEmployeesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        DialogEmployeesLayout.setVerticalGroup(
            DialogEmployeesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DialogEmployeesLayout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        txtFrom.setText("jTextField2");

        txtPassword.setText("jTextField3");

        txtServer.setText("jTextField4");

        txtPort.setText("jTextField1");

        setBackground(java.awt.Color.white);

        jLabel1.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel1.setForeground(java.awt.Color.blue);
        jLabel1.setText("Month:");

        jLabel2.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel2.setForeground(java.awt.Color.blue);
        jLabel2.setText("Year:");

        comboYear.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030" }));
        comboYear.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                comboYearPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        comboMonth.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
        comboMonth.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                comboMonthPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Reports/Exports", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 2, 12), java.awt.Color.blue)); // NOI18N

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        tableloans.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane4.setViewportView(tableloans);

        jLabel7.setText("Employee:");

        comboEmployees.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                comboEmployeesPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
                comboEmployeesPopupMenuCanceled(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 951, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(343, 343, 343)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboEmployees, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtLastname, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(comboEmployees, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtLastname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Loan Records", jPanel6);

        jPanel3.setBackground(java.awt.Color.white);
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.lightGray, java.awt.Color.darkGray));

        jPanel4.setBackground(java.awt.Color.white);
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.lightGray, java.awt.Color.blue));

        jLabel5.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel5.setForeground(java.awt.Color.blue);
        jLabel5.setText("Name of Report:");

        jLabel4.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel4.setForeground(java.awt.Color.blue);
        jLabel4.setText("Report Path:");

        buttonAttachReport.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        buttonAttachReport.setText("Attach Report");
        buttonAttachReport.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        buttonAttachReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAttachReportActionPerformed(evt);
            }
        });

        txtEmployeeName.setEditable(false);
        txtEmployeeName.setBackground(java.awt.Color.lightGray);

        path.setEditable(false);
        path.setBackground(java.awt.Color.lightGray);

        buttonSend.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        buttonSend.setText("Send ");
        buttonSend.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        buttonSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSendActionPerformed(evt);
            }
        });

        buttonAddemployee.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        buttonAddemployee.setText("+++");
        buttonAddemployee.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        buttonAddemployee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddemployeeActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel3.setForeground(java.awt.Color.blue);
        jLabel3.setText("Employee:");

        jLabel6.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel6.setForeground(java.awt.Color.blue);
        jLabel6.setText("Email:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(2, 2, 2)
                        .addComponent(name_attach, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(buttonAttachReport, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(2, 2, 2)
                        .addComponent(path, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 265, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtEmployeeName, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                            .addComponent(txtEmployeeEmail))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonAddemployee, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(buttonSend, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buttonAddemployee)
                            .addComponent(txtEmployeeName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(buttonAttachReport)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(path, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(txtEmployeeEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(name_attach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonSend))))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(49, 49, 49))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(123, 123, 123)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(238, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Email Reports", jPanel3);

        jPanel2.setBackground(java.awt.Color.white);
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.white, java.awt.Color.lightGray));

        buttonExport1.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        buttonExport1.setForeground(java.awt.Color.red);
        buttonExport1.setText("Export to Excel");
        buttonExport1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonExport1ActionPerformed(evt);
            }
        });

        tableKra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tableKra);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(buttonExport1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1030, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonExport1)
                .addContainerGap())
        );

        jTabbedPane1.addTab("KRA ", jPanel2);

        jPanel1.setBackground(java.awt.Color.white);
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        tableEmployees.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tableEmployees.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableEmployeesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableEmployees);

        buttonExport.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        buttonExport.setForeground(java.awt.Color.red);
        buttonExport.setText("Export to Excel");
        buttonExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonExportActionPerformed(evt);
            }
        });

        buttonViewPrint.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        buttonViewPrint.setForeground(java.awt.Color.red);
        buttonViewPrint.setText("View/Print ");
        buttonViewPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonViewPrintActionPerformed(evt);
            }
        });

        comboBankname.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                comboBanknamePopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 895, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(comboBankname, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(322, 322, 322)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonExport, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonViewPrint, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonExport)
                    .addComponent(comboBankname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonViewPrint)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Employee's Bank Details", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(617, 617, 617)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addGap(2, 2, 2)
                        .addComponent(comboYear, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1078, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(comboYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void comboMonthPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_comboMonthPopupMenuWillBecomeInvisible
        Update_tableEmployees();
        Update_tableKra();
        update_tableloans();
    }//GEN-LAST:event_comboMonthPopupMenuWillBecomeInvisible

    private void comboYearPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_comboYearPopupMenuWillBecomeInvisible
         Update_tableEmployees();
         Update_tableKra();
         update_tableloans();
         
    }//GEN-LAST:event_comboYearPopupMenuWillBecomeInvisible

    private void buttonExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExportActionPerformed
        if(tableEmployees.getRowCount() == 0){
            JOptionPane.showMessageDialog(null, "No rows","Alert!",JOptionPane.INFORMATION_MESSAGE);
        return;
        }
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("File Extension .xls","xls");
        chooser.setFileFilter(filter);
        chooser.setDialogTitle("Choose Directory");
        chooser.setMultiSelectionEnabled(false);
        chooser.setAcceptAllFileFilterUsed(false);
        if(chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){
            List<JTable> tb = new ArrayList<>();
            List<String>nom = new ArrayList<>();
            tb.add(tableEmployees);
            nom.add("socios");
            String file = chooser.getSelectedFile().toString().concat(".xls");
            try{
                Exporter e = new Exporter(new File(file),tb,nom);
                if(e.export()){
                    JOptionPane.showMessageDialog(null,"Exported","Successfully",JOptionPane.INFORMATION_MESSAGE);
                }
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,""+ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
            }
        }
     
    }//GEN-LAST:event_buttonExportActionPerformed
    private void kraEmployeesNbankReport(){
        try {
            JasperDesign jd = JRXmlLoader.load("EmployeesBankreport.jrxml");
            String sql = "SELECT * FROM companytable,employeeregistrationtable,paymenttable,banktable WHERE employeeregistrationtable.staffNo ="
                    + " paymenttable.staffNo AND employeeregistrationtable.branchsortcode = banktable.branchcode AND paymenttable.netsalary > '1000' "
                    + "AND banktable.bankname = '"+comboBankname.getSelectedItem().toString()+"' AND paymenttable.month = "
                    + " '"+comboMonth.getSelectedItem().toString()+"' AND paymenttable.year = '"+comboYear.getSelectedItem().toString()+"'";
            JRDesignQuery newQuery = new JRDesignQuery();
            newQuery.setText(sql);
            jd.setQuery(newQuery);
            JasperReport jr = JasperCompileManager.compileReport(jd);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, conn);
            JasperViewer.viewReport(jp, false);                 //    -----------if you want the report to print without viewing you remove this line
            //JasperPrintManager.printReport(jp, false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        } finally {
            try {
                rs.close();
                pst.close();
            } catch (Exception e) {

            }
        }
    }
    private void buttonViewPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonViewPrintActionPerformed
         
         String Year = comboYear.getSelectedItem().toString();
         String Month = comboMonth.getSelectedItem().toString();
         try{
             String sql = "SELECT * FROM paymenttable WHERE  month = '"+Month+"' AND year = '"+Year+"'";
             pst = conn.prepareStatement(sql);
             rs = pst.executeQuery();
                if(rs.next()){
                    kraEmployeesNbankReport();
                }else{
                    JOptionPane.showMessageDialog(null, "Cannot Generate Report. No Payment records Found");
                }
         }catch(Exception e){
             
         }finally{
             try{
                 rs.close();
                 pst.close();
             }catch(Exception e){
                 
             }
         }
         
    }//GEN-LAST:event_buttonViewPrintActionPerformed

    private void buttonExport1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExport1ActionPerformed
         if(tableEmployees.getRowCount() == 0){
            JOptionPane.showMessageDialog(null, "Exported","Successfully",JOptionPane.INFORMATION_MESSAGE);
        return;
        }
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("File Extension .xls","xls");
        chooser.setFileFilter(filter);
        chooser.setDialogTitle("Choose Directory");
        chooser.setMultiSelectionEnabled(false);
        chooser.setAcceptAllFileFilterUsed(false);
        if(chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){
            List<JTable> tb = new ArrayList<>();
            List<String>nom = new ArrayList<>();
            tb.add(tableKra);
            nom.add("socios");
            String file = chooser.getSelectedFile().toString().concat(".xls");
            try{
                Exporter e = new Exporter(new File(file),tb,nom);
                if(e.export()){
                    JOptionPane.showMessageDialog(null,"Exported","Successfully",JOptionPane.INFORMATION_MESSAGE);
                }
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,""+ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
            }
        }
     
    }//GEN-LAST:event_buttonExport1ActionPerformed

    private void buttonAddemployeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddemployeeActionPerformed
        showDialogEmployees();
    }//GEN-LAST:event_buttonAddemployeeActionPerformed

    private void buttonAttachReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAttachReportActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        //String filename1 = f.getAbsolutePath();
        attachment_path = f.getAbsolutePath();
        path.setText(attachment_path);
    }//GEN-LAST:event_buttonAttachReportActionPerformed

    private void sendemail(){
        String to = txtEmployeeEmail.getText();
        String from = txtFrom.getText();

        String subject = name_attach.getText();

        final String username = txtFrom.getText();
        final String password = txtPassword.getText();
        
        // Assuming you are sending email through smtp.gmail.com
        String host = txtServer.getText();
        String port = txtPort.getText();
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

    
        // Get the Session object.
        Session session = Session.getInstance(props,
        new javax.mail.Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, password);
        }
        });
        if(path.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Please Choose the File location");
            buttonAttachReport.requestFocus();
        }else if(name_attach.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Please Name the File");
            name_attach.requestFocus();
        }else if(txtEmployeeName.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Please Select Employee");
            txtEmployeeName.requestFocus();
        }else{
    try {
        
    Message message = new MimeMessage(session);

    message.setFrom(new InternetAddress(from));

    message.setRecipients(Message.RecipientType.TO,
    InternetAddress.parse(to));
    message.setSubject(subject);
    
                        // attachment code
    
             MimeBodyPart messageBodyPart = new MimeBodyPart();
             messageBodyPart.setText(subject); 
             Multipart multipart = new MimeMultipart();
             multipart.addBodyPart(messageBodyPart);
             
             messageBodyPart = new MimeBodyPart();
             javax.activation.DataSource source = new FileDataSource(attachment_path);
             messageBodyPart.setDataHandler(new DataHandler(source));
             messageBodyPart.setFileName(name_attach.getText());
             multipart.addBodyPart(messageBodyPart);
             message.setContent(multipart);
             
             Transport.send(message);
             JOptionPane.showMessageDialog(null, "Message Sent Sucessfully...");
                } catch (MessagingException e) {
            JOptionPane.showMessageDialog(null,"Please Check on your Internet Connection");
            }
        }
    }
    private void buttonSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSendActionPerformed
        sendemail();
    }//GEN-LAST:event_buttonSendActionPerformed

    private void tableEmailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableEmailMouseClicked
        selectedRowEmail();
        DialogEmployees.dispose();
    }//GEN-LAST:event_tableEmailMouseClicked
    private void clearKra(){
        try{
           String sql = "DELETE FROM kratable WHERE month = '"+comboMonth.getSelectedItem().toString()+"'"
                   + "AND year = '"+comboYear.getSelectedItem().toString()+"' ";
           pst = conn.prepareStatement(sql);
           pst.execute();
           JOptionPane.showMessageDialog(null, "Deleted");
       }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
       }finally{
           try{
               rs.close();
               pst.close();
           }catch(Exception e){
               
           }
       }
       Update_tableKra();
    }
    private void tableEmployeesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableEmployeesMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tableEmployeesMouseClicked

    private void comboBanknamePopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_comboBanknamePopupMenuWillBecomeInvisible
        Update_tableEmployees();
    }//GEN-LAST:event_comboBanknamePopupMenuWillBecomeInvisible

    
    private void comboEmployeesPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_comboEmployeesPopupMenuWillBecomeInvisible
        try{
            String name = (String)comboEmployees.getSelectedItem();
            String sql = "SELECT * FROM employeeregistrationtable WHERE status = 'active' AND firstname = ? ";
            pst = conn.prepareStatement(sql);
            
            pst.setString(1, name);
            rs = pst.executeQuery();
                if(rs.next()){
                        staffno = rs.getString("staffno");
                            if(comboEmployees.getSelectedItem().toString().equals("Select")){
                                txtLastname.setVisible(false);
                            }else{
                                txtLastname.setVisible(true);
                                txtLastname.setText(rs.getString("lastname"));
                            }
                        
                }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e+" exportpanel.comboEmployeesPopupMenuWillBecomeInvisible");
        }finally{
                try{
                    rs.close();
                    pst.close();
                }catch(Exception e){

                }
            }
             update_tableloans();
             //staffno = "";
    }//GEN-LAST:event_comboEmployeesPopupMenuWillBecomeInvisible

    private void comboEmployeesPopupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_comboEmployeesPopupMenuCanceled
        // TODO add your handling code here:
    }//GEN-LAST:event_comboEmployeesPopupMenuCanceled


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog DialogEmployees;
    private javax.swing.JButton buttonAddemployee;
    private javax.swing.JButton buttonAttachReport;
    public javax.swing.JButton buttonExport;
    public javax.swing.JButton buttonExport1;
    private javax.swing.JButton buttonSend;
    public javax.swing.JButton buttonViewPrint;
    public javax.swing.JComboBox comboBankname;
    public javax.swing.JComboBox<String> comboEmployees;
    private javax.swing.JComboBox comboMonth;
    private javax.swing.JComboBox comboYear;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField name_attach;
    private javax.swing.JTextField path;
    private javax.swing.JTable tableEmail;
    public javax.swing.JTable tableEmployees;
    public javax.swing.JTable tableKra;
    private javax.swing.JTable tableloans;
    private javax.swing.JTextField txtEmployeeEmail;
    private javax.swing.JTextField txtEmployeeName;
    private javax.swing.JTextField txtFrom;
    private javax.swing.JLabel txtLastname;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JTextField txtPort;
    private javax.swing.JTextField txtServer;
    // End of variables declaration//GEN-END:variables
}
