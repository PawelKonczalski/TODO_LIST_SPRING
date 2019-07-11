package pl.babel.lang;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
class LangService {
    private LangRepository langRepository;

    LangService(LangRepository langRepository) {
        this.langRepository = langRepository;
    }

    List<LangDTO> findAll(){
        return langRepository
                .findAll()
                .stream()
                .map(LangDTO::new)
                .collect(Collectors.toList());
    }
}