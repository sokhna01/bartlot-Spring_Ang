
import { Component, ViewChild, AfterViewInit } from '@angular/core';
import { BaseApp } from '../services/base-app/base_app';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ActivatedRoute, Router } from "@angular/router";
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatDialog } from '@angular/material/dialog';
import { MatSort } from '@angular/material/sort';
import { DataService } from "../services/data-service/data_service";
import { ListMeterServiceService } from '../services/service/list-meter-service.service';
// import * as FileSaver from 'file-saver';

@Component({
  selector: 'app-result-update-source-demo',
  templateUrl: './result-update-source-demo.component.html',
  styleUrls: ['./result-update-source-demo.component.css']
})
export class ResultUpdateSourceDemoComponent implements AfterViewInit {
  baseUrl: string;
  token!: string;
  pickupDate!: Date;
  pickupDate_string!: string;
  headerString: string = 'Resultat BD avant execution Tâche 3';
  fileName!: string;
  idcompany !: string;
  listProfilsTmp: Array<any> = [];
  listProfils: { [key: string]: string } = {};
  trips: any;
  value: boolean = false;
  sub: any;
  public loading = false;
  displayedColumns: string[] = ['idClient', 'idSite', 'idPointComptage', 'idCompteur', 'horodotage', 'dataAPlus', 'dataAMoins', 'dataRPlus', 'dataRMoins', 'source', 'presence', 'qualite'];
  dataSource: any;
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(public http: HttpClient, public baseApp: BaseApp, private router: Router, private route: ActivatedRoute,
    public dialog: MatDialog, private data: DataService, private listMeterService: ListMeterServiceService) {
    this.baseApp.loggedOut();
    if (!localStorage.getItem("token")) {
      this.data.changeMessage("false");
      this.router.navigate(['/login']);
    }

    this.baseUrl = this.baseApp.getBaseUrl();
    const item = localStorage.getItem("token");
    const itemProfil = localStorage.getItem('listProfils');
    const itemCompany = localStorage.getItem('company_id');
    if (typeof item == "string" && typeof itemProfil == "string" && typeof itemCompany == "string") {
      this.token = item;
      this.listProfilsTmp = JSON.parse(itemProfil);
      this.idcompany = itemCompany;
    }
    for (let i = 0; i < this.listProfilsTmp.length; i++) {
      this.listProfils[this.listProfilsTmp[i].pf_code + ''] = "true";
    }
  }

  ngAfterViewInit() {
    console.log('Outside route');
    this.sub = this.route.params.subscribe(params => {
      this.pickupDate_string = params['pickup_date'];
      this.fileName = params['filename'];
      this.pickupDate = params['pickup_date'];
      this.goGetListMeter();
      // this.pickupDateBis = datePipe.transform(this.pickupDate_string, 'MMM d, y');
    });
  }



  doUpdateSourcePresence() {
    this.loading = true;
    this.listMeterService.updateSourcePresence(this.idcompany, this.listProfils, this.token, this.baseUrl).subscribe({
      next: (data) => {
        console.log(data);
        this.headerString = 'Resultat BD après exécution Tâche 3';
        //setTimeout(()=>{
        this.loading = false;
        if (data.msg == 'update_ok') {
          setTimeout(() => {
            this.goGetListMeter();
          }, 800);
        }
      },
      error: (error) => {
        this.loading = false;
        console.log(error);
      }
    });
  }


  goGetListMeter() {
    this.loading = true;
    this.listMeterService.getListMeter(this.idcompany, this.fileName, this.pickupDate, this.listProfils, this.token, this.baseUrl).subscribe({
      next: (data) => {
        console.log(data);
        console.log('select ok');
        //setTimeout(()=>{
        this.loading = false;
        this.trips = data;
        this.dataSource = new MatTableDataSource(this.trips);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
      },
      error: (error) => {
        this.loading = false;
        console.log(error);
      }
    });
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

}
