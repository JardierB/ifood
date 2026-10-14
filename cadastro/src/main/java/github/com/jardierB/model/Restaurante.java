package github.com.jardierB.model;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity 
@Table (name = "restaurantes")
public class Restaurante extends PanacheEntityBase {
    //No panache a propiedades ao serem compilada viram prive
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    public Long id;

    public String proprietario;
    
    public String cnpj;

    public  String nome;

    @ManyToOne 
    public Localizacao localizacao;

    @CreationTimestamp 
    public Date dataCriacao;
    
    @UpdateTimestamp 
    public Date dataAtualizacao;

}
