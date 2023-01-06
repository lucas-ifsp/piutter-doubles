package br.edu.ifsp.domain.usecase;

import java.util.List;
import java.util.UUID;

public interface PiutterService {

    boolean piueet(UUID userUuid, String text); // send a piueet and return true if succeeded. Otherwise, return false.

    List<String>  fetchTrendingTopics(); // gets the list of trending topics of the day
}
