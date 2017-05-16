package io.reactivesw.admin.authentication.application.service;

import io.reactivesw.admin.authentication.application.model.AdminSession;
import io.reactivesw.admin.authentication.application.model.ModuleView;
import io.reactivesw.admin.authentication.application.model.ScopeView;
import io.reactivesw.admin.authentication.application.model.VerifyResult;
import io.reactivesw.admin.authentication.infrastructure.enums.Permission;
import io.reactivesw.authentication.JwtUtil;
import io.reactivesw.authentication.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Verify application for verify each token's login status and permissions.
 */
@Service
public class VerifyApplication {

  /**
   * LOG.
   */
  private final static Logger LOG = LoggerFactory.getLogger(VerifyApplication.class);

  /**
   * Auth cache key.
   */
  public final static String AUTH_KEY = "admin:auth:";

  /**
   * Scope field key.
   */
  public final static String SCOPE_FIELD_KEY = "scopes";

  /**
   * Redis ops. cache cluster should be used.
   */
  @Autowired
  private transient RedisTemplate redisTemplate;

  /**
   * Admin application.
   */
  @Autowired
  private transient AdminApplication adminApplication;

  /**
   * JWT(json web token) update
   */
  @Autowired
  private transient JwtUtil jwtUtil;

  /**
   * Verify the token.
   *
   * @param tokenStr String
   * @param resource String
   * @param action   String
   * @return boolean
   */
  public VerifyResult verify(String tokenStr, String resource, String action) {
    LOG.debug("Enter. tokenStr: {}, resource: {}, action: {}.", tokenStr, resource, action);

    Token token = jwtUtil.parseToken(tokenStr);
    String adminId = token.getSubjectId();
    String tokenId = token.getTokenId();

    VerifyResult result = new VerifyResult();
    result.setAdminId(adminId);
    result.setLogin(verifyLoginStatus(adminId, tokenId));
    result.setHashPermission(verifyPermission(adminId, resource, action));

    LOG.debug("Exit. result: {}.", result);
    return result;
  }

  /**
   * Verify login status.
   *
   * @param adminId String
   * @return boolean
   */
  public boolean verifyLoginStatus(String adminId, String tokenId) {
    LOG.debug("Enter. adminId: {}, tokenId.", adminId, tokenId);

    String mapKey = AUTH_KEY + adminId;
    AdminSession adminSession = (AdminSession) redisTemplate.boundHashOps(mapKey).get(tokenId);

    boolean result = false;
    if (adminSession != null) {
      // be careful, keep all authentication machine in the same time.
      long lifeTime = adminSession.getLastVisitTime() + adminSession.getExpiresIn();
      long curTime = System.currentTimeMillis();
      if (curTime < lifeTime) {
        result = true;
        this.updateSession(mapKey, tokenId, adminSession);
      }
    }
    LOG.debug("Exit. result: {}.", result);
    return result;
  }

  /**
   * Verify if the session got the right permission.
   *
   * @param adminId  String
   * @param resource resource path
   * @param action   action
   * @return boolean
   */
  public boolean verifyPermission(String adminId, String resource, String action) {
    LOG.debug("Enter. adminId: {}, resource: {}, action: {}.", adminId, resource, action);


    boolean result = false;
    Permission permissionNeeded = Permission.getPermission(action);
    List<Permission> permissions = getPermissionOwned(adminId, resource);
    if (permissions.contains(permissionNeeded)) {
      result = true;
    }

    LOG.debug("Exit. result: {}.", result);
    return result;
  }

  /**
   * Get all permissions.
   *
   * @param adminId  AdminSession
   * @param resource resource path
   * @return list of permission
   */
  private List<Permission> getPermissionOwned(String adminId, String resource) {
    LOG.debug("Enter. adminId: {}, resource: {}.", adminId, resource);

    List<ScopeView> scopes = getScopes(adminId);

    List<Permission> result = new ArrayList<>();
    scopes.stream().forEach(
        scopeView -> {
          List<Permission> permissions = scopeView.getPermissions();

          List<String> paths = new ArrayList<String>();
          List<ModuleView> moduleViews = scopeView.getModuleViews();
          moduleViews.stream().forEach(
              moduleView -> paths.add(moduleView.getPath())
          );

          if (paths.contains(resource)) {
            result.addAll(permissions);
          }
        }
    );
    LOG.debug("Exit. permissions size: {}.", result.size());
    LOG.debug("Permissions: {}.", result);

    return result;
  }

  /**
   * Get all scopes of an admin.
   *
   * @param adminId String
   * @return List of scope view
   */
  private List<ScopeView> getScopes(String adminId) {
    LOG.debug("Enter. adminId: {}.", adminId);

    String mapKey = AUTH_KEY + adminId;
    List<ScopeView> scopes = (List) redisTemplate.boundHashOps(mapKey).get(SCOPE_FIELD_KEY);
    if (scopes == null || scopes.isEmpty()) {
      scopes = adminApplication.getScopes(adminId);
      redisTemplate.boundHashOps(mapKey).put(SCOPE_FIELD_KEY, scopes);
    }

    LOG.debug("Exit. scope size: {}.", scopes.size());
    LOG.debug("Scope: {}.", scopes);
    return scopes;
  }

  /**
   * For each time the admin visit any service, we update the last visit time build his/her sign in
   * status.
   *
   * @param mapKey       String
   * @param fieldKey     String
   * @param adminSession session
   */
  private void updateSession(String mapKey, String fieldKey, AdminSession adminSession) {
    LOG.debug("Enter: mapKey: {}, fieldKey: {}, adminSession: {}", mapKey, fieldKey,
        adminSession);

    adminSession.setLastVisitTime(System.currentTimeMillis());

    redisTemplate.boundHashOps(mapKey).put(fieldKey, adminSession);

    LOG.debug("Enter: mapKey: {}, fieldKey: {}, adminSession: {}", mapKey, fieldKey,
        adminSession);
  }


}
