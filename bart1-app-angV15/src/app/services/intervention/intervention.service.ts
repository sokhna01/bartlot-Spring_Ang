import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class InterventionService {

  constructor(public http: HttpClient) { }

  AddIntervention(idCompteur: string, beginDate: string, endDate: string, baseUrl: string, token: string) {

    let headers = new HttpHeaders({ 'Content-Type': 'application/x-www-form-urlencoded', 'Authorization': 'Bearer' + token });
    let dataform = "idCompteur=" + idCompteur + "&beginDate=" + beginDate + "&endDate=" + endDate;
    const url = baseUrl + 'tache9';
    return this.http.post<any>(url, dataform, { headers, responseType: 'json' });
  }

  editIntervention(id: number, beginDate: string, endDate: string, annuler: boolean, baseUrl: string, token: string) {

    let headers = new HttpHeaders({ 'Content-Type': 'application/x-www-form-urlencoded', 'Authorization': 'Bearer' + token });
    let dataform = "id=" + id + "&beginDate=" + beginDate + "&endDate=" + endDate + "&annuler=" + annuler;
    const url = baseUrl + 'update_intervention';
    return this.http.post<any>(url, dataform, { headers, responseType: 'json' });
  }

  // getListIntervention(idcompany: string, baseUrl: string, token: string) {

  //   let headers = new HttpHeaders({ 'Content-Type': 'application/x-www-form-urlencoded', 'Authorization': 'Bearer' + token });
  //   let dataform = "idcompany=" + idcompany;
  //   const url = baseUrl + 'getlist_intervention';
  //   return this.http.post<any>(url, dataform, { headers, responseType: 'json' });

  // }

  searchListInterventionByDate(beginDate: string, endDate: string, baseUrl: string, token: string) {

    let headers = new HttpHeaders({ 'Content-Type': 'application/x-www-form-urlencoded', 'Authorization': 'Bearer' + token });
    let dataform = "begin_horodatage=" + beginDate + "&end_horodatage=" + endDate;
    const url = baseUrl + 'search_by_date';
    return this.http.post<any>(url, dataform, { headers, responseType: 'json' });

  }
}
