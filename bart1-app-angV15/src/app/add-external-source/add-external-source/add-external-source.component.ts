import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MeterConfiguration } from 'src/app/interfaces/meter-configuration';
import { MeterData } from 'src/app/interfaces/meter-data';
import { MeterDataService } from 'src/app/services/meter-data/meter-data.service';

@Component({
  selector: 'app-add-external-source',
  templateUrl: './add-external-source.component.html',
  styleUrls: ['./add-external-source.component.css'],
})
export class AddExternalSourceComponent {
  meterDataForm!: FormGroup;
  meterConfigurations!: MeterConfiguration[];
  meterDatas!: MeterData[];
  selectedFile: File | null = null;

  constructor(
    private formBuilder: FormBuilder,
    private meterDataService: MeterDataService
  ) {}

  ngOnInit() {
    this.meterDataForm = this.formBuilder.group({
      idClient: ['', Validators.required],
      idSite: ['', Validators.required],
      idPointComptage: ['', Validators.required]
    });
  }

  onSubmit() {
    const idClientControl = this.meterDataForm.get('idClient');
    const idSiteControl = this.meterDataForm.get('idSite');
    const idPointComptageControl = this.meterDataForm.get('idPointComptage');
    const idClient = idClientControl?.value;
    const idSite = idSiteControl?.value;
    const idPointComptage = idPointComptageControl?.value;
    this.meterDataService.getMeterDatas(idClient, idSite, idPointComptage).subscribe((meterDatas: MeterData[]) => {
      this.meterDatas = meterDatas;
    });
  }
  

  onFileSelected(files: FileList) {
    this.selectedFile = files.item(0);
  }
}
