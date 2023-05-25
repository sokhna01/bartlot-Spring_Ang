import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import { Data, Router } from '@angular/router';
import { DataService } from 'src/app/services/data-service/data_service';
import { MeterDataService } from 'src/app/services/meter-data/meter-data.service';
import * as XLSX from 'xlsx';
import Swal from 'sweetalert2';
import { DatePipe } from '@angular/common';
import { BaseApp } from 'src/app/services/base-app/base_app';
import { debounceTime, distinctUntilChanged, startWith } from 'rxjs/operators';

declare var $: any;

interface ClientData {
  idClient: string;
  idSites: string[];
  idPoints: string[];
}


@Component({
  selector: 'app-task5',
  templateUrl: './task5.component.html',
  styleUrls: ['./task5.component.css']
})
export class Task5Component implements OnInit {

  idClient = new FormControl();
  idSite = new FormControl();
  idPointComptage = new FormControl();

  regexDate = /^([0-2][0-9]|3[0-1])\/(0[1-9]|1[0-2])\/[0-9]{4}\s([01][0-9]|2[0-3]):[0-5][0-9]$/;
  regexDec =  /^\d+\.\d+$/;
  regexInt =  /^\d+$/;

  clients: any[] = [];
  filteredClients: string[] = []; 

  sites: any[] = [];
  filteredSites: string[] = [];

  points: any[] = [];
  filteredPoints: string[] = [];

  clientsData: ClientData[] = [];

  token!: string;
  baseUrl!: string;

  meterDataForm!: FormGroup;
  selectedFile!: File;

  headers!: string[];
  isFileUploaded: boolean = false;
  rows!: any[][];
  meterData: any[][] = [];

  showForm = true;
  rowIndex = -1;
  hasErrors: boolean = false;

  idCompany: any= localStorage.getItem('company_id');

  isSaveEnabled: boolean = false;
  sizeOk: boolean = false;



  expectedHeaders = ['Horodatage', 'Data A+', 'Data A-', 'Data R+', 'Data R-'];

 constructor(
   private formBuilder: FormBuilder,
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
  
  this.meterDataForm = this.formBuilder.group({
    idClient: ['', Validators.required],
    idSite: ['', Validators.required],
    idPointComptage: ['', Validators.required]
  });
  
  const token = localStorage.getItem("token");
  this.baseUrl = this.baseApp.getBaseUrl();

  this.meterDataService.getSelectListData(this.baseUrl, this.token).subscribe((data: any) => {  
    this.clients = data[0].idClients;
    this.sites = data[0].idSites;
    this.points = data[0].idPointComptage;
  });
  

  this.meterDataService.getAllClientsWithSitesAndPoints(this.baseUrl, this.token).subscribe((data: any) => {
    this.clientsData = data;
  });

  this.idClient.valueChanges.pipe(
    startWith(''), 
    debounceTime(300),
    distinctUntilChanged()
  ).subscribe(clientValue => {
    this.filteredClients = this.filterClients(clientValue);
    this.filteredSites = this.filterSites(clientValue);
    this.filteredPoints = this.filterPoints(clientValue);
    
    this.idSite.setValue('');
    this.idPointComptage.setValue('');
  });
  
}

filterClients(value: string): string[] {
  const filterValue = value.toLowerCase();

  return this.clients.filter(client => client.toLowerCase().includes(filterValue));
}

filterSites(clientValue: string): string[] {
  const filteredSites: Set<string> = new Set();

  if (clientValue) {
    const selectedClient = clientValue.toLowerCase();
    for (const association of this.clientsData) {
      if (association.idClient.toLowerCase() === selectedClient) {
        for (const site of association.idSites) {
          filteredSites.add(site);
        }
      }
    }
  }

  return Array.from(filteredSites);
}

filterPoints(clientValue: string): string[] {
  const filteredPoints: Set<string> = new Set();

  if (clientValue) {
    const selectedClient = clientValue.toLowerCase();
    for (const association of this.clientsData) {
      if (association.idClient.toLowerCase() === selectedClient) {
        for (const point of association.idPoints) {
          filteredPoints.add(point);
        }
      }
    }
  }

  return Array.from(filteredPoints);
}



