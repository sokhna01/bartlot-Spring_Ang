import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";
import { sysOptions } from './translation/constants';
import { TranslateService } from '@ngx-translate/core';
import { DataService } from "./services/data-service/data_service";
import { Subscription } from 'rxjs';
import { AuthenticationService } from './services/auth-service/authentication.service';
import { Token } from '@angular/compiler';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'app';
  listActions: any;
  Adding: Array<any> = [];
  Modifying: Array<any> = [];
  Manage: Array<any> = [];
  Reporting: Array<any> = [];
  menuBar: Array<any> = [];
  isLogged: boolean = true;
  company_type!: string;
  company_id!: string;
  profilDemo: string = "";
  selectedLanguage = sysOptions.systemLanguage;
  subscription!: Subscription;

  constructor(private router: Router, public translate: TranslateService, private activatedRoute: ActivatedRoute, private data: DataService, private authenticationService: AuthenticationService) {
    this.translate.use(this.selectedLanguage);
  }
  ngOnInit() {

    // const path = this.activatedRoute.snapshot.queryParams['path'];
    // const navigateTo = '/' + path;
    // if (path) {
    //   this.router.navigate([navigateTo]);
    // }

    /*
      ici on fait la traduction
      c'est à dire si on recupere une langue dans la bdd
      alors l'app sera traduit en fonction de cette langue
      sinon on va traduire l'app par defaut c'est à dire l'anglais
     */

    if (localStorage.getItem('language')) {

      const result = localStorage.getItem('language');

      if (typeof result == "string") {
        this.translate.use(result);
      }
    }
    else {
      this.translate.use(sysOptions.systemLanguage);
    }
    this.manageMenuBar();
  }

  manageMenuBar() {
    
    let isLoggedStr;
    this.subscription = this.data.currentMessage.subscribe((message: any) => {
      isLoggedStr = message;
      this.isLogged = JSON.parse(isLoggedStr);
      if (this.isLogged) {

        const resultCompanyId = localStorage.getItem('company_id');
        const resultCompanyType = localStorage.getItem('company_type');
        const resultProfile = localStorage.getItem('profilDemo');
        const resultList = localStorage.getItem('listActions');

        if (typeof resultCompanyId == "string" && typeof resultCompanyType == "string" && typeof resultProfile == "string" && typeof resultList == "string") {
          // console.log(resultCompanyId);
          // console.log(resultCompanyType);
          // console.log(resultList);
          // console.log(resultProfile);
          this.company_id = resultCompanyId;
          this.company_type = resultCompanyType;
          this.profilDemo = resultProfile;
          this.listActions = JSON.parse(resultList);
        }

        if (localStorage.getItem('company_type') == 'societe') {
          // Je veux pas afficher l'option add_trip_file à une compagnie de type taxi
          for (let i = 0; i < this.listActions.length; i++) {
            if (this.listActions[i].actCode == 'act_007') {
              /*
              donc lui il retir le dernier element du tableau
               */
              this.listActions.splice(i, 1); 
            }
          }
        }


        this.emptyArrays();
        this.fillArrays();


        if (this.Adding.length != 0) {
          var obj = {
            category: "Adding",
            listAction: this.Adding
          };
          this.menuBar.push(obj);
        }
        if (this.Modifying.length != 0) {
          var obj = {
            category: "Modifying",
            listAction: this.Modifying
          };
          this.menuBar.push(obj);
        }
        if (this.Manage.length != 0) {
          var obj = {
            category: "Manage",
            listAction: this.Manage
          };
          this.menuBar.push(obj);
        }
      }
      else {
        console.log('False call');
        this.emptyArrays();
      }
    });
  }

  fillArrays() {
    for (let i = 0; i < this.listActions.length; i++) {
      if (this.listActions[i].category && this.listActions[i].category.toLowerCase() == "adding") {
        let length = this.Adding.length;
        this.Adding[length] = this.listActions[i];
      } 
      else if (this.listActions[i].category && this.listActions[i].category.toLowerCase() == "modifying") {
        let length = this.Modifying.length;
        this.Modifying[length] = this.listActions[i];
      }
       else if (this.listActions[i].category && this.listActions[i].category.toLowerCase() == "manage") {
        let length = this.Manage.length;
        this.Manage[length] = this.listActions[i];
      } 
      else if (this.listActions[i].category && this.listActions[i].category.toLowerCase() == "reporting") {
        let length = this.Reporting.length;
        this.Reporting[length] = this.listActions[i];
      }
    };
  }

  
  emptyArrays() {
    this.Adding = [];
    this.Modifying = [];
    this.Manage = [];
    this.Reporting = [];
    this.menuBar = [];
  }


  logOut() {
    this.authenticationService.logout();

  }
}
