package fr.epita.kesKonAVu.domain.SerieProgression;

import fr.epita.kesKonAVu.domain.episodeFollowUp.EpisodeFollowUp;

import java.util.Set;

public interface EpisodeFollowUpSaveProgressionDomainService {

    String saveSerieprogression(Set<EpisodeFollowUp> listIn);
}
