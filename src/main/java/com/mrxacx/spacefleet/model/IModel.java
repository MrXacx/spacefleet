package com.mrxacx.spacefleet.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.UUID;

public abstract class IModel {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  protected UUID id;
}
