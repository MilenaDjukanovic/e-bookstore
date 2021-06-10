import { Component, OnInit } from '@angular/core';
import { FormGroup } from "@angular/forms";
import { MatDialog } from "@angular/material/dialog";
import { FieldConfig } from "../../../model/form/field.interface";
import { ImgurDialogComponent } from "../../imgur-dialog/imgur-dialog.component";

@Component({
  selector: 'app-upload-imgur',
  templateUrl: './upload-imgur.component.html',
  styleUrls: ['./upload-imgur.component.css']
})
export class UploadImgurComponent implements OnInit {

  public field!: FieldConfig;
  public group!: FormGroup

  public imageUrl!: string;

  constructor(private dialog: MatDialog) { }

  public ngOnInit(): void {
  }

  public onUploadImage(){
    const dialogRef = this.dialog.open(ImgurDialogComponent);
    dialogRef.afterClosed().subscribe(result => {
      if(result['imageUrl']){
        this.imageUrl = result['imageUrl'];
      }
    })
  }
}
