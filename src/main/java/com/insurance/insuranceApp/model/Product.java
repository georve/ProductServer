package com.insurance.insuranceApp.model;




import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;
/**
 *
 * Product is an entity used to define a product in
 * insurance business and its has children
 * @author georman.calderon georve@gmail.com
 */
@Entity
@Table(name="PRODUCT")
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernate_lazy_initializer", "handler"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Product {

    @Id
    @Column(name="id",unique=true,nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @EqualsAndHashCode.Include
    private Integer Id;
    @Getter
    @Setter
    @Column(name="name",nullable = false)
    private String name;

    @Getter
    @Setter
    @Column(name="level")
    private Integer level;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY,optional=false)
    @JoinColumn(name = "parentid",nullable = true)
    private Product parent;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "parent")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Setter
    private Set<Product> children;

    @JsonIgnore
    public Set<Product> getChildren() {
        return children;
    }

}
