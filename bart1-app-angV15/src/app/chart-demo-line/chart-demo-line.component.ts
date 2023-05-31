
import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute, Router } from "@angular/router";
import { BaseApp } from '../services/base-app/base_app';
import { DataService } from "../services/data-service/data_service";
import { ListMeterServiceService } from '../services/service/list-meter-service.service';
import { CreateHighChartService } from '../services/high-chart/create-high-chart.service';

@Component({
  selector: 'app-chart-demo-line',
  templateUrl: './chart-demo-line.component.html',
  styleUrls: ['./chart-demo-line.component.css']
})
export class ChartDemoLineComponent implements OnInit {
  token: any;
  baseUrl!: string;
  public loading = false;
  list: any;
  listCompteurPr: Array<any> = [];
  listCompteurRe: Array<any> = [];
  listPuissancePr: Array<any> = [];
  listPuissanceRe: Array<any> = [];
  listPresencePr: Array<any> = [];
  listPresenceRe: Array<any> = [];
  listQualitePr: Array<any> = [];
  listQualiteRe: Array<any> = [];
  listProfilsTmp: Array<any> = [];
  listProfils: { [key: string]: string } = {};

  map: Map<string, string[]> = new Map<string, string[]>();

  constructor(public http: HttpClient, public baseApp: BaseApp, private router: Router, private route: ActivatedRoute,
    private data: DataService, private listMeterservice: ListMeterServiceService,
    private highChartService: CreateHighChartService) {
    this.baseApp.loggedOut();
    if (!localStorage.getItem("token")) {
      this.data.changeMessage("false");
      this.router.navigate(['/login']);
    }
    this.getBaseUrl();
    //this.translate.use(this.selectedLanguage);
    this.token = localStorage.getItem("token");
    const item = localStorage.getItem("token");
    const itemProfil = localStorage.getItem('listProfils');
    const itemCompany = localStorage.getItem("company_id");
    if (typeof item == "string" && typeof itemProfil == "string") {
      this.token = item;
      this.listProfilsTmp = JSON.parse(itemProfil);
    }
    for (let i = 0; i < this.listProfilsTmp.length; i++) {
      this.listProfils[this.listProfilsTmp[i].pf_code + ''] = "true";
    }
  }

  ngOnInit() {
    // this.loading = true;
    this.doGetDataMeter();
  }

  doGetDataMeter() {
    this.loading = true;
    this.listMeterservice.getDataMeterReporting(this.baseUrl, this.token).subscribe(
      {
        next:
          (data) => {

            this.loading = false;
            this.listCompteurPr = data['CPT-P'];
            this.listCompteurRe = data['CPT-R'];
            console.log(this.listCompteurPr);
            console.log(this.listCompteurRe);
            let objPresencePr: any = {};
            let objPresenceRe: any = {};
            let objPuissancePr: any = {};
            let objPuissanceRe: any = {};
            let objQualitePr: any = {};
            let objQualiteRe: any = {};
            for (let i = 0; i < this.listCompteurPr.length; i++) {
              objPresencePr['x'] = i + 1;
              objPresencePr['y'] = Number(this.listCompteurPr[i].presence);
              objPresencePr['datetime'] = this.listCompteurPr[i].horodotage;
              // console.log(obj)
              this.listPresencePr.push(objPresencePr);
              // this.listPresencePr.push(Number(this.listCompteurPr[i].presence));

              objPuissancePr['x'] = i + 1;
              objPuissancePr['y'] = Number(this.listCompteurPr[i].dataAPlus) - Number(this.listCompteurPr[i].dataAMoins);
              objPuissancePr['datetime'] = this.listCompteurPr[i].horodotage;
              this.listPuissancePr.push(objPuissancePr);

              objQualitePr['x'] = i + 1;
              objQualitePr['y'] = Number(this.listCompteurPr[i].qualite);
              objQualitePr['datetime'] = this.listCompteurPr[i].horodotage;
              this.listQualitePr.push(objQualitePr);

              objPresencePr = {};
              objPuissancePr = {};
              objQualitePr = {};
            }

            for (let i = 0; i < this.listCompteurRe.length; i++) {
              objPresenceRe['x'] = i + 1;
              objPresenceRe['y'] = Number(this.listCompteurRe[i].presence);
              objPresenceRe['datetime'] = this.listCompteurRe[i].horodotage;
              this.listPresenceRe.push(objPresenceRe);
              // this.listPresenceRe.push(Number(this.listCompteurRe[i].presence));

              objPuissanceRe['x'] = i + 1;
              objPuissanceRe['y'] = Number(this.listCompteurRe[i].dataAPlus) - Number(this.listCompteurRe[i].dataAMoins);
              objPuissanceRe['datetime'] = this.listCompteurRe[i].horodotage;
              this.listPuissanceRe.push(objPuissanceRe);

              objQualiteRe['x'] = i + 1;
              objQualiteRe['y'] = Number(this.listCompteurRe[i].qualite);
              objQualiteRe['datetime'] = this.listCompteurRe[i].horodotage;
              this.listQualiteRe.push(objQualiteRe);

              objPresenceRe = {};
              objPuissanceRe = {};
              objQualiteRe = {};
            }
            // console.log(this.listPresence);
            this.highChartService.plotChart("line",
              this.listPuissancePr,
              this.listPresencePr,
              this.listPuissanceRe,
              this.listPresenceRe,
              this.listQualitePr,
              this.listQualiteRe);
          },
        error: (error) => {
          this.loading = false;
          console.log(error);
        }
      });
  }

  getBaseUrl() {
    this.baseUrl = this.baseApp.getBaseUrl();
  }
}
