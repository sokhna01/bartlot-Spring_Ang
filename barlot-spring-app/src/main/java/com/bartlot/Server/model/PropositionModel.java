package com.bartlot.Server.model;

import java.sql.Timestamp;
import lombok.Data;

@Data
public class PropositionModel {

    private Timestamp horodotage;

    /*
     * changer les principale par pr
     * chager source sr
     */

    private String sourceChoisie; // valeur possible : Pr,Ma,Sr,Re,Hi
    // data principale
    private String dataAPlusPr;
    private String dataAMoinsPr;
    private String dataRPlusPr;
    private String dataRMoinsPr;
    private String idCompteurPr;

    // data Redondant
    private String dataAPlusRe;
    private String dataAMoinsRe;
    private String dataRPlusRe;
    private String dataRMoinsRe;
    private String idCompteurRe;

    // data SourceExterne
    private String dataAPlusSe;
    private String dataAMoinsSe;
    private String dataRPlusSe;
    private String dataRMoinsSe;
    private String idCompteurSe;

    // data source historique
    private String dataAPlusHi;
    private String dataAMoinsHi;
    private String dataRPlusHi;
    private String dataRMoinsHi;
    private String idCompteurHi;

    // data source Manual
    private String dataAPlusMa;
    private String dataAMoinsMa;
    private String dataRPlusMa;
    private String dataRMoinsMa;
    private String idCompteurMa;

}
