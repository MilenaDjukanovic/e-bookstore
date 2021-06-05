import { Component, OnInit } from '@angular/core';
import { Validators } from "@angular/forms";
import { Router } from "@angular/router";
import { first } from "rxjs/operators";
import { AuthService } from "../../services/authority/auth.service";
import { CreateRepresentativeUser } from "../../shared/model/user.model";

@Component({
  selector: 'app-register-representative',
  templateUrl: './register-representative.component.html',
  styleUrls: ['./register-representative.component.css']
})
export class RegisterRepresentativeComponent implements OnInit{

  public formConfiguration!: any;

  public error!: string;

  constructor(private router: Router, private authService: AuthService){

  }

  public ngOnInit(): void{
    this.initializeFormConfiguration();
  }

  public registerRepresentativeUser(event: any): void{
    const representativeUser: CreateRepresentativeUser = event['values'];
    if(!this.areFormValuesValid(representativeUser)){
      return;
    }

    this.authService.registerRepresentative(representativeUser).pipe(first()).subscribe(data => {
      this.redirectToLogin();
    }, error => {
      this.error = error;
    })
  }

  public areFormValuesValid(representativeUser: CreateRepresentativeUser): boolean{
    if(representativeUser['password'] !== representativeUser['rePassword']){
      this.error = 'Passwords do not match! Please try again.';
      return false;
    }

    return true;
  }

  public redirectToLogin(): void{
    this.router.navigate(['login']);
  }

  private initializeFormConfiguration(): void{
    this.formConfiguration = {
      controls: [{
        controlName: 'firstName',
        type: 'text',
        placeholder: 'First Name',
        validators: [Validators.required],
        error: 'First Name not valid!'
      }, {
        controlName: 'lastName',
        type: 'text',
        placeholder: 'Last Name',
        validators: [Validators.required],
        error: 'Last Name not valid!'
      }, {
        controlName: 'username',
        type: 'text',
        placeholder: 'E-Mail',
        validators: [Validators.required, Validators.email],
        error: 'E-Mail not valid!'
      }, {
        controlName: 'password',
        type: 'password',
        placeholder: 'Password',
        validators: [Validators.required],
        error: 'Password not valid!'
      }, {
        controlName: 'rePassword',
        type: 'password',
        placeholder: 'Password',
        validators: [Validators.required],
        error: 'Password not valid!'
      }, {
        controlName: 'representativeRegistrationKey',
        type: 'password',
        placeholder: 'Representative Registration Key',
        validators: [Validators.required],
        error: 'Representative Registration Key not valid!'
      }],
      navigation: {
        text: 'Back to Login',
        path: ['login']
      }
    }
  }

}
