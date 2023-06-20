import { Component, Inject, ViewChild } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { BaseApp } from '../services/base-app/base_app';
import { format } from 'date-fns';
import { TableConfigurationPointsDeCompteurService } from '../services/table_configuration_points_de_compteur/table-configuration-points-de-compteur.service';

@Component({
  selector: 'app-pop-up-table',
  templateUrl: './pop-up-table.component.html',
  styleUrls: ['./pop-up-table.component.css']
})
export class PopUpTableComponent {

  editForm: FormGroup;
  baseUrl!: string;
  public loading = false;
  @ViewChild('picker') picker: any;
  @ViewChild('EndPicker') EndPicker: any;
  idcompany !: string;
  token!: string;
  listProfilsTmp: Array<any> = [];
  listProfils: { [key: string]: string } = {};
  isValid: boolean = true;
  message!: string;
  success: boolean = false;

  constructor(
    private _fb: FormBuilder,
    public dialogRef: MatDialogRef<PopUpTableComponent>,
    public baseApp: BaseApp,
    private doTableconfig: TableConfigurationPointsDeCompteurService,
    @Inject(MAT_DIALOG_DATA) public data: any) {
      
    this.baseUrl = this.baseApp.getBaseUrl();
    const item = localStorage.getItem("token");
    const itemProfil = localStorage.getItem('listProfils');
    const itemCompany = localStorage.getItem('company_id');
    if (typeof item == "string" && typeof itemProfil == "string" && typeof itemCompany == "string") {
      this.token = item;
      this.listProfilsTmp = JSON.parse(itemProfil);
      this.idcompany = itemCompany;
    }
    this.editForm = this._fb.group({

      id_client: [null, [Validators.required]],
      id_site: [null, [Validators.required]],
      id_point_de_comptage: [null, [Validators.required]],
      convention_signe: [null, [Validators.required]],
      id_compteur_principal: [null, [Validators.required]],
      id_compteur_redondance: [null, [Validators.required]],
      tension_nominale_vn_primaire: [null, [Validators.required, Validators.pattern(/^\d+(\.\d+)?$/)]],
      courant_nominale_in_primaire: [null, [Validators.required, Validators.pattern(/^\d+(\.\d+)?$/)]],
      puissance_nominale_pn_du_point_de_comptage: [null, [Validators.required, Validators.pattern(/^\d+(\.\d+)?$/)]],
      b_injection: [null, [Validators.required, Validators.pattern(/^\d+(\.\d+)?$/)]],
      b_soutirage: [null, [Validators.required, Validators.pattern(/^\d+(\.\d+)?$/)]],
      alarme_seul_a_plus: [null, [Validators.required, Validators.pattern(/^\d+(\.\d+)?$/)]],
      alarme_seul_a_moins: [null, [Validators.required, Validators.pattern(/^\d+(\.\d+)?$/)]],
      alarme_seul_r_plus: [null, [Validators.required, Validators.pattern(/^\d+(\.\d+)?$/)]],
      alarme_seul_r_moins: [null, [Validators.required, Validators.pattern(/^\d+(\.\d+)?$/)]],
      alarme_seul_r_tg_mois: [null, [Validators.required, Validators.pattern(/^\d+(\.\d+)?$/)]]
    });
  }
 
  onFormSubmit() {
    this.loading = true;
    if (this.editForm.valid) {

      // const alarmeseulaplus = format(this.editForm.value.alarme_seul_a_plus, 'yyyy-MM-dd');
      // const alarmeseulamoins = format(this.editForm.value.alarme_seul_a_moins, 'yyyy-MM-dd');
      // const alarmeseulrplus = format(this.editForm.value.alarme_seul_r_plus, 'yyyy-MM-dd');
      // const alarmeseulrmoins = format(this.editForm.value.alarme_seul_r_moins, 'yyyy-MM-dd');
      // const alarmeseulrtgmois = format(this.editForm.value.alarme_seul_r_tg_mois, 'yyyy-MM-dd');
      
      this.loading = true;
      this.doTableconfig.editTable_configuration_points_de_compteur(
        this.data.id,
        this.idcompany,
        this.editForm.value.id_client, 
        this.editForm.value.id_site ,
        this.editForm.value.id_point_de_comptage ,
        this.editForm.value.convention_signe , 
        this.editForm.value.id_compteur_principal, 
        this.editForm.value.id_compteur_redondance, 
        this.editForm.value.tension_nominale_vn_primaire, 
        this.editForm.value.courant_nominale_in_primaire, 
        this.editForm.value.puissance_nominale_pn_du_point_de_comptage, 
        this.editForm.value.b_injection, 
        this.editForm.value.b_soutirage, 
        this.editForm.value.alarme_seul_a_plus,
        this.editForm.value.alarme_seul_a_moins, 
        this.editForm.value.alarme_seul_r_plus, 
        this.editForm.value.alarme_seul_r_moins, 
        this.editForm.value.alarme_seul_r_tg_mois,
        // alarmeseulaplus,
        // alarmeseulamoins,
        // alarmeseulrplus,
        // alarmeseulrmoins,
        // alarmeseulrtgmois,      
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
