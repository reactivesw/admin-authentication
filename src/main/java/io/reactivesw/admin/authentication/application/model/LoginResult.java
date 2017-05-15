package io.reactivesw.admin.authentication.application.model;

import lombok.Data;

/**
 * Login result.
 */
@Data
public class LoginResult {

  /**
   * AdminView.
   */
  private AdminView adminView;

  /**
   * String token.
   */
  private String token;
}
