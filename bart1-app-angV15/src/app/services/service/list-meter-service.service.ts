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




}
