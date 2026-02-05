package sitprojekat.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import sitprojekat.model.Combo;

@Service
public class ComboService {

	@Autowired
	private HttpService httpService;

	private final String controllerPath = "/combos";

	public List<Combo> getCombos() {
		return httpService.get(httpService.API_BASE_URL + controllerPath, new ParameterizedTypeReference<>() {});
    }
	public Combo findByID(String id) {
		return httpService.get(httpService.API_BASE_URL + controllerPath + "/" + id, Combo.class);
	}
}