 onSubmit() {
   if (this.rows) {
     const meterData = [
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
     this.showForm = false;
     this.meterData = meterData;
    } 
 }

 isFormValid(): boolean {
  const idClient = this.idClient.value;
  const idSite = this.idSite.value;
  const idPointComptage = this.idPointComptage.value;

  return (
    idClient !== '' &&
    idSite !== '' &&
    idPointComptage !== '' &&
    this.isFileUploaded &&
    this.filteredClients.includes(idClient) &&
    this.filteredSites.includes(idSite) &&
    this.filteredPoints.includes(idPointComptage)
  );
}




onFileSelected(event: any) {
 this.isFileUploaded = true;
 const file = event.target.files.item(0);
 if (file) {
    let fileSize = file.size;
    let fileType = file.type;
  
    if (fileSize > 80000000 || fileType !== 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet') {
      this.sizeOk = false;
      $("#BigSizeFileErrorModal").modal({ backdrop: "static" });
    } else { 
      this.sizeOk = true;
    }

   const fileReader = new FileReader();
   this.selectedFile = file;
   fileReader.readAsBinaryString(file);
   fileReader.onload = (e: any) => {
     const workbook = XLSX.read(e.target.result, { type: 'binary' });
     const firstSheetName = workbook.SheetNames[0];

     const worksheet = workbook.Sheets[firstSheetName];
     const meterData = XLSX.utils.sheet_to_json(worksheet, { header: 1}) as Array<Array<string>>;

     // Remplir tous les tableaux avec des tableaux de longueur 5 parce que sheet_to_json ignore les cellules vides dans les colonnes droites
     for (let i = 0; i < meterData.length; i++) {
       while (meterData[i].length < 5) {
         meterData[i].push('');
       }
       while (meterData[i].length > 5) {
         meterData[i].pop();
       }
}
     // Définition des valeurs de headers et rows
     this.headers = meterData[0];
     this.rows = meterData.slice(1).map((row: Array<string>, i: number) => {
       const rowData: Array<string | number | null> = row.map((cell: string | number, j: number) => {
          if (j == 0 && typeof cell === 'number') {
           let milliseconds = (cell - 25569) * 86400 * 1000; 
           milliseconds = ((milliseconds+1)/600000) * 600000;
           const date = new Date(milliseconds);
           const datePipe = new DatePipe('en-US');

           return datePipe.transform(date,'dd/MM/yyyy HH:mm');
           // return(milliseconds);            

         } else {
           return cell;
         }
       });
       return rowData;
     });      
   }
 }
}
 isHeaderValid(index: number): boolean {
   return this.meterData[0][index] == this.expectedHeaders[index];
 }

 isNewHeaderValid(index: number, value: any): boolean {
  return value === this.expectedHeaders[index];
}

 isCellValid(i: number, j: number): boolean {
   if (j === 0) {
     if(this.regexDate.test(this.meterData[i + 1][j]) === false){
       this.hasErrors = true;
     }
     else{
       this.hasErrors = false;
     }
     return this.regexDate.test(this.meterData[i + 1][j]);
   } else {
     const cellValue = this.meterData[i + 1][j];
     if(cellValue === null || cellValue === undefined || cellValue === ''){
       return true; // consider null cells as valid
     }
     else {
       const testValue1 = this.regexDec.test(cellValue);
       const testValue2 = this.regexInt.test(cellValue);
 
       if((testValue2 || testValue1) === false){
         this.hasErrors = true;
       }
       else{
         this.hasErrors = false;
       }
       return (testValue2 || testValue1);
     }
   }
 }
 
 onHeaderClick(event: MouseEvent) {
  const th = event.target as HTMLTableCellElement;
  const index = th.cellIndex;

  if (th.classList.contains('invalid-header')) {
    if (th.isContentEditable === false) {
      th.contentEditable = 'true';
      th.focus();
      th.addEventListener('input', () => {
        const value = th.innerText;
        const isValidHeader = this.isNewHeaderValid(index, value);
        
        th.classList.toggle('invalid-header', !isValidHeader);

        this.isSaveEnabled = this.isTableValid();
      });
    }
  
    // Surveillance des clics hors de la cellule
    const clickOutsideCell = (e: MouseEvent) => {
      if (e.target !== th) {
        let value = th.innerText.trim();
        th.innerText = value;
        th.contentEditable = 'true';
        document.removeEventListener('click', clickOutsideCell);
        document.removeEventListener('keydown', handleKeyDown);
      }
    };

    const handleKeyDown = (e: KeyboardEvent) => {
      if (e.key === 'Enter') {
        th.blur(); 
        e.preventDefault(); 
      }
    };

    th.addEventListener('blur', () => {
      const value = th.innerText;

      const isValidHeader = this.isNewHeaderValid(index, value);

      th.classList.toggle('invalid-header', !isValidHeader);


      this.isSaveEnabled = this.isTableValid();
    });

    document.addEventListener('click', clickOutsideCell);
    document.addEventListener('keydown', handleKeyDown);
  }
}

onCellClick(event: MouseEvent) {
  const td = event.target as HTMLTableCellElement;
  const j = td.cellIndex;

  if (td.classList.contains('invalid-cell') && td.innerText !== 'null') {
    if (td.isContentEditable === false) {
      td.contentEditable = 'true';
      td.focus();
      td.addEventListener('input', () => {
        const value = td.innerText;
        const isCellValid = j === 0 ? this.regexDate.test(value) : (this.regexDec.test(value) || this.regexInt.test(value));

        td.classList.toggle('invalid-cell', !isCellValid);
 
        this.isSaveEnabled = this.isTableValid();
      });
      
    }

    // Surveillance des clics hors de la cellule
    const clickOutsideCell = (e: MouseEvent) => {
      if (e.target !== td) {
        let value = td.innerText.trim();
        td.innerText = value;
        td.contentEditable = 'true';
        document.removeEventListener('click', clickOutsideCell);
        document.removeEventListener('keydown', handleKeyDown);
      }
    };

    const handleKeyDown = (e: KeyboardEvent) => {
      if (e.key === 'Enter') {
        td.blur(); 
        e.preventDefault(); 
      }
    };

    td.addEventListener('blur', () => {
      const value = td.innerText;
      const isCellValid = j === 0 ? this.regexDate.test(value) : (this.regexDec.test(value) || this.regexInt.test(value));

      td.classList.toggle('invalid-cell', !isCellValid);

      this.isSaveEnabled = this.isTableValid();
    });

    document.addEventListener('click', clickOutsideCell);
    document.addEventListener('keydown', handleKeyDown);
  }
}

// checkTableValidity(): void {
//   let hasErrors = false;

//   // Vérifier les en-têtes
//   for (let i = 0; i < this.headers.length; i++) {
//     if (!this.isHeaderValid(i)) {
//       hasErrors = true;
//       break;
//     }
//   }

//   // Vérifier les cellules
//   for (let i = 0; i < this.rows.length; i++) {
//     for (let j = 0; j < this.rows[i].length; j++) {
//       if (!this.isCellValid(i, j)) {
//         hasErrors = true;
//         break;
//       }
//     }
//   }

//   this.isSaveEnabled = !hasErrors;
// }

isTableValid(): boolean {
  const invalidHeaders = document.querySelectorAll('.invalid-header');
  const invalidCells = document.querySelectorAll('.invalid-cell');
  const isTableValid = invalidHeaders.length === 0 && invalidCells.length === 0;
  return isTableValid;
}

 onSave() {
   const worksheet = XLSX.utils.table_to_sheet(document.getElementById('displayTable'));
   const workbook = XLSX.utils.book_new();
   const fileName = 'data.xlsx';
   XLSX.utils.book_append_sheet(workbook, worksheet, 'Sheet1');
 
   const file = new File([XLSX.write(workbook, { bookType: 'xlsx', type: 'array' })], fileName, { type: 'application/vnd.ms-excel' });
 
   const idClient = this.meterDataForm.get('idClient')!.value;
   const idSite= this.meterDataForm.get('idSite')!.value;
   const idPointComptage = this.meterDataForm.get('idPointComptage')!.value;

   localStorage.setItem('idClient', idClient);
  //  this.meterDataService.idClient = idClient;
  //  this.meterDataService.idCompteur = idClient;
   this.meterDataService.idSite = idSite;
   this.meterDataService.idPointDeComptage = idPointComptage;


   this.meterDataService.insertXlsxToBD(idClient,this.idCompany, fileName, this.token, this.baseUrl, file).subscribe(
     (response) => {
      if (response.msg === 'insert_ok') {
      $("#successModal").modal({ backdrop: "static" });

     }
    },
     (error) => {
      $("#failedModal").modal({ backdrop: "static" });
     }
   );
 } 
 onModalCancel() {
  this.router.navigateByUrl('/home').then(() => {
    window.location.reload();
  });
}

 onCancel() {
 window.location.href = '/home';
 }
}
// onFileSelected(event: any) {
//   const file = event.target.files.item(0);
//   if (file) {
//     const fileReader = new FileReader();
//     fileReader.readAsBinaryString(file);
//     fileReader.onload = (e: any) => {
//       const workbook = XLSX.read(e.target.result, { type: 'binary' });
//       const firstSheetName = workbook.SheetNames[0];
//       const worksheet = workbook.Sheets[firstSheetName];
//       const meterData = XLSX.utils.sheet_to_json(worksheet, { header: 1, raw: true }) as Array<Array<string>>;

//       // Définition des valeurs de headers, rows et errors
//       this.headers = meterData[0] as string[];
//       this.headers = this.headers.map(header => String(header));
      
//       this.rows = meterData.slice(1).map((row: Array<unknown>, i: number) => {
//         const rowData: Array<string | number | null> = row.map((cell: string | number | unknown, j: number) => {
//           return cell as string | number | null;
//         });      
//         return rowData;
//       });
            
//     }
//   }
// }


  // checkValidity(): void {
  //   const cells = document.querySelectorAll('.invalid-cell');
  //   if (cells.length === 0) {
  //     this.isSaveEnabled = true;
  //   } else {
  //     this.isSaveEnabled = false;
  //   }
  
  //   const isValid = Array.from(document.querySelectorAll('tbody tr'))
  //     .slice(1)
  //     .every(row =>
  //       Array.from(row.querySelectorAll('td')).every(
  //         cell => !cell.classList.contains('invalid-cell')
  //       )
  //     );
  
  //   if (isValid) {
  //     this.isSaveEnabled = true;
  //   }
    
  //   this.hasErrors = false;
  // }