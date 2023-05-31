import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class WorkTableServiceService {

  constructor(public http: HttpClient) { }

  updateWorkTable(horodatage: string, dataAPlus: string, dataAMoins: string, dataRPlus: string, dataRMoins: string, source: string, idCompteur: string, commentaire: string, baseUrl: string) {

    let headers = new HttpHeaders({ 'Content-Type': 'application/x-www-form-urlencoded' });
    let dataform = "horodatage=" + horodatage + "&dataAPlus=" + dataAPlus + "&dataAMoins=" + dataAMoins + "&dataRPlus=" + dataRPlus + "&dataRMoins=" + dataRMoins + "&source=" + source + "&idCompteur=" + idCompteur + "&commentaire=" + commentaire;
    const url = baseUrl + 'tache8_update_table';

    return this.http.post<any>(url, dataform, { headers, responseType: 'json' });
  }

  getListDataAnterieur(horodatage: string, baseUrl: string) {

    let headers = new HttpHeaders({ 'Content-Type': 'application/x-www-form-urlencoded' });
    let dataform = "horodatage=" + horodatage;
    const url = baseUrl + 'getlist_data_anterieur';

    return this.http.post<any>(url, dataform, { headers, responseType: 'json' });
  }

}
