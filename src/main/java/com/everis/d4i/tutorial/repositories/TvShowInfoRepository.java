package com.everis.d4i.tutorial.repositories;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.everis.d4i.tutorial.json.TvShowRest;
import com.everis.d4i.tutorial.utils.constants.RestConstants;

@FeignClient(name = "tvShowsInfo", url = RestConstants.EXTERNAL_API + RestConstants.EXTERNAL_INFO
		+ RestConstants.EXTERNAL_FILM)
public interface TvShowInfoRepository {

	@RequestMapping(method = RequestMethod.GET, value = RestConstants.RESOURCE_NAME, produces = "application/json")
	TvShowRest findByName(@PathVariable("name") String name);
}
