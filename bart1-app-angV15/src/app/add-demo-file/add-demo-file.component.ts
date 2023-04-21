import { Component, EventEmitter, OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { BaseApp } from '../services/base-app/base_app';
import { Router } from "@angular/router";
import { DataService } from "../services/data-service/data_service";
import { FileUploader } from 'ng2-file-upload';

declare var $: any;


@Component({
  selector: 'app-add-demo-file',
  templateUrl: './add-demo-file.component.html',
  styleUrls: ['./add-demo-file.component.css']
})
export class AddDemoFileComponent implements OnInit {

  baseUrl!: String;

  fileName: any;
  line_number!: String;
  pickupDate: string = '2019-04-26';
  pickupDateBis!: string;
  listProfilsTmp: Array<any> = [];
  listProfils: { [key: string]: string } = {};
  token!: string;
  public uploader!: FileUploader;
  sizeOk: boolean = false;
  fileItem: any;
  public loading = false;

  constructor(public http: HttpClient, public baseApp: BaseApp, private router: Router, private data: DataService) {
    this.baseApp.loggedOut();
    if (!localStorage.getItem("token")) {
      this.data.changeMessage("false");
      this.router.navigate(['/login']);
      return;
    }

    this.getBaseUrl();
    const item = localStorage.getItem("token");
    const itemProfil = localStorage.getItem('listProfils');
    if (typeof item == "string" && typeof itemProfil == "string") {
      this.token = item;
      this.listProfilsTmp = JSON.parse(itemProfil);
    }

    for (let i = 0; i < this.listProfilsTmp.length; i++) {
      this.listProfils[this.listProfilsTmp[i].pf_code + ''] = "true";
    }
  }

  ngOnInit() {
    const url = this.baseUrl + "upload";

    this.uploader = new FileUploader({
      url: url,
      itemAlias: 'file'
    });
  }

  listernerUpload() {
    this.uploader.onAfterAddingFile = (file: any) => { file.withCredentials = true; };
    this.uploader.onCompleteItem = (item: any, response: any, status: any, headers: any) => {

      // console.log('Response: ', response);
      // console.log('status: ', status);
      if (status == 200) {
        // let splitted = response.split(",");
        // let resp = splitted[0];
        // this.pickupDate = splitted[1];
        // this.line_number = splitted[1];
        // let datePipe = new DatePipe('en-US');
        // const item = datePipe.transform(this.pickupDate, 'MMM d, y');
        // if (typeof item == "string") {
        //   this.pickupDateBis = item;
        // }
        // resp = resp.trim();
        if (response == 'upload_ok') {
          this.loading = false;
          $("#successModal").modal({ backdrop: "static" });
          $("#confirmModalnow").modal("hide");
          //this.doSelectTripsCare();
          setTimeout(() => {
            this.doUploadMeterData();
            // $("#successModal ").modal("hide");
          }, 1000);
        }
        else {
          this.loading = false;
          $("#failedModal").modal({ backdrop: "static" });
          $("#confirmModalnow").modal("hide");
        }
      }
      else {
        this.loading = false;
        $("#failedModal").modal({ backdrop: "static" });
        $("#confirmModalnow").modal("hide");
      }
    };
  }

  uploadFile() {
    let idCompany = localStorage.getItem('company_id');
    this.loading = true;
    this.uploader.onBeforeUploadItem = () => {
      this.uploader.options.additionalParameter = {
        option: 'committed',
        idCompany: idCompany
      };
    };
    this.fileItem.upload();
    this.listernerUpload();

  }

  public onFileSelected(event: EventEmitter<File[]> | any) {
    let sizeQueue = this.uploader.getNotUploadedItems().length;

    this.fileItem = this.uploader.queue[this.uploader.queue.length - 1]; // Je recupere le dernier fichier selectionné dans la queue.
    const file: File = event[0];

    if (file != undefined) {
      let fileSize = file.size;
      let fileType = file.type;
      let fileName = file.name;
      this.fileName = file.name
      if (fileType != '') { // Pour prendre en compte le cas ou le navigateur ne trouve pas l'extension automatiquement
        if (fileSize < 80000000 && fileType == 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet') {
          this.sizeOk = true;
          this.readBase64(file)
            .then(function (data) {
              //console.log(data);
            })
        }
        else {
          this.sizeOk = false;
          $("#BigSizeFileErrorModal").modal({ backdrop: "static" });
        }
      }
      else { //Alors trouver le type a travers l'extension
        let fileExtension = this.getFileExtension(fileName);// Alors trouver le type a travers l'extension
        if (fileExtension != 0) {
          if (fileSize < 80000000 && (fileExtension == 'xlsx' || fileExtension == 'xls')) {
            this.sizeOk = true;
            this.readBase64(file)
              .then(function (data) {
                //console.log(data);
              })
          }
          else {
            this.sizeOk = false;
            $("#BigSizeFileErrorModal").modal({ backdrop: "static" });
          }
        }
        console.log(fileExtension);
      }
    }
    //this.uploader.queue.length=0;
  }

  getFileExtension(file_name: any) {
    let file_name_string = file_name;
    let file_name_array = file_name_string.split(".");
    if (file_name_array.length > 1) {
      let file_extension = file_name_array[file_name_array.length - 1];
      return file_extension;
    }
    else {
      return 0;
    }
  }

  readBase64(file: any): Promise<any> {
    var reader = new FileReader();
    var future = new Promise((resolve, reject) => {
      reader.addEventListener("load", function () {
        resolve(reader.result);
      }, false);

      reader.addEventListener("error", function (event) {
        reject(event);
      }, false);

      reader.readAsDataURL(file);
    });
    return future;
  }

  uploadMeterData() {
    let idcompany = localStorage.getItem('company_id');
    let headers = new HttpHeaders({ 'Content-Type': 'application/x-www-form-urlencoded' });
    let dataform = "filename=" + this.fileName + "&idcompany=" + idcompany;
    const url = this.baseUrl + 'insert_meter_data';
    return this.http.post<any>(url, dataform, { headers, responseType: 'json' });
  }

  doUploadMeterData() {
    $("#fileExistModal").modal("hide");

    this.loading = true;
    this.uploadMeterData().subscribe(
      {

        next: (data) => {
          $("#successModal ").modal("hide");
          console.log(data);
          this.loading = false;
          let strSubData = data.msg.trim();
          if (strSubData == 'insert_ok') {
            //console.log('if ok');
            this.navigateTripCare();
          }
          else {
            this.loading = false;
            $("#failedModal").modal({ backdrop: "static" });
            $("#fileExistModal").modal("hide");
          }
        },
        error: (error) => {
          $("#successModal ").modal("hide");
          $("#failedModal").modal({ backdrop: "static" });
          this.loading = false;
          console.log(error);
        }
      });

  }

  navigateTripCare() {
    this.router.navigate(['result_upload_file_demo', { type: 'dispatched_trip', filename: this.fileName, pickup_date: this.pickupDate }]);
  }



  getBaseUrl() {
    this.baseUrl = this.baseApp.getBaseUrl();
  }
}

