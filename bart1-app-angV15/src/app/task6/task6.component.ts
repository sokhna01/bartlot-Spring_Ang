import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BaseApp } from '../services/base-app/base_app';
import { DataService } from '../services/data-service/data_service';
import { MeterDataService } from '../services/meter-data/meter-data.service';
import * as XLSX from 'xlsx';
import { DatePipe } from '@angular/common';

declare var $: any;


@Component({
  selector: 'app-task6',
  templateUrl: './task6.component.html',
  styleUrls: ['./task6.component.css']
})
export class Task6Component implements OnInit {
  token!: string;
  baseUrl!: string;
  importedFile!: any;

  meterDataExterne: any[][] = [];
  rows!: any[][];
  headers!: string[];



  constructor(
    private meterDataService: MeterDataService,
    private data: DataService,
    private router: Router,
    public baseApp: BaseApp
  ) {
    if (!localStorage.getItem("token")) {
      this.data.changeMessage("false");
      this.router.navigate(['/login']);
      return;
    }
    this.baseUrl = this.baseApp.getBaseUrl();
    const item = localStorage.getItem("token");
    if (typeof item == "string") {
      this.token = item;
    }
  }


  ngOnInit() {
    this.getXLSXFile();
  }

  getXLSXFile() {
    //const idClient = this.meterDataService.idClient;
    const idClient = localStorage.getItem('idClient');
    const idCompany = localStorage.getItem('company_id');
  if (idClient !== null) {
    this.meterDataService.getCreatedXLSX(idClient, this.token, this.baseUrl)
      .subscribe((blob: Blob) => {
        const fileReader = new FileReader();
        fileReader.onload = (e: any) => {
          const binaryString = e.target.result;
          const workbook = XLSX.read(binaryString, { type: 'binary' });
          const firstSheetName = workbook.SheetNames[0];
          const worksheet = workbook.Sheets[firstSheetName];

          let meterDataExterne: Array<Array<string>> = [];  // Initialize meterDataExterne
          meterDataExterne = XLSX.utils.sheet_to_json(worksheet, { header: 1 }) as Array<Array<string>>;

          for (let i = 0; i < meterDataExterne.length; i++) {
            if (meterDataExterne[i].length < 8) {
              meterDataExterne[i].push('');
            } else if (meterDataExterne[i].length > 8) {
              meterDataExterne[i].pop();
            }
          }
          this.headers = meterDataExterne[0];
          this.rows = meterDataExterne.slice(1).map((row: Array<string>, i: number) => {
            const rowData: Array<string | number | null> = row.map((cell: string | number, j: number) => {
              // if (j === 0 && typeof cell === 'number') {
              //   let milliseconds = (cell - 2556) * 86400 * 1000;
              //   milliseconds = ((milliseconds + 6) / 600000) * 600000;
              //   const date = new Date(milliseconds);
              //   const datePipe = new DatePipe('en-US');
              //   return cell;

              //    return datePipe.transform(date, 'dd/MM/yyyy HH:mm');
              //   //return(milliseconds);            
              // } 
              if (j === 0 && typeof cell === 'number') {
                const secondsInDay = 86400;
                const excelStartDate = new Date(Date.UTC(1899, 11, 30, 0, 0, 0)); 
                const excelDays = Math.floor(cell);
                const excelTime = (cell - excelDays) * secondsInDay * 1000;
                const excelDate = excelStartDate.getTime() + (excelDays * secondsInDay * 1000) + excelTime;
                const date = new Date(excelDate);
                date.setSeconds(date.getSeconds() + 9);
                const datePipe = new DatePipe('en-US');
                return datePipe.transform(date, 'yyyy-MM-dd HH:mm');
              } else {
                return cell;
              }
              
              
              
              
              
            });
            return rowData;
          });

          if (this.rows) {
            const meterDataExterne = [
              this.headers,
              ...this.rows.filter((_, i) => true).map((row: Array<string | number | null>) => {
                if (row.length < this.headers.length) {
                  const diff = this.headers.length - row.length;
                  const newRow = [...row, ...new Array(diff).fill(null)];
                  return newRow;
                } else {
                  return row;
                }
              })
            ];
            this.meterDataExterne = meterDataExterne;
          }

        };

        fileReader.readAsBinaryString(blob);
      });

  }else{
    console.log("Client ID is null");
    $("#failedModal").modal({ backdrop: "static" });

  }
}

  onDownload() {
    const filename = 'donnees_externe.xlsx';
  
    const workbook = XLSX.utils.book_new();
    const worksheet = XLSX.utils.aoa_to_sheet(this.meterDataExterne);
  
    XLSX.utils.book_append_sheet(workbook, worksheet, 'Sheet 1');
  
    const wbout = XLSX.write(workbook, { type: 'array', bookType: 'xlsx' });
    const blob = new Blob([wbout], { type: 'application/octet-stream' });
  
    if (window.navigator && (window.navigator as any).msSaveOrOpenBlob) {
      // Support pour Internet Explorer
      (window.navigator as any).msSaveOrOpenBlob(blob, filename);
    } else {
      const url = window.URL.createObjectURL(blob);
  
      const link = document.createElement('a');
      if ('download' in link) {
        link.href = url;
        link.download = filename;
        link.style.display = 'none';
  
        document.body.appendChild(link);
        link.click();
        document.body.removeChild(link);
  
        window.URL.revokeObjectURL(url);
      } else {
        // Téléchargement via une redirection
        window.location.href = url;
      }
    }
    window.location.href = '/home';
  }  
  
  onCancel() {
    window.location.href = '/home';
  }
}