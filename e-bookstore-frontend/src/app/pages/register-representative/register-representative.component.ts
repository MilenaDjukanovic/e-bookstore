import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { Router } from "@angular/router";
import { first } from "rxjs/operators";
import { AuthService } from "../../services/auth.service";
import { CreateRepresentativeUser, ICreateRepresentativeUser } from "../../shared/model/user.model";

@Component({
  selector: 'app-register-representative',
  templateUrl: './register-representative.component.html',
  styleUrls: ['./register-representative.component.css']
})
export class RegisterRepresentativeComponent implements OnInit{

  public registerRepresentativeForm!: FormGroup;

  public error!: string;

  constructor(private formBuilder: FormBuilder, private router: Router, private authService: AuthService){

  }

  public ngOnInit(): void{
    this.initializeForm();
  }

  public registerRepresentativeUser(): void{
    if(!this.areFormValuesValid()){
      return;
    }

    const representativeUser = this.getRepresentativeFromFormValues();
    this.authService.registerRepresentative(representativeUser).pipe(first()).subscribe(data => {
      this.redirectToLogin();
    }, error => {
      this.error = error;
    })
  }

  private getRepresentativeFromFormValues(): any{
    const email = this.getFormControlValue('email');
    const firstName = this.getFormControlValue('firstName');
    const lastName = this.getFormControlValue('lastName');
    const password = this.getFormControlValue('password');
    const rePassword = this.getFormControlValue('rePassword');
    const representativeRegistrationKey = this.getFormControlValue('representativeRegistrationKey');

    return new CreateRepresentativeUser(email, password, rePassword, firstName, lastName, representativeRegistrationKey);
  }

  private getFormControlValue(controlName: string): string{
    return this.registerRepresentativeForm['controls'][controlName]['value'];
  }

  public areFormValuesValid(): boolean{
    if(this.registerRepresentativeForm.invalid){
      // #TODO check if error needs to be added here
      return false;
    }

    if(this.registerRepresentativeForm['controls']['password']['value'] !== this.registerRepresentativeForm['controls']['rePassword']['value']){
      this.error = 'Passwords do not match! Please try again.';
      return false;
    }

    return true;
  }

  public redirectToLogin(): void{
    this.router.navigate(['login']);
  }

  private initializeForm(): void{
    this.registerRepresentativeForm = this.formBuilder.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', Validators.required],
      password: ['', Validators.required],
      rePassword: ['', Validators.required],
      representativeRegistrationKey: ['', Validators.required]
    });
  }

}
