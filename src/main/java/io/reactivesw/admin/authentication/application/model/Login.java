package io.reactivesw.admin.authentication.application.model;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;

/**
 * Info for login.
 */
public class Login {

  /**
   * Email for login.
   */
  @Email
  @NotNull
  private String email;

  /**
   * Password for login.
   */
  @NotNull
  private String password;

  /**
   * Tostring.
   * @return String
   */
  @Override
  public String toString() {
    return "Login{"
        + "email='" + email + '\''
        + '}';
  }

  /**
   * Getter.
   * @return String
   */
  public String getEmail() {
    return email;
  }

  /**
   * Setter.
   * @param email String
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Getter.
   * @return String
   */
  public String getPassword() {
    return password;
  }

  /**
   * Setter.
   * @param password String
   */
  public void setPassword(String password) {
    this.password = password;
  }
}
