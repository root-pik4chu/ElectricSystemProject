# ElectricSystemProject ✌😊😉
1. for executing the given project system need JAVA and swing 
2. for the connection purpose i use MySql database and for connection need (mysql-connector-java) i.e jar file
3. rs2Xml.jar 👀---- this is also jar file which i use for the displaying the table output on swing form
4. ✌ now for the executing file----------------------execute----------------------------

5. you have to put all the files in single folder
6. with icons in one folder and remaining files .....
eg...🤷‍♀️ single folder [ (icon folder) + remaining all the files  ]
                            ^                             
                            |                             
                   all the icons and jpgs ...             

7. after taking these steps ----------> add external libs 
                                             |-----> mysql connector
                                             |----->rs2xml.jar
                                             
                                             
8. now it will throw errors 
    {
      make data base name - ebs 
                              |-----> it contain tables (bill,customer,login,meter_info,tax)
                                                          ^      ^       ^        ^       ^
                                                          |      |        |       |       |------> col names in table tax [ cost_per_unit, meter_rent, service_charge, service_tax, swacch_bharat_cess, fixed_tax] 
                                                          |      |        |       |-----> col names in table meter_info [meter_no, meter_loc, meter_type, phasecode, bill_type, days]    
                                                          |      |        |------>  col names in table login [meter_no, userName, Name, password , user]     
                                                          |      |-------> col names in table customer [name, meter_no, address, city, state, email, phone]               
                                                          |------>  col names in table bill [meter_no, month, units, totalBill, status]      
                                                                          
                                                        
      
9. now run the loginPage.java 
10. for login and sign up [ do these as you do in sites ]

✌😊 
