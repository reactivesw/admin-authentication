package io.reactivesw.admin.authentication.application.service;

import io.reactivesw.admin.authentication.application.model.AdminSession;
import io.reactivesw.admin.authentication.application.model.AdminView;
import io.reactivesw.admin.authentication.application.model.Login;
import io.reactivesw.admin.authentication.application.model.LoginResult;
import io.reactivesw.admin.authentication.application.model.mapper.AdminMapper;
import io.reactivesw.admin.authentication.domain.model.Admin;
import io.reactivesw.admin.authentication.domain.service.AdminService;
import io.reactivesw.admin.authentication.infrastructure.configuration.AppConfig;
import io.reactivesw.admin.authentication.infrastructure.util.PasswordUtil;
import io.reactivesw.authentication.JwtUtil;
import io.reactivesw.authentication.TokenType;
import io.reactivesw.exception.PasswordErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * Login applications.
 */
@Service
public class LoginApplication {

  /**
   * LOG.
   */
  private final static Logger LOG = LoggerFactory.getLogger(LoginApplication.class);

  /**
   * Admin service.
   */
  @Autowired
  private transient AdminService adminService;

  /**
   * JWT(json web token) update
   */
  @Autowired
  private transient JwtUtil jwtUtil;

  /**
   * Redis ops.
   */
  @Autowired
  private transient RedisTemplate redisTemplate;

  /**
   * App config.
   */
  @Autowired
  private transient AppConfig appConfig;

  /**
   * Login with email and password.
   *
   * @param login Login info
   * @return LoginResult
   */
  public LoginResult login(Login login) {
    LOG.debug("Enter. login: {}.", login);

    Admin admin = adminService.getByEmail(login.getEmail());

    boolean isPwdCorrect = PasswordUtil.checkPassword(login.getPassword(), admin.getPassword());
    if (!isPwdCorrect) {
      throw new PasswordErrorException("Password or email not correct.");
    }

    String token = jwtUtil.generateToken(TokenType.ADMIN, admin.getId());
    LoginResult result = new LoginResult();
    result.setToken(token);

    AdminView adminView = AdminMapper.toModel(admin);
    result.setAdminView(adminView);

    cacheLoginStatus(result);
    LOG.debug("Enter. loginResult: {}.", result);
    return result;
  }


  /**
   * Cache login result.
   *
   * @param result login result.
   */
  private void cacheLoginStatus(LoginResult result) {
    LOG.debug("Enter. loginResult: {}", result);

    String adminId = result.getAdminView().getId();
    String mapKey = VerifyApplication.AUTH_KEY + adminId;
    String fieldKey = jwtUtil.parseToken(result.getToken()).getTokenId();

    long curTime = System.currentTimeMillis();
    AdminSession adminSession = new AdminSession(curTime, curTime, appConfig.getExpiresIn(),
        result.getAdminView());

    redisTemplate.boundHashOps(mapKey).put(fieldKey, adminSession);

    LOG.debug("Exit. adminSession: {}", adminSession);
  }
}
