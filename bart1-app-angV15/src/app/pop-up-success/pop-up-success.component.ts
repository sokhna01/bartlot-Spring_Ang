import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

import { BaseApp } from '../services/base-app/base_app';
import { Router } from '@angular/router';


@Component({
  selector: 'app-pop-up-success',
  templateUrl: './pop-up-success.component.html',
  styleUrls: ['./pop-up-success.component.css']
})
export class PopUpSuccessComponent {

  constructor(

    private router: Router,
    public dialogRef: MatDialogRef<PopUpSuccessComponent>,
    public baseApp: BaseApp,
    @Inject(MAT_DIALOG_DATA) public data: any) { }

  onFormSubmit() {

    this.router.navigate([this.data.endpoint]);
    this.dialogRef.close(true);

  }
}
