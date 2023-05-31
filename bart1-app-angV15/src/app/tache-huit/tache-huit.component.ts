
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
import { PopUpEditTacheHuitComponent } from '../pop-up-edit-tache-huit/pop-up-edit-tache-huit.component';
import { PopUpUpdateTacheHuitComponent } from '../pop-up-update-tache-huit/pop-up-update-tache-huit.component';
import { MatRadioChange } from '@angular/material/radio';
import { PopUpHorodatageComponent } from '../pop-up-horodatage/pop-up-horodatage.component';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-tache-huit',
  templateUrl: './tache-huit.component.html',
  styleUrls: ['./tache-huit.component.css']
})
export class TacheHuitComponent implements OnInit {

  baseUrl: string;
  token!: string;
  listProfilsTmp: Array<any> = [];
  listProfils: { [key: string]: string } = {};
  idcompany !: string;
  isValidated: boolean = false;
  dataMap: Map<string, string[]> = new Map<string, string[]>();
  public loading = false;


  displayedColumns: string[] = [
    'horodatage', 'dataAPlusPr', 'dataAMoinsPr', 'dataRPlusPr',
    'dataRMoinsPr', 'idCompteurPr', 'dataAPlusRe', 'dataAMoinsRe',
    'dataRPlusRe', 'dataRMoinsRe', 'idCompteurRe', 'dataAPlusSe',
    'dataAMoinsSe', 'dataRPlusSe', 'dataRMoinsSe', 'idCompteurSe', 'interpolation', 'saisie', 'anterieure'];

  dataSource: any;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(public http: HttpClient, public baseApp: BaseApp, public translate: TranslateService, private router: Router, private route: ActivatedRoute,
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

  ngOnInit() {

    this.doGetListMeter();
  }

  doGetListMeter() {
    this.loading = true;

    this.listMeterService.tache8GetTable(this.token, this.baseUrl).subscribe({
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

  openEditManualForm(_row: any) {

    const dialogRef = this.dialog.open(PopUpEditTacheHuitComponent, {
      data: _row
    });

    dialogRef.afterClosed().subscribe(result => {

      /*
       *result c'est l'objet passer apres validation du popup
       *je test si l'horodatage de cet objet et dans le map
       *si oui je supprime le map pour eviter les doublou
      */

      if (result) {

        if (this.dataMap.has(result.horodatage)) {
          this.dataMap.delete(result.horodatage);
        }

        this.dataMap.set(result.horodatage, [result.dataAPlus, result.dataAMoins, result.dataRPlus, result.dataRMoins, "Man"]);
        /*
         *pour activer le bouton
        */
        if (this.dataMap.size === 0) {
          this.isValidated = false;
        } else {
          this.isValidated = true;
        }
      }

    });

  }


  doUpdateTask() {

    this.dialog.open(PopUpUpdateTacheHuitComponent, {
      width: '500px',
      data: this.dataMap
    });

  }

  openHorodatageForm(row: any) {

    const dialogRef = this.dialog.open(PopUpHorodatageComponent, {
      width: '500px',
      data: row.horodatage
    });

    dialogRef.afterClosed().subscribe(result => {

      /*
       *result c'est le map passer apres validation du popup horodatage
       *je test si la cle de ce map est dans le dataMap
       *si oui je supprime le map pour eviter les doublou
      */

      if (result) {

        if (this.dataMap.has(result.keys().next().value)) {
          this.dataMap.delete(result.keys().next().value);
        }

        this.dataMap.set(result.keys().next().value,
          result.get(result.keys().next().value));
        /*
         *pour activer le bouton
        */

        if (this.dataMap.size === 0) {
          this.isValidated = false;
        } else {
          this.isValidated = true;
        }
      }


    });

  }


  onChange(mrChange: MatRadioChange, row: any) {

    let selectedValue = mrChange.value;
    let rowId = selectedValue.slice(0, -3);
    let source = selectedValue.slice(-2);

    if (this.dataMap.has(rowId)) {
      this.dataMap.delete(rowId);
    }

    if (source == "Pr") {

      this.dataMap.set(row.horodatage, [row.dataAPlusPr, row.dataAMoinsPr, row.dataRPlusPr, row.dataRMoinsPr, source, row.idCompteurPr]);

    } else if (source == "Re") {

      this.dataMap.set(row.horodatage, [row.dataAPlusRe, row.dataAMoinsRe, row.dataRPlusRe, row.dataRMoinsRe, source, row.idCompteurRe]);

    } else if (source == "Se") {

      this.dataMap.set(row.horodatage, [row.dataAPlusSe, row.dataAMoinsSe, row.dataRPlusSe, row.dataRMoinsSe, source, row.idCompteurSe]);

    } else if (source == "Ma") {

      this.openEditManualForm(row);
    }
    else if (source == "Au") {

      this.openHorodatageForm(row);

    }
    else if (source == "In") {

      this.dataMap.set(row.horodatage, ['null', 'null', 'null', 'null', source, "CPT-I"]);

    }
    /*
    pour activer le bouton
    */
    if (this.dataMap.size === 0) {
      this.isValidated = false;
    } else {
      this.isValidated = true;
    }

  }


}

