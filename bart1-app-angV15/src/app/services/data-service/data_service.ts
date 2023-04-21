import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable()
export class DataService {

  private isLoggedSource: any;
  currentMessage: any;

  constructor() {
    if (localStorage.getItem("token")) {
      // Prend en charge le reload de la page. Si le user s'est deja connecte et que son jeton est toujours valide
      this.isLoggedSource = new BehaviorSubject('true'); // Envoyer un event au menu pour lui dire que le user est toujours loggin et afficher le menu alors      
      this.currentMessage = this.isLoggedSource.asObservable();
    }
    else {
      // Le user n'est pas connecte ou bien le jeton a expire, donc envoie d'un event pour dire au menu de ne plus safficher
      this.isLoggedSource = new BehaviorSubject('false');
      this.currentMessage = this.isLoggedSource.asObservable();
    }
  }

  changeMessage(message: string) {
    //console.log(message);
    this.isLoggedSource.next(message);
  }
}
