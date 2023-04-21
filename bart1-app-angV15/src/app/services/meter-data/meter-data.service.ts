import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { MeterData, MeterConfiguration } from '../models';

@Injectable({
  providedIn: 'root'
})
export class MeterDataService {
  private baseUrl = 'http://localhost:3000';

  constructor(private http: HttpClient) {}

  getClientIds(): Observable<string[]> {
    return this.http.get<string[]>(`${this.baseUrl}/meter-data/client-ids`);
  }

  getSiteIds(): Observable<string[]> {
    return this.http.get<string[]>(`${this.baseUrl}/meter-data/site-ids`);
  }

  getPointIds(): Observable<string[]> {
    return this.http.get<string[]>(`${this.baseUrl}/meter-data/point-ids`);
  }

  getMeterConfigurations(): Observable<MeterConfiguration[]> {
    return this.http.get<MeterConfiguration[]>(`${this.baseUrl}/meter-configuration`);
  }

  saveMeterData(meterData: MeterData, file: File): Observable<any> {
    const formData = new FormData();
    formData.append('file', file);
    formData.append('meterData', JSON.stringify(meterData));
    return this.http.post<any>(`${this.baseUrl}/meter-data`, formData);
  }
}
