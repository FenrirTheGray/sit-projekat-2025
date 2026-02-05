package rs.ac.singidunum.cms.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.cms.dto.create.ArticleCreateRequestDTO;
import rs.ac.singidunum.cms.dto.response.ArticleResponseDTO;
import rs.ac.singidunum.cms.dto.update.ArticleUpdateRequestDTO;

import java.util.List;

@Service
public class ArticleService {

	private final HttpService httpService;

	public ArticleService(HttpService httpService) {
		this.httpService = httpService;
	}

	public List<ArticleResponseDTO> findAll() {
		String endpoint = httpService.API_BASE_URL + "/articles";
		return httpService.get(endpoint,
				new ParameterizedTypeReference<List<ArticleResponseDTO>>() {}, true);
	}

	public ArticleResponseDTO findByKey(String key) {
		String endpoint = httpService.API_BASE_URL + "/articles/" + key;
		return httpService.get(endpoint, ArticleResponseDTO.class, true);
	}

	public ArticleResponseDTO create(ArticleCreateRequestDTO dto) {
		String endpoint = httpService.API_BASE_URL + "/articles";
		return httpService.post(endpoint, dto, ArticleResponseDTO.class, true);
	}

	public ArticleResponseDTO update(String key, ArticleUpdateRequestDTO dto) {
		String endpoint = httpService.API_BASE_URL + "/articles";
		return httpService.put(endpoint, key, dto, ArticleResponseDTO.class, true);
	}

	public void deleteByKey(String key) {
		String endpoint = httpService.API_BASE_URL + "/articles";
		httpService.delete(endpoint, key, true);
	}
}
