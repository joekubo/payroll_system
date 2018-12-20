package payroll_system;

public class SearchEmployee {
    private String staffno;
    private String fname;
    private String lname;
    private String pin;
    private String gender;
    private String marital;
    private String email;
    private String address;
    private String city;
    private String tel;
    private String department;
    private String basic;
    
  public SearchEmployee(String Staffno,String Fname,String Lname,String Pin,String Gender,String 
          Marital,String Email,String Address,String City,String Tel,String Department,String Basic){
      this.staffno = Staffno;
      this.fname = Fname;
      this.lname = Lname;
      this.pin = Pin;
      this.gender = Gender;
      this.marital = Marital;
      this.email = Email;
      this.address = Address;
      this.city = City;
      this.tel = Tel;
      this.department = Department;
      this.basic = Basic;
  }
  public String getStaffno(){
      return staffno;
  }
  public String getFname(){
      return fname;
  }
  public String getLname(){
      return lname;
  }
  public String getPin(){
      return pin;
  }
  public String getGender(){
      return gender;
  }
  public String getMarital(){
      return marital;
  }
  public String getEmail(){
      return email;
  }
  public String getAddress(){
      return address;
  }
  public String getCity(){
      return city;
  }
  public String getTel(){
      return tel;
  }
  public String getDepartment(){
      return department;
  }
  public String getBasic(){
      return basic;
  }
}
