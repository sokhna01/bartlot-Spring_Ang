// import { Component } from '@angular/core';
// import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
// import { MatDialog } from '@angular/material/dialog';
// import { BaseApp } from '../services/base-app/base_app';
// import { ListMeterServiceService } from '../services/service/list-meter-service.service';
// import { HttpClient } from '@angular/common/http';
// import { ActivatedRoute, Router } from '@angular/router';
// import { DataService } from '../services/data-service/data_service';
// import { PopUpSuccessComponent } from '../pop-up-success/pop-up-success.component';

// @Component({
//   selector: 'app-tache-douze',
//   templateUrl: './tache-douze.component.html',
//   styleUrls: ['./tache-douze.component.css']
// })
// export class TacheDouzeComponent {

//   editForm: FormGroup;
//   baseUrl!: string;
//   public loading = false;
//   idcompany!: string;
//   token!: string;
//   listProfilsTmp: Array<any> = [];
//   listProfils: { [key: string]: string } = {};

//     // id_client!: string;
//     // id_site!: string;
//     // id_point_de_comptage!: string;
//     // convention!: boolean; 
//     // id_compteur_principal!: string;
//     // id_compteur_redondance!: string;
//     // tension_nominale_vn_primaire!: string;
//     // courant_nominale_in_primaire!: string;
//     // puissance_nominale_pn_du_point_de_comptage!: string;
//     // β_Injection!: string;
//     // β_Soutirage!: string;
//     // alarme_seul_a_plus!: string;
//     // alarme_seul_a_moins!: string;
//     // alarme_seul_r_plus!: string;
//     //  alarme_seul_r_moins!: string;
    
    

//     constructor(
//       private _fb: FormBuilder, public http: HttpClient, public baseApp: BaseApp,
//       private router: Router, private route: ActivatedRoute, public dialog: MatDialog,
//       private data: DataService, private listMeterService: ListMeterServiceService){

//       this.baseUrl = this.baseApp.getBaseUrl();
//       this.editForm = this._fb.group({
//         id_client:[null, [Validators.required]],
//         id_site:[null, [Validators.required]],
//         id_point_de_comptage:[null, [Validators.required]],
//         convention:[null, [Validators.required]],
//         id_compteur_principal:[null, [Validators.required]],
//         id_compteur_redondance:[null, [Validators.required]],
//         tension_nominale_vn_primaire:[null, [Validators.pattern(/^\d+\.\d+$/)]],
//         courant_nominale_in_primaire:[null, [Validators.pattern(/^\d+\.\d+$/)]],
//         puissance_nominale_pn_du_point_de_comptage:[null, [Validators.pattern(/^\d+\.\d+$/)]],      
//         β_Injection:[null, [Validators.pattern(/^\d+\.\d+$/)]],
//         β_Soutirage:[null, [Validators.pattern(/^\d+\.\d+$/)]],
//         alarme_seul_a_plus:[null, [Validators.required]],
//         alarme_seul_a_moins:[null, [Validators.required]],
//         alarme_seul_r_plus:[null, [Validators.required]],
//         alarme_seul_r_moins :[null, [Validators.required]],
//         alarme_seul_r_tg_mois :[null,[Validators.required]]
//       });

//       this.baseApp.loggedOut();

//       if (!localStorage.getItem("token")) {
//         this.data.changeMessage("false");
//         this.router.navigate(['/login']);
//       }

//       this.baseUrl = this.baseApp.getBaseUrl();
//       const item = localStorage.getItem("token");
//       const itemProfil = localStorage.getItem('listProfils');
//       const itemCompany = localStorage.getItem('company_id');
//       if (typeof item == "string" && typeof itemProfil == "string" && typeof itemCompany == "string") {
//         this.token = item;
//         this.listProfilsTmp = JSON.parse(itemProfil);
//         this.idcompany = itemCompany;
//       }

      
//       for (let i = 0; i < this.listProfilsTmp.length; i++) {
//         this.listProfils[this.listProfilsTmp[i].pf_code + ''] = "true";
//       }

//     }


//     onFormSubmit() {
//       if (this.editForm.valid) {

//         this.loading = true;

