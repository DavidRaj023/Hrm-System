package com.infinira.hr.model;

import java.text.MessageFormat;
import java.util.Date;

public class Employee {
    private int empId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String fatherName;
    private Date dob;
    private Gender gender;
    private MaritalStatus maritalStatus;
    private String uid;
    private String email;
    private String phone;
    private String bloodGroup;
    private Date doj;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private String title;
    private byte[] resume;
    private byte[] photo;
    private EmployeeStatus employeeStatus;

    
    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        validate(FIRST_NAME, firstName, FIRST_NAME_LENGTH);
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        validate(MIDDLE_NAME, middleName, MIDDLE_NAME_LENGTH);
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        validate(LAST_NAME, lastName, LAST_NAME_LENGTH);
        this.lastName = lastName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        validate(FIRST_NAME, fatherName, FATHER_NAME_LENGTH);
        this.fatherName = fatherName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        validate(UID, uid, UID_LENGTH);
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        validate(EMAIL, email, EMAIL_LENGTH);
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        validate(PHONE, phone, PHONE_LENGTH);
        this.phone = phone;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        validate(BLOOD_GROUP, bloodGroup, BLOOD_GROUP_LENGTH);
        this.bloodGroup = bloodGroup;
    }

    public Date getDoj() {
        return doj;
    }

    public void setDoj(Date doj) {
        this.doj = doj;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        validate(ADDRESS_LINE1, addressLine1, ADDRESS_LINE1_LENGTH);
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        validate(ADDRESS_LINE1, addressLine2, ADDRESS_LINE1_LENGTH);
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        validate(CITY, city, CITY_LENGTH);
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        validate(STATE, state, STATE_LENGTH);
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        validate(POSTAL_CODE, postalCode, POSTAL_CODE_LENGTH);
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        validate(COUNTRY, country, COUNTRY_LENGTH);
        this.country = country;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        validate(TITLE, title, TITLE_LENGTH);
        this.title = title;
    }

    public byte[] getResume() {
        return resume;
    }

    public void setResume(byte[] resume) {
        this.resume = resume;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public EmployeeStatus getEmployeeStatus() {
        return employeeStatus;
    }

    public void setEmployeeStatus(EmployeeStatus employeeStatus) {
        this.employeeStatus = employeeStatus;
    }
    
    private void validate(String paramName, String paramValue, int length) {
        if (paramValue == null) {
            throw new RuntimeException(MessageFormat.format(NULL_FIELD, paramName));
        }
        if (paramValue.isEmpty()) {
            throw new RuntimeException(MessageFormat.format(EMPTY_FIELD, paramName));
        }
        if (paramValue.length() > length) {
            throw new RuntimeException(MessageFormat.format(INVALID_LENGTH, paramName, length));
        }
    }

    private final String FIRST_NAME = "firstName";
    private final String MIDDLE_NAME = "middleName";
    private final String LAST_NAME = "lastName";
    private final String FATHER_NAME = "fatherName";
    private final String UID = "uid";
    private final String EMAIL = "email";
    private final String PHONE = "phone";
    private final String BLOOD_GROUP = "bloodGroup";
    private final String ADDRESS_LINE1 = "addressLine1";
    private final String ADDRESS_LINE2 =  "addressLne2";
    private final String CITY =  "city";
    private final String STATE =  "state";
    private final String POSTAL_CODE =  "postalCode";
    private final String COUNTRY =  "country";
    private final String TITLE =  "title";
    
    private final int FIRST_NAME_LENGTH = 35;
    private final int MIDDLE_NAME_LENGTH = 35;
    private final int LAST_NAME_LENGTH = 35;
    private final int FATHER_NAME_LENGTH = 70;
    private final int UID_LENGTH = 15;
    private final int EMAIL_LENGTH = 254;
    private final int PHONE_LENGTH = 15;
    private final int BLOOD_GROUP_LENGTH = 4;
    private final int ADDRESS_LINE1_LENGTH = 50;
    private final int ADDRESS_LINE2_LENGTH = 50;
    private final int CITY_LENGTH = 50;
    private final int STATE_LENGTH = 35;
    private final int POSTAL_CODE_LENGTH = 10;
    private final int COUNTRY_LENGTH = 56;
    private final int TITLE_LENGTH = 30;
    
    
    private final String INVALID_LENGTH = "The Maximum length of this field {0} is {1}";
    private final String EMPTY_FIELD = "The field{0}  cannot be Empty: ";
    private final String NULL_FIELD = "This field Cannot be Null: {0}";
}


