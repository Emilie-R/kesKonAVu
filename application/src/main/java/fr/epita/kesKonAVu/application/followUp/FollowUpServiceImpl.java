package fr.epita.kesKonAVu.application.followUp;

import fr.epita.kesKonAVu.domain.catalogue.CatalogueService;
import fr.epita.kesKonAVu.domain.common.AlreadyExistingException;
import fr.epita.kesKonAVu.domain.common.BusinessException;
import fr.epita.kesKonAVu.domain.common.NotFoundException;
import fr.epita.kesKonAVu.domain.followUp.FollowUp;
import fr.epita.kesKonAVu.domain.followUp.FollowUpRepository;
import fr.epita.kesKonAVu.domain.followUp.StatusEnum;
import fr.epita.kesKonAVu.domain.resource.*;
import fr.epita.kesKonAVu.domain.user.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Set;

@Service
@Transactional
public class FollowUpServiceImpl implements FollowUpService {

    @Autowired
    FollowUpRepository followUpRepository;
    @Autowired
    CatalogueService catalogueService;
    @Autowired
    ResourceRepository resourceRepository;
    @Autowired
    SerieRepository serieRepository;


    /**
     * allow to Create a new FollowUp for the user (CU : Ajouter une ressource à ma sélection)
     * If the resource selected isn't present in the database, the resource is created with data retrieved from the catalogue.
     * @param followUp followUp with id of the Member, id
     * @return followUp followUp created
     */
    @Override
    public FollowUp createNewFollowUp(final FollowUp followUp) {
        final String imdbId = followUp.getResource().getExternalKey();
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
                Resource movie;

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

                followUpToSave.setResource(movie);

            } else if (followUp.getResource().getResourceType() == ResourceTypeEnum.SERIE) {
                /* FOR a Serie (Resource of ResourceType SERIE) */
                Serie serie;

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

                followUpToSave.setResource(serie);
            }
        }

        followUpToSave.setMember(member);
        followUpToSave.setStatus(statusToCreate);
        followUpToSave.setCreationDate(LocalDate.now());
        followUpToSave.setLastModificationDate(LocalDate.now());
        return followUpRepository.save(followUpToSave);
    }
    @Override
    public FollowUp findOne (Long id) {
        if (followUpRepository.findById(id).isPresent())
        {
            return followUpRepository.findById(id).get();
        } else {
            throw new NotFoundException("FollowUp non trouvé en BDD");
        }
    }

    @Override
    public String updateFollowUp (FollowUp followUp) {
        String retour = "KO";
        try {
            followUpRepository.save(followUp);
            retour = "OK";
        } catch (BusinessException e) {
            throw new BusinessException("Echec création followUp pour l'id : " + followUp.getIdFollowUp() );
        }
        return retour;
    }
//
//    @Override
//    public String updateRating (Long id, Integer rating) {
//        FollowUp followUp = followUpRepository.findById(id).get();
//        followUp.setNote(rating);
//        followUpRepository.save(followUp);
//        return "OK";
//    }

    @Override
    public Long deleteFollowUp (final Long idFollowUp) {
        followUpRepository.deleteById(idFollowUp);
        return idFollowUp;
    }
}
