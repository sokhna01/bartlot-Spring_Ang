import { Injectable } from '@angular/core';
import { Subscription } from 'rxjs';
import { Router } from "@angular/router";
import { JwtHelperService } from '@auth0/angular-jwt';


@Injectable()
export class BaseApp {

  baseUrl: string;
  baseUrlUploadDemo: string;
  remainingSecondFromJWT!: number;
  token_timer: number;
  idle_timer: number;
  jwtHelper: JwtHelperService = new JwtHelperService();

  constructor(private router: Router) {
    this.baseUrl = 'http://localhost:8080/';
    this.baseUrlUploadDemo = 'http://23.22.149.155:80/careTransport/uploadDemoFile.php';
    this.token_timer = 50;
    this.idle_timer = 15;
  }

  loggedOut() {
    if (localStorage.getItem('token')) {

      const jwtToken = localStorage.getItem('token');

      if (typeof jwtToken == "string") {
        if (this.jwtHelper.isTokenExpired(jwtToken)) {
          localStorage.clear();
        }
      }

    }
    else {
      localStorage.clear();
    }
  }


  getBaseUrl() {
    return this.baseUrl;
  }

  getBaseUrlUploadDemo() {
    return this.baseUrlUploadDemo;
  }

  logOut() {
    let language = localStorage.getItem('language');
    localStorage.clear();
    if (typeof language == "string") {
      localStorage.setItem('language', language);
    }

    window.location.reload();
    this.router.navigate(['/login']);
  }

  getRemainingSecondJWT() {
    return this.remainingSecondFromJWT;
  }

}
