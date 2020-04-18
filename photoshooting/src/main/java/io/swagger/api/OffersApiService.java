package io.swagger.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.swagger.model.Meeting;
import io.swagger.model.Offer;
import io.swagger.model.Post;

@Service
public class OffersApiService {
	
	@Autowired
	private OffersApiRepository offersApiRepository;
	
	public List<Offer> getOffers(){
		List<Offer> offers = new ArrayList<>();
		offersApiRepository.findAll().forEach(offers::add);
		return offers;
	}
	
	public String addOffer(Offer offer) {
		offersApiRepository.save(offer);
		Integer id = offer.getId();
		String ids = id.toString();
		return ids;
	}
	
	public Offer getOfferbyId(Integer id) {
		return offersApiRepository.findOne(id);
	}
	
	public String updateOffer(Integer id, Offer body) {
		Offer offer = offersApiRepository.findOne(id);
		offer.setTitle(body.getTitle());
		offer.setDescription(body.getDescription());
		final Offer updatedOffer = offersApiRepository.save(offer);
		return "OK";
	}
	
	public String deleteOffer(Integer id) {
		offersApiRepository.delete(id);
		return "OK";
	}
	
	public Boolean checkIfExists(Integer id) {
		Offer offer = offersApiRepository.findOne(id);
		if (offer != null) {
			return true;
		}
		else return false;
	}

}
