package fr.epita.kesKonAVu.infrastructure.catalogueOmdb;


import fr.epita.kesKonAVu.domain.catalogueOmdb.EpisodeCatalogue;
import fr.epita.kesKonAVu.domain.catalogueOmdb.ItemCatalogue;
import fr.epita.kesKonAVu.domain.catalogueOmdb.SerieSeasonCatalogue;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CatalogueDtoMapper {

    public ItemCatalogue DtoToItemCatalogue(final ItemCatalogueDto itemCatalogueDto){

        ItemCatalogue itemCatalogue = new ItemCatalogue();

        itemCatalogue.setTitle(itemCatalogueDto.getTitle());
        itemCatalogue.setYear(itemCatalogueDto.getYear());
        itemCatalogue.setPoster(itemCatalogueDto.getPoster());
        itemCatalogue.setPlot(itemCatalogueDto.getPlot());
        itemCatalogue.setActors(itemCatalogueDto.getActors());
        itemCatalogue.setGenre(itemCatalogueDto.getGenre());
        itemCatalogue.setDirector(itemCatalogueDto.getDirector());
        itemCatalogue.setImdbId(itemCatalogueDto.getImdbId());
        itemCatalogue.setType(itemCatalogueDto.getType());
        itemCatalogue.setTotalSeasons(itemCatalogueDto.getTotalSeasons());
        itemCatalogue.setRuntime(itemCatalogueDto.getRuntime());

        return itemCatalogue;
    }

    public EpisodeCatalogue dtoToEpisodeCatalogue (EpisodeCatalogueDto episodeCatalogueDto) {
       EpisodeCatalogue episodeCatalogue = new EpisodeCatalogue();
       episodeCatalogue.setImdbId(episodeCatalogueDto.getImdbId());
       episodeCatalogue.setTitle(episodeCatalogueDto.getTitle());
       episodeCatalogue.setEpisodeNumber(episodeCatalogueDto.getEpisodeNumber());
       return episodeCatalogue;
    }

    public List<EpisodeCatalogue> dtoListToEpisodeCatalogue(List<EpisodeCatalogueDto> episodeCatalogueDtoList) {

        List<EpisodeCatalogue> episodes = new ArrayList<>();
        for (EpisodeCatalogueDto episodeDto : episodeCatalogueDtoList) {
            episodes.add(dtoToEpisodeCatalogue(episodeDto));
        }
        return episodes;
    }

    public SerieSeasonCatalogue dtoToSerieSeasonCatalogue(SerieSeasonCatalogueDto serieSeasonCatalogueDto) {
        SerieSeasonCatalogue serieSeasonCatalogue = new SerieSeasonCatalogue();

        serieSeasonCatalogue.setSeason(serieSeasonCatalogueDto.getSeason());
        serieSeasonCatalogue.setTotalSeasons(serieSeasonCatalogueDto.getTotalSeasons());
        serieSeasonCatalogue.setEpisodes(dtoListToEpisodeCatalogue(serieSeasonCatalogueDto.getEpisodes()));
        return serieSeasonCatalogue;
    }

}
