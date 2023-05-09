import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MeterDataService {

  constructor(private http: HttpClient) { }

  getSelectListData(baseUrl: string, token: string): Observable<any> {
    let headers = new HttpHeaders({'Authorization': 'Bearer ' + token });
    const url = baseUrl + 'selectListData';
    return this.http.get<any>(url, {headers});
  }
  public idClient: string = '';
  public fileName: string = '';

  public file: any;
  insertXlsxToBD(idClient: string, fileName: string, token: string, baseUrl: string, file: any) {
    const formData = new FormData();
    formData.append('idClient', idClient);
    formData.append('file', file, fileName);
    const headers = new HttpHeaders({ 'Authorization': 'Bearer ' + token });
    const url = baseUrl + 'insert_meter_data_externe';
  
    return this.http.post<any>(url, formData, { headers, responseType: 'json' });
  }
}
