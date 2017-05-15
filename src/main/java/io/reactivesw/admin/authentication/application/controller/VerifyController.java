package io.reactivesw.admin.authentication.application.controller;

import io.reactivesw.admin.authentication.application.model.VerifyBody;
import io.reactivesw.admin.authentication.application.model.VerifyResult;
import io.reactivesw.admin.authentication.application.service.VerifyApplication;
import io.reactivesw.admin.authentication.infrastructure.Router;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Verify controller.
 */
@RestController
public class VerifyController {

  /**
   * LOG.
   */
  private final static Logger LOG = LoggerFactory.getLogger(VerifyController.class);

  /**
   * sign in service.
   */
  @Autowired
  private transient VerifyApplication verifyApplication;


  /**
   * Verify api.
   *
   * @return VerifyResult
   */
  @PostMapping(Router.ADMIN_SESSION_STATUS)
  public VerifyResult verify(@RequestBody VerifyBody body) {
    LOG.info("Enter. body: {}.", body);

    VerifyResult result = verifyApplication.verify(body.getToken(), body.getResource(), body
        .getAction());

    LOG.info("Exit: result: {}.", result);
    return result;
  }

}
