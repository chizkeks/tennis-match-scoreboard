package org.petprojects.tennis.mapper;


import org.petprojects.tennis.dto.FinishedMatchDto;
import org.petprojects.tennis.dto.OngoingMatchDto;
import org.petprojects.tennis.entity.Match;

public class MatchMapper {
    public static Match ongoingMatchToMatch(OngoingMatchDto match) {
        Match m = new Match();
        m.setFirstPlayer(PlayerMapper.dtoToPlayer(match.getFirstPlayer()));
        m.setSecondPlayer(PlayerMapper.dtoToPlayer(match.getSecondPlayer()));
        m.setWinner(PlayerMapper.dtoToPlayer(match.getWinner()));
        return m;
    }

    public static FinishedMatchDto matchToFinishedMatchDto(Match match) {
        return FinishedMatchDto.builder()
                        .id(match.getId())
                                .firstPlayer(PlayerMapper.playerToDto(match.getFirstPlayer()))
                                        .secondPlayer(PlayerMapper.playerToDto(match.getSecondPlayer()))
                                                .winner(PlayerMapper.playerToDto(match.getWinner()))
                                                        .build();
    }
}
