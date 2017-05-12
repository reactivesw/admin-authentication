package io.reactivesw.admin.authentication.infrastructure;

/**
 * Admin auth router.
 */
public class Router {
  /**
   * Authentication root.
   */
  public static final String AUTHENTICATION_ROOT = "/";

  /**
   * Id params.
   */
  public static final String ID = "{id}";

  /**
   * The constant AUTHENTICATION_HEALTH_CHECK.
   */
  public static final String AUTHENTICATION_HEALTH_CHECK = AUTHENTICATION_ROOT + "health";

  /**
   * Module root.
   */
  public static final String MODULE_ROOT = AUTHENTICATION_ROOT + "modules/";

  /**
   * Module with id.
   */
  public static final String MODULE_WITH_ID = MODULE_ROOT + ID;


  /**
   * Scope root.
   */
  public static final String SCOPE_ROOT = AUTHENTICATION_ROOT + "scopes/";

  /**
   * Scope with id.
   */
  public static final String SCOPE_WITH_ID = SCOPE_ROOT + ID;
}
