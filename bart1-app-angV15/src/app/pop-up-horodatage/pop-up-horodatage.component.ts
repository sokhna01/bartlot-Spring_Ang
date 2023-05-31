import { Component, Inject } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialog, MatDialogRef } from '@angular/material/dialog';
import { WorkTableServiceService } from '../services/work-table-service/work-table-service.service';
import { BaseApp } from '../services/base-app/base_app';
import { format } from 'date-fns';


@Component({
  selector: 'app-pop-up-horodatage',
  templateUrl: './pop-up-horodatage.component.html',
  styleUrls: ['./pop-up-horodatage.component.css']
})

export class PopUpHorodatageComponent {
  editForm: FormGroup;
  selectedValue!: string;
  baseUrl!: string;
  public loading = false;
  dataMap: Map<string, string[]> = new Map<string, string[]>();
  maxDate = new Date();
  success: number = 0;

  constructor(
    private _fb: FormBuilder,
    public dialog: MatDialog,
    public dialogRef: MatDialogRef<PopUpHorodatageComponent>,
    public workTableService: WorkTableServiceService,
    public baseApp: BaseApp,
    @Inject(MAT_DIALOG_DATA) public data: any) {


    this.baseUrl = this.baseApp.getBaseUrl();
    this.editForm = this._fb.group({
      dataAPlus: null,
      dataAMoins: null,
      dataRPlus: null,
      dataARMoins: null,
      horodatage: [new FormControl<Date | null>(null), [Validators.required]],
    });


    // ** pour afficher uniquement les date aterieure*****//

    // je recuperer la date de la ligne corespondant YYYY-MM-dd
    const dateOnly = this.data.split('T')[0];
    // je converti le string en date
    this.maxDate = new Date(dateOnly);
    // je soustrait la date pour ignorer la date du row
    // por afficher uniquement les date anterieur de la date du row
    this.maxDate.setDate(this.maxDate.getDate() - 1);

  }

  onFormSubmit() {
    // this.loading = true;
    if (this.editForm.valid) {

      // je recupere l'heure puis faire la concatenation
      const timeString = this.data.substring(this.data.indexOf('T'));
      const date = format(this.editForm.value.horodatage, 'yyyy-MM-dd');
      const horodatage = date.concat(timeString);

      this.loading = true;
      if (this.data) {
        this.loading = false;
        this.workTableService.getListDataAnterieur(
          horodatage,
          this.baseUrl
        ).subscribe({
          next: (data) => {

            if (data.length != 0) {

              this.editForm.value.dataAPlus = data[0].dataAPlus;
              this.editForm.value.dataAMoins = data[0].dataAMoins;
              this.editForm.value.dataRPlus = data[0].dataRPlus;
              this.editForm.value.dataRMoins = data[0].dataRMoins;

              this.dataMap.set(this.data,
                [data[0].dataAPlus, data[0].dataAMoins, data[0].dataRPlus, data[0].dataRMoins, data[0].source, data[0].idCompteur]);
              this.success = 1;

            }
            else if (data.length === 0) {

              this.success = 2;

            }

          }, error: (error) => {
            this.loading = false;
            console.log(error);
          }
        })

      }

    }

  }

  validated() {
    this.dialogRef.close(this.dataMap);
  }

}
