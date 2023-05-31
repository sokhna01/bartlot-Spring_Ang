import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { BaseApp } from '../services/base-app/base_app';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { DataService } from '../services/data-service/data_service';
import { format } from 'date-fns';
import { PopUpSuccessComponent } from '../pop-up-success/pop-up-success.component';
import { InterventionService } from '../services/intervention/intervention.service';
@Component({
  selector: 'app-landing-page-intervention',
  templateUrl: './landing-page-intervention.component.html',
  styleUrls: ['./landing-page-intervention.component.css']
})
export class LandingPageInterventionComponent implements OnInit {

  editForm: FormGroup;
  baseUrl!: string;
  public loading = false;
  token!: string;
  listProfilsTmp: Array<any> = [];
  listProfils: { [key: string]: string } = {};
  donnees: { [key: string]: string } = {};
  minDateFin = new Date();
  isValidated: boolean = false;


  @ViewChild('picker') picker: any;
  @ViewChild('EndPicker') EndPicker: any;

  constructor(

    private _fb: FormBuilder, public http: HttpClient, public baseApp: BaseApp,
    private router: Router, public dialog: MatDialog,
    private data: DataService, private doIntervention: InterventionService) {

    this.baseUrl = this.baseApp.getBaseUrl();
    this.editForm = this._fb.group({
      start: [new FormControl<Date | null>(null), [Validators.required]],
      end: [new FormControl<Date | null>(null), [Validators.required]],
    });


    // je desactive le end pour quoi choisi toujour le start
    this.editForm.get('end')?.disable();

    const dayStart = new Date();
    const dayEnd = new Date();


    // Définissez l'heure, les minutes et les secondes à zéro
    dayStart.setHours(0, 0, 0, 0);
    dayEnd.setHours(23, 50, 0, 0);
    // Définissez la valeur du FormControl sur la date modifiée
    // Utilisez patchValue pour définir la valeur du FormControl dans le FormGroup
    this.editForm.patchValue({
      start: dayStart,
      end: dayEnd
    });


    // pour afficher uniquement les date posterieur

    this.minDateFin.setDate(this.minDateFin.getDate() - 1);

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

  ngOnInit(): void {

    // je recupere la date du debut
    const startDateControl: any = this.editForm.get('start');

    // apres avoir choisi une date
    startDateControl.valueChanges.subscribe((value: any) => {

      // je verifie si on a choisi une date
      if (value) {

        this.isValidated = true;
        // j'active le start date
        this.editForm.get('end')?.enable();
        //si oui la date de end sera la date debut
        // c'est a dire on choisi à partir de la date de debut
        // la date de fin doit etre superieur ou egal à la date debut
        const endDate = new Date(value)

        endDate.setHours(23, 50, 0, 0);

        this.editForm.patchValue({
          end: endDate
        });

        // la date min sera egal à la date debut
        this.minDateFin = endDate;

      }

    });

  }

  onFormSubmit() {

    if (this.editForm.valid) {

      const startDate = format(this.editForm.value.start, 'yyyy-MM-dd HH:mm:ss');
      const endDate = format(this.editForm.value.end, 'yyyy-MM-dd HH:mm:ss');


      this.loading = true;
      this.doIntervention.searchListInterventionByDate(
        startDate,
        endDate,
        this.baseUrl,
        this.token,

      ).subscribe({
        next: (data) => {

          if (data.length != 0) {
            this.loading = false;
            // je stock le resultat dans des variables temporaires du navigateur
            localStorage.setItem('begin_horodatage', startDate);
            localStorage.setItem('end_horodatage', endDate);
            localStorage.setItem('list_intervention', JSON.stringify(data));
            this.router.navigate(['/intervention']);
          }
          else if (data.length === 0) {
            // j'affiche le popUp
            this.loading = false;
            this.donnees['message'] = "TITLE_DATA_NOT_AVAILABLE";
            this.donnees['endpoint'] = "/landing_page_intervention";
            this.dialog.open(PopUpSuccessComponent, {
              width: '500px',
              data: this.donnees

            });

          }

        },
        error: (error) => {
          this.loading = false;
          console.log(error);
        }
      });

    }

  }
}
