package rs.ac.singidunum.cms.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.cms.dto.create.ComboCreateRequestDTO;
import rs.ac.singidunum.cms.dto.response.ComboResponseDTO;
import rs.ac.singidunum.cms.dto.update.ComboUpdateRequestDTO;

import java.util.List;

@Service
public class ComboService {

	private final HttpService httpService;

	public ComboService(HttpService httpService) {
		this.httpService = httpService;
	}

	public List<ComboResponseDTO> findAll() {
		String endpoint = httpService.API_BASE_URL + "/combos";
		return httpService.get(endpoint,
				new ParameterizedTypeReference<List<ComboResponseDTO>>() {}, true);
	}

	public ComboResponseDTO findByKey(String key) {
		String endpoint = httpService.API_BASE_URL + "/combos/" + key;
		return httpService.get(endpoint, ComboResponseDTO.class, true);
	}

	public ComboResponseDTO create(ComboCreateRequestDTO dto) {
		String endpoint = httpService.API_BASE_URL + "/combos";
		return httpService.post(endpoint, dto, ComboResponseDTO.class, true);
	}

	public ComboResponseDTO update(String key, ComboUpdateRequestDTO dto) {
		String endpoint = httpService.API_BASE_URL + "/combos";
		return httpService.put(endpoint, key, dto, ComboResponseDTO.class, true);
	}

	public void deleteByKey(String key) {
		String endpoint = httpService.API_BASE_URL + "/combos";
		httpService.delete(endpoint, key, true);
	}
}
