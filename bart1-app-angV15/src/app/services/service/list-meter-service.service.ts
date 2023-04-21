import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ListMeterServiceService {

  constructor(public http: HttpClient) { }

  getDataMeterReporting(idcompany: string, baseUrl: string, token: string) {
    //this.pickupDate='2019-11-01';
    let headers = new HttpHeaders({ 'Content-Type': 'application/x-www-form-urlencoded', 'Authorization': 'Bearer' + token });
    // let dataform = "idcompany="+idcompany+"&pickupDate="+this.pickupDate+"&listProfils="+JSON.stringify(this.listProfils);
    let dataform = "idcompany=" + idcompany;
    const url = baseUrl + 'getlistmeterdatareporting';
    return this.http.post<any>(url, dataform, { headers, responseType: 'json' });
  }

  updateSourcePresence(idcompany: string, listProfils: { [key: string]: string } = {}, token: string, baseUrl: string) {

    let headers = new HttpHeaders({ 'Content-Type': 'application/x-www-form-urlencoded', 'Authorization': 'Bearer' + token });
    let dataform = "idcompany=" + idcompany + "&listProfils=" + JSON.stringify(listProfils);
    const url = baseUrl + 'tache3';
    return this.http.post<any>(url, dataform, { headers, responseType: 'json' });
  }



  updateQualitePresence(idcompany: string, listProfils: { [key: string]: string } = {}, token: string, baseUrl: string) {

    //this.pickupDate='2019-11-01';
    let headers = new HttpHeaders({ 'Content-Type': 'application/x-www-form-urlencoded', 'Authorization': 'Bearer' + token });
    let dataform = "idcompany=" + idcompany + "&listProfils=" + JSON.stringify(listProfils);
    const url = baseUrl + 'tache4';
    return this.http.post<any>(url, dataform, { headers, responseType: 'json' });
  }

  getListMeter(idcompany: string, fileName: string, pickupDate: Date, listProfils: { [key: string]: string } = {}, token: string, baseUrl: string) {

    //this.pickupDate='2019-11-01';
    let headers = new HttpHeaders({ 'Content-Type': 'application/x-www-form-urlencoded', 'Authorization': 'Bearer' + token });
    let dataform = "idcompany=" + idcompany;
    // const url = baseUrl + 'demo/getlistmeterdata';
    const url = baseUrl + 'getlistmeterdata';
    return this.http.post<any>(url, dataform, { headers, responseType: 'json' });
  }

  updateTask2(idcompany: string, listProfils: { [key: string]: string } = {}, token: string, baseUrl: string) {

    //this.pickupDate='2019-11-01';
    let headers = new HttpHeaders({ 'Content-Type': 'application/x-www-form-urlencoded', 'Authorization': 'Bearer' + token });
    let dataform = "idcompany=" + idcompany + "&listProfils=" + JSON.stringify(listProfils);
    const url = baseUrl + 'tache2';
    // const url = baseUrl + 'demo/tache2';

    return this.http.post<any>(url, dataform, { headers, responseType: 'json' });
  }
}
