package io.reactivesw.admin.authentication.infrastructure.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Password util.
 */
public final class PasswordUtil {

  /**
   * Encrypt tool.
   */
  private static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

  /**
   * Private default constructor.
   */
  private PasswordUtil() {
  }

  /**
   * Hash raw password.
   *
   * @param password raw password.
   * @return hashed password
   */
  public static String hashPassword(String password) {
    return bCryptPasswordEncoder.encode(password);
  }

  /**
   * Check if the password if correct.
   *
   * @param originalPassword raw password
   * @param hashedPassword   hashed password
   * @return result.
   */
  public static boolean checkPassword(String originalPassword, String hashedPassword) {
    return bCryptPasswordEncoder.matches(originalPassword, hashedPassword);
  }

}
