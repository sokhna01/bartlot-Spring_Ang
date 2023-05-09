import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddDemoFileComponent } from './add-demo-file/add-demo-file.component';
import { ChartDemoLineComponent } from './chart-demo-line/chart-demo-line.component';
import { ChartDemoComponent } from './chart-demo/chart-demo.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { ResultUpdateQualiteDemoComponent } from './result-update-qualite-demo/result-update-qualite-demo.component';
import { ResultUpdateSourceDemoComponent } from './result-update-source-demo/result-update-source-demo.component';
import { ResultUploadFileDemoComponent } from './result-upload-file-demo/result-upload-file-demo.component';
import { ResultUploadMissedDataDemoComponent } from './result-upload-missed-data-demo/result-upload-missed-data-demo.component';
import { AddProfilComponent } from './add-profil/add-profil.component';
import { AddExternalSourceComponent } from './add-external-source/add-external-source/add-external-source.component';
import { AddSourcePresenceQualiteComponent } from './add-source-presence-qualite/add-source-presence-qualite.component';
const routes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'login', component: LoginComponent },
  { path: 'home', component: HomeComponent },
  { path: 'result_upload_file_demo', component: ResultUploadFileDemoComponent },
  { path: 'add_demo_file', component: AddDemoFileComponent },
  { path: 'chart_demo', component: ChartDemoComponent },
  { path: 'chart_demo_line', component: ChartDemoLineComponent },
  { path: 'result_update_source_demo', component: ResultUpdateSourceDemoComponent },
  { path: 'result_update_qualite_demo', component: ResultUpdateQualiteDemoComponent },
  { path: 'add_profil', component: AddProfilComponent},
  { path: 'add_external_source', component: AddExternalSourceComponent},
  { path: 'add_source_presence_qualite', component: AddSourcePresenceQualiteComponent},
  { path: 'result_upload_missed_data_demo', component: ResultUploadMissedDataDemoComponent }];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
