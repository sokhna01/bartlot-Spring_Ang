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

}