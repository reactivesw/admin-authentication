package io.reactivesw.admin.authentication.application.model;

import java.io.Serializable;

/**
 * Admin session for save each sessions of those who has login.
 */
public class AdminSession implements Serializable {

  /**
   * Auto generated serial version id.
   */
  private static final long serialVersionUID = -3600640015597918768L;

  /**
   * Login time.
   */
  private long loginTime;

  /**
   * Last visit time.
   */
  private long lastVisitTime;

  /**
   * The login status will be expired in `expiresIn`.
   */
  private long expiresIn;

  /**
   * Admin info.
   */
  private AdminView admin;

  /**
   * Constructor.
   */
  public AdminSession(long loginTime, long lastVisitTime, long expiresIn, AdminView adminView) {
    this.loginTime = loginTime;
    this.lastVisitTime = lastVisitTime;
    this.expiresIn = expiresIn;
    this.admin = adminView;
  }

  /**
   * Getter.
   *
   * @return long
   */
  public long getLoginTime() {
    return loginTime;
  }

  /**
   * Setter.
   *
   * @param loginTime long
   */
  public void setLoginTime(long loginTime) {
    this.loginTime = loginTime;
  }

  /**
   * Getter.
   *
   * @return long
   */
  public long getLastVisitTime() {
    return lastVisitTime;
  }

  /**
   * Setter.
   *
   * @param lastVisitTime long
   */
  public void setLastVisitTime(long lastVisitTime) {
    this.lastVisitTime = lastVisitTime;
  }

  /**
   * Getter.
   *
   * @return long
   */
  public long getExpiresIn() {
    return expiresIn;
  }

  /**
   * Setter.
   *
   * @param expiresIn long
   */
  public void setExpiresIn(long expiresIn) {
    this.expiresIn = expiresIn;
  }

  /**
   * Getter.
   *
   * @return AdminView
   */
  public AdminView getAdmin() {
    return admin;
  }

  /**
   * Setter.
   *
   * @param admin AdminView
   */
  public void setAdmin(AdminView admin) {
    this.admin = admin;
  }
}
