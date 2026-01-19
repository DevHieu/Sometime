package com.asm.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class XJpa {
  private static EntityManagerFactory factory;
  static {
    factory = Persistence.createEntityManagerFactory("SOF308_ASM");
  }

  public static EntityManager getEntityManager() {
    return factory.createEntityManager();
  }
}
