import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ListMeterServiceService {

  constructor(public http: HttpClient) { }

  getDataMeterReporting(baseUrl: string, token: string) {
    let headers = new HttpHeaders({ 'Content-Type': 'application/x-www-form-urlencoded', 'Authorization': 'Bearer' + token });

    const url = baseUrl + 'getlistmeterdatareporting';
    return this.http.get<any>(url, { headers, responseType: 'json' });
  }

  updateSourcePresence(listProfils: { [key: string]: string } = {}, token: string, baseUrl: string) {

    let headers = new HttpHeaders({ 'Content-Type': 'application/x-www-form-urlencoded', 'Authorization': 'Bearer' + token });
    let dataform = "listProfils=" + JSON.stringify(listProfils);
    const url = baseUrl + 'tache3';
    return this.http.post<any>(url, dataform, { headers, responseType: 'json' });
  }



  updateQualitePresence(token: string, baseUrl: string) {

    let headers = new HttpHeaders({ 'Content-Type': 'application/x-www-form-urlencoded', 'Authorization': 'Bearer' + token });

    const url = baseUrl + 'tache4';


    return this.http.get<any>(url, { headers, responseType: 'json' });
  }

  getListMeter(token: string, baseUrl: string) {

    let headers = new HttpHeaders({ 'Content-Type': 'application/x-www-form-urlencoded', 'Authorization': 'Bearer' + token });
    // const url = baseUrl + 'demo/getlistmeterdata';
    const url = baseUrl + 'getlistmeterdata';
    return this.http.get<any>(url, { headers, responseType: 'json' });
  }

  tache8GetTable(token: string, baseUrl: string) {

    let headers = new HttpHeaders({ 'Content-Type': 'application/x-www-form-urlencoded', 'Authorization': 'Bearer' + token });

    // const url = baseUrl + 'demo/getlistmeterdata';
    const url = baseUrl + 'tache8_get_table';
    return this.http.get<any>(url, { headers, responseType: 'json' });
  }

  updateTask2(listProfils: { [key: string]: string } = {}, token: string, baseUrl: string) {

    let headers = new HttpHeaders({ 'Content-Type': 'application/x-www-form-urlencoded', 'Authorization': 'Bearer' + token });
    let dataform = "listProfils=" + JSON.stringify(listProfils);
    const url = baseUrl + 'tache2';
    return this.http.post<any>(url, dataform, { headers, responseType: 'json' });
  }


  table_configuration_points_de_compteur(idcompany: string, id_client: string, id_site: string, id_point_de_comptage: string, convention_signe: string,
    id_compteur_principal: string, id_compteur_redondance: string, tension_nominale_vn_primaire: number, courant_nominale_in_primaire: number,
    puissance_nominale_pn_du_point_de_comptage: number, b_injection: number, b_soutirage: number, alarme_seul_a_plus: number, alarme_seul_a_moins: number,
    alarme_seul_r_plus: number, alarme_seul_r_moins: number, alarme_seul_r_tg_mois: number, baseUrl: string) {

    let headers = new HttpHeaders({ 'Content-Type': 'application/x-www-form-urlencoded' });
    let dataform = "idcompany=" + idcompany + "&id_client=" + id_client + "&id_site=" + id_site + "&id_point_de_comptage=" + id_point_de_comptage +
      "&convention_signe=" + convention_signe + "&id_compteur_principal=" + id_compteur_principal + "&id_compteur_redondance=" + id_compteur_redondance +
      "&tension_nominale_vn_primaire=" + tension_nominale_vn_primaire + "&courant_nominale_in_primaire=" + courant_nominale_in_primaire +
      "&puissance_nominale_pn_du_point_de_comptage=" + puissance_nominale_pn_du_point_de_comptage + "&b_injection=" + b_injection + "&b_soutirage=" + b_soutirage +
      "&alarme_seul_a_plus=" + alarme_seul_a_plus + "&alarme_seul_a_moins=" + alarme_seul_a_moins + "&alarme_seul_r_plus=" + alarme_seul_r_plus +
      "&alarme_seul_r_moins=" + alarme_seul_r_moins + "&alarme_seul_r_tg_mois=" + alarme_seul_r_tg_mois;

    const url = baseUrl + 'tache12';

    return this.http.post<any>(url, dataform, { headers, responseType: 'json' });
  }

}
