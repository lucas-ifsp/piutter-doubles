package br.edu.ifsp.domain.usecase;

import java.util.List;
import java.util.UUID;

public interface PiutterService {
    boolean piuweet(UUID userUuid, String text); // send a piuweet and return true if succeeded. Otherwise, return false.
    List<String>  fetchTrendingTopics(); // gets the list of trending topics of the day
}
