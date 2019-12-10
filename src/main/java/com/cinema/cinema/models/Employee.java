package com.cinema.cinema.models;
import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "employees")
public class Employee extends Person {

    public Employee() {

    }
    public Employee(String name, Address address, Account account, int type) {
        super(name, address);
        this.account = account;
        this.type = type;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    @NotEmpty
    private Account account;

    @Column(name = "type", columnDefinition = "integer default 0")
    private int type;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
