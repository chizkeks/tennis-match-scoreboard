package org.petprojects.tennis.service;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.petprojects.tennis.dto.OngoingMatchDto;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OngoingMatchesService {
    public static OngoingMatchesService getInstance() {return OngoingMatchesServiceHelper.singletonObject;}
    private static class OngoingMatchesServiceHelper {
        public static OngoingMatchesService singletonObject = new OngoingMatchesService();
    }

    private Map<UUID, OngoingMatchDto> currentMatches = new HashMap<>();

    public UUID addMatch(OngoingMatchDto match){
        UUID uuid = UUID.randomUUID();
        currentMatches.put(uuid, match);
        return uuid;
    }

    public OngoingMatchDto getMatch(UUID id){
        return currentMatches.get(id);
    }
}
