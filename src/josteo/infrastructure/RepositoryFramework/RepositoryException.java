/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package josteo.infrastructure.RepositoryFramework;

/**
 *
 * @author cristiano
 */
public class RepositoryException extends Exception {
  public RepositoryException(Exception e) {
    super(e);
  }

  public RepositoryException() {
    super();
  }
}
