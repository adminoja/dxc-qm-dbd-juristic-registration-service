package th.go.dxc.share.util.mapstruct;

import org.mapstruct.Mapper;

import th.go.dxc.app.model.DbdJuristicRegistration;
import th.go.dxc.infra.connector_juristic_api.model.response.JuristicRegistrationResponse;

@Mapper(componentModel = "spring")
public interface MapperFacade {
    public DbdJuristicRegistration toDbdJuristicRegistration(JuristicRegistrationResponse model);
}
