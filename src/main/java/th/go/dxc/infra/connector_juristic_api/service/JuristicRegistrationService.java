package th.go.dxc.infra.connector_juristic_api.service;

import th.go.dxc.infra.connector_juristic_api.model.request.RequesterDetails;
import th.go.dxc.infra.connector_juristic_api.model.response.JuristicRegistrationResponse;
import th.go.dxc.infra.connector_juristic_api.model.response.LoginResponse;

public interface JuristicRegistrationService {
	public void token(String userNin);
	public JuristicRegistrationResponse findProfile(RequesterDetails requesterDetails);
}
