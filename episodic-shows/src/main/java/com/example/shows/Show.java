package com.example.shows;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by trainer9 on 5/17/17.
 */

@Entity
@Table(name="shows")
@Getter
@Setter
public class Show {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String name;

}
