package br.edu.ifsp.domain.usecase;

import br.edu.ifsp.domain.doubles.PiutterServiceStub;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FilterTrendingTopicsTest {

    @Mock
    PiutterService piutterServiceMock;
    @InjectMocks
    FilterTrendingTopics sut;

    @Test
    @DisplayName("Should return empty list if no topics are found")
    void shouldReturnEmptyListIfNoTopicsAreFound() {
        when(piutterServiceMock.fetchTrendingTopics()).thenReturn(null);
        assertThat(sut.filterByContent("Type")).isEmpty();
    }

    @Test
    @DisplayName("Should return elements, throw, and than empty lists")
    void shouldReturnElementsThrowAndThanEmptyLists() {
        when(piutterServiceMock.fetchTrendingTopics())
                .thenReturn(List.of("Mock", "Mockito", "Mosquito"))
                .thenThrow(new IllegalStateException())
                .thenReturn(Collections.emptyList());
        assertThat(sut.filterByContent("Mock")).hasSize(2);
        assertThatIllegalStateException().isThrownBy(() -> sut.filterByContent("text"));
        assertThat(sut.filterByContent("text")).isEmpty();
        assertThat(sut.filterByContent("text")).isEmpty(); // keep the last defined expectation
    }

    @Test
    @DisplayName("Should filter topics according to filter using stub")
    void shouldFilterTopicsAccordingToFilterTextUsingStub() {
        //Given
        var piutterService = new PiutterServiceStub();
        var sut = new FilterTrendingTopics(piutterService);
        //When
        final List<String> obtained = sut.filterByContent("Jav");
        //Then
        assertThat(obtained).contains("Java", "JavaScript");
    }

    @Test
    @DisplayName("Should filter topics according to filter using mockito")
    void shouldFilterTopicsAccordingToFilterTextUsingMockito() {
        PiutterService piutterService = mock(); // static method that created the mock
        var sut = new FilterTrendingTopics(piutterService);
        //pre-program expectations
        final List<String> topics = List.of("Java", "JavaScript", "TypeScript");
        when(piutterService.fetchTrendingTopics()).thenReturn(topics);
        final List<String> obtained = sut.filterByContent("Jav");
        assertThat(obtained).contains("Java", "JavaScript");
    }

    @Test
    @DisplayName("Should fail test due to unnecessary stubbing use")
    void shouldFailTestDueToUnnecessaryStubbingUse() {
        when(piutterServiceMock.fetchTrendingTopics()).thenReturn(List.of("Hello"));
        assertThatIllegalArgumentException().isThrownBy(() -> sut.filterByContent(""));
    }
}