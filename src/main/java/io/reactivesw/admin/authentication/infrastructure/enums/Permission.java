package io.reactivesw.admin.authentication.infrastructure.enums;

/**
 * Permissions for each module.
 */
public enum Permission {
  /**
   * Permission for save a resource.
   */
  CREATE("create"),

  /**
   * Permission for read a resource.
   */
  READ("read"),

  /**
   * Permission for update a resource.
   */
  UPDATE("update"),

  /**
   * Permission for delete a resource.
   */
  DELETE("delete");

  /**
   * Value.
   */
  private String value;

  /**
   * Constructor.
   *
   * @param value String
   */
  Permission(String value) {
    this.value = value;
  }

  /**
   * Get string value.
   *
   * @return String
   */
  public String getValue() {
    return value;
  }

  /**
   * Get Permission by string.
   *
   * @param value string
   * @return Permission
   */
  public static Permission getPermission(String value) {
    Permission result = null;
    switch (value) {
      case "save":
        result = CREATE;
        break;
      case "read":
        result = READ;
        break;
      case "update":
        result = UPDATE;
        break;
      case "delete":
        result = DELETE;
        break;
      default:
        break;
    }
    return result;
  }
}
