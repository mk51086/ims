package com.ims.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "report", schema = "ims")
public class Report {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    private String name;
    private String description;
    @OneToMany(mappedBy = "id")
    private List<InventoryItem> items;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

}