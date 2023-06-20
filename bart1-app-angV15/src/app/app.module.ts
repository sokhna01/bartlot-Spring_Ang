import { NgModule, LOCALE_ID } from '@angular/core';
import { MatDialogModule } from '@angular/material/dialog'
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClient, HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { TranslateModule, TranslateLoader } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { NgxLoadingModule } from 'ngx-loading';
import { BrowserModule } from '@angular/platform-browser';
import { MatTableModule } from '@angular/material/table';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatMenuModule } from '@angular/material/menu';
import { MatCardModule } from '@angular/material/card';
import { MatSelectModule } from '@angular/material/select';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSortModule } from '@angular/material/sort';
import { MatRadioModule } from '@angular/material/radio';
import { MatNativeDateModule } from '@angular/material/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatExpansionModule } from '@angular/material/expansion';
import { FileUploadModule } from 'ng2-file-upload';
import * as fr from '@angular/common/locales/fr';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { registerLocaleData } from '@angular/common';

/*
service
 */
import { JwtInterceptorService } from './interceptors/jwt-interceptor.service';
import { DataService } from "./services/data-service/data_service";
import { BaseApp } from './services/base-app/base_app';

/*
route
 */
import { AppRoutingModule } from './app-routing.module';

/*
 *component
*/
import { ResultUploadFileDemoComponent } from './result-upload-file-demo/result-upload-file-demo.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { AppComponent } from './app.component';
import { ResultUploadMissedDataDemoComponent } from './result-upload-missed-data-demo/result-upload-missed-data-demo.component';
import { AddDemoFileComponent } from './add-demo-file/add-demo-file.component';
import { ResultUpdateQualiteDemoComponent } from './result-update-qualite-demo/result-update-qualite-demo.component';
import { ResultUpdateSourceDemoComponent } from './result-update-source-demo/result-update-source-demo.component';
import { ChartDemoComponent } from './chart-demo/chart-demo.component';
import { ChartDemoLineComponent } from './chart-demo-line/chart-demo-line.component';
import { AddProfilComponent } from './add-profil/add-profil.component';
import { TacheHuitComponent } from './tache-huit/tache-huit.component';
import { PopUpEditTacheHuitComponent } from './pop-up-edit-tache-huit/pop-up-edit-tache-huit.component';
import { PopUpUpdateTacheHuitComponent } from './pop-up-update-tache-huit/pop-up-update-tache-huit.component';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { PopUpHorodatageComponent } from './pop-up-horodatage/pop-up-horodatage.component';
import { PopUpSuccessComponent } from './pop-up-success/pop-up-success.component';
import { TacheNeufComponent } from './tache-neuf/tache-neuf.component';

import { TacheDouzeComponent } from './tache-douze/tache-douze.component';
import { TableConfigurationPointsDeCompteurComponent } from './table-configuration-points-de-compteur/table-configuration-points-de-compteur.component';

// datepicker
import {
  NgxMatDatetimePickerModule,
  NgxMatNativeDateModule,
  NgxMatTimepickerModule
} from '@angular-material-components/datetime-picker';
import { TableauInterventionComponent } from './tableau-intervention/tableau-intervention.component';
import { PopUpInterventionComponent } from './pop-up-intervention/pop-up-intervention.component';
import { LandingPageInterventionComponent } from './landing-page-intervention/landing-page-intervention.component';
import { Task6Component } from './task6/task6.component';
import { Task5Component } from './task5/task5.component';


export function createTranslateLoader(http: HttpClient) {
  return new TranslateHttpLoader(http, './assets/translate/', '.json');
}
@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    ResultUploadFileDemoComponent,
    ResultUploadMissedDataDemoComponent,
    AddDemoFileComponent,
    ResultUpdateQualiteDemoComponent,
    ResultUpdateSourceDemoComponent,
    ChartDemoComponent,
    ChartDemoLineComponent,
    AddProfilComponent,
    TacheHuitComponent,
    PopUpEditTacheHuitComponent,
    PopUpUpdateTacheHuitComponent,
    PopUpHorodatageComponent,
    PopUpSuccessComponent,
    TacheDouzeComponent,
    TableConfigurationPointsDeCompteurComponent,
    TacheNeufComponent,
    TableauInterventionComponent,
    PopUpInterventionComponent,
    Task6Component,
    Task5Component,
    LandingPageInterventionComponent,

  ],
  imports: [
    MatDatepickerModule,
    NgxMatDatetimePickerModule,
    NgxMatTimepickerModule,
    NgxMatNativeDateModule,
    HttpClientModule,
    BrowserModule,
    MatAutocompleteModule,
    AppRoutingModule,
    MatSnackBarModule,
    BrowserAnimationsModule,
    MatTableModule,
    TranslateModule,
    MatMenuModule,
    ReactiveFormsModule,
    FormsModule,
    FileUploadModule,
    MatDialogModule,
    MatSelectModule,
    MatCardModule,
    MatPaginatorModule,
    MatProgressSpinnerModule,
    MatIconModule,
    MatToolbarModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatSortModule,
    MatRadioModule,
    MatNativeDateModule,
    MatNativeDateModule,
    MatExpansionModule,

    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: (createTranslateLoader),
        deps: [HttpClient]
      }
    }),
    NgxLoadingModule.forRoot({}),
  ],
  providers: [{ provide: HTTP_INTERCEPTORS, useClass: JwtInterceptorService, multi: true }, BaseApp, DataService,
  { provide: LOCALE_ID, useValue: 'fr-FR' },],
  bootstrap: [AppComponent]
})
export class AppModule {
  constructor() {
    registerLocaleData(fr.default);
  }
}
