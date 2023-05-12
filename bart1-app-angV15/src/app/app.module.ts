import { NgModule } from '@angular/core';
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
    Task6Component,
    Task5Component
  ],
  imports: [
    HttpClientModule,
    BrowserModule,
    AppRoutingModule,
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
    MatDatepickerModule,
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
  providers: [{ provide: HTTP_INTERCEPTORS, useClass: JwtInterceptorService, multi: true }, BaseApp, DataService,],
  bootstrap: [AppComponent]
})
export class AppModule { }
