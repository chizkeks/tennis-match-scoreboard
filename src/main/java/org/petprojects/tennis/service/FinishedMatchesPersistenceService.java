package org.petprojects.tennis.service;

import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceException;
import lombok.Data;
import org.petprojects.tennis.dao.MatchRepository;
import org.petprojects.tennis.dao.PlayerRepository;
import org.petprojects.tennis.dto.FinishedMatchDto;
import org.petprojects.tennis.dto.OngoingMatchDto;
import org.petprojects.tennis.entity.Match;
import org.petprojects.tennis.entity.Player;
import org.petprojects.tennis.mapper.MatchMapper;
import org.petprojects.tennis.mapper.PlayerMapper;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class FinishedMatchesPersistenceService {
    private MatchRepository matchRepository;
    private PlayerRepository playerRepository;

    public FinishedMatchesPersistenceService() {
        matchRepository = new MatchRepository();
        playerRepository = new PlayerRepository();
    }
    public List<FinishedMatchDto> getFinishedMatchesList() {
        return matchRepository.findAll().stream().map(MatchMapper::matchToFinishedMatchDto).collect(Collectors.toList());
    }
    public List<FinishedMatchDto> getFinishedMatchesByPlayerName(String name) {
        return  matchRepository.findAllByPlayerName(name).stream().map(MatchMapper::matchToFinishedMatchDto).collect(Collectors.toList());
    }
    public List<FinishedMatchDto> getFinishedMatchesWithPagination(int page, int pageSize) {
        return matchRepository.findAllWithPagination(page * pageSize, pageSize).stream().map(MatchMapper::matchToFinishedMatchDto).collect(Collectors.toList());
    }

    public List<FinishedMatchDto> getFinishedMatchesByPlayerNameWithPagination(String name, int page, int pageSize) {
        return matchRepository.findByPlayerNameWithPagination(name, page * pageSize, pageSize).stream().map(MatchMapper::matchToFinishedMatchDto).collect(Collectors.toList());
    }

    public void save(OngoingMatchDto match) throws PersistenceException {
        Player firstPlayer = PlayerMapper.dtoToPlayer(match.getFirstPlayer());
        Player secondPlayer  = PlayerMapper.dtoToPlayer(match.getSecondPlayer());

        Match newMatch = MatchMapper.ongoingMatchToMatch(match);

        //Creation of players if required
        try {
            firstPlayer = playerRepository.findByName(firstPlayer.getName());
        } catch (NoResultException e) {
            playerRepository.create(firstPlayer);
        }

        try {
            secondPlayer = playerRepository.findByName(secondPlayer.getName());
        } catch (NoResultException e) {
            playerRepository.create(secondPlayer);
        }

        newMatch.setFirstPlayer(firstPlayer);
        newMatch.setSecondPlayer(secondPlayer);
        newMatch.setWinner(firstPlayer.getName().equals(newMatch.getWinner().getName()) ? firstPlayer : secondPlayer);

        matchRepository.create(newMatch);
    }
}
