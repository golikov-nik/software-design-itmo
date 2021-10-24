package ru.akirakozov.sd.refactoring.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBManager {
  public static final String DB_URL = "jdbc:sqlite:test.db";

  public static void withDatabase(final DBConsumer action) {
    try {
      try (var c = DriverManager.getConnection(DB_URL); var stmt = c.createStatement()) {
        action.apply(stmt);
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
