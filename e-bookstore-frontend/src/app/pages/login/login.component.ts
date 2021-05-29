import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {AuthService} from "../../services/auth.service";
import {AuthUser, IAuthUser} from "../../shared/model/user.model";
import {first} from "rxjs/operators";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public loginForm!: FormGroup;

  private returnUrl!: string;
  private error!: string;

  private submitted = false;

  constructor(private formBuilder: FormBuilder, private router: Router,
              private route: ActivatedRoute, private authService: AuthService) {
    // redirect to home if already logged in
    if (this.authService.getCurrentUserValue()) {
      this.router.navigate(['/']);
    }
  }

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      email: ['', Validators.required],
      password: ['', Validators.required]
    });

    this.returnUrl = this.route.snapshot.queryParams.returnUrl || '/';
  }

  public onSubmit(): void {
    this.submitted = true;
    if(this.loginForm.invalid) {
      return;
    }
    const email = this.loginForm.controls.email.value;
    const password = this.loginForm.controls.password.value;
    let user = new AuthUser(email, password);

    this.authService.login(user).pipe(first()).subscribe(
      data => {
        this.router.navigate([this.returnUrl]);
      },
        error => {
          this.error = error;
        }
    )
  }

  public redirectToRegister(): void {
    this.router.navigate(['/register']);
  }
}
