import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { MeterConfiguration } from 'src/app/interfaces/meter-configuration';

@Injectable({
  providedIn: 'root'
})
export class MeterConfigService {
  private apiUrl = 'https://localhost:8080/getlistmeterconfig';

  constructor(private http: HttpClient) { }

  getAllMeterConfigurations(): Observable<MeterConfiguration[]> {
    return this.http.get<MeterConfiguration[]>(this.apiUrl);
  }
}
