package pl.babel.hello;

import org.springframework.stereotype.Service;
import pl.babel.lang.Lang;
import pl.babel.lang.LangRepository;

import java.util.Optional;

@Service
class HelloService {
    private static final String FALLBACK_NAME = "world";
    private static final Lang FALLBACK_LANG = new Lang(1, "Hello", "en");

    private LangRepository repository;

    HelloService(LangRepository repository) {
        this.repository = repository;
    }

    String prepareGreeting(String name, Integer langId) {
        langId = Optional.ofNullable(langId).orElse(FALLBACK_LANG.getId());
        var welcomeMsg = repository.findById(langId).orElse(FALLBACK_LANG).getWelcomeMsg();
        var nameToWelcome = Optional.ofNullable(name).orElse(FALLBACK_NAME);
        return welcomeMsg + " " + nameToWelcome + "!";
    }
}
