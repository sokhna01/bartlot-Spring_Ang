import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators} from '@angular/forms';
import { Data, Router } from '@angular/router';
import { DataService } from 'src/app/services/data-service/data_service';
import { MeterDataService } from 'src/app/services/meter-data/meter-data.service';
import * as XLSX from 'xlsx';
import { DatePipe } from '@angular/common';



@Component({
  selector: 'app-add-external-source',
  templateUrl: './add-external-source.component.html',
  styleUrls: ['./add-external-source.component.css'],
})
export class AddExternalSourceComponent implements OnInit {
   regexDate = /^([0-2][0-9]|3[0-1])\/(0[1-9]|1[0-2])\/[0-9]{4}\s([01][0-9]|2[0-3]):[0-5][0-9]$/;
   regexDec =  /^\d+\.\d+$/;
   regexInt =  /^\d+$/;
  token!: string;
  clients: any[] = [];
  sites: any[] = [];
  points: any[] = [];

  meterDataForm!: FormGroup;
  selectedFile!: File;

  headers!: string[];
  isFileUploaded: boolean = false;
  rows!: any[][];
  meterData: any[][] = [];

  showForm = true;
  rowIndex = -1;
  hasErrors: boolean = false;

  isSaveEnabled: boolean = false;
  // displayedColumns: string[] = ['Horodatage', 'Data A+', 'Data A-', 'Data R+', 'Data R-'];


  constructor(
    private formBuilder: FormBuilder,
    private meterDataService: MeterDataService,
    private data: DataService,
    private router: Router
  ) {
    if (!localStorage.getItem("token")) {
      this.data.changeMessage("false");
      this.router.navigate(['/login']);
    }
  }

  ngOnInit() {
    this.meterDataForm = this.formBuilder.group({
      idClient: ['', Validators.required],
      idSite: ['', Validators.required],
      idPointComptage: ['', Validators.required]
    });

    this.meterDataService.getSelectListData().subscribe((data: any) => {
      for (let i = 0; i < data.length; i++) {
        this.sites[i] = data[i].idsite;
        this.clients[i] = data[i].idclient;
        this.points[i] = data[i].idpointcomptage
      }
    });
  }
  onSubmit() {
    if (this.rows) {
      const meterData = [
        this.headers,
        ...this.rows.filter((_, i) => true)
      ];
      this.showForm = false;
  
      this.meterData = meterData;
    }
  }


  onSave() {
    // Envoyer les données a la tache 6
    // Afficher un message de confirmation
    alert("Les données ont été enregistrées avec succès !"); //A changer
  }

  onCancel() {
  // Rediriger l'utilisateur vers la page d'accueil
  window.location.href = '/';
}

onFileSelected(event: any) {
  this.isFileUploaded = true;
  const file = event.target.files.item(0);
  if (file) {
    const fileReader = new FileReader();
    fileReader.readAsBinaryString(file);
    fileReader.onload = (e: any) => {
      const workbook = XLSX.read(e.target.result, { type: 'binary' });
      const firstSheetName = workbook.SheetNames[0];
      const worksheet = workbook.Sheets[firstSheetName];
      const meterData = XLSX.utils.sheet_to_json(worksheet, { header: 1}) as Array<Array<string>>;
      console.log(meterData);
      
      // Définition des valeurs de headers, rows et errors
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
  isHeaderInvalid(index: number): boolean {
    const expectedHeaders = ['Horodatage', 'Data A+', 'Data A-', 'Data R+', 'Data R-'];
    return this.meterData[0][index] !== expectedHeaders[index];
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
      // return cellValue !== undefined && typeof cellValue === 'number';

      const testValue1 = this.regexDec.test(cellValue);
      const testValue2 = this.regexInt.test(cellValue);

      // cellValue !== undefined && regexDec.test(cellValue);
      if(cellValue !== undefined && (testValue2 || testValue1) === false){
        this.hasErrors = true;
      }
      else{
        this.hasErrors = false;
      }
      return cellValue !== undefined && (testValue2 || testValue1);
    }
  }

  onCellClick(event: MouseEvent) {
    const td = event.target as HTMLTableCellElement;
    // const tr = event.target as HTMLTableRowElement;
    // const i = (tr)?.rowIndex;
    const j = td.cellIndex;
    const isValid = false;
    
    if (td.classList.contains('invalid-cell') && td.innerText !== undefined) {
      
      if (td.isContentEditable === false) {
        td.contentEditable = 'true';
        td.focus();
        td.addEventListener('input', () => {
          const value = td.innerText;  //const ==> n'ayant pas besoin d'etre reaffectée
          if(j == 0){
            if(this.regexDate.test(value) === true){
              td.classList.remove('invalid-cell');
              this.isSaveEnabled = true;
            }
            else{
              td.classList.add('invalid-cell');
              this.isSaveEnabled = false;
            }
          }
          else{
            if( (this.regexDec.test(value) === true) || (this.regexInt.test(value) === true )){
              td.classList.remove('invalid-cell');
              this.isSaveEnabled = true;
            }
            else{
              td.classList.add('invalid-cell');
              this.isSaveEnabled = false;
            }
          }
        });
      }
      

      //Surveillance des clics hors de la cellule
      document.addEventListener('click', function clickOutsideCell(e: MouseEvent) {
       const tr = event.target as HTMLTableRowElement;
       const i = (tr)?.rowIndex;
       const j = td.cellIndex;

        if (e.target !== td) {
          let value = td.innerText.trim();
          td.innerText = value;
          td.contentEditable = 'true';
          document.removeEventListener('click', clickOutsideCell);
        }
      });
    }
  }

  checkValidity(): void {
    const cells = document.querySelectorAll('.invalid-cell');
    if (cells.length === 0)
    this.isSaveEnabled = true;
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

  // onCellClick(event: MouseEvent) {
  //   const td = event.target as HTMLTableCellElement;
  //   const j = td.cellIndex;
  
  //   if (td.classList.contains('invalid-cell') && td.innerText !== undefined) {
  
  //     if (td.isContentEditable === false) {
  //       td.contentEditable = 'true';
  //       td.focus();
  //       td.addEventListener('blur', () => {
  //         const value = td.innerText.trim();
  //         if (j == 0) {
  //           if (this.regexDate.test(value)) {
  //             td.classList.remove('invalid-cell');
  //           } else {
  //             td.classList.add('invalid-cell');
  //           }
  //         } else {
  //           if (this.regexDec.test(value) || this.regexInt.test(value)) {
  //             td.classList.remove('invalid-cell');
  //           } else {
  //             td.classList.add('invalid-cell');
  //           }
  //         }
  //       });
  //     }
  //   }
  // }
  

      
              
