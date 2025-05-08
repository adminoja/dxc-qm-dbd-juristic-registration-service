package th.go.dxc.app.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import th.go.dxc.app.model.DbdJuristicRegistration;
import th.go.dxc.app.model.DbdJuristicRegistrationFilter;

public interface DbdJuristicRegistationService {

	public Page<DbdJuristicRegistration> findByOrganizationJuristicID(DbdJuristicRegistrationFilter filter, Pageable pageable);

}
