import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {
  private baseUrl = 'http://localhost:8080/profiles';

  constructor(private http: HttpClient) {}

  addProfile(pf_name: string, pf_description: string, pf_code: string, category: string): Observable<any> {
    const data = {
      pf_name,
      pf_description,
      pf_code,
      category
    };
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': 'Bearer ' + localStorage.getItem('token')
      })
    };
    return this.http.post(this.baseUrl+"/add", data, httpOptions);
  }
}
