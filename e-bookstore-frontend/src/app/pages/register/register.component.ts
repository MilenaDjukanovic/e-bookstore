import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {CreateUser} from "../../shared/model/user.model";
import {first} from "rxjs/operators";
import {AuthService} from "../../services/auth.service";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  public registerForm!: FormGroup;

  public error!: string;

  constructor(private formBuilder: FormBuilder, private router: Router, private authService: AuthService) {

  }

  ngOnInit(): void {
    this.registerForm = this.formBuilder.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', Validators.required],
      password: ['', Validators.required],
      rePassword: ['', Validators.required]
    });
  }

  public onSubmit(): void {
    if(this.registerForm.invalid) {
      return;
    }
    const password = this.registerForm.controls.password.value;
    const rePassword = this.registerForm.controls.rePassword.value;

    if(password !== rePassword) {
      this.error = "Passwords do not match! Please try again."
      return;
    }
    const firstName = this.registerForm.controls.firstName.value;
    const lastName = this.registerForm.controls.lastName.value;
    const email = this.registerForm.controls.email.value;

    let user = new CreateUser(email, password, rePassword, firstName, lastName);

    this.authService.register(user).pipe(first()).subscribe(
      data => {
        this.router.navigate(['login']);
      },
      error => {
        this.error = error['statusText'];
      }
    )
  }

  public redirectToLogin(): void {
    this.router.navigate(['login']);
  }
}
