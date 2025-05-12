package th.go.dxc.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import th.go.dxc.app.model.DbdJuristicRegistration;
import th.go.dxc.app.model.DbdJuristicRegistrationFilter;
import th.go.dxc.infra.connector_juristic_api.model.request.RequesterDetails;
import th.go.dxc.infra.connector_juristic_api.model.response.JuristicRegistrationResponse;
import th.go.dxc.infra.connector_juristic_api.service.JuristicRegistrationService;
import th.go.dxc.share.commons.util.ObjectMapperService;
import th.go.dxc.share.security.service.SecurityService;

@Slf4j
public class DbdJuristicRegistationServiceJpaImpl implements DbdJuristicRegistationService {
	private JuristicRegistrationService juristicRegistrationService;
	private final MapperFacade mapperFacade;
	private final ObjectMapperService mapperService;
	private final SecurityService securityService;
	
	public DbdJuristicRegistationServiceJpaImpl(JuristicRegistrationService juristicRegistrationService, 
			MapperFacade mapperFacade, ObjectMapperService mapperService, SecurityService securityService) {
		super();
		this.juristicRegistrationService = juristicRegistrationService;
		this.mapperFacade = mapperFacade;
		this.mapperService = mapperService;
		this.securityService = securityService;
	}

	@Override
	public Page<DbdJuristicRegistration> findByOrganizationJuristicID(DbdJuristicRegistrationFilter filter, Pageable pageable) {
		Page<DbdJuristicRegistration> resultPage = null;
		DbdJuristicRegistration dbdJuristicRegistration = null;
		List<Object> resultList = new ArrayList<Object>();
		RequesterDetails requesterDetails = new RequesterDetails();
		requesterDetails.setOrganizationJuristicID(filter.getOrganizationJuristicID());
		
//		DxcUserDetails userDetails = securityService.getCurrentUser();
		log.info("securityService UserNin = " + securityService.getCurrentUser().getUserNin());
//		String userNin = securityService.getCurrentUser().getUserNin();
//		String result = juristicRegistrationService.token(userNin);
		
//		if ("Success".contains(result)) {
			JuristicRegistrationResponse juristicRegistrationResponse = juristicRegistrationService.findProfile(requesterDetails);
			log.info("profileResponse = " + juristicRegistrationResponse);
			
			if (juristicRegistrationResponse != null && juristicRegistrationResponse.getStatus().getCode() != "1000") {
				dbdJuristicRegistration = mapperFacade.map(juristicRegistrationResponse, DbdJuristicRegistration.class);
				resultList.add(dbdJuristicRegistration);
			}
//		}
		
		log.info("resultList = ", resultList);
		log.debug("resultList = ", resultList);
		resultPage = mapperService.mapSortedAndSlicedPage(resultList, pageable, DbdJuristicRegistration.class);
		return resultPage;
	}
}


