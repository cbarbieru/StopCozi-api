package ro.gov.ithub.stopcozi.model;

import ro.gov.ithub.stopcozi.model.repo.Agency;

public class Adaptors {

    public static Agency from(Agency agency){
        Agency agencyApi = new Agency();
        agencyApi.setName(agency.getName());
        agencyApi.setCountyCode(agency.getCountyCode());
        agencyApi.setId(agency.getId());

        return agencyApi;
    }
}
