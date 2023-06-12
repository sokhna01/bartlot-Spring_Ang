package com.bartlot.Server.model;

import java.sql.Timestamp;

public class PropositionModel {

    private Timestamp horodatage;

    /*
     * changer les principale par pr
     * chager source sr
     */

    private int id;
    private String sourceChoisie; // valeur possible : Pr,Ma,Sr,Re,Hi
    // data principale
    private Double dataAPlusPr;
    private Double dataAMoinsPr;
    private Double dataRPlusPr;
    private Double dataRMoinsPr;
    private String idCompteurPr;

    // data Redondant
    private Double dataAPlusRe;
    private Double dataAMoinsRe;
    private Double dataRPlusRe;
    private Double dataRMoinsRe;
    private String idCompteurRe;

    // data SourceExterne
    private Double dataAPlusSe;
    private Double dataAMoinsSe;
    private Double dataRPlusSe;
    private Double dataRMoinsSe;
    private String idCompteurSe;

    // data source historique
    private Double dataAPlusHi;
    private Double dataAMoinsHi;
    private Double dataRPlusHi;
    private Double dataRMoinsHi;
    private String idCompteurHi;

    // data source Manual
    private Double dataAPlusMa;
    private Double dataAMoinsMa;
    private Double dataRPlusMa;
    private Double dataRMoinsMa;
    private String idCompteurMa;

    public Timestamp getHorodatage() {
        return horodatage;
    }

    public void setHorodatage(Timestamp horodatage) {
        this.horodatage = horodatage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSourceChoisie() {
        return sourceChoisie;
    }

    public void setSourceChoisie(String sourceChoisie) {
        this.sourceChoisie = sourceChoisie;
    }

    public Double getDataAPlusPr() {
        return dataAPlusPr;
    }

    public void setDataAPlusPr(Double dataAPlusPr) {
        this.dataAPlusPr = dataAPlusPr;
    }

    public Double getDataAMoinsPr() {
        return dataAMoinsPr;
    }

    public void setDataAMoinsPr(Double dataAMoinsPr) {
        this.dataAMoinsPr = dataAMoinsPr;
    }

    public Double getDataRPlusPr() {
        return dataRPlusPr;
    }

    public void setDataRPlusPr(Double dataRPlusPr) {
        this.dataRPlusPr = dataRPlusPr;
    }

    public Double getDataRMoinsPr() {
        return dataRMoinsPr;
    }

    public void setDataRMoinsPr(Double dataRMoinsPr) {
        this.dataRMoinsPr = dataRMoinsPr;
    }

    public String getIdCompteurPr() {
        return idCompteurPr;
    }

    public void setIdCompteurPr(String idCompteurPr) {
        this.idCompteurPr = idCompteurPr;
    }

    public Double getDataAPlusRe() {
        return dataAPlusRe;
    }

    public void setDataAPlusRe(Double dataAPlusRe) {
        this.dataAPlusRe = dataAPlusRe;
    }

    public Double getDataAMoinsRe() {
        return dataAMoinsRe;
    }

    public void setDataAMoinsRe(Double dataAMoinsRe) {
        this.dataAMoinsRe = dataAMoinsRe;
    }

    public Double getDataRPlusRe() {
        return dataRPlusRe;
    }

    public void setDataRPlusRe(Double dataRPlusRe) {
        this.dataRPlusRe = dataRPlusRe;
    }

    public Double getDataRMoinsRe() {
        return dataRMoinsRe;
    }

    public void setDataRMoinsRe(Double dataRMoinsRe) {
        this.dataRMoinsRe = dataRMoinsRe;
    }

    public String getIdCompteurRe() {
        return idCompteurRe;
    }

    public void setIdCompteurRe(String idCompteurRe) {
        this.idCompteurRe = idCompteurRe;
    }

    public Double getDataAPlusSe() {
        return dataAPlusSe;
    }

    public void setDataAPlusSe(Double dataAPlusSe) {
        this.dataAPlusSe = dataAPlusSe;
    }

    public Double getDataAMoinsSe() {
        return dataAMoinsSe;
    }

    public void setDataAMoinsSe(Double dataAMoinsSe) {
        this.dataAMoinsSe = dataAMoinsSe;
    }

    public Double getDataRPlusSe() {
        return dataRPlusSe;
    }

    public void setDataRPlusSe(Double dataRPlusSe) {
        this.dataRPlusSe = dataRPlusSe;
    }

    public Double getDataRMoinsSe() {
        return dataRMoinsSe;
    }

    public void setDataRMoinsSe(Double dataRMoinsSe) {
        this.dataRMoinsSe = dataRMoinsSe;
    }

    public String getIdCompteurSe() {
        return idCompteurSe;
    }

    public void setIdCompteurSe(String idCompteurSe) {
        this.idCompteurSe = idCompteurSe;
    }

    public Double getDataAPlusHi() {
        return dataAPlusHi;
    }

    public void setDataAPlusHi(Double dataAPlusHi) {
        this.dataAPlusHi = dataAPlusHi;
    }

    public Double getDataAMoinsHi() {
        return dataAMoinsHi;
    }

    public void setDataAMoinsHi(Double dataAMoinsHi) {
        this.dataAMoinsHi = dataAMoinsHi;
    }

    public Double getDataRPlusHi() {
        return dataRPlusHi;
    }

    public void setDataRPlusHi(Double dataRPlusHi) {
        this.dataRPlusHi = dataRPlusHi;
    }

    public Double getDataRMoinsHi() {
        return dataRMoinsHi;
    }

    public void setDataRMoinsHi(Double dataRMoinsHi) {
        this.dataRMoinsHi = dataRMoinsHi;
    }

    public String getIdCompteurHi() {
        return idCompteurHi;
    }

    public void setIdCompteurHi(String idCompteurHi) {
        this.idCompteurHi = idCompteurHi;
    }

    public Double getDataAPlusMa() {
        return dataAPlusMa;
    }

    public void setDataAPlusMa(Double dataAPlusMa) {
        this.dataAPlusMa = dataAPlusMa;
    }

    public Double getDataAMoinsMa() {
        return dataAMoinsMa;
    }

    public void setDataAMoinsMa(Double dataAMoinsMa) {
        this.dataAMoinsMa = dataAMoinsMa;
    }

    public Double getDataRPlusMa() {
        return dataRPlusMa;
    }

    public void setDataRPlusMa(Double dataRPlusMa) {
        this.dataRPlusMa = dataRPlusMa;
    }

    public Double getDataRMoinsMa() {
        return dataRMoinsMa;
    }

    public void setDataRMoinsMa(Double dataRMoinsMa) {
        this.dataRMoinsMa = dataRMoinsMa;
    }

    public String getIdCompteurMa() {
        return idCompteurMa;
    }

    public void setIdCompteurMa(String idCompteurMa) {
        this.idCompteurMa = idCompteurMa;
    }
}
