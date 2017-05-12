package io.reactivesw.admin.authentication.application.service;

import org.springframework.stereotype.Service;

/**
 * Verify application for verify each token's login status and permissions.
 */
@Service
public class VerifyApplication {

  /**
   * LOG.
   */
//  private final static Logger LOG = LoggerFactory.getLogger(VerifyApplication.class);

  /**
   * Auth cache key.
   */
  public final static String AUTH_KEY = "admin:auth:";

  /**
   * Redis ops. cache cluster should be used.
   */
//  @Autowired
//  private transient RedisTemplate redisTemplate;

  /**
   * JWT(json web token) update
   */
//  @Autowired
//  private transient JwtUtil jwtUtil;
}
