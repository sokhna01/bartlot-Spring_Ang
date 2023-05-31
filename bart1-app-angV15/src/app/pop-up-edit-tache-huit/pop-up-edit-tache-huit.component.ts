
import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { BaseApp } from '../services/base-app/base_app';


@Component({
  selector: 'app-pop-up-edit-tache-huit',
  templateUrl: './pop-up-edit-tache-huit.component.html',
  styleUrls: ['./pop-up-edit-tache-huit.component.css']
})

export class PopUpEditTacheHuitComponent implements OnInit {

  editForm: FormGroup;
  baseUrl!: string;
  public loading = false;

  constructor(
    private _fb: FormBuilder,
    public dialogRef: MatDialogRef<PopUpEditTacheHuitComponent>,
    public baseApp: BaseApp,
    @Inject(MAT_DIALOG_DATA) public data: any) {

    this.baseUrl = this.baseApp.getBaseUrl();
    this.editForm = this._fb.group({

      dataAPlus: [null, [Validators.required]], dataAMoins: [null, [Validators.required]],
      dataRPlus: [null, [Validators.required]], dataRMoins: [null, [Validators.required]],

    });
  }

  ngOnInit(): void {
    this.editForm.patchValue(this.data);
  }

  onFormSubmit() {
    this.loading = true;
    if (this.editForm.valid) {

      let data = {
        "horodatage": this.data.horodatage,
        "dataAPlus": this.editForm.value.dataAPlus,
        "dataAMoins": this.editForm.value.dataAMoins,
        "dataRPlus": this.editForm.value.dataRPlus,
        "dataRMoins": this.editForm.value.dataRMoins
      };
      this.dialogRef.close(data);

    }

  }

}
