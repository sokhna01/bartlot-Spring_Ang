import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Router } from "@angular/router";
import { TranslateService } from '@ngx-translate/core';
import { BaseApp } from '../services/base-app/base_app';
import { DataService } from "../services/data-service/data_service";
import { AuthenticationService } from '../services/auth-service/authentication.service';

declare var $: any;

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  token: any;
  baseUrl!: string;
  username!: string;
  password!: string;
  listProfils: any;
  version: string = '2.2.1';
  public loading = false;

  constructor(public http: HttpClient, public baseApp: BaseApp, private router: Router, public translate: TranslateService, private route: ActivatedRoute,
    private data: DataService, private authservice: AuthenticationService) {

    if (localStorage.getItem("token")) {
      this.data.changeMessage("true");
      this.router.navigate(['/home']);
    }
    this.getBaseUrl();
  }

  doLogin() {
    this.loading = true;
    this.authservice.Login(this.username, this.password).subscribe({
      next: (data) => {
        this.loading = false;

        if (data.error = 'null') {
          localStorage.setItem('id', data.id);
          localStorage.setItem('address', data.address1);
          localStorage.setItem('phone', '' + data.phone)
          localStorage.setItem('useautocompletion', '' + data.useautocompletion);
          localStorage.setItem('reset_password', data.reset_password);
          localStorage.setItem('listProfils', JSON.stringify(data.profiles));
          localStorage.setItem('profilDemo', data.profiles[0].pf_name);
          localStorage.setItem('listActions', data.actions);
          localStorage.setItem('token', data.token);


          let listActions = data.actions;

          if (listActions.length > 0) {

            localStorage.setItem('listActions', JSON.stringify(listActions));
          }

          localStorage.setItem('language', data.language);

          if (localStorage.getItem('language')) {

            const item = localStorage.getItem('language');
            if (typeof item == "string") {

              this.translate.use(item);
            }

          }
          console.log(data);

          this.data.changeMessage("true");
          this.router.navigate(['/home']);
        }
      },
      error: (err) => {
        this.loading = false;
        if (err.status === 401 && err.error.error === 'AuthenticationError') {
          $('#failedModal').modal('show');
        } else if (err.status === 500) {
          $('#errorModal').modal('show');
        }
      }
    });
  }

  getBaseUrl() {
    this.baseUrl = this.baseApp.getBaseUrl();
  }

}