//         this.listMeterService.table_configuration_points_de_compteur(
//         this.idcompany, this.editForm.value.id_client, this.editForm.value.id_site ,this.editForm.value.id_point_de_comptage , this.editForm.value.convention , 
//         this.editForm.value.id_compteur_principal, this.editForm.value.id_compteur_redondance, this.editForm.value.tension_nominale_vn_primaire, this.editForm.value.courant_nominale_in_primaire, 
//         this.editForm.value.puissance_nominale_pn_du_point_de_comptage, this.editForm.value.β_Injection, this.editForm.value.β_Soutirage, this.editForm.value.alarme_seul_a_plus,
//         this.editForm.value.alarme_seul_a_moins, this.editForm.value.alarme_seul_r_plus, this.editForm.value.alarme_seul_r_moins, this.editForm.value.alarme_seul_r_tg_mois,this.baseUrl

//         ).subscribe({
//         next: (data) => {
//           this.loading = false;
//           console.log(data.msg);
//           let msg = "";
//           if (data.msg == "insert_ok") {
//             msg = "Opération réussie"
//           } else {
//             msg = "Compteur non disponible"
//           }

//           this.dialog.open(PopUpSuccessComponent, {
//             width: '500px',
//             data: msg

//           });
//         },

//         error: (error) => {
//           this.loading = false;
//           console.log(error);
//         }
//       });

//     }

//   }

// }


import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { BaseApp } from '../services/base-app/base_app';
import { ListMeterServiceService } from '../services/service/list-meter-service.service';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { DataService } from '../services/data-service/data_service';
import { format } from 'date-fns';
import { PopUpSuccessComponent } from '../pop-up-success/pop-up-success.component';

@Component({
  selector: 'app-tache-douze',
  templateUrl: './tache-douze.component.html',
  styleUrls: ['./tache-douze.component.css']
})
export class TacheDouzeComponent {

  editForm: FormGroup;
  baseUrl!: string;
  public loading = false;
  idcompany!: string;
  token!: string;
  listProfilsTmp: Array<any> = [];
  listProfils: { [key: string]: string } = {};

  constructor(
    private _fb: FormBuilder, public http: HttpClient, public baseApp: BaseApp,
    private router: Router, private route: ActivatedRoute, public dialog: MatDialog,
    private data: DataService, private listMeterService: ListMeterServiceService) {

    this.baseUrl = this.baseApp.getBaseUrl();
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
      alarme_seul_r_tg_mois:[null, [Validators.required, Validators.pattern(/^\d+(\.\d+)?$/)]]
    });

    this.baseApp.loggedOut();

    if (!localStorage.getItem("token")) {
      this.data.changeMessage("false");
      this.router.navigate(['/login']);
    }

    this.baseUrl = this.baseApp.getBaseUrl();
    const item = localStorage.getItem("token");
    const itemProfil = localStorage.getItem('listProfils');
    const itemCompany = localStorage.getItem('company_id');
    if (typeof item === "string" && typeof itemProfil === "string" && typeof itemCompany === "string") {
      this.token = item;
      this.listProfilsTmp = JSON.parse(itemProfil);
      this.idcompany = itemCompany;
    }
    for (let i = 0; i < this.listProfilsTmp.length; i++) {
        this.listProfils[this.listProfilsTmp[i].pf_code + ''] = "true";
    }      
  }

  onFormSubmit() {
  
            this.loading = true;

            // const alarmeseulaplus = format(this.editForm.value.alarme_seul_a_plus, 'yyyy-MM-dd');
            // const alarmeseulamoins = format(this.editForm.value.alarme_seul_a_moins, 'yyyy-MM-dd');
            // const alarmeseulrplus = format(this.editForm.value.alarme_seul_r_plus, 'yyyy-MM-dd');      
            // const alarmeseulrmoins = format(this.editForm.value.alarme_seul_r_moins, 'yyyy-MM-dd');
            // const alarmeseulrtgmois = format(this.editForm.value.alarme_seul_r_tg_mois, 'yyyy-MM-dd');
   
            this.listMeterService.table_configuration_points_de_compteur(
            this.idcompany,
            this.editForm.value.id_client,
            this.editForm.value.id_site,
            this.editForm.value.id_point_de_comptage,
            this.editForm.value.convention_signe,
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
            this.baseUrl
            ).subscribe({
            next: (data) => {
              this.loading = false;
              console.log(data.msg);
              let msg = "";
              if (data.msg == "insert_ok") {
                msg = "Opération réussie"
              } else {
                msg = "Compteur non disponible"
              }

              this.dialog.open(PopUpSuccessComponent, {
                width: '500px',
                data: msg
    
              });
            },
    
            error: (error) => {
              this.loading = false;
              console.log(error);
            }
          });
    
  }
        
}

