import { Component } from '@angular/core';
import { MeterDataService } from '../services/meter-data/meter-data.service';
import { DataService } from '../services/data-service/data_service';
import { Router } from '@angular/router';
import { BaseApp } from '../services/base-app/base_app';



@Component({
  selector: 'app-add-source-presence-qualite',
  templateUrl: './add-source-presence-qualite.component.html',
  styleUrls: ['./add-source-presence-qualite.component.css']
})
export class AddSourcePresenceQualiteComponent {
  token!: string;
  baseUrl!: string;

  importedFile!: File;


  constructor(
    private meterDataService: MeterDataService,
    private data: DataService,
    private router: Router,
    public baseApp: BaseApp
  ) {
    if (!localStorage.getItem("token")) {
      this.data.changeMessage("false");
      this.router.navigate(['/login']);
      return;
    }
    this.baseUrl = this.baseApp.getBaseUrl();
    const item = localStorage.getItem("token");
    if (typeof item == "string") {
      this.token = item;
    }
}
}