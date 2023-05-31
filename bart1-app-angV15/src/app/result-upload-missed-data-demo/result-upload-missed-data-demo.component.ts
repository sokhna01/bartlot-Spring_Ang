import { Component, ViewChild, OnInit } from '@angular/core';
import { BaseApp } from '../services/base-app/base_app';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute, Router } from "@angular/router";
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatDialog } from '@angular/material/dialog';
import { MatSort } from '@angular/material/sort';
import { DataService } from "../services/data-service/data_service";
import { ListMeterServiceService } from '../services/service/list-meter-service.service';

@Component({
  selector: 'app-result-upload-missed-data-demo',
  templateUrl: './result-upload-missed-data-demo.component.html',
  styleUrls: ['./result-upload-missed-data-demo.component.css']
})
export class ResultUploadMissedDataDemoComponent implements OnInit {
  baseUrl: string;
  token!: string;
  headerString: string = 'Resultat BD avant execution Tâche 2';
  listProfilsTmp: Array<any> = [];
  listProfils: { [key: string]: string } = {};
  value: boolean = false;
  sub: any;
  public loading = false;

  displayedColumns: string[] = ['idClient', 'idSite', 'idPointComptage', 'idCompteur', 'horodatage', 'dataAPlus', 'dataAMoins', 'dataRPlus', 'dataRMoins', 'source', 'presence', 'qualite'];

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
    if (typeof item == "string" && typeof itemProfil == "string") {
      this.token = item;
      this.listProfilsTmp = JSON.parse(itemProfil);
    }

    for (let i = 0; i < this.listProfilsTmp.length; i++) {
      this.listProfils[this.listProfilsTmp[i].pf_code + ''] = "true";
    }
  }

  ngOnInit() {

    this.sub = this.route.params.subscribe(params => {
      this.doGetListMeter();
      // this.pickupDateBis = datePipe.transform(this.pickupDate_string, 'MMM d, y');
    });
  }


  doUpdateTask2() {
    this.loading = true;
    this.listMeterService.updateTask2(this.listProfils, this.token, this.baseUrl).subscribe({
      next: (data) => {
        this.headerString = 'Resultat BD après execution Tâche 2';

        //setTimeout(()=>{
        this.loading = false;
        if (data.msg == 'update_ok') {
          setTimeout(() => {
            this.doGetListMeter();
          }, 800);
        }
      },
      error: (error) => {
        this.loading = false;
        console.log(error);
      }
    });
  }



  doGetListMeter() {
    this.loading = true;
    this.listMeterService.getListMeter(this.token, this.baseUrl).subscribe({
      next: (data) => {

        //setTimeout(()=>{
        this.loading = false;
        this.dataSource = new MatTableDataSource(data);
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

