package cl.goviedo.usermanagment.entities;


import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "phone")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PhoneEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long number;
    private int cityCode;
    private String contryCode;
}
