import { HttpClient } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { Router } from '@angular/router';
import { DataService } from '../services/data-service/data_service';
import { MatDialog } from '@angular/material/dialog';
import { BaseApp } from '../services/base-app/base_app';
import { MatTableDataSource } from '@angular/material/table';
import { InterventionService } from '../services/intervention/intervention.service';
import { PopUpInterventionComponent } from '../pop-up-intervention/pop-up-intervention.component';

@Component({
  selector: 'app-tableau-intervention',
  templateUrl: './tableau-intervention.component.html',
  styleUrls: ['./tableau-intervention.component.css']
})
export class TableauInterventionComponent implements OnInit {
  baseUrl: string;
  token!: string;
  listProfilsTmp: Array<any> = [];
  listProfils: { [key: string]: string } = {};
  idcompany !: string;
  public loading = false;
  displayedColumns: string[] = ['id', 'startHorodatage', 'endHorodatage', 'idclient', 'idcompteur', 'idsite', 'annuler', 'action'];

  dataSource: any;
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(public http: HttpClient, public baseApp: BaseApp, private router: Router,
    public dialog: MatDialog, private data: DataService, private intervention: InterventionService) {
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

  ngOnInit() {
    this.doGetListMeter();
  }


  doGetListMeter() {

    this.loading = true;
    const list = localStorage.getItem('list_intervention');
    if (list) {
      const parsedList = JSON.parse(list);
      // remplir le data source
      this.dataSource = new MatTableDataSource(parsedList);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
      this.loading = false;
    }

  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  deleteEmployee(row: any) {

    this.loading = true;
    this.intervention.editIntervention(
      row.id,
      row.startHorodatage,
      row.endHorodatage,
      true,
      this.baseUrl,
      this.token,

    ).subscribe({
      next: (data) => {

        if (data.msg == "insert_ok") {

          this.loading = false;
          // on reload le tableau
          const beginDate = localStorage.getItem('begin_horodatage');
          const endDate = localStorage.getItem('end_horodatage');
          if (beginDate && endDate) {
            this.intervention.searchListInterventionByDate(
              beginDate,
              endDate,
              this.baseUrl,
              this.token,

            ).subscribe({
              next: (data) => {
                this.loading = false;
                localStorage.setItem('list_intervention', JSON.stringify(data));
                this.doGetListMeter();

              },
              error: (error) => {
                this.loading = false;
                console.log(error);
              }
            });
          }


        }

      },
      error: (error) => {
        this.loading = false;
        console.log(error);
      }
    });
  }

  openEditForm(data: any) {
    const dialogRef = this.dialog.open(PopUpInterventionComponent, {
      data: data,
    });

    dialogRef.afterClosed().subscribe({
      next: () => {

        this.loading = false;
        const beginDate = localStorage.getItem('begin_horodatage');
        const endDate = localStorage.getItem('end_horodatage');
        if (beginDate && endDate) {
          this.intervention.searchListInterventionByDate(
            beginDate,
            endDate,
            this.baseUrl,
            this.token,

          ).subscribe({
            next: (data) => {
              this.loading = false;
              localStorage.setItem('list_intervention', JSON.stringify(data));
              this.doGetListMeter();


            },
            error: (error) => {
              this.loading = false;
              console.log(error);
            }
          });
        }
      },
    });
  }
}
