import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { DataService } from "../data-service/data_service";


@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  public loading = false;
  constructor(private router: Router, private data: DataService, private http: HttpClient) { }

  Login(username: string, password: string, companyCode: string): Observable<any> {
    let headers = new HttpHeaders({ 'Content-Type': 'application/x-www-form-urlencoded' });
    let dataform = "username=" + username + "&password=" + password + "&companyCode=" + companyCode;
    const url = 'http://localhost:8080/login/web';
    
    return this.http.post<any>(url, dataform, { headers, responseType: 'json' });
}


  logout() {
    this.data.changeMessage("false");
    let language = localStorage.getItem('language');
    localStorage.clear();
    if (language) {
      // console.log(language);
      localStorage.setItem('language', language);
    }
    else {
      console.log('null');
    }
    this.router.navigate(['/login']);
  }
}
