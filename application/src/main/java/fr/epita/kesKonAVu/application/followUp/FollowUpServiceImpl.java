package fr.epita.kesKonAVu.application.followUp;

import fr.epita.kesKonAVu.domain.catalogue.CatalogueService;
import fr.epita.kesKonAVu.domain.common.AlreadyExistingException;
import fr.epita.kesKonAVu.domain.common.NotFoundException;
import fr.epita.kesKonAVu.domain.followUp.FollowUp;
import fr.epita.kesKonAVu.domain.followUp.FollowUpRepository;
import fr.epita.kesKonAVu.domain.followUp.SerieFollowUp;
import fr.epita.kesKonAVu.domain.followUp.StatusEnum;
import fr.epita.kesKonAVu.domain.resource.*;
import fr.epita.kesKonAVu.domain.user.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Set;

@Service
public class FollowUpServiceImpl implements FollowUpService {

    @Autowired
    FollowUpRepository followUpRepository;
    @Autowired
    CatalogueService catalogueService;
    @Autowired
    ResourceRepository resourceRepository;
    @Autowired
    SerieRepository serieRepository;

    @Override
    @Transactional
    public FollowUp createNewFollowUp(final FollowUp resourceFollowUp) {
        final String imdbId = resourceFollowUp.getResource().getExternalKey();
        final Member member = resourceFollowUp.getMember();

        /* Status to set for the creation of the FollowUp */
        StatusEnum statusToCreate = StatusEnum.VU;
        if (resourceFollowUp.getStatus() == StatusEnum.AVOIR) {
            statusToCreate = StatusEnum.AVOIR;
        }

        if (resourceFollowUp.getResource().getResourceType() != null) {


            if(resourceFollowUp.getResource().getResourceType() == ResourceTypeEnum.MOVIE) {
                /* FOR a Movie (Resource of ResourceType MOVIE) */
                Resource movie;
                FollowUp resourceFollowUpToSave = new FollowUp();

                try {
                    movie = resourceRepository.findMovieByExternalKey(imdbId);
                    if (followUpRepository.findByResourceAndMember(movie,member) != null) {
                        /* FollowUp already created => skip */
                        throw new AlreadyExistingException("ressourceFollowUp already exists");
                    }
                } catch (NotFoundException e) {
                    movie = catalogueService.findMovieByImdbId(imdbId);
                    movie.setCreationDate(LocalDate.now());
                    movie = resourceRepository.save(movie);
                }

                resourceFollowUpToSave.setResource(movie);
                resourceFollowUpToSave.setMember(member);
                resourceFollowUpToSave.setStatus(statusToCreate);
                resourceFollowUpToSave.setCreationDate(LocalDate.now());
                resourceFollowUpToSave.setLastModificationDate(LocalDate.now());

                return followUpRepository.save(resourceFollowUpToSave);

            } else if (resourceFollowUp.getResource().getResourceType() == ResourceTypeEnum.SERIE) {
                /* FOR a Serie (Resource of ResourceType SERIE) */
                Serie serie;
                SerieFollowUp serieFollowUpToSave = new SerieFollowUp();

                try {
                    serie = serieRepository.findByExternalKey(imdbId);
                    if (followUpRepository.findByResourceAndMember(serie,member) != null) {
                        /* FollowUp already created => skip */
                        throw new AlreadyExistingException("ressourceFollowUp already exists");
                    }
                } catch (NotFoundException e) {
                    serie = catalogueService.findSerieByImdbId(imdbId);
                    serie.setCreationDate(LocalDate.now());
                    final Set<Episode> allEpisodes = catalogueService.findAllEpisodes(serie);
                    serie.setEpisodes(allEpisodes);

                    if (allEpisodes != null ) {
                        serie.setNumberOfEpisodes(allEpisodes.size());
                    } else {
                        serie.setNumberOfEpisodes(0);
                    }
                    serie = serieRepository.save(serie);
                }

                serieFollowUpToSave.setResource(serie);
                serieFollowUpToSave.setMember(member);
                serieFollowUpToSave.setStatus(statusToCreate);
                serieFollowUpToSave.setCreationDate(LocalDate.now());
                serieFollowUpToSave.setLastModificationDate(LocalDate.now());

                return followUpRepository.save(serieFollowUpToSave);
            }
        }
        return null;
    }
    @Override
    public FollowUp findOne (Long id) {
        if (followUpRepository.findById(id).isPresent())
        {
            return followUpRepository.findById(id).get();
        } else {
            throw new NotFoundException("resourceFollowUp non trouvé en BDD");
        }
    }
}
