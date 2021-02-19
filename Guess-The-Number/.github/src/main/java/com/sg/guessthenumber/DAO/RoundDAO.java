package com.sg.guessthenumber.DAO;

import com.sg.guessthenumber.entity.Round;

import java.util.List;

public interface RoundDAO {
    List<Round> getAllRoundsByGameId(int gameID);
    Round getRoundById(int roundId);
    Round addRound(Round round);
}
