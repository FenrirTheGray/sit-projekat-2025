package rs.ac.singidunum.servelogic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.servelogic.mapper.ArticleMapper;
import rs.ac.singidunum.servelogic.repository.IArticleRepository;
import rs.ac.singidunum.servelogic.repository.IComboRepository;
import rs.ac.singidunum.servelogic.utility.ArangoFusekiReferenceService;

@Service
public class ComboService {

    @Autowired
    private IComboRepository repo;
    @Autowired
    private ArangoFusekiReferenceService populator;
    @Autowired
    private ComboMapper mapper;
}
