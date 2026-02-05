package rs.ac.singidunum.cms.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.cms.dto.create.ModifierTypeCreateRequestDTO;
import rs.ac.singidunum.cms.dto.response.ModifierTypeResponseDTO;
import rs.ac.singidunum.cms.dto.update.ModifierTypeUpdateRequestDTO;

import java.util.List;

@Service
public class ModifierTypeService {

	private final HttpService httpService;

	public ModifierTypeService(HttpService httpService) {
		this.httpService = httpService;
	}

	public List<ModifierTypeResponseDTO> findAll() {
		String endpoint = httpService.API_BASE_URL + "/modifiertypes";
		return httpService.get(endpoint,
				new ParameterizedTypeReference<List<ModifierTypeResponseDTO>>() {}, true);
	}

	public ModifierTypeResponseDTO findById(String id) {
		String endpoint = httpService.API_BASE_URL + "/modifiertypes/" + id;
		return httpService.get(endpoint, ModifierTypeResponseDTO.class, true);
	}

	public ModifierTypeResponseDTO create(ModifierTypeCreateRequestDTO dto) {
		String endpoint = httpService.API_BASE_URL + "/modifiertypes";
		return httpService.post(endpoint, dto, ModifierTypeResponseDTO.class, true);
	}

	public ModifierTypeResponseDTO update(String id, ModifierTypeUpdateRequestDTO dto) {
		String endpoint = httpService.API_BASE_URL + "/modifiertypes";
		return httpService.put(endpoint, id, dto, ModifierTypeResponseDTO.class, true);
	}

	public void deleteById(String id) {
		String endpoint = httpService.API_BASE_URL + "/modifiertypes";
		httpService.delete(endpoint, id, true);
	}
}
