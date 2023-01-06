package br.edu.ifsp.domain.doubles;

import br.edu.ifsp.domain.usecase.PiutterService;

import java.util.List;
import java.util.UUID;

public class PiutterServiceStub implements PiutterService {

    @Override
    public boolean piueet(UUID userUuid, String text) {
        return true;
    }

    @Override
    public List<String> fetchTrendingTopics() {
        return List.of("Java", "JavaScript", "TypeScript");
    }
}
