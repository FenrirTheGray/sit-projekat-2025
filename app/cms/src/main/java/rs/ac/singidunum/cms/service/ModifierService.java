package rs.ac.singidunum.cms.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.cms.dto.create.ModifierCreateRequestDTO;
import rs.ac.singidunum.cms.dto.response.ModifierResponseDTO;
import rs.ac.singidunum.cms.dto.update.ModifierUpdateRequestDTO;

import java.util.List;

@Service
public class ModifierService {

	private final HttpService httpService;

	public ModifierService(HttpService httpService) {
		this.httpService = httpService;
	}

	public List<ModifierResponseDTO> findAll() {
		String endpoint = httpService.API_BASE_URL + "/modifiers";
		return httpService.get(endpoint,
				new ParameterizedTypeReference<List<ModifierResponseDTO>>() {}, true);
	}

	public ModifierResponseDTO findByKey(String key) {
		String endpoint = httpService.API_BASE_URL + "/modifiers/" + key;
		return httpService.get(endpoint, ModifierResponseDTO.class, true);
	}

	public ModifierResponseDTO create(ModifierCreateRequestDTO dto) {
		String endpoint = httpService.API_BASE_URL + "/modifiers";
		return httpService.post(endpoint, dto, ModifierResponseDTO.class, true);
	}

	public ModifierResponseDTO update(String key, ModifierUpdateRequestDTO dto) {
		String endpoint = httpService.API_BASE_URL + "/modifiers";
		return httpService.put(endpoint, key, dto, ModifierResponseDTO.class, true);
	}

	public void deleteByKey(String key) {
		String endpoint = httpService.API_BASE_URL + "/modifiers";
		httpService.delete(endpoint, key, true);
	}
}
