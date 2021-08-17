package fr.epita.kesKonAVu.infrastructure.resource.catalogue.omdb;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ItemFromOmdb {

        @JsonProperty("Response")
        private String response;

        /* Specific data if response = False */
        @JsonProperty("Error")
        private String error;

        @JsonProperty("Title")
        private String title;

        @JsonProperty("Year")
        private String year;

        @JsonProperty("Rated")
        private String rated;

        @JsonProperty("Released")
        private String releasedDate;

        @JsonProperty("Runtime")
        private String runtime;

        @JsonProperty("Genre")
        private String genre;

        @JsonProperty("Director")
        private String director;

        @JsonProperty("Writer")
        private String writer;

        @JsonProperty("Actors")
        private String actors;

        @JsonProperty("Plot")
        private String plot;

        @JsonProperty("Language")
        private String language;

        @JsonProperty("Country")
        private String country;

        @JsonProperty("Awards")
        private String awards;

        @JsonProperty("Poster")
        private String poster;

        @JsonProperty("Ratings")
        private List<RatingFromOmdb> ratings;

        @JsonProperty("Metascore")
        private String metascore;

        @JsonProperty("imdbRating")
        private String imdbRating;

        @JsonProperty("imdbVotes")
        private String imdbVotes;

        @JsonProperty("imdbID")
        private String imdbId;

        @JsonProperty("Type")
        private String type;

        @JsonProperty("DVD")
        private String dvd;

        @JsonProperty("BoxOffice")
        private String boxOffice;

        @JsonProperty("Production")
        private String production;

        @JsonProperty("Website")
        private String webSite;

        /* Specific data for Response of Type series */
        @JsonProperty("totalSeasons")
        private String totalSeasons;

        /* Specific data for Response of Type episode */
        @JsonProperty("seriesID")
        private String seriesId;

        /* Specific data for Response of Type episode */
        @JsonProperty("Season")
        private String seasonNumber;

        /* Specific data for Response of Type episode */
        @JsonProperty ("Episode")
        private String episodeNumber;

        public String getResponse() {
            return response;
        }

        public void setResponse(String response) {
            this.response = response;
        }

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }

        public String getTotalSeasons() {
            return totalSeasons;
        }

        public void setTotalSeasons(String totalSeasons) {
            this.totalSeasons = totalSeasons;
        }

        public String getSeriesId() {
            return seriesId;
        }

        public void setSeriesId(String seriesId) {
            this.seriesId = seriesId;
        }

        public String getSeasonNumber() {
            return seasonNumber;
        }

        public void setSeasonNumber(String seasonNumber) {
            this.seasonNumber = seasonNumber;
        }

        public String getEpisodeNumber() {
            return episodeNumber;
        }

        public void setEpisodeNumber(String episodeNumber) {
            this.episodeNumber = episodeNumber;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public String getRated() {
            return rated;
        }

        public void setRated(String rated) {
            this.rated = rated;
        }

        public String getReleasedDate() {
            return releasedDate;
        }

        public void setReleasedDate(String releasedDate) {
            this.releasedDate = releasedDate;
        }

        public String getRuntime() {
            return runtime;
        }

        public void setRuntime(String runtime) {
            this.runtime = runtime;
        }

        public String getGenre() {
            return genre;
        }

        public void setGenre(String genre) {
            this.genre = genre;
        }

        public String getDirector() {
            return director;
        }

        public void setDirector(String director) {
            this.director = director;
        }

        public String getWriter() {
            return writer;
        }

        public void setWriter(String writer) {
            this.writer = writer;
        }

        public String getActors() {
            return actors;
        }

        public void setActors(String actors) {
            this.actors = actors;
        }

        public String getPlot() {
            return plot;
        }

        public void setPlot(String plot) {
            this.plot = plot;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getAwards() {
            return awards;
        }

        public void setAwards(String awards) {
            this.awards = awards;
        }

        public String getPoster() {
            return poster;
        }

        public void setPoster(String poster) {
            this.poster = poster;
        }

        public List<RatingFromOmdb> getRatings() {
            return ratings;
        }

        public void setRatings(List<RatingFromOmdb> ratings) {
            this.ratings = ratings;
        }

        public String getMetascore() {
            return metascore;
        }

        public void setMetascore(String metascore) {
            this.metascore = metascore;
        }

        public String getImdbRating() {
            return imdbRating;
        }

        public void setImdbRating(String imdbRating) {
            this.imdbRating = imdbRating;
        }

        public String getImdbVotes() {
            return imdbVotes;
        }

        public void setImdbVotes(String imdbVotes) {
            this.imdbVotes = imdbVotes;
        }

        public String getImdbId() {
            return imdbId;
        }

        public void setImdbId(String imdbId) {
            this.imdbId = imdbId;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getDvd() {
            return dvd;
        }

        public void setDvd(String dvd) {
            this.dvd = dvd;
        }

        public String getBoxOffice() {
            return boxOffice;
        }

        public void setBoxOffice(String boxOffice) {
            this.boxOffice = boxOffice;
        }

        public String getProduction() {
            return production;
        }

        public void setProduction(String production) {
            this.production = production;
        }

        public String getWebSite() {
            return webSite;
        }

        public void setWebSite(String webSite) {
            this.webSite = webSite;
        }

}
