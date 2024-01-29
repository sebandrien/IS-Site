package com.example.is_site;
import jakarta.persistence.*;

@Entity
public class SiteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

}