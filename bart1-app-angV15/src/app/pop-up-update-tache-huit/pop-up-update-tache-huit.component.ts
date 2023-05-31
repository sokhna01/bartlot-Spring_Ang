import { Component, Inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog, MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { WorkTableServiceService } from '../services/work-table-service/work-table-service.service';

import { BaseApp } from '../services/base-app/base_app';
import { Router } from '@angular/router';

@Component({
  selector: 'app-pop-up-update-tache-huit',
  templateUrl: './pop-up-update-tache-huit.component.html',
  styleUrls: ['./pop-up-update-tache-huit.component.css']
})
export class PopUpUpdateTacheHuitComponent {


  baseUrl!: string;
  editForm: FormGroup;
  public loading = false;
  success: boolean = false;
  message!: string;

  constructor(
    private _fb: FormBuilder,
    private router: Router,
    public dialog: MatDialog,
    public dialogRef: MatDialogRef<PopUpUpdateTacheHuitComponent>,
    public workTableService: WorkTableServiceService,
    public baseApp: BaseApp,
    @Inject(MAT_DIALOG_DATA) public data: any) {

    this.baseUrl = this.baseApp.getBaseUrl();
    this.editForm = this._fb.group({
      commentaire: [null, [Validators.required]]

    });

  }

  onFormSubmit() {

    this.loading = true;
    let i = 0;

    this.data.forEach((value: string, key: string) => {

      this.workTableService.updateWorkTable(key, value[0], value[1], value[2],
        value[3], value[4], value[5], this.editForm.value.commentaire, this.baseUrl
      ).subscribe({
        next: (data) => {

          this.loading = false;
          if (data.msg.trim() == 'success') {

            // si on fait pas ce test on risque d'avoir
            // un bug sur le popup
            i++;
            if (i == this.data.size) {

              this.success = true;
              this.message = "SUCCESS_SUBMISSION"

            }

          } else {
            // si on fait pas ce test on risque d'avoir
            // un bug sur le popup
            i++;
            if (i == this.data.size) {

              this.success = true;
              this.message = "ERROR"

            }
          }

        }, error: (error) => {
          this.loading = false;
          console.log(error);
        }
      })


    });


  }
  home() {

    this.router.navigate(['/home']);
    this.dialogRef.close(true);

  }
}
