package ru.vtb.course.lesson4_1.repositories;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "logins")
public class Logins {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "access_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date access_date;


    @ManyToOne()
    @JoinColumn(name = "user_id")
    private Users users;

    @Column(name = "application")
    private String application;

    public Date getAccess_date() {
        return access_date;
    }

    public void setAccess_date(Date access_date) {
        this.access_date = access_date;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    @Override
    public String toString() {
        return "Logins{" +
                "id=" + id +
                ", access_date=" + access_date +
                ", users=" + users +
                ", application='" + application + '\'' +
                '}';
    }
}
