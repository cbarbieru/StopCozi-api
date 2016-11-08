package ro.gov.ithub.stopcozi.model;

import ro.gov.ithub.stopcozi.model.repo.Agency;

public class Adaptors {

    public static ro.gov.ithub.stopcozi.model.api.Agency from(Agency agency){
        ro.gov.ithub.stopcozi.model.api.Agency agencyApi = new ro.gov.ithub.stopcozi.model.api.Agency();
        agencyApi.setName(agency.getName());
        agencyApi.setCountyCode(agency.getCountyCode());
        agencyApi.setId(agency.getId());

        return agencyApi;
    }
}
