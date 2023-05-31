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
  selector: 'app-tache-neuf',
  templateUrl: './tache-neuf.component.html',
  styleUrls: ['./tache-neuf.component.css']
})
export class TacheNeufComponent implements OnInit {

  editForm: FormGroup;
  baseUrl!: string;
  public loading = false;
  token!: string;
  listProfilsTmp: Array<any> = [];
  listProfils: { [key: string]: string } = {};
  minDateDebut = new Date();
  minDateFin = new Date();
  donnees: { [key: string]: string } = {};




  @ViewChild('picker') picker: any;
  @ViewChild('EndPicker') EndPicker: any;

  constructor(

    private _fb: FormBuilder, public http: HttpClient, public baseApp: BaseApp,
    private router: Router, public dialog: MatDialog,
    private data: DataService, private doIntervention: InterventionService) {

    this.baseUrl = this.baseApp.getBaseUrl();
    this.editForm = this._fb.group({
      idCompteur: [null, [Validators.required]],
      start: [new FormControl<Date | null>(null), [Validators.required]],
      end: [new FormControl<Date | null>(null), [Validators.required]],
    });




    // je desactive le end pour quoi choisi toujour le start
    this.editForm.get('end')?.disable();

    const today = new Date();

    // Définissez l'heure, les minutes et les secondes à zéro
    today.setHours(0, 0, 0, 0);
    // Définissez la valeur du FormControl sur la date modifiée
    // Utilisez patchValue pour définir la valeur du FormControl dans le FormGroup
    this.editForm.patchValue({
      start: today,
      end: today
    });


    // pour afficher uniquement les date posterieur

    this.minDateDebut.setDate(this.minDateFin.getDate() - 1);
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

        // j'active le start date
        this.editForm.get('end')?.enable();
        //si oui la date de end sera la date debut
        // c'est a dire on choisi à partir de la date de debut
        // la date de fin doit etre superieur ou egal àa la date debut
        this.editForm.patchValue({
          end: value
        });

        // la date min sera egal à la date debut
        this.minDateFin = value;

      }

    });


    // Abonnement aux modifications de la valeur du champ "end"
    // this.editForm.get('end')?.valueChanges.subscribe((newValue) => {
    //   const startDate = new Date(this.editForm.value.start);
    //   const endDate = new Date(this.editForm.value.end);
    //   console.log('La valeur de "end" a été modifiée :', newValue);
    //   console.log("start date" + startDate);
    //   console.log("end date" + endDate);
    // });


    // // Abonnement aux modifications de la valeur du champ "end"
    // this.editForm.get('end')?.valueChanges.subscribe((newValue) => {
    //   console.log('La valeur de "end" a été modifiée :', newValue);
    //   // Effectuez les actions souhaitées lorsque la valeur de "end" change
    // });

  }

  onFormSubmit() {

    if (this.editForm.valid) {

      const startDate = format(this.editForm.value.start, 'yyyy-MM-dd HH:mm:ss');
      const endDate = format(this.editForm.value.end, 'yyyy-MM-dd HH:mm:ss');

      console.log(this.editForm.value);


      this.loading = true;
      this.doIntervention.AddIntervention(
        this.editForm.value.idCompteur,
        startDate,
        endDate,
        this.baseUrl,
        this.token,


      ).subscribe({
        next: (data) => {
          this.loading = false;
          let msg = "";
          if (data.msg == "insert_ok") {
            msg = "SUCCESS_MESSAGE"
          } else if (data.msg == "not_ok") {

            msg = "METER_NOT_FOUND"
          } else if (data.msg == "exist") {

            msg = "HORODATA_EXIT"

          }

          this.donnees['message'] = msg;
          this.donnees['endpoint'] = "/home";

          this.dialog.open(PopUpSuccessComponent, {
            width: '500px',
            data: this.donnees

          });

        },
        error: (error) => {
          this.loading = false;
          console.log(error);
        }
      });

    }

  }
}
