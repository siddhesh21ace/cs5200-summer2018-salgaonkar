package edu.northeastern.cs5200.card;

import org.apache.http.client.utils.URIBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.util.Collections;

@Service
public class CardService {
    private static final String BASE_URL = "https://api.pokemontcg.io/v1/cards";

    public ResponseEntity<String> fetchAllCards(CardCriteria cardCriteria) {
        String urlParamString = createURLParamString(cardCriteria, BASE_URL);

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Page-Size", String.valueOf(cardCriteria.getPageSize()));
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
                "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        return restTemplate.exchange(urlParamString, HttpMethod.GET, entity, String.class);
    }

    public void addIfNotEmpty(URIBuilder uriBuilder, String key, String value) {
        if (!StringUtils.isEmpty(value)) {
            uriBuilder.addParameter(key, value);
        }
    }

    public String createURLParamString(CardCriteria cardCriteria, String baseUrl) {
        URIBuilder uriBuilder;
        try {
            uriBuilder = new URIBuilder(baseUrl);
            addIfNotEmpty(uriBuilder, "supertype", cardCriteria.getSuperType());
            addIfNotEmpty(uriBuilder, "subtype", cardCriteria.getSubType());
            addIfNotEmpty(uriBuilder, "setCode", cardCriteria.getSetCode());
            addIfNotEmpty(uriBuilder, "name", cardCriteria.getName());
            addIfNotEmpty(uriBuilder, "weaknesses", cardCriteria.getWeaknesses());
            addIfNotEmpty(uriBuilder, "attackDamage", cardCriteria.getAttackDamage());
            addIfNotEmpty(uriBuilder, "attackCost", cardCriteria.getAttackCost());
            addIfNotEmpty(uriBuilder, "retreatCost", cardCriteria.getRetreatCost());
            addIfNotEmpty(uriBuilder, "hp", cardCriteria.getHp());
            addIfNotEmpty(uriBuilder, "nationalPokedexNumber", cardCriteria.getPokedexNumber());

            baseUrl = URLDecoder.decode(uriBuilder.toString(), "UTF-8");
        } catch (URISyntaxException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return baseUrl;
    }

    public ResponseEntity<String> fetchCardById(String cardId) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
                "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        return restTemplate.exchange(BASE_URL + "/" + cardId, HttpMethod.GET, entity, String.class);
    }
}
