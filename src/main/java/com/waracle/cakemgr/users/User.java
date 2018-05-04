package com.waracle.cakemgr.users;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Range(max = 4294967295L)
    @Column(nullable = false, updatable = false, unique = true)
    public Long id;

    public String username;
    public String password;
    public String status;

}
