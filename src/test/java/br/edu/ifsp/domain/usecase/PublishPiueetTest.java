package br.edu.ifsp.domain.usecase;

import br.edu.ifsp.domain.doubles.PiutterServiceStub;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PublishPiueetTest {
    @Mock PiutterService piutterServiceMock;
    @InjectMocks PublishPiueet sut;
    final UUID userUuid = UUID.randomUUID();

    @Test
    @DisplayName("Should return false if service can not publish a piuweet")
    void shouldReturnFalseIfServiceCanNotPublishAPiuweet() {
        when(piutterServiceMock.piuweet(userUuid, "Hello!")).thenReturn(false);
        final boolean obtained = sut.sent(userUuid, "Hello!");
        verify(piutterServiceMock, times(1)).piuweet(userUuid, "Hello!");
        assertThat(obtained).isFalse();
    }

}