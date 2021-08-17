package fr.epita.kesKonAVu.application.followUp;

import fr.epita.kesKonAVu.domain.followUp.ResourceFollowUp;
import fr.epita.kesKonAVu.domain.followUp.ResourceFollowUpRepository;
import fr.epita.kesKonAVu.domain.followUp.SerieFollowUp;
import fr.epita.kesKonAVu.domain.followUp.StatusEnum;
import fr.epita.kesKonAVu.domain.resource.*;
import fr.epita.kesKonAVu.domain.user.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;

@Service
public class FollowUpServiceImpl implements FollowUpService {

    @Autowired
    ResourceFollowUpRepository resourceFollowUpRepository;
    @Autowired
    ResourceRepository resourceRepository;
    @Autowired
    SerieRepository serieRepository;

    @Override
    public void createNewFollowUp(final ResourceFollowUp resourceFollowUp) {
        final String imdbId = resourceFollowUp.getResource().getExternalKey();
        final Member member = resourceFollowUp.getMember();

        /* Status to set for the creation of the ResourceFollowUp */
        StatusEnum statusToCreate = StatusEnum.VU;
        if (resourceFollowUp.getStatus() == StatusEnum.AVOIR) {
            statusToCreate = StatusEnum.AVOIR;
        }

        if (resourceFollowUp.getResource().getResourceType() != null) {


            if(resourceFollowUp.getResource().getResourceType() == ResourceTypeEnum.MOVIE) {
                /* FOR a Movie (Resource of ResourceType MOVIE) */
                Resource movie = new Resource();
                ResourceFollowUp resourceFollowUpToSave = new ResourceFollowUp();

                if (resourceRepository.findMovieByExternalKey(imdbId) == null) {
                    movie = resourceRepository.getMovieFromCatalogueByImdbId(imdbId);
                    movie = resourceRepository.save(movie);
                } else {
                    movie = resourceRepository.findMovieByExternalKey(imdbId);
                    if (resourceFollowUpRepository.findByResourceAndMember(movie,member) != null) {
                        /* ResourceFollowUp already created => skip */
                        return;
                    }
                };
                resourceFollowUpToSave.setResource(movie);
                resourceFollowUpToSave.setMember(member);
                resourceFollowUpToSave.setStatus(statusToCreate);
                resourceFollowUpToSave.setCreationDate(LocalDate.now());
                resourceFollowUpToSave.setLastModificationDate(LocalDate.now());

                resourceFollowUpRepository.save(resourceFollowUp);

            } else if (resourceFollowUp.getResource().getResourceType() == ResourceTypeEnum.SERIE) {
                /* FOR a Serie (Resource of ResourceType SERIE) */
                Serie serie = new Serie();
                SerieFollowUp serieFollowUpToSave = new SerieFollowUp();

                if (serieRepository.findByExternalKey(imdbId) == null) {
                    serie = serieRepository.getSerieFromCatalogueByImdbId(imdbId);
                    final Set<Episode> allEpisodes = serieRepository.getAllEpisodesFromCatalogue(serie);
                    serie.setEpisodes(allEpisodes);
                    serie = serieRepository.save(serie);
                } else {
                    serie = serieRepository.findByExternalKey(imdbId);
                    if (resourceFollowUpRepository.findByResourceAndMember(serie,member) != null) {
                        /* ResourceFollowUp already created => skip */
                        return;
                    }
                };
                serieFollowUpToSave.setResource(serie);
                serieFollowUpToSave.setMember(member);
                serieFollowUpToSave.setStatus(statusToCreate);
                serieFollowUpToSave.setCreationDate(LocalDate.now());
                serieFollowUpToSave.setLastModificationDate(LocalDate.now());

                resourceFollowUpRepository.save(serieFollowUpToSave);
            }
        }

    }
}
