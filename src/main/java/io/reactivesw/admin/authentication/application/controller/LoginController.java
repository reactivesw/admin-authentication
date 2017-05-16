package io.reactivesw.admin.authentication.application.controller;

import io.reactivesw.admin.authentication.application.model.Login;
import io.reactivesw.admin.authentication.application.model.LoginResult;
import io.reactivesw.admin.authentication.application.service.LoginApplication;
import io.reactivesw.admin.authentication.infrastructure.Router;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import javax.validation.Valid;

/**
 * Login controller.
 */
@RestController
@CrossOrigin
public class LoginController {

  /**
   * LOG.
   */
  private final static Logger LOG = LoggerFactory.getLogger(LoginController.class);

  /**
   * sign in service.
   */
  @Autowired
  private transient LoginApplication loginApplication;


  /**
   * sign in with email.
   *
   * @param login SignIn
   * @return LoginResult
   */
  @PostMapping(value = Router.ADMIN_LOGIN)
  public LoginResult signInWithEmail(@RequestBody @Valid Login login) throws IOException {
    LOG.info("Enter. login: {}.", login);

    LoginResult result = loginApplication.login(login);

    LOG.info("Exit: loginResult: {}.", result);
    return result;
  }

}
