import { Component, OnInit} from '@angular/core';
import { MatDialogRef } from "@angular/material/dialog";
import { ImageUploadService } from "../../../services/image-upload.service";

@Component({
  selector: 'app-imgur-dialog',
  templateUrl: './imgur-dialog.component.html',
  styleUrls: ['./imgur-dialog.component.css']
})
export class ImgurDialogComponent implements OnInit {

  public image!: File;
  public imageUrl!: string;
  public error!: string;

  constructor(public dialogRef: MatDialogRef<ImgurDialogComponent>, private imageUploadService: ImageUploadService) { }

  public ngOnInit(): void {
  }

  public uploadImage(): void{
    this.imageUploadService.uploadImage(this.image).subscribe(response => {
      const imageUrl = response['data']['link'];
      this.dialogRef.close({imageUrl: imageUrl})
    }, error => {
      this.error = 'Image upload failed: ' + error;
    })
  }

  public handleFileInput(event: any): void{
    const files = event['target']['files']
    if(files && files.item(0)){
      this.error = '';
      this.image = files.item(0);
    }
  }

}
