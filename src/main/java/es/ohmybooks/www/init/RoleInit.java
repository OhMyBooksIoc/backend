/*TODO omple taula roles però si no es tracta d'un test només s'ha d'executar una vegada
package es.ohmybooks.www.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import es.ohmybooks.www.model.RoleModel;
import es.ohmybooks.www.repository.RoleRepository;

@Component
public class RoleInit implements ApplicationListener<ContextRefreshedEvent> {
    
  @Autowired
  RoleRepository roleRepository;

  @Override
  public void onApplicationEvent(ContextRefreshedEvent event) {
    roleRepository.save(new RoleModel("ROLE_ADMIN"));
    roleRepository.save(new RoleModel("ROLE_USER"));
    roleRepository.save(new RoleModel("ROLE_AUTHOR"));
  }
}
*/