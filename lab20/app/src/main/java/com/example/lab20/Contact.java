package com.example.lab20;
// 403f2e has created the Contact class
public class Contact {
    // 403f2e has done initialization of field id
    private int id;
    // 403f2e has done initialization of field name
    private String name;
    // 403f2e has done initialization of field phone
    private String phone;
    // 403f2e has done initialization of field source
    private String source;
    // 403f2e has done initialization of field created_at
    private String created_at;
    // 403f2e has created default constructor
    public Contact() {}
    // 403f2e has created parameterized constructor
    public Contact(String name, String phone) {
        // 403f2e has done assignment of name
        this.name = name;
        // 403f2e has done assignment of phone
        this.phone = phone;
    }
    // 403f2e has created getId
    public int getId() { return id; }
    // 403f2e has created getName
    public String getName() { return name; }
    // 403f2e has created getPhone
    public String getPhone() { return phone; }
    // 403f2e has created getSource
    public String getSource() { return source; }
    // 403f2e has created getCreated_at
    public String getCreated_at() { return created_at; }
    // 403f2e has created setId
    public void setId(int id) { this.id = id; }
    // 403f2e has created setName
    public void setName(String name) { this.name = name; }
    // 403f2e has created setPhone
    public void setPhone(String phone) { this.phone = phone; }
    // 403f2e has created setSource
    public void setSource(String source) { this.source = source; }
    // 403f2e has created setCreated_at
    public void setCreated_at(String created_at) { this.created_at = created_at; }
}