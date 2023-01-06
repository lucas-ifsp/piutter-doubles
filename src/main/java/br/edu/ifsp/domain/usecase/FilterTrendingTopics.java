package br.edu.ifsp.domain.usecase;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class FilterTrendingTopics {
    private final PiutterService piutterService; // Dependency to be injected
    public FilterTrendingTopics(PiutterService piutterService) { // Dependency injection
        this.piutterService = piutterService;
    }

    public List<String> filterByContent(String text){
        final var filterText= Objects.requireNonNull(text);

        if(filterText.isEmpty() || filterText.isBlank()) throw new IllegalArgumentException("Filter must not be black or empty");

        // Dependency. A call to the real implementation will violate unit test principle.
        final List<String> trendingTopics = piutterService.fetchTrendingTopics();

        if(trendingTopics == null) return Collections.emptyList();

        return trendingTopics.stream()
                .filter(topic -> topic.contains(filterText))
                .toList();
    }
}
