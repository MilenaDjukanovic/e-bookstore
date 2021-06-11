import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {AuthService} from "../../../services/authority/auth.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  public isPublishingHouse!: boolean;
  public isAdmin!: boolean;

  constructor(private router: Router, public authService: AuthService) {
    this.isPublishingHouse = this.authService.isPublishingHouseRepresentative();
    this.isAdmin = this.authService.isAdmin();
  }

  ngOnInit(): void {
    this.router.events.subscribe(() => {
      window.scroll(0,0);
    })
  }

  public logout(): void {
    this.authService.logout();
    this.router.navigate(['/login'])
  }
}
