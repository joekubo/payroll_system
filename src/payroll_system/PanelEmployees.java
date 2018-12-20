
package payroll_system;
import java.awt.event.KeyEvent;
import javax.swing.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
public class PanelEmployees extends javax.swing.JPanel {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        
    public PanelEmployees() {
        initComponents();
        conn = javaconnect.ConnecrDb();
        reset();
        findEmployees();
    }
    
    //-------------------------------------------------------------Department & Designation combos-------------------------------------
    public void fillComboDepartment(){
        try{
            String sql = "SELECT * FROM departmenttable WHERE status = '1'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                String Department = rs.getString("department");
                comboDepartment.addItem(Department);
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
    public void removecomboDepartment(){
        comboDepartment.removeAllItems();
        comboDepartment.addItem("Select Department");
    }
    public void fillComboDesignation(){
        try{
            String sql = "SELECT * FROM designationtable WHERE departmentId = '"+txtDepartmentId.getText()+"' AND status = '1'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                String Designation = rs.getString("designation");
                comboDesignation.addItem(Designation);
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
    
   
   //--------------------------------------------------------------DialogQuiting-----------------------------------------------------------------------
    private void showDialogQuitting(){
        DialogQuitting.setVisible(true);
        DialogQuitting.setSize(732,390);
        DialogQuitting.setLocationRelativeTo(null);
        DialogQuitting.setResizable(false);
        DialogQuitting.setAlwaysOnTop(true);
        update_tableQuitting();
        buttonSaveQuit.setEnabled(true);
        buttonUpdateQuit.setEnabled(false);
        buttonDeleteQuit.setEnabled(false);
    }
    private void update_tableQuitting(){
        try{
            String sql = "SELECT quitTable.staffNo AS '             Staff No',employeeRegistrationTable.firstName||' '||employeeRegistrationtable."
                    + "lastName AS '                 Employee Name',quitTable.date AS '                 Date' FROM quitTable,"
                    + "employeeRegistrationtable WHERE quitTable.staffNo = employeeRegistrationTable.staffNo";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            tableReasonQuit.setModel(DbUtils.resultSetToTableModel(rs));
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
    private void saveQuit(){
        try{
            DateFormat currenttime = new SimpleDateFormat("HH:mm:ss ---dd/MM/yyyy");
            Calendar cal = Calendar.getInstance();
            
            String Reason = txtQuiting.getText();
            
            if(Reason.equals("")){
                txtExceptn.setText("Please Enter the Reason for quitting");
                txtQuiting.requestFocus();
            }else{
                String sql = "INSERT INTO quitTable(staffNo,date,reason)VALUES(?,?,?)";
                pst = conn.prepareStatement(sql);
                
                pst.setString(1, txtStaffNo.getText());
                pst.setString(2, currenttime.format(cal.getTime()));
                pst.setString(3, Reason);
                
                pst.execute();
                Quit();
               
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
        update_tableQuitting();
    }
    private void updateQuitting(){
        try{
            DateFormat currenttime = new SimpleDateFormat("HH:mm:ss ---dd/MM/yyyy");
            Calendar cal = Calendar.getInstance();
            String Reason = txtQuiting.getText();
            
            String sql = "UPDATE quitTable SET date = '"+currenttime.format(cal.getTime())+"',reason = '"+Reason+"' "
                    + "WHERE staffNo = '"+txtStaffNo.getText()+"'";
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
        update_tableQuitting();
    }
    private void deleteQuit(){
        try{
            String sql = "DELETE FROM quitTable WHERE staffNo = '"+txtStaffNo.getText()+"'";
            pst = conn.prepareStatement(sql);
            pst.execute();
            resetQuit();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }finally{
            try{
                rs.close();
                pst.close();
            }catch(Exception e){
                
            }
        }
        update_tableQuitting();
    }
    private void resetQuit(){
        txtStaffNo.setText("");
        txtDate.setText("");
        txtQuiting.setText("");
        txtQuiting.requestFocus();
    }
    private void selectedRowQuiting(){
        buttonSaveQuit.setEnabled(false);
        buttonUpdateQuit.setEnabled(true);
        buttonDeleteQuit.setEnabled(true);
        try{
            int row = tableReasonQuit.getSelectedRow();
            String table_click = tableReasonQuit.getValueAt(row, 0).toString();
            
            String sql = "SELECT * FROM quitTable WHERE staffNo = '"+table_click+"'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
                if(rs.next()){
                    String Staffno = rs.getString("staffNo");
                    txtStaffNo.setText(Staffno);
                    
                    String Dte = rs.getString("date");
                    txtDate.setText(Dte);
                    
                    String Reason = rs.getString("reason");
                    txtQuiting.setText(Reason);
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
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    public ArrayList<SearchEmployee> ListEmployee(String ValToSearch){
        ArrayList<SearchEmployee>employeeList = new ArrayList<SearchEmployee>();
       
        try{
            String searchQuery = "SELECT * FROM employeeregistrationtable WHERE staffNo||''||firstName||''||lastName||''||pinNo||''||gender||''||"
                    + "marital||''||email||''||address||''||city||''||tel||''||''||department||''||basicsalary LIKE'%"+ValToSearch+"%' AND "
                    + "status = 'active'";
            pst = conn.prepareStatement(searchQuery);
            rs = pst.executeQuery();
            SearchEmployee search;
            while(rs.next()){
                search = new SearchEmployee(
                                    rs.getString("staffNo"),
                                    rs.getString("firstName"),
                                    rs.getString("lastName"),
                                    rs.getString("pinNo"),
                                    rs.getString("gender"),
                                    rs.getString("marital"),
                                    rs.getString("email"),
                                    rs.getString("address"),
                                    rs.getString("city"),
                                    rs.getString("tel"),
                                    rs.getString("department"),
                                    rs.getString("basicSalary")
                                    
                                            );
                employeeList.add(search);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null ,e);
        }finally{
            try{
                rs.close();
                pst.close();
            }catch(Exception e){
                
            }
        }
        return employeeList;
    }
    public void findEmployees(){
        ArrayList<SearchEmployee> employees = ListEmployee(txtSearch.getText());
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"Staff No","First Name","Last Name","PIN No","Gender",
            "Marital","Email","Address","City","Tel","Department","Basic"});
        
        Object[] row = new Object[12];
        
            for(int i = 0; i < employees.size(); i ++){
                
             row[0] = employees.get(i).getStaffno();
             row[1] = employees.get(i).getFname();
             row[2] = employees.get(i).getLname();
             row[3] = employees.get(i).getPin();
             row[4] = employees.get(i).getGender();
             row[5] = employees.get(i).getMarital();
             row[6] = employees.get(i).getEmail();
             row[7] = employees.get(i).getAddress();
             row[8] = employees.get(i).getCity();
             row[9] = employees.get(i).getTel();
             row[10] = employees.get(i).getDepartment();
             row[11] = employees.get(i).getBasic();
             
             model.addRow(row);
            }
            tableEmployees.setModel(model);
    }
//-----------------------------------------------------------DialogBank---------------------------------------------------------------------
    private void showBanks(){
        DialogBank.setVisible(true);
        DialogBank.setSize(424,385);
        DialogBank.setResizable(false);
        DialogBank.setLocationRelativeTo(null);
        DialogBank.setAlwaysOnTop(true);
        Update_tableBanks();
        DialogBank.setTitle("Bank Details");
    }
    
    private void Update_tableBanks(){
        try{
            String sql = "SELECT id AS '', bankName AS 'Bank Name',branchName AS 'Branch Name',branchCode AS 'Branch Code' FROM bankTable";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            tableBanks.setModel(DbUtils.resultSetToTableModel(rs));
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
    private void selectedRowBanks(){
        try{
            int row = tableBanks.getSelectedRow();
            String Table_click = tableBanks.getValueAt(row, 0).toString();
            
            String sql = "SELECT * FROM bankTable WHERE id = '"+Table_click+"'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
                if(rs.next()){
                    String bankcode = rs.getString("branchCode");
                    txtBranchSortCode.setText(bankcode);
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
//--------------------------------------------------DialogQuit-----------------------------------------------------------------------------------------
    private void showDialogQuit(){
        DialogQuit.setVisible(true);
        DialogQuit.setSize(703,317);
        DialogQuit.setResizable(false);
        DialogQuit.setLocationRelativeTo(null);
        DialogQuit.setAlwaysOnTop(true);
        DialogQuit.setTitle("Quiting");
        Update_tableQuit();
    }
    private void Update_tableQuit(){
        try{
            String sql = "SELECT staffNo AS [Staff No],firstName||' '||lastName AS [Name] FROM employeeregistrationtable WHERE status = 'gone'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            tableQuit.setModel(DbUtils.resultSetToTableModel(rs));
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
    private void selectedRowQuit(){
        try{
            int row = tableQuit.getSelectedRow();
            String Table_click = tableQuit.getValueAt(row, 0).toString();
            
            String sql = "SELECT * FROM employeeregistrationtable WHERE staffNo = '"+Table_click+"'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
                if(rs.next()){
                    String staffno = rs.getString("staffNo");
                    txtStaffNo.setText(staffno);
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
    private void Quit(){
        try{
            String staffno = txtStaffNo.getText();
            String sql = "UPDATE employeeregistrationtable SET status = 'gone' WHERE staffNo = '"+staffno+"'";
            pst = conn.prepareStatement(sql);
            pst.execute();
            reset();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }finally{
            try{
                rs.close();
                pst.close();
            }catch(Exception e){
                
            }
        }
        Update_tableQuit();
        findEmployees();
    }
    private void restore(){
        try{
            String staffno = txtStaffNo.getText();
            String sql = "UPDATE employeeregistrationtable SET status = 'active' WHERE staffNo = '"+staffno+"'";
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
        Update_tableQuit();
        findEmployees();
    }
//-----------------------------------------------------------------------------------------------------------------------------------------
    public void reset(){
        txtStaffNo.setText("");
        txtFirstName.setText("");
        txtLastName.setText("");
        txtPinNo.setText("");
        comboGender.setSelectedItem("Select Gender");
        comboMaritalStatus.setSelectedItem("Select Marital");
        txtEmail.setText("");
        txtAddress.setText("");
        txtCity.setText("");
        txtTel.setText("");
        txtBasicSalary.setText("");
        txtSearch.setText("");
        txtIdnumber.setText("");
        txtBranchSortCode.setText("");
        txtAccountNo.setText("");
        txtNssfNo.setText("");
        txtNhifNo.setText("");
        txtStaffNo.requestFocus();
        buttonSave.setEnabled(true);
        buttonQuitEmployee.setEnabled(false);
        buttonUpdate.setEnabled(false);
        findEmployees();
        comboDepartment.removeAllItems();
        comboDepartment.addItem("Select Department");
        fillComboDepartment();
        comboDesignation.removeAllItems();
        comboDesignation.addItem("Select Designation");
    }
    private void emailValidation(){
        boolean status = ValidateEmail.validateEmail(txtEmail.getText());
        if(!status){
            txtException.setText("Invalid Email Address!!!");
            txtEmail.setText("");
            txtEmail.requestFocus();
        }
    }

    private void selectedRow(){
        try{
            int row = tableEmployees.getSelectedRow();
            String Table_click = tableEmployees.getValueAt(row, 0).toString();
            
            String sql = "SELECT * FROM employeeregistrationtable WHERE staffNo = '"+Table_click+"' AND status = 'active'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
                if(rs.next()){
                    
                    String staffno = rs.getString("staffNo");
                    txtStaffNo.setText(staffno);
                    
                    String firstname = rs.getString("firstName");
                    txtFirstName.setText(firstname);
                    
                    String lastname = rs.getString("lastName");
                    txtLastName.setText(lastname);
                    
                    String pinno = rs.getString("pinNo");
                    txtPinNo.setText(pinno);
                    
                    String gender = rs.getString("gender");
                    comboGender.setSelectedItem(gender);
                    
                    String marital = rs.getString("marital");
                    comboMaritalStatus.setSelectedItem(marital);
                    
                    String email = rs.getString("email");
                    txtEmail.setText(email);
                    
                    String address = rs.getString("address");
                    txtAddress.setText(address);
                    
                    String city = rs.getString("city");
                    txtCity.setText(city);
                    
                    String Tel = rs.getString("tel");
                    txtTel.setText(Tel);
                    
                    String basic = rs.getString("basicSalary");
                    txtBasicSalary.setText(basic);
                    
                    String sortcode = rs.getString("branchSortCode");
                    txtBranchSortCode.setText(sortcode);
                    
                    String accountno = rs.getString("accountNumber");
                    txtAccountNo.setText(accountno);
                    
                    String Nssf = rs.getString("NssfNo");
                    txtNssfNo.setText(Nssf);
                    
                    String Nhif = rs.getString("NhifNo");
                    txtNhifNo.setText(Nhif);
                    
                    String Department = rs.getString("department");
                    comboDepartment.setSelectedItem(Department);
                    
                    String Idnumber = rs.getString("idnumber");
                    txtIdnumber.setText(Idnumber);
                    
                    String Designation = rs.getString("designation");
                    comboDesignation.addItem(Designation);
                    comboDesignation.setSelectedItem(Designation);
                    
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
    private void save(){
        try{
            String Staffno = txtStaffNo.getText();
            String Firstname = txtFirstName.getText();
            String Lastname = txtLastName.getText();
            String Pin = txtPinNo.getText();
            String Gender = comboGender.getSelectedItem().toString();
            String Marital = comboMaritalStatus.getSelectedItem().toString();
            String Email = txtEmail.getText();
            String Address = txtAddress.getText();
            String City = txtCity.getText();
            String Tel = txtTel.getText();
            String Basic = txtBasicSalary.getText();
            String sortcode = txtBranchSortCode.getText();
            String accountno = txtAccountNo.getText();
            String nssfno = txtNssfNo.getText();
            String nhifno = txtNhifNo.getText();
            String Status = "active";
            String Department = comboDepartment.getSelectedItem().toString();
            String Designation = comboDesignation.getSelectedItem().toString();
            String Idnumber = txtIdnumber.getText();
            
            
            if(Staffno.equals("")){
                txtException.setText("Please Enter Staff No");
                txtStaffNo.requestFocus();
            }else if(Firstname.equals("")){
                txtException.setText("Please Enter First Name");
                txtFirstName.requestFocus();
            }else if(Lastname.equals("")){
                txtException.setText("Please Enter Last Name");
                txtLastName.requestFocus();
            }else if(Pin.equals("")){
                txtException.setText("Please Enter Pin No");
                txtPinNo.requestFocus();
            }else if(Gender.equals("Select Gender")){
                txtException.setText("Please Select Gender");
                comboGender.requestFocus();
            }else if(Marital.equals("Select Marital")){
                txtException.setText("Please Select Marital ");
                comboMaritalStatus.requestFocus();
            }else if(Email.equals("")){
                txtException.setText("Please Enter Email");
                txtEmail.requestFocus();
            }else if(Address.equals("")){
                txtException.setText("Please Enter Address");
                txtAddress.requestFocus();
            }else if(City.equals("")){
                txtException.setText("Please Enter City");
                txtCity.requestFocus();
            }else if(Tel.equals("")){
                txtException.setText("Please Enter Tel");
                txtTel.requestFocus();
            }else if(Department.equals("Select Department")){
                txtException.setText("Please Select Department");
                comboDepartment.requestFocus();
            }else if(Designation.equals("Select Designation")){
                txtException.setText("Please Select Designation");
                comboDesignation.requestFocus();
            }else if(Idnumber.equals("")){
                txtException.setText("Please Enter ID No");
                txtIdnumber.requestFocus();
            }else if(nssfno.equals("")){
                txtException.setText("Please Enter NSSF No");
                txtNssfNo.requestFocus();
            }else if(nhifno.equals("")){
                txtException.setText("Please Enter NHIF No");
                txtNhifNo.requestFocus();
            }else if(Basic.equals("")){
                txtException.setText("Please Enter Basic Salary");
                txtBasicSalary.requestFocus();
            }else if(sortcode.equals("")){
                txtException.setText("Please Enter Branch Sort Code");
                txtBranchSortCode.requestFocus();
            }else if(accountno.equals("")){
                txtException.setText("Please Enter Account No");
                txtAccountNo.requestFocus();
            }else{
                String sql = "INSERT INTO employeeregistrationtable(staffNo,firstName,lastName,pinNo,gender,marital,email,"
                        + "address,city,tel,basicSalary,status,branchSortCode,accountNumber,nssfno,nhifno,department,"
                        + "designation,idnumber)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                pst = conn.prepareStatement(sql);
                
                pst.setString(1, Staffno);
                pst.setString(2, Firstname);
                pst.setString(3, Lastname);
                pst.setString(4, Pin);
                pst.setString(5, Gender);
                pst.setString(6, Marital);
                pst.setString(7, Email);
                pst.setString(8, Address);
                pst.setString(9, City);
                pst.setString(10, Tel);
                pst.setString(11, Basic);
                pst.setString(12, Status);
                pst.setString(13, sortcode);
                pst.setString(14, accountno);
                pst.setString(15, nssfno);
                pst.setString(16, nhifno);
                pst.setString(17, Department);
                pst.setString(18, Designation);
                pst.setString(19, Idnumber);
                
                
                pst.execute();
                JOptionPane.showMessageDialog(null, "Saved");
                reset();
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
    private void update(){
        try{
            String Staffno = txtStaffNo.getText();
            String Firstname = txtFirstName.getText();
            String Lastname = txtLastName.getText();
            String Pin = txtPinNo.getText();
            String Gender = comboGender.getSelectedItem().toString();
            String Marital = comboMaritalStatus.getSelectedItem().toString();
            String Email = txtEmail.getText();
            String Address = txtAddress.getText();
            String City = txtCity.getText();
            String Tel = txtTel.getText();
            String Basic = txtBasicSalary.getText();
            String sortcode = txtBranchSortCode.getText();
            String accountno = txtAccountNo.getText();
            String Nssfno = txtNssfNo.getText();
            String Nhifno = txtNhifNo.getText();
            String Department = comboDepartment.getSelectedItem().toString();
            String Designation = comboDesignation.getSelectedItem().toString();
            String Idnumber = txtIdnumber.getText();
            
            String sql = "UPDATE employeeregistrationtable SET staffNo = '"+Staffno+"',firstName = '"+Firstname+"'"
                    + ",lastName = '"+Lastname+"',pinNo = '"+Pin+"',gender = '"+Gender+"',marital = '"+Marital+"',email = "
                    + "'"+Email+"',address = '"+Address+"', city = '"+City+"',tel = '"+Tel+"', basicSalary = '"+Basic+"',"
                    + "branchSortCode = '"+sortcode+"',accountNumber = '"+accountno+"',nssfno = '"+Nssfno+"',nhifno = "
                    + "'"+Nhifno+"',department = '"+Department+"',designation = '"+Designation+"',idnumber = '"+Idnumber+"'"
                    + " WHERE staffNo = '"+Staffno+"'";
            pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Updated");
            reset();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }finally{
            try{
                rs.close();
                pst.close();
            }catch(Exception e){
                
            }
        }
        findEmployees();
    }
    private void delete(){
        try{
            String Staffno = txtStaffNo.getText();
            String sql = "DELETE FROM employeeregistrationtable WHERE staffNo = '"+Staffno+"'";
            pst = conn.prepareStatement(sql);
            
            pst.execute();
            JOptionPane.showMessageDialog(null, "Deleted");
            reset();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }finally{
            try{
                rs.close();
                pst.close();
            }catch(Exception e){
                
            }
        }
        findEmployees();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        DialogQuit = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableQuit = new javax.swing.JTable();
        buttonRestore = new javax.swing.JButton();
        txtQuitId = new javax.swing.JTextField();
        DialogBank = new javax.swing.JDialog();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableBanks = new javax.swing.JTable();
        DialogQuitting = new javax.swing.JDialog();
        jPanel5 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tableReasonQuit = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtQuiting = new javax.swing.JTextArea();
        jPanel6 = new javax.swing.JPanel();
        buttonSaveQuit = new javax.swing.JButton();
        buttonUpdateQuit = new javax.swing.JButton();
        buttonExitQuit = new javax.swing.JButton();
        buttonDeleteQuit = new javax.swing.JButton();
        txtExceptn = new javax.swing.JLabel();
        txtDate = new javax.swing.JLabel();
        txtDepartmentId = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableEmployees = new javax.swing.JTable();
        txtSearch = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txtStaffNo = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtFirstName = new javax.swing.JTextField();
        txtAddress = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        comboMaritalStatus = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtPinNo = new javax.swing.JTextField();
        comboGender = new javax.swing.JComboBox();
        txtTel = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtCity = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtLastName = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtBasicSalary = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtBranchSortCode = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        txtAccountNo = new javax.swing.JTextField();
        txtNssfNo = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtNhifNo = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        comboDepartment = new javax.swing.JComboBox();
        comboDesignation = new javax.swing.JComboBox();
        jLabel20 = new javax.swing.JLabel();
        txtIdnumber = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        buttonUpdate = new javax.swing.JButton();
        buttonAddNew = new javax.swing.JButton();
        buttonSave = new javax.swing.JButton();
        buttonExit = new javax.swing.JButton();
        buttonPrint = new javax.swing.JButton();
        buttonQuitEmployee = new javax.swing.JButton();
        txtException = new javax.swing.JLabel();
        buttonPrint1 = new javax.swing.JButton();

        DialogQuit.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        DialogQuit.setBackground(java.awt.Color.white);

        jPanel3.setBackground(java.awt.Color.white);
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.white, java.awt.Color.blue));

        tableQuit.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tableQuit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableQuitMouseClicked(evt);
            }
        });
        tableQuit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tableQuitKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(tableQuit);

        buttonRestore.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        buttonRestore.setText("Restore");
        buttonRestore.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        buttonRestore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRestoreActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 675, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(buttonRestore, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonRestore)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout DialogQuitLayout = new javax.swing.GroupLayout(DialogQuit.getContentPane());
        DialogQuit.getContentPane().setLayout(DialogQuitLayout);
        DialogQuitLayout.setHorizontalGroup(
            DialogQuitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        DialogQuitLayout.setVerticalGroup(
            DialogQuitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        txtQuitId.setText("jTextField1");

        tableBanks.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tableBanks.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableBanksMouseClicked(evt);
            }
        });
        tableBanks.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tableBanksKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tableBanks);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout DialogBankLayout = new javax.swing.GroupLayout(DialogBank.getContentPane());
        DialogBank.getContentPane().setLayout(DialogBankLayout);
        DialogBankLayout.setHorizontalGroup(
            DialogBankLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        DialogBankLayout.setVerticalGroup(
            DialogBankLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DialogBankLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabel17.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel17.setForeground(java.awt.Color.blue);
        jLabel17.setText("Reason For Quiting:");

        tableReasonQuit.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tableReasonQuit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableReasonQuitMouseClicked(evt);
            }
        });
        tableReasonQuit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tableReasonQuitKeyReleased(evt);
            }
        });
        jScrollPane5.setViewportView(tableReasonQuit);

        txtQuiting.setColumns(20);
        txtQuiting.setRows(5);
        txtQuiting.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtQuitingKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtQuitingKeyReleased(evt);
            }
        });
        jScrollPane4.setViewportView(txtQuiting);

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.darkGray, java.awt.Color.pink));

        buttonSaveQuit.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        buttonSaveQuit.setText("Save");
        buttonSaveQuit.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        buttonSaveQuit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSaveQuitActionPerformed(evt);
            }
        });

        buttonUpdateQuit.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        buttonUpdateQuit.setText("Update");
        buttonUpdateQuit.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        buttonUpdateQuit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonUpdateQuitActionPerformed(evt);
            }
        });

        buttonExitQuit.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        buttonExitQuit.setText("Exit");
        buttonExitQuit.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        buttonExitQuit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonExitQuitActionPerformed(evt);
            }
        });

        buttonDeleteQuit.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        buttonDeleteQuit.setText("Delete");
        buttonDeleteQuit.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        buttonDeleteQuit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDeleteQuitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonSaveQuit, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(buttonUpdateQuit, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                .addComponent(buttonDeleteQuit, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73)
                .addComponent(buttonExitQuit, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonExitQuit)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(buttonDeleteQuit)
                        .addComponent(buttonUpdateQuit)
                        .addComponent(buttonSaveQuit)))
                .addContainerGap())
        );

