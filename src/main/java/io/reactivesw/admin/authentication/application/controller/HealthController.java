package io.reactivesw.admin.authentication.application.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static io.reactivesw.admin.authentication.infrastructure.Router.AUTHENTICATION_HEALTH_CHECK;


/**
 * Health api.
 */
@RestController
@CrossOrigin
public class HealthController {

  /**
   * Service name.
   */
  @Value("${spring.application.name}")
  private transient String serviceName;

  /**
   * This api is used for health check.
   *
   * @return service name.
   */
  @GetMapping(AUTHENTICATION_HEALTH_CHECK)
  public String health() {
    return serviceName + ", system time: " + System.currentTimeMillis();
  }
}
