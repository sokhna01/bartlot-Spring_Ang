import { Component, Inject, ViewChild } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { BaseApp } from '../services/base-app/base_app';
import { format } from 'date-fns';
import { InterventionService } from '../services/intervention/intervention.service';

@Component({
  selector: 'app-pop-up-intervention',
  templateUrl: './pop-up-intervention.component.html',
  styleUrls: ['./pop-up-intervention.component.css']
})
export class PopUpInterventionComponent {

  editForm: FormGroup;
  baseUrl!: string;
  public loading = false;
  @ViewChild('picker') picker: any;
  @ViewChild('EndPicker') EndPicker: any;
  minDateDebut = new Date();
  minDateFin = new Date();
  token!: string;
  listProfilsTmp: Array<any> = [];
  listProfils: { [key: string]: string } = {};
  isValid: boolean = true;
  message!: string;
  success: boolean = false;
  constructor(
    private _fb: FormBuilder,
    public dialogRef: MatDialogRef<PopUpInterventionComponent>,
    public baseApp: BaseApp,
    private doIntervention: InterventionService,
    @Inject(MAT_DIALOG_DATA) public data: any) {

    this.baseUrl = this.baseApp.getBaseUrl();
    const item = localStorage.getItem("token");
    const itemProfil = localStorage.getItem('listProfils');
    if (typeof item == "string" && typeof itemProfil == "string") {
      this.token = item;
      this.listProfilsTmp = JSON.parse(itemProfil);
    }
    this.editForm = this._fb.group({

      idclient: [null, [Validators.required]],
      idcompteur: [null, [Validators.required]],
      idsite: [null, [Validators.required]],
      startHorodatage: [new FormControl<Date | null>(null), [Validators.required]],
      endHorodatage: [new FormControl<Date | null>(null), [Validators.required]],

    });

    // je desactive le endHorodatage pour quoi choisi toujour le start
    this.editForm.get('endHorodatage')?.disable();
    this.editForm.get('idsite')?.disable();
    this.editForm.get('idcompteur')?.disable();
    this.editForm.get('idclient')?.disable();

    // pour afficher uniquement les date posterieur

    this.minDateDebut.setDate(this.minDateDebut.getDate() - 1);
    this.minDateFin.setDate(this.minDateFin.getDate() - 1);
    this.baseApp.loggedOut();

  }

  ngOnInit(): void {

    // mettre dans les input start and end date leurs valeurs respectif
    this.editForm.patchValue(this.data);
    const start = new Date(this.data.startHorodatage);
    const end = new Date(this.data.endHorodatage);
    this.editForm.patchValue({
      startHorodatage: start,
      endHorodatage: end

    });

    // je recupere la date de debut
    const startDateControl: any = this.editForm.get('startHorodatage');

    // apres avoir choisi une date
    startDateControl.valueChanges.subscribe((value: any) => {

      // je verifie si on a choisi une date
      if (value) {

        // j'active le end date
        this.editForm.get('endHorodatage')?.enable();
        //si oui la date de end sera la date debut
        // c'est a dire on choisi à partir de la date de debut
        // la date de fin doit etre superieur ou egal àa la date debut
        this.editForm.patchValue({
          endHorodatage: value
        });

        // la date min sera egal à la date debut
        this.minDateFin = value;
        // activer le button
        this.isValid = false;

      }

    });
  }

  onFormSubmit() {
    this.loading = true;
    if (this.editForm.valid) {

      const startDate = format(this.editForm.value.startHorodatage, 'yyyy-MM-dd HH:mm:ss');
      const endDate = format(this.editForm.value.endHorodatage, 'yyyy-MM-dd HH:mm:ss');

      this.loading = true;
      this.doIntervention.editIntervention(
        this.data.id,
        startDate,
        endDate,
        false,
        this.baseUrl,
        this.token,

      ).subscribe({
        next: (data) => {
          this.loading = false;
          if (data.msg == "insert_ok") {
            this.message = "SUCCESS_MESSAGE"
            this.success = true;
          }
        },
        error: (error) => {
          this.loading = false;
          console.log(error);
        }
      });

    }

  }

  dissmissPopUp() {
    this.dialogRef.close();
  }

}
