import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { Router } from "@angular/router";
import { first } from "rxjs/operators";
import { PublishingHouseService } from "../../services/core/publishing-house.service";
import { CreatePublishingHouse } from "../../shared/model/publishing-house.model";

@Component({
  selector: 'app-register-publishing-house',
  templateUrl: './register-publishing-house.component.html',
  styleUrls: ['./register-publishing-house.component.css']
})
export class RegisterPublishingHouseComponent implements OnInit{

  public registerPublishingHouseForm!: FormGroup;

  public error!: string;

  public formConfiguration!: any;

  constructor(private formBuilder: FormBuilder, private router: Router, private publishingHouseService: PublishingHouseService){
  }

  public ngOnInit(): void{
    this.initializeFormConfiguration();
  }

  public registerPublishingHouse(event: any): void{
    const publishingHouse: CreatePublishingHouse = event['values'];
    this.publishingHouseService.create(publishingHouse).pipe(first()).subscribe(data => {
      console.log(data);
    }, error => {
      this.error = error;
    })
  }

  public redirectToRegisterRepresentative(): void{
    this.router.navigate(['register-representative']);
  }

  private initializeFormConfiguration(): void{
    this.formConfiguration = {
      controls: [{
        controlName: 'companyName',
        type: 'text',
        placeholder: 'Company Name',
        validators: [Validators.required],
        error: 'Company name not valid!'
      }, {
        controlName: 'email',
        type: 'text',
        placeholder: 'E-Mail',
        validators: [Validators.required, Validators.email],
        error: 'E-Mail not valid!'
      }, {
        controlName: 'tin',
        type: 'text',
        placeholder: 'Taxpayer Identification Number',
        validators: [Validators.required],
        error: 'TIN not valid!'
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
