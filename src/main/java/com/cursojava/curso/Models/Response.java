package com.cursojava.curso.Models;

import java.util.ArrayList;

public class Response {
    private float resultCount;
    ArrayList<JsonAJava> results = new ArrayList<>();
    // Getter Methods

    public ArrayList<JsonAJava> getResults() {
        return results;
    }
    public float getResultCount() {
        return resultCount;
    }
    // Setter Methods
    public void setResults(ArrayList<JsonAJava> results) {
        this.results = results;
    }
    public void setResultCount( float resultCount ) {
        this.resultCount = resultCount;
    }




  /*  public String ResponseOne (){
        RestTemplate restTemplate = new RestTemplate();
        //Codigo que formatea la salida a Json
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        messageConverters.add(converter);
        restTemplate.setMessageConverters(messageConverters);
        //Codigo del RestTemplate de la api publica
        UriComponents uri = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("itunes.apple.com")
                .path("search")
                .queryParam("term=radiohead")
                .build();
        ResponseEntity<JsonAJava> entity = restTemplate.getForEntity(uri.toUriString(),JsonAJava.class);
        return entity.getBody().getArtistViewUrl();
    }*/
}

/*______________________________________________________________________________________________________________
    RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        restTemplate = restTemplateBuilder.build();
                // objeto de donde va a ser url ,objeto en este caso JsonAJava
                JsonAJava jsonAJava = restTemplate.getForObject("https://itunes.apple.com/search?term=radiohead", JsonAJava.class);
        return jsonAJava.getArtistViewUrl();
_____________________________________________________________________________________________________________
restTemplate.setMessageConverters(messageConverters);
        UriComponents uri = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("itunes.apple.com")
                .path("search")
                .queryParam("term=radiohead")
                .build();
        ResponseEntity<JsonAJava> entity = restTemplate.getForEntity(uri.toUriString(),JsonAJava.class);
        return entity.getBody().getArtistViewUrl();
______________________________________________________________________________________________________________

        String url = "https://itunes.apple.com/search?term=radiohead";
        Object[] itunes = restTemplate.getForObject(url, Object[].class);
        return Arrays.asList(itunes);
______________________________________________________________________________________________________________

       //getArtistViewUrl() es el get de la url para escuchar la musica

    */
       /* private float resultCount;
        ArrayList<JsonAJava> results = new ArrayList<JsonAJava>();

        String url = "https://itunes.apple.com/search?term=radiohead";

        // Getter Methods

        public float getResultCount() {
            return resultCount;
        }

        // Setter Methods


        public void setResultCount( float resultCount ) {
            this.resultCount = resultCount;
        }*/


