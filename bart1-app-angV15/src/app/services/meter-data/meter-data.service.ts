import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MeterDataService {
  private selectListDataUrl = 'http://localhost:8080/selectListData';

  constructor(private http: HttpClient) { }

  getSelectListData(): Observable<any> {
    return this.http.get<any>(this.selectListDataUrl);
  }
}
