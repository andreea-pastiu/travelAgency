package org.travelagency.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "user")
public class User
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int Id;

    @Column
    private String username;

    @Column
    private String password;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_vacations",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "package_id"))
    private List<Package> vacations;

    public User(int id, String username, String password)
    {
        Id = id;
        this.username = username;
        this.password = password;
    }

    public User(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    public User()
    {
    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    public int getId()
    {
        return Id;
    }

    public List<Package> getVacations()
    {
        return vacations;
    }
}
