package org.petprojects.tennis.service;

import lombok.*;
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

    @Getter(AccessLevel.PRIVATE)
    @Setter(AccessLevel.PRIVATE)
    private Map<UUID, OngoingMatchDto> currentMatches = new HashMap<>();

    public UUID addMatch(OngoingMatchDto match){
        UUID uuid = UUID.randomUUID();
        currentMatches.put(uuid, match);
        return uuid;
    }

    public OngoingMatchDto getMatch(UUID id){
        return currentMatches.get(id);
    }

    public void removeMatch(UUID id){
        currentMatches.remove(id);
    }
}
