package rs.ac.singidunum.cms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.cms.dto.create.CategoryCreateRequestDTO;
import rs.ac.singidunum.cms.dto.response.CategoryResponseDTO;
import rs.ac.singidunum.cms.dto.update.CategoryUpdateRequestDTO;

import java.util.List;

@Service
public class CategoryService {

	@Autowired
	private HttpService httpService;

	public List<CategoryResponseDTO> findAll() {
		String endpoint = httpService.API_BASE_URL + "/categories";
		return httpService.get(endpoint,
				new ParameterizedTypeReference<List<CategoryResponseDTO>>() {}, true);
	}

	public CategoryResponseDTO findById(String id) {
		return httpService.get(httpService.API_BASE_URL + "/categories/" + id,
				CategoryResponseDTO.class, true);
	}

	public CategoryResponseDTO create(CategoryCreateRequestDTO dto) {
		return httpService.post(httpService.API_BASE_URL + "/categories",
				dto, CategoryResponseDTO.class, true);
	}

	public CategoryResponseDTO update(String id, CategoryUpdateRequestDTO dto) {
		return httpService.put(httpService.API_BASE_URL + "/categories", id,
				dto, CategoryResponseDTO.class, true);
	}

	public void deleteById(String id) {
		httpService.delete(httpService.API_BASE_URL + "/categories", id, true);
	}
}
