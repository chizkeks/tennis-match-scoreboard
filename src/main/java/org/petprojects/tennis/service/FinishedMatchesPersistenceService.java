package org.petprojects.tennis.service;

import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceException;
import lombok.Data;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.petprojects.tennis.dao.MatchRepository;
import org.petprojects.tennis.dao.PlayerRepository;
import org.petprojects.tennis.dto.FinishedMatchDto;
import org.petprojects.tennis.dto.OngoingMatchDto;
import org.petprojects.tennis.entity.Match;
import org.petprojects.tennis.entity.Player;
import org.petprojects.tennis.mapper.MatchMapper;
import org.petprojects.tennis.mapper.PlayerMapper;
import org.petprojects.tennis.util.HibernateUtil;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class FinishedMatchesPersistenceService {
    private Session session;
    private MatchRepository matchRepository;
    final PlayerRepository playerRepository;

    public static FinishedMatchesPersistenceService getInstance() {return FinishedMatchesPersistenceServiceHelper.singletonObject;}
    private static class FinishedMatchesPersistenceServiceHelper {
        public static FinishedMatchesPersistenceService singletonObject = new FinishedMatchesPersistenceService();
    }

    private FinishedMatchesPersistenceService() {
        this.session = HibernateUtil.getSessionFactory().openSession();
        this.matchRepository = new MatchRepository(session);
        this.playerRepository = new PlayerRepository(session);
    }
    public List<FinishedMatchDto> getFinishedMatchesList() {
        return matchRepository.findAll().stream().map(MatchMapper::matchToFinishedMatchDto).collect(Collectors.toList());
    }

    public List<FinishedMatchDto> getFinishedMatchesByPlayerNameWithPagination(String name, int page, int pageSize) {
        return matchRepository.findByPlayerNameWithPagination(name, page * pageSize, pageSize).stream().map(MatchMapper::matchToFinishedMatchDto).collect(Collectors.toList());
    }

    public void save(OngoingMatchDto match) {

        Transaction transaction = session.beginTransaction();
        try {
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

            try {
                transaction.commit();
            } catch (Exception e) {
                System.out.println("Commit was unsuccessful");
                transaction.rollback();
            }
        } catch (PersistenceException e) {
            System.err.println("Error during transaction: " + e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;  // rethrow exception if necessary
        }
    }
}
