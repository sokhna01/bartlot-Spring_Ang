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
import { TacheHuitComponent } from './tache-huit/tache-huit.component';
import { TacheNeufComponent } from './tache-neuf/tache-neuf.component';
import { TableauInterventionComponent } from './tableau-intervention/tableau-intervention.component';
import { LandingPageInterventionComponent } from './landing-page-intervention/landing-page-intervention.component';
import { Task6Component } from './task6/task6.component';
import { Task5Component } from './task5/task5.component';
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
  { path: 'add_profil', component: AddProfilComponent },
  { path: 'tache_huit', component: TacheHuitComponent },
  { path: 'tache_neuf', component: TacheNeufComponent },
  { path: 'task5', component: Task5Component },
  { path: 'task6', component: Task6Component },
  { path: 'landing_page_intervention', component: LandingPageInterventionComponent },
  { path: 'intervention', component: TableauInterventionComponent },
  { path: 'result_upload_missed_data_demo', component: ResultUploadMissedDataDemoComponent }];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
