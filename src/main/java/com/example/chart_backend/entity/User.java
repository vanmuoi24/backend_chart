package com.example.chart_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class User {
    @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String userFullname;
  private String userEmail;
  private String userPhone;
  private String userPassword;
  private String userRole;
}
