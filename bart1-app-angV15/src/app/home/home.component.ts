import { Component } from '@angular/core';
import { Router } from "@angular/router";
import { DataService } from "../services/data-service/data_service";
import { BaseApp } from '../services/base-app/base_app';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

  token: any;
  baseUrl!: String;
  profilDemo!: string;
  listProfils: any;

  constructor(private baseApp: BaseApp, private router: Router, private data: DataService) {

    this.baseApp.loggedOut();

    if (!localStorage.getItem("token")) {
      this.data.changeMessage("false");
      this.router.navigate(['/login']);
    }

  }


}
