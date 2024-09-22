package org.petprojects.tennis.service;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.petprojects.tennis.dao.BaseRepository;
import org.petprojects.tennis.dao.MatchRepository;
import org.petprojects.tennis.dto.FinishedMatchDto;
import org.petprojects.tennis.dto.OngoingMatchDto;
import org.petprojects.tennis.entity.Match;
import org.petprojects.tennis.mapper.MatchMapper;
import org.petprojects.tennis.util.HibernateUtil;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FinishedMatchesPersistenceService {
    private BaseRepository<Integer, Match> matchRepository = new MatchRepository(HibernateUtil.getSessionFactory().getCurrentSession());

    public static FinishedMatchesPersistenceService getInstance() {return FinishedMatchesPersistenceServiceHelper.singletonObject;}
    private static class FinishedMatchesPersistenceServiceHelper {
        public static FinishedMatchesPersistenceService singletonObject = new FinishedMatchesPersistenceService();
    }

    public List<FinishedMatchDto> getFinishedMatchesList() {
        return matchRepository.findAll().stream().map(MatchMapper::matchToFinishedMatchDto).collect(Collectors.toList());
    }

    public void save(OngoingMatchDto match) {
        matchRepository.create(MatchMapper.ongoingMatchToMatch(match));
    }
}
