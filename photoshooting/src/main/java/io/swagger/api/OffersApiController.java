package io.swagger.api;

import io.swagger.model.Offer;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-03-29T16:53:00.885Z[GMT]")
@Controller
public class OffersApiController implements OffersApi {

    private static final Logger log = LoggerFactory.getLogger(OffersApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    
    @Autowired
    private OffersApiService offersApiService;

    @org.springframework.beans.factory.annotation.Autowired
    public OffersApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<String> createOffer(@ApiParam(value = "Offer object that needs to be created" ,required=true )  @Valid @RequestBody Offer body
) {
        String accept = request.getHeader("Accept");
        String msg = offersApiService.addOffer(body);
        return new ResponseEntity<String>(msg, HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<String> deleteOffer(@Min(1)@ApiParam(value = "Offer id",required=true, allowableValues="") @PathVariable("id") Integer id
) {
        String accept = request.getHeader("Accept");
        String msg = offersApiService.deleteOffer(id);
        return new ResponseEntity<String>(msg, HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<String> editOffer(@ApiParam(value = "Offer object that needs to be created" ,required=true )  @Valid @RequestBody Offer body
,@Min(1)@ApiParam(value = "Offer id",required=true, allowableValues="") @PathVariable("id") Integer id
) {
        String accept = request.getHeader("Accept");
        String msg = offersApiService.updateOffer(id, body);
        return new ResponseEntity<String>(msg, HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Offer> getOffer(@Min(1)@ApiParam(value = "Offer id",required=true, allowableValues="") @PathVariable("id") Integer id
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Offer>(objectMapper.readValue("{\n  \"description\" : \"description\",\n  \"id\" : 0,\n  \"title\" : \"title\",\n  \"option\" : \"sell\"\n}", Offer.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Offer>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        Offer offer = offersApiService.getOfferbyId(id);
        return new ResponseEntity<Offer>(offer, HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<Offer>> getOffers() {
        String accept = request.getHeader("Accept");
        List<Offer> offers = offersApiService.getOffers();
        return new ResponseEntity<List<Offer>>(offers, HttpStatus.NOT_IMPLEMENTED);
    }

}
