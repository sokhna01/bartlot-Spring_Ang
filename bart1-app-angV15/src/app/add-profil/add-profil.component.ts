import { Component } from '@angular/core';
import { ProfileService } from '../services/profile-service/profile.service';
import { DataService } from '../services/data-service/data_service';
import { Route, Router } from '@angular/router';

@Component({
  selector: 'app-add-profil',
  templateUrl: './add-profil.component.html',
  styleUrls: ['./add-profil.component.css']
})

export class AddProfilComponent {
  token!: string;
  pf_name!: string;
  pf_description!: string;
  pf_code!: string;
  category!: string;
  headerString: string = 'Add a profile';
  public loading = false;

  constructor(private profileService: ProfileService,  private data: DataService, private router: Router) {
    if (!localStorage.getItem("token")) {
      this.data.changeMessage("false");
      this.router.navigate(['/login']);
    }
    this.addProfile();

   }
  
  addProfile(){
    this.loading = true;
    this.profileService.addProfile(this.pf_name, this.pf_description, this.pf_code, this.category)
      .subscribe(
        data => {
          console.log(data);
          this.loading = false;
        },
        error => {
          console.log(error);
          this.loading = false;
        });
  }

}