        txtExceptn.setFont(new java.awt.Font("Noto Sans", 3, 12)); // NOI18N
        txtExceptn.setForeground(java.awt.Color.red);

        txtDate.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        txtDate.setForeground(java.awt.Color.red);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane5)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel17)
                        .addGap(2, 2, 2)
                        .addComponent(jScrollPane4)
                        .addGap(9, 9, 9))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(180, 180, 180)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtExceptn, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(148, 148, 148))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtExceptn, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout DialogQuittingLayout = new javax.swing.GroupLayout(DialogQuitting.getContentPane());
        DialogQuitting.getContentPane().setLayout(DialogQuittingLayout);
        DialogQuittingLayout.setHorizontalGroup(
            DialogQuittingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DialogQuittingLayout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        DialogQuittingLayout.setVerticalGroup(
            DialogQuittingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setBackground(java.awt.Color.white);
        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Employees Registration Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 2, 12), java.awt.Color.blue)); // NOI18N

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
        tableEmployees.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tableEmployeesKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tableEmployeesKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tableEmployees);

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel12.setForeground(java.awt.Color.blue);
        jLabel12.setText("Search:");

        jPanel1.setBackground(java.awt.Color.white);
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.white, java.awt.Color.blue));

        txtStaffNo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtStaffNoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtStaffNoKeyReleased(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel7.setForeground(java.awt.Color.blue);
        jLabel7.setText("Email:");

        txtFirstName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFirstNameKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtFirstNameKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFirstNameKeyReleased(evt);
            }
        });

        txtAddress.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAddressKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtAddressKeyPressed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel4.setForeground(java.awt.Color.blue);
        jLabel4.setText("PIN No:");

        comboMaritalStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Marital", "SINGLE", "MARRIED" }));
        comboMaritalStatus.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                comboMaritalStatusPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jLabel3.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel3.setForeground(java.awt.Color.blue);
        jLabel3.setText("Last Name:");

        jLabel1.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel1.setForeground(java.awt.Color.blue);
        jLabel1.setText("Staff No:");

        txtPinNo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPinNoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPinNoKeyReleased(evt);
            }
        });

        comboGender.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Gender", "MALE", "FEMALE", "Others ..." }));
        comboGender.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                comboGenderPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        txtTel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTelKeyPressed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel10.setForeground(java.awt.Color.blue);
        jLabel10.setText("Tel:");

        txtCity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCityKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCityKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCityKeyReleased(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel8.setForeground(java.awt.Color.blue);
        jLabel8.setText("Address:");

        jLabel5.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel5.setForeground(java.awt.Color.blue);
        jLabel5.setText("Gender:");

        txtLastName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLastNameKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtLastNameKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtLastNameKeyReleased(evt);
            }
        });

        txtEmail.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtEmailFocusLost(evt);
            }
        });
        txtEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEmailKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEmailKeyReleased(evt);
            }
        });

        txtBasicSalary.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBasicSalaryKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBasicSalaryKeyTyped(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel11.setForeground(java.awt.Color.blue);
        jLabel11.setText("Basic Salary:");

        jLabel2.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel2.setForeground(java.awt.Color.blue);
        jLabel2.setText("First Name:");

        jLabel6.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel6.setForeground(java.awt.Color.blue);
        jLabel6.setText("Marital Status:");

        jLabel13.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel13.setForeground(java.awt.Color.blue);
        jLabel13.setText("City:");

        jLabel14.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel14.setForeground(java.awt.Color.blue);
        jLabel14.setText("Branch Sort Code:");

        txtBranchSortCode.setEditable(false);
        txtBranchSortCode.setBackground(java.awt.Color.lightGray);

        jButton1.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jButton1.setForeground(java.awt.Color.red);
        jButton1.setText("+");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel15.setForeground(java.awt.Color.blue);
        jLabel15.setText("Account No:");

        txtNssfNo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNssfNoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNssfNoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNssfNoKeyTyped(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel9.setForeground(java.awt.Color.blue);
        jLabel9.setText("NSSF No:");

        jLabel16.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel16.setForeground(java.awt.Color.blue);
        jLabel16.setText("NHIF No:");

        txtNhifNo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNhifNoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNhifNoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNhifNoKeyTyped(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel18.setForeground(java.awt.Color.blue);
        jLabel18.setText("Department:");

        jLabel19.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel19.setForeground(java.awt.Color.blue);
        jLabel19.setText("Designation:");

        comboDepartment.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Department" }));
        comboDepartment.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                comboDepartmentFocusGained(evt);
            }
        });
        comboDepartment.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                comboDepartmentPopupMenuWillBecomeVisible(evt);
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                comboDepartmentPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        comboDepartment.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                comboDepartmentMouseClicked(evt);
            }
        });
        comboDepartment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboDepartmentActionPerformed(evt);
            }
        });

        comboDesignation.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Designation" }));
        comboDesignation.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                comboDesignationFocusGained(evt);
            }
        });
        comboDesignation.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                comboDesignationMouseClicked(evt);
            }
        });
        comboDesignation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboDesignationActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabel20.setForeground(java.awt.Color.blue);
        jLabel20.setText("ID No:");

        txtIdnumber.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtIdnumberKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIdnumberKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIdnumberKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(18, 18, 18)
                            .addComponent(jLabel1))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(3, 3, 3)))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboMaritalStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtStaffNo, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtFirstName)
                        .addComponent(txtLastName)
                        .addComponent(txtPinNo)
                        .addComponent(comboGender, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(44, 44, 44)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel10)
                    .addComponent(jLabel13)
                    .addComponent(jLabel19)
                    .addComponent(jLabel18))
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTel, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtCity, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtAddress, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(comboDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboDesignation, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(jLabel11)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNhifNo)
                            .addComponent(txtNssfNo, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(txtBranchSortCode)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtBasicSalary)
                            .addComponent(txtAccountNo, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel20)
                        .addGap(18, 18, 18)
                        .addComponent(txtIdnumber, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtStaffNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtPinNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(comboGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(comboMaritalStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(35, 35, 35))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel8)
                                .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtTel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(comboDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(comboDesignation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(txtIdnumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtNssfNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNhifNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(txtBranchSortCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(txtAccountNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtBasicSalary, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(java.awt.Color.white);
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        buttonUpdate.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        buttonUpdate.setText("Update");
        buttonUpdate.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        buttonUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonUpdateActionPerformed(evt);
            }
        });

        buttonAddNew.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        buttonAddNew.setText("Add New");
        buttonAddNew.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        buttonAddNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddNewActionPerformed(evt);
            }
        });

        buttonSave.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        buttonSave.setText("Save");
        buttonSave.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        buttonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSaveActionPerformed(evt);
            }
        });

        buttonExit.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        buttonExit.setText("Exit");
        buttonExit.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        buttonExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonExitActionPerformed(evt);
            }
        });

        buttonPrint.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        buttonPrint.setText("Print All");
        buttonPrint.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        buttonPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPrintActionPerformed(evt);
            }
        });

        buttonQuitEmployee.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        buttonQuitEmployee.setText("Quit Employee");
        buttonQuitEmployee.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        buttonQuitEmployee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonQuitEmployeeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonAddNew, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(buttonUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(buttonExit, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(buttonQuitEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonAddNew)
                    .addComponent(buttonSave)
                    .addComponent(buttonUpdate)
                    .addComponent(buttonExit)
                    .addComponent(buttonPrint)
                    .addComponent(buttonQuitEmployee))
                .addContainerGap())
        );

        txtException.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        txtException.setForeground(java.awt.Color.red);
        txtException.setText("   ");

        buttonPrint1.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        buttonPrint1.setText("Restore");
        buttonPrint1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        buttonPrint1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPrint1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtException, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(103, 103, 103)
                                .addComponent(buttonPrint1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 923, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 923, Short.MAX_VALUE)
                                .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(329, 329, 329)
                        .addComponent(jLabel12)
                        .addGap(4, 4, 4)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtException)
                    .addComponent(buttonPrint1)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tableEmployeesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableEmployeesMouseClicked
        selectedRow();
        buttonSave.setEnabled(false);
        buttonUpdate.setEnabled(true);
        txtException.setText("");
        buttonQuitEmployee.setEnabled(true);
    }//GEN-LAST:event_tableEmployeesMouseClicked

    private void tableEmployeesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableEmployeesKeyReleased
        if (evt.getKeyCode()==KeyEvent.VK_DOWN || evt.getKeyCode()==KeyEvent.VK_UP){
            selectedRow();
        }
    }//GEN-LAST:event_tableEmployeesKeyReleased

    private void buttonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSaveActionPerformed
        save();
    }//GEN-LAST:event_buttonSaveActionPerformed

    private void txtEmailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEmailFocusLost
        emailValidation();
    }//GEN-LAST:event_txtEmailFocusLost

    private void txtEmailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailKeyReleased
        txtEmail.setText(txtEmail.getText().toLowerCase());
    }//GEN-LAST:event_txtEmailKeyReleased

    private void txtStaffNoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStaffNoKeyReleased
        txtStaffNo.setText(txtStaffNo.getText().toUpperCase());
    }//GEN-LAST:event_txtStaffNoKeyReleased

    private void txtFirstNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFirstNameKeyReleased
        txtFirstName.setText(txtFirstName.getText().toUpperCase());
    }//GEN-LAST:event_txtFirstNameKeyReleased

    private void txtFirstNameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFirstNameKeyTyped
        char vchar = evt.getKeyChar();
      if(((Character.isDigit(vchar)))
          ||(vchar == KeyEvent.VK_BACK_SPACE)
          
          || (vchar == KeyEvent.VK_DELETE)){
        evt.consume();
    }

    }//GEN-LAST:event_txtFirstNameKeyTyped

    private void txtLastNameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLastNameKeyTyped
        char vchar = evt.getKeyChar();
      if(((Character.isDigit(vchar)))
          ||(vchar == KeyEvent.VK_BACK_SPACE)
          
          || (vchar == KeyEvent.VK_DELETE)){
        evt.consume();
    }

    }//GEN-LAST:event_txtLastNameKeyTyped

    private void txtLastNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLastNameKeyReleased
        txtLastName.setText(txtLastName.getText().toUpperCase());
    }//GEN-LAST:event_txtLastNameKeyReleased

    private void txtPinNoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPinNoKeyReleased
        txtPinNo.setText(txtPinNo.getText().toUpperCase());
    }//GEN-LAST:event_txtPinNoKeyReleased

    private void txtAddressKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAddressKeyTyped
       char vchar = evt.getKeyChar();
      if((!(Character.isDigit(vchar)))
          ||(vchar == KeyEvent.VK_BACK_SPACE)
          
          || (vchar == KeyEvent.VK_DELETE)){
        evt.consume();
    }
    }//GEN-LAST:event_txtAddressKeyTyped

    private void txtCityKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCityKeyTyped
        char vchar = evt.getKeyChar();
      if(((Character.isDigit(vchar)))
          ||(vchar == KeyEvent.VK_BACK_SPACE)
          
          || (vchar == KeyEvent.VK_DELETE)){
        evt.consume();
    }
    }//GEN-LAST:event_txtCityKeyTyped

    private void txtCityKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCityKeyReleased
        txtCity.setText(txtCity.getText().toUpperCase());
    }//GEN-LAST:event_txtCityKeyReleased

    private void txtTelKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelKeyTyped
        char vchar = evt.getKeyChar();
      if((!(Character.isDigit(vchar)))
          ||(vchar == KeyEvent.VK_BACK_SPACE)
          
          || (vchar == KeyEvent.VK_DELETE)){
        evt.consume();
    }
    }//GEN-LAST:event_txtTelKeyTyped

    private void txtBasicSalaryKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBasicSalaryKeyTyped
        char vchar = evt.getKeyChar();
      if((!(Character.isDigit(vchar)))
          ||(vchar == KeyEvent.VK_BACK_SPACE)
          
          || (vchar == KeyEvent.VK_DELETE)){
        evt.consume();
    }
    }//GEN-LAST:event_txtBasicSalaryKeyTyped

    private void txtStaffNoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStaffNoKeyPressed
       txtException.setText("");
       if (evt.getKeyCode()==KeyEvent.VK_ENTER ){
           save();
        }
    }//GEN-LAST:event_txtStaffNoKeyPressed

    private void txtFirstNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFirstNameKeyPressed
        txtException.setText("");
        if (evt.getKeyCode()==KeyEvent.VK_ENTER ){
           save();
        }
    }//GEN-LAST:event_txtFirstNameKeyPressed

    private void txtLastNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLastNameKeyPressed
       txtException.setText("");
       if (evt.getKeyCode()==KeyEvent.VK_ENTER ){
           save();
        }
    }//GEN-LAST:event_txtLastNameKeyPressed

    private void txtPinNoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPinNoKeyPressed
        txtException.setText("");
        if (evt.getKeyCode()==KeyEvent.VK_ENTER ){
           save();
        }
    }//GEN-LAST:event_txtPinNoKeyPressed

    private void comboGenderPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_comboGenderPopupMenuWillBecomeInvisible
        txtException.setText("");
    }//GEN-LAST:event_comboGenderPopupMenuWillBecomeInvisible

    private void comboMaritalStatusPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_comboMaritalStatusPopupMenuWillBecomeInvisible
        txtException.setText("");
    }//GEN-LAST:event_comboMaritalStatusPopupMenuWillBecomeInvisible

    private void txtEmailKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailKeyPressed
        txtException.setText("");  
        if (evt.getKeyCode()==KeyEvent.VK_ENTER ){
           save();
        }
    }//GEN-LAST:event_txtEmailKeyPressed

    private void txtAddressKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAddressKeyPressed
        txtException.setText("");
        if (evt.getKeyCode()==KeyEvent.VK_ENTER ){
           save();
        }
    }//GEN-LAST:event_txtAddressKeyPressed

    private void txtCityKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCityKeyPressed
        txtException.setText("");
        if (evt.getKeyCode()==KeyEvent.VK_ENTER ){
           save();
        }
    }//GEN-LAST:event_txtCityKeyPressed

    private void txtTelKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelKeyPressed
        txtException.setText("");
        if (evt.getKeyCode()==KeyEvent.VK_ENTER ){
           save();
        }
    }//GEN-LAST:event_txtTelKeyPressed

    private void txtBasicSalaryKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBasicSalaryKeyPressed
        txtException.setText("");
        if (evt.getKeyCode()==KeyEvent.VK_ENTER ){
           save();
        }
    }//GEN-LAST:event_txtBasicSalaryKeyPressed

    private void buttonAddNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddNewActionPerformed
        reset();
    }//GEN-LAST:event_buttonAddNewActionPerformed

    private void buttonExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExitActionPerformed
       this.setVisible(false);
    }//GEN-LAST:event_buttonExitActionPerformed

    private void buttonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUpdateActionPerformed
        String Staffno = txtStaffNo.getText();
        if(Staffno.equals("")){
            txtException.setText("Cannot Update Blank Fields!");
        }else{
            update();
        }
    }//GEN-LAST:event_buttonUpdateActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
       findEmployees();
    }//GEN-LAST:event_txtSearchKeyReleased
    private void allEmployeesReport(){
        try {
            JasperDesign jd = JRXmlLoader.load("Allregisteredemployees.jrxml");
            String sql = "SELECT * FROM companytable,employeeregistrationtable";
            JRDesignQuery newQuery = new JRDesignQuery();
            newQuery.setText(sql);
            jd.setQuery(newQuery);
            JasperReport jr = JasperCompileManager.compileReport(jd);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, conn);
            JasperViewer.viewReport(jp, false);               //      -----------if you want the report to print without viewing you remove this line
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
    private void buttonPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPrintActionPerformed
        allEmployeesReport();
    }//GEN-LAST:event_buttonPrintActionPerformed

    private void buttonQuitEmployeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonQuitEmployeeActionPerformed
        showDialogQuitting();
    }//GEN-LAST:event_buttonQuitEmployeeActionPerformed

    private void buttonRestoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRestoreActionPerformed
        restore();
        deleteQuit();
    }//GEN-LAST:event_buttonRestoreActionPerformed

    private void tableQuitKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableQuitKeyReleased
        if (evt.getKeyCode()==KeyEvent.VK_DOWN || evt.getKeyCode()==KeyEvent.VK_UP){
            selectedRow();
        }
    }//GEN-LAST:event_tableQuitKeyReleased

    private void tableQuitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableQuitMouseClicked
        selectedRowQuit();
    }//GEN-LAST:event_tableQuitMouseClicked

    private void buttonPrint1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPrint1ActionPerformed
        showDialogQuit();
    }//GEN-LAST:event_buttonPrint1ActionPerformed

    private void tableEmployeesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableEmployeesKeyPressed
       if (evt.getKeyCode()==KeyEvent.VK_DELETE ){
           delete();
        }
    }//GEN-LAST:event_tableEmployeesKeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       showBanks();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tableBanksMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableBanksMouseClicked
        selectedRowBanks();
        DialogBank.dispose();
    }//GEN-LAST:event_tableBanksMouseClicked

    private void tableBanksKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableBanksKeyReleased
    if (evt.getKeyCode()==KeyEvent.VK_DOWN || evt.getKeyCode()==KeyEvent.VK_UP){
            selectedRowBanks();
        }
    }//GEN-LAST:event_tableBanksKeyReleased

    private void txtNssfNoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNssfNoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNssfNoKeyTyped

    private void txtNssfNoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNssfNoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNssfNoKeyPressed

    private void txtNhifNoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNhifNoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNhifNoKeyTyped

    private void txtNhifNoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNhifNoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNhifNoKeyPressed

    private void txtNhifNoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNhifNoKeyReleased
        txtNhifNo.setText(txtNhifNo.getText().toUpperCase());
    }//GEN-LAST:event_txtNhifNoKeyReleased

    private void txtNssfNoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNssfNoKeyReleased
        txtNssfNo.setText(txtNssfNo.getText().toUpperCase());
    }//GEN-LAST:event_txtNssfNoKeyReleased

    private void txtQuitingKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQuitingKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQuitingKeyReleased

    private void txtQuitingKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQuitingKeyPressed
        txtExceptn.setText("");
    }//GEN-LAST:event_txtQuitingKeyPressed

    private void tableReasonQuitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableReasonQuitMouseClicked
        txtExceptn.setText("");
        selectedRowQuiting();
    }//GEN-LAST:event_tableReasonQuitMouseClicked

    private void buttonSaveQuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSaveQuitActionPerformed
        saveQuit();
    }//GEN-LAST:event_buttonSaveQuitActionPerformed

    private void buttonUpdateQuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUpdateQuitActionPerformed
        updateQuitting();
    }//GEN-LAST:event_buttonUpdateQuitActionPerformed

    private void buttonExitQuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExitQuitActionPerformed
        DialogQuitting.dispose();
    }//GEN-LAST:event_buttonExitQuitActionPerformed

    private void buttonDeleteQuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDeleteQuitActionPerformed
        deleteQuit();
        restore();
    }//GEN-LAST:event_buttonDeleteQuitActionPerformed

    private void tableReasonQuitKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableReasonQuitKeyReleased
        if (evt.getKeyCode()==KeyEvent.VK_DOWN || evt.getKeyCode()==KeyEvent.VK_UP){
            selectedRowQuiting();
        }
    }//GEN-LAST:event_tableReasonQuitKeyReleased

    private void comboDesignationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comboDesignationMouseClicked
      
    }//GEN-LAST:event_comboDesignationMouseClicked

    private void uploadDesignated(){
        String Department = (String)comboDepartment.getSelectedItem();
        String sql = "SELECT * FROM departmenttable WHERE department = ? AND status = '1'";

        try{
            pst=conn.prepareStatement(sql);
            pst.setString(1, Department);
            rs=pst.executeQuery();

            if(rs.next()){
                String Id = rs.getString("id");
                txtDepartmentId.setText(Id);
                
                comboDesignation.removeAllItems();
                comboDesignation.addItem("Select Designation");
            }
     }   catch(Exception e){
         JOptionPane.showMessageDialog(null, e);
         reset();
     }finally{
         try{
             rs.close();
             pst.close();
         }catch(Exception e){

         }
     }  
    }
    private void comboDepartmentPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_comboDepartmentPopupMenuWillBecomeInvisible
        uploadDesignated();
        fillComboDesignation();
    }//GEN-LAST:event_comboDepartmentPopupMenuWillBecomeInvisible

    private void comboDepartmentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comboDepartmentMouseClicked
     
    }//GEN-LAST:event_comboDepartmentMouseClicked

    private void comboDepartmentPopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_comboDepartmentPopupMenuWillBecomeVisible
    
    }//GEN-LAST:event_comboDepartmentPopupMenuWillBecomeVisible

    private void comboDesignationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboDesignationActionPerformed
        
    }//GEN-LAST:event_comboDesignationActionPerformed

    private void comboDesignationFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_comboDesignationFocusGained
       if(comboDepartment.getSelectedItem().toString().equals("Select Department")){
            JOptionPane.showMessageDialog(null, "Please Select Department");
            comboDepartment.requestFocus();
        }
    }//GEN-LAST:event_comboDesignationFocusGained

    private void comboDepartmentFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_comboDepartmentFocusGained
       
    }//GEN-LAST:event_comboDepartmentFocusGained

    private void comboDepartmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboDepartmentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboDepartmentActionPerformed

    private void txtIdnumberKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdnumberKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdnumberKeyPressed

    private void txtIdnumberKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdnumberKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdnumberKeyReleased

    private void txtIdnumberKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdnumberKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdnumberKeyTyped
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog DialogBank;
    private javax.swing.JDialog DialogQuit;
    private javax.swing.JDialog DialogQuitting;
    private javax.swing.JButton buttonAddNew;
    private javax.swing.JButton buttonDeleteQuit;
    private javax.swing.JButton buttonExit;
    private javax.swing.JButton buttonExitQuit;
    private javax.swing.JButton buttonPrint;
    private javax.swing.JButton buttonPrint1;
    private javax.swing.JButton buttonQuitEmployee;
    private javax.swing.JButton buttonRestore;
    private javax.swing.JButton buttonSave;
    private javax.swing.JButton buttonSaveQuit;
    private javax.swing.JButton buttonUpdate;
    private javax.swing.JButton buttonUpdateQuit;
    public javax.swing.JComboBox comboDepartment;
    public javax.swing.JComboBox comboDesignation;
    private javax.swing.JComboBox comboGender;
    private javax.swing.JComboBox comboMaritalStatus;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
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
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable tableBanks;
    private javax.swing.JTable tableEmployees;
    private javax.swing.JTable tableQuit;
    private javax.swing.JTable tableReasonQuit;
    private javax.swing.JTextField txtAccountNo;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtBasicSalary;
    private javax.swing.JTextField txtBranchSortCode;
    private javax.swing.JTextField txtCity;
    private javax.swing.JLabel txtDate;
    private javax.swing.JTextField txtDepartmentId;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JLabel txtException;
    private javax.swing.JLabel txtExceptn;
    private javax.swing.JTextField txtFirstName;
    private javax.swing.JTextField txtIdnumber;
    private javax.swing.JTextField txtLastName;
    private javax.swing.JTextField txtNhifNo;
    private javax.swing.JTextField txtNssfNo;
    private javax.swing.JTextField txtPinNo;
    private javax.swing.JTextField txtQuitId;
    private javax.swing.JTextArea txtQuiting;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtStaffNo;
    private javax.swing.JTextField txtTel;
    // End of variables declaration//GEN-END:variables
}
