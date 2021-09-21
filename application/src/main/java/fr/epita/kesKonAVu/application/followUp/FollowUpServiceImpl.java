package fr.epita.kesKonAVu.application.followUp;

import fr.epita.kesKonAVu.domain.catalogueOmdb.CatalogueService;
import fr.epita.kesKonAVu.domain.common.AlreadyExistingException;
import fr.epita.kesKonAVu.domain.common.BusinessException;
import fr.epita.kesKonAVu.domain.common.NotFoundException;
import fr.epita.kesKonAVu.domain.followUp.FollowUp;
import fr.epita.kesKonAVu.domain.followUp.FollowUpRepository;
import fr.epita.kesKonAVu.domain.followUp.StatusEnum;
import fr.epita.kesKonAVu.domain.resource.*;
import fr.epita.kesKonAVu.domain.user.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Set;

@Service
public class FollowUpServiceImpl implements FollowUpService {


    @Autowired
    FollowUpRepository followUpRepository;
    @Autowired
    CatalogueService catalogueService;
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    SerieRepository serieRepository;
    @Autowired
    ResourceRepository resourceRepository;

    private static final Logger LOG = LoggerFactory.getLogger(FollowUpServiceImpl.class);

    /**
     * allow to Create a new FollowUp for the user (CU : Ajouter une ressource à ma sélection)
     * If the resource selected isn't present in the database, the resource is created with data retrieved from the catalogue.
     * @param followUp followUp with id of the Member, id
     * @return followUp followUp created
     */
    @Override
    @Transactional
    public FollowUp createNewFollowUpFromImdbId(final FollowUp followUp) {
        LOG.info("createNewFollowUpFromImdbId - begin");

        final String imdbId = followUp.getResource().getImdbId();
        final Member member = followUp.getMember();

        FollowUp followUpToSave = new FollowUp();

        /* Status to set for the creation of the FollowUp */
        StatusEnum statusToCreate = StatusEnum.VU;
        if (followUp.getStatus() == StatusEnum.AVOIR) {
            statusToCreate = StatusEnum.AVOIR;
        }

        if (followUp.getResource().getResourceType() != null) {

            if(followUp.getResource().getResourceType() == ResourceTypeEnum.MOVIE) {
                /* FOR a Movie (Resource of ResourceType MOVIE) */
                Movie movie;

                try {
                    movie = movieRepository.findByImdbId(imdbId);
                    if (followUpRepository.findByResourceAndMember(movie,member) != null) {
                        /* FollowUp already created => skip */
                        throw new AlreadyExistingException("ressourceFollowUp already exists");
                    }
                } catch (NotFoundException e) {
                    movie = catalogueService.findMovieByImdbId(imdbId);
                    movie.setLastModificationDateTime(LocalDateTime.now());
                    movie = movieRepository.save(movie);
                    LOG.info("createNewFollowUpFromImdbId - New movie : " + movie.getTitle() + " / " + movie.getIdResource());
                }

                followUpToSave.setResource(movie);

            } else if (followUp.getResource().getResourceType() == ResourceTypeEnum.SERIE) {
                /* FOR a Serie (Resource of ResourceType SERIE) */
                Serie serie;

                try {
                    serie = serieRepository.findByImdbId(imdbId);
                    if (followUpRepository.findByResourceAndMember(serie,member) != null) {
                        /* FollowUp already created => skip */
                        throw new AlreadyExistingException("ressourceFollowUp already exists");
                    }
                } catch (NotFoundException e) {
                    serie = catalogueService.findSerieByImdbId(imdbId);
                    serie.setLastModificationDateTime(LocalDateTime.now());
                    final Set<Episode> allEpisodes = catalogueService.findAllEpisodes(serie);
                    serie.setEpisodes(allEpisodes);

                    if (allEpisodes != null ) {
                        serie.setNumberOfEpisodes(allEpisodes.size());
                    } else {
                        serie.setNumberOfEpisodes(0);
                    }
                    serie = serieRepository.save(serie);
                    LOG.info("createNewFollowUpFromImdbId - New serie : " + serie.getTitle() + " / " + serie.getIdResource());
                }

                followUpToSave.setResource(serie);
            }
        }

        followUpToSave.setMember(member);
        followUpToSave.setStatus(statusToCreate);
        followUpToSave.setCreationDateTime(LocalDateTime.now());
        followUpToSave.setLastModificationDateTime(LocalDateTime.now());
        LOG.info("createNewFollowUpFromImdbId - end");
        return followUpRepository.save(followUpToSave);
    }

    @Override
    @Transactional(readOnly = true)
    public FollowUp findOne (Long id) {
        return followUpRepository.findById(id).orElseThrow(
                () -> new NotFoundException("FollowUp non trouvé en BDD " + id));
    }

    @Override
    @Transactional
    public FollowUp updateFollowUp (FollowUp followUp) {

        try {
            return followUpRepository.save(followUp);

        } catch (BusinessException e) {
            throw new BusinessException("Echec mise à jour followUp pour l'id : " + followUp.getIdFollowUp() );
        }
    }


    @Override
    @Transactional
    public Long deleteFollowUp (final Long idFollowUp) {
        followUpRepository.deleteById(idFollowUp);
        return idFollowUp;
    }

}
