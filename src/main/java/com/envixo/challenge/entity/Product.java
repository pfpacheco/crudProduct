package com.envixo.challenge.entity;


import javax.persistence.*;

@Entity
public class Product {

    @Id
    @SequenceGenerator(name = "prodSeq", sequenceName = "prodSeq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "prodSeq", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "title", columnDefinition = "varchar(255) not null")
    private String title;

    @Column(name = "description", columnDefinition = "varchar(255) not null")
    private String description;

    @Column(name = "img_path", columnDefinition = "varchar(1024)")
    private String path;

    @Column(name = "status", columnDefinition = "boolean not null")
    private Boolean status;

    @Column(name = "category", columnDefinition = "varchar(255)")
    private String category;

    @Column(name = "tag", columnDefinition = "varchar(255)")
    private String tag;
    @Column(name = "price", columnDefinition = "numeric(10,2) not null")
    private Float price;

    @Column(name = "price_comparison", columnDefinition = "numeric(10,2)")
    private Float priceComparison;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tags) {
        this.tag = tag;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getPriceComparison() {
        return priceComparison;
    }

    public void setPriceComparison(Float priceComparison) {
        this.priceComparison = priceComparison;
    }
}
