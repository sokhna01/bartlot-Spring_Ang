package com.bartlot.Server.model;

import java.util.List;

public class ClientSitePointAssociation {
    private String idClient;
    private List<String> idSites;
    private List<String> idPoints;

    public String getIdClient() {
        return idClient;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

    public List<String> getIdSites() {
        return idSites;
    }

    public void setIdSites(List<String> idSites) {
        this.idSites = idSites;
    }

    public List<String> getIdPoints() {
        return idPoints;
    }

    public void setIdPoints(List<String> idPoints) {
        this.idPoints = idPoints;
    }
}
