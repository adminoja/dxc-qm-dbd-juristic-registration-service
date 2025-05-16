package th.go.dxc.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import th.go.dxc.app.model.DbdJuristicRegistration;
import th.go.dxc.app.model.DbdJuristicRegistrationFilter;
import th.go.dxc.app.model.DbdJuristicRegistration.OrganizationJuristicObjective;
import th.go.dxc.infra.connector_juristic_api.model.request.RequesterDetails;
import th.go.dxc.infra.connector_juristic_api.model.response.JuristicRegistrationResponse;
import th.go.dxc.infra.connector_juristic_api.service.JuristicRegistrationService;
import th.go.dxc.share.commons.util.ObjectMapperService;

@Slf4j
public class DbdJuristicRegistationServiceJpaImpl implements DbdJuristicRegistationService {
	private JuristicRegistrationService juristicRegistrationService;
	private final MapperFacade mapperFacade;
	private final ObjectMapperService mapperService;
	
	public DbdJuristicRegistationServiceJpaImpl(JuristicRegistrationService juristicRegistrationService, 
			MapperFacade mapperFacade, ObjectMapperService mapperService) {
		super();
		this.juristicRegistrationService = juristicRegistrationService;
		this.mapperFacade = mapperFacade;
		this.mapperService = mapperService;
	}

	@Override
	public Page<DbdJuristicRegistration> findByOrganizationJuristicID(DbdJuristicRegistrationFilter filter, Pageable pageable) {
		Page<DbdJuristicRegistration> resultPage = null;
		DbdJuristicRegistration dbdJuristicRegistration = null;
		List<Object> resultList = new ArrayList<Object>();
		RequesterDetails requesterDetails = new RequesterDetails();
		requesterDetails.setOrganizationJuristicID(filter.getOrganizationJuristicID());
		
		JuristicRegistrationResponse juristicRegistrationResponse = juristicRegistrationService.findProfile(requesterDetails);
		if (juristicRegistrationResponse != null && juristicRegistrationResponse.getStatus().getCode() != "1000") {
			dbdJuristicRegistration = mapperFacade.map(juristicRegistrationResponse, DbdJuristicRegistration.class);
			resultList.add(dbdJuristicRegistration);
		}
		
		resultPage = mapperService.mapSortedAndSlicedPage(resultList, pageable, DbdJuristicRegistration.class);
		resultPage = resultPage.map((m) -> {
			List<OrganizationJuristicObjective> juristicObjective = m.getData().getOrganizationJuristicPerson().getOrganizationJuristicObjective();
			
			if (juristicObjective != null) {
				for (OrganizationJuristicObjective obj : juristicObjective) {
					if ("R".equals(obj.getJuristicObjective())) {
						obj.setJuristicObjective("วัตถุประสงค์ตอนจัดตั้ง");
					} else if ("F".equals(obj.getJuristicObjective())) {
						obj.setJuristicObjective("วัตถุประสงค์ที่ยื่นงบการเงินปีล่าสุด");
					}
				}
			}
			
			return m;
		});
		
		log.info("Success resultPage ");
		return resultPage;
	}
	
}
