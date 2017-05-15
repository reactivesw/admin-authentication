package io.reactivesw.admin.authentication.application.model;

import lombok.Data;
import org.hibernate.validator.constraints.Email;

import java.util.List;

import javax.validation.constraints.NotNull;

/**
 * Admin Draft.
 */
@Data
public class AdminDraft {

  /**
   * Email of this account.
   */
  @Email
  @NotNull
  private String email;

  /**
   * Password
   */
  @NotNull
  private String password;

  /**
   * List of role ids.
   */
  private List<String> roles;
}
