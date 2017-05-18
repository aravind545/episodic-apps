package com.example.users;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by trainer9 on 5/17/17.
 */

@Entity
@Table(name="users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String email;

}
