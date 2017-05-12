package io.reactivesw.admin.authentication.domain.service;

import io.reactivesw.admin.authentication.domain.model.Scope;
import io.reactivesw.admin.authentication.infrastructure.repository.ScopeRepository;
import io.reactivesw.exception.NotExistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

  /**
   * Get all scopes from database.
   *
   * @return List of scope
   */
  public List<Scope> getAll() {
    LOG.debug("Enter.");

    List<Scope> scopes = scopeRepository.findAll();

    LOG.debug("Exit. scopes size: {}.", scopes.size());
    LOG.trace("Scopes: {}.", scopes);

    return scopes;
  }

  /**
   * Get scopes by id list
   *
   * @param ids list of ids
   * @return list of scope
   */
  public List<Scope> getListById(List<String> ids) {
    LOG.debug("Enter. ids: {}.", ids);

    List<Scope> scopes = new ArrayList<>();
    ids.stream().forEach(
        id -> scopes.add(getById(id))
    );

    LOG.debug("Exit. scopeSize: {}.", scopes.size());
    LOG.trace("ScopeList: {}.", scopes);
    return scopes;
  }

}
