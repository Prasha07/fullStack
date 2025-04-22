package com.backend.E_commerce.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
public class Cateogry {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(max=50)
    private String name ;

    @ManyToOne
    @JoinColumn(name = "parent_cateogry_id")
    private  Cateogry parent_cateogry;

    private int level;

    public Cateogry(Long id, String name, Cateogry parent_cateogry, int level) {
        this.id = id;
        this.name = name;
        this.parent_cateogry = parent_cateogry;
        this.level = level;
    }

    public Cateogry(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Cateogry getParent_cateogry() {
        return parent_cateogry;
    }

    public void setParent_cateogry(Cateogry parent_cateogry) {
        this.parent_cateogry = parent_cateogry;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
