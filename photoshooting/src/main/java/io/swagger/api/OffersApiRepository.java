package io.swagger.api;

import org.springframework.data.repository.CrudRepository;

import io.swagger.model.Offer;

public interface OffersApiRepository extends CrudRepository<Offer, Integer> {

}
