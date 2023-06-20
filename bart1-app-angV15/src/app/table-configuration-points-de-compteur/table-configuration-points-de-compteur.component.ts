import { HttpClient } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { ActivatedRoute, Router } from '@angular/router';
import { DataService } from '../services/data-service/data_service';
import { MatDialog } from '@angular/material/dialog';
import { BaseApp } from '../services/base-app/base_app';
import { MatTableDataSource } from '@angular/material/table';
import { TableConfigurationPointsDeCompteurService } from '../services/table_configuration_points_de_compteur/table-configuration-points-de-compteur.service';
import { PopUpTableComponent } from '../pop-up-table/pop-up-table.component';

// import { InterventionService } from '../services/intervention/intervention.service';
// import { PopUpInterventionComponent } from '../pop-up-intervention/pop-up-intervention.component';

@Component({
  selector: 'app-table-configuration-points-de-compteur',
  templateUrl: './table-configuration-points-de-compteur.component.html',
  styleUrls: ['./table-configuration-points-de-compteur.component.css']
})
export class TableConfigurationPointsDeCompteurComponent implements OnInit{
  baseUrl: string;
  token!: string;
  listProfilsTmp: Array<any> = [];
  listProfils: { [key: string]: string } = {};
  idcompany !: string;
  value: boolean = false;
  sub: any;
  public loading = false;
  displayedColumns: string[] = ['id', 'id_client', 'id_site', 'id_point_de_comptage', 'convention_signe', 'id_compteur_principal','id_compteur_redondance', 'tension_nominale_vn_primaire',
  'courant_nominale_in_primaire', 'puissance_nominale_pn_du_point_de_comptage','b_injection', 'b_soutirage', 'alarme_seul_a_plus', 'alarme_seul_a_moins', 'alarme_seul_r_plus', 
  'alarme_seul_r_moins', 'alarme_seul_r_tg_mois', 'annuler', 'action'];
  
  dataSource: any;
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  constructor(public http: HttpClient, public baseApp: BaseApp, private router: Router, private route: ActivatedRoute,
    public dialog: MatDialog, private data: DataService, private tableConfigurationPointsDeCompteur: TableConfigurationPointsDeCompteurService) {
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
    this.tableConfigurationPointsDeCompteur.getListTable_configuration_points_de_compteur(this.baseUrl, this.token).subscribe({
      next: (data) => {

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

  deleteEmployee(row: any) {

    this.loading = true;
    this.tableConfigurationPointsDeCompteur.editTable_configuration_points_de_compteur(
      row.id,
      this.idcompany,
      row.id_client,
      row.id_site,
      row.id_point_de_comptage,
      row.convention_signe,
      row.id_compteur_principal,
      row.id_compteur_redondance,
      row.tension_nominale_vn_primaire,
      row.courant_nominale_in_primaire,
      row.puissance_nominale_pn_du_point_de_comptage,
      row.b_injection,
      row.b_soutirage,
      row.alarme_seul_a_plus,
      row.alarme_seul_a_moins,
      row.alarme_seul_r_plus,
      row.alarme_seul_r_moins,
      row.alarme_seul_r_tg_mois,
      true,
      this.baseUrl,
      this.token,

    ).subscribe({
      next: (data) => {
        this.loading = false;
        let msg = "";
        if (data.msg == "insert_ok") {
          msg = "Opération réussie";
        }

        this.doGetListMeter();

      },
      error: (error) => {
        this.loading = false;
        console.log(error);
      }
    });
  }

  openEditForm(data: any) {
    const dialogRef = this.dialog.open(PopUpTableComponent, {
      data: data,
    });

    dialogRef.afterClosed().subscribe({
      next: () => {
        this.doGetListMeter();
      },
    });
  }

}
