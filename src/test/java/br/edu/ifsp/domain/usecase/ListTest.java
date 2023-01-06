package br.edu.ifsp.domain.usecase;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ListTest {

    @Mock List<String> listMock;
    @Mock Math mathMock;

    @Test
    @DisplayName("Test List with matchers in verify calls")
    void testListWithMatchersInVerifyCalls() {
        listMock.add(anyString());
        // listMock.add(0, anyString()); // Exception! Do not mix exact values with matchers
        listMock.add(eq(10), anyString()); // Correct use. Creates a matcher with value "equal" to 0.
        verify(listMock).add(anyString()); // * It counts as a call to listMock.add(anyString())
        verify(listMock).add(anyInt(), anyString()); // anyInt could be eq(10)
        verify(listMock, atMost(2)).add(anyString()); // fails if listMock.add is called more than twice (*).
        verify(listMock, never()).size();
    }

    @Test
    @DisplayName("Test List with mocks with dummy object")
    void testListWithMocksWithDummyObject() {
        final String element = "It's better to use argument matcher";
        when(listMock.get(anyInt())).thenReturn(element); // anyInt() creates a dummy int value that works in any case
        assertThat(listMock.get(0)).isEqualTo(element); // PASS
        assertThat(listMock.get(99)).isEqualTo(element); // PASS
    }

    @Test
    @DisplayName("Test List with mocks")
    void testListWithMocks() {
        final String element = "It's better to use argument matcher";
        when(listMock.get(0)).thenReturn(element);
        assertThat(listMock.get(0)).isEqualTo(element); // PASS
        assertThat(listMock.get(99)).isEqualTo(element); // FAIL
    }

}
