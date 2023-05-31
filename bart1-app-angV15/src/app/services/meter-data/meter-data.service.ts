import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MeterDataService {
  private selectListDataUrl = 'http://localhost:8080/selectListData';

  constructor(private http: HttpClient) { }

  // getSelectListData(): Observable<any> {
  //   return this.http.get<any>(this.selectListDataUrl);
  // }

  getSelectListData(baseUrl: string, token: string): Observable<any> {
    let headers = new HttpHeaders({ 'Authorization': 'Bearer ' + token });
    const url = baseUrl + 'selectListData';
    return this.http.get<any>(url, { headers });
  }
  public idClient: string = '';
  public idCompteur: string = '';
  public idSite: string = '';
  public idPointDeComptage: string = '';
  public fileName: string = '';

  public file: any;
  insertXlsxToBD(idClient: string, idCompany: any, fileName: string, token: string, baseUrl: string, file: any) {
    const formData = new FormData();
    formData.append('idClient', idClient);
    formData.append('idCompany', idCompany);
    formData.append('file', file, fileName);

    const headers = new HttpHeaders({ 'Authorization': 'Bearer ' + token });
    const url = baseUrl + 'tache5';

    return this.http.post<any>(url, formData, { headers, responseType: 'json' });
  }

  getCreatedXLSX(idClient: string, idCompany: number, token: string, baseUrl: string) {
    const params = new HttpParams()
      .set('idClient', idClient)
      .set('idCompany', idCompany);
    const headers = new HttpHeaders({ 'Authorization': 'Bearer ' + token });
    const url = baseUrl + 'tache6';

    return this.http.get(url, {
      headers,
      params,
      responseType: 'blob'
    });
  }

  getAllClientsWithSitesAndPoints(baseUrl: string, token: string): Observable<any> {
    const url = baseUrl + 'clients';
    let headers = new HttpHeaders({ 'Authorization': 'Bearer ' + token });
    return this.http.get<any>(url, { headers });
  }


}
