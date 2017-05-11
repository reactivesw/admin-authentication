package io.reactivesw.admin.authentication.domain.service;

import io.reactivesw.admin.authentication.domain.model.Scope;
import io.reactivesw.admin.authentication.infrastructure.repository.ScopeRepository;
import io.reactivesw.exception.NotExistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Scope service, used to manage scopes.
 */
@Service
public class ScopeService {

  /**
   * LOG.
   */
  private static final Logger LOG = LoggerFactory.getLogger(ScopeService.class);

  /**
   * Scope repository.
   */
  @Autowired
  private transient ScopeRepository scopeRepository;

  /**
   * Create module with sample.
   *
   * @param scope Scope
   * @return Scope
   */
  public Scope save(Scope scope) {
    LOG.debug("Enter. scope: {}.", scope);

    Scope savedScope = scopeRepository.save(scope);

    LOG.debug("Exit. scope: {}.", savedScope);

    return savedScope;
  }

  /**
   * Get scope by id.
   *
   * @param id String
   * @return Scope
   */
  public Scope getById(String id) {
    LOG.debug("Enter. id: {}.", id);

    Scope scope = scopeRepository.findOne(id);
    if (scope == null) {
      throw new NotExistException("Scope not exist. id: " + id);
    }

    LOG.debug("Exit. scope: {}.", scope);

    return scope;
  }
}
