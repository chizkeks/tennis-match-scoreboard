package org.petprojects.tennis.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


public interface BaseEntity <K extends Serializable> {
    K getId();
    void setId(K id);
}
