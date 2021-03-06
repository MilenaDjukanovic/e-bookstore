import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {BasicLayoutComponent} from "./layout/basic-layout/basic-layout.component";
import {LoginComponent} from "./pages/login/login.component";
import { RegisterPublishingHouseComponent } from "./pages/register-publishing-house/register-publishing-house.component";
import { RegisterRepresentativeComponent } from "./pages/register-representative/register-representative.component";
import {HeaderComponent} from "./shared/components/header/header.component";
import {HomeComponent} from "./pages/home/home.component";
import {RegisterComponent} from "./pages/register/register.component";
import {AuthGuard} from "./guards/auth.guard";
import {ShoppingCartComponent} from "./pages/shopping-cart/shopping-cart.component";
import {CreateBookRequestComponent} from "./pages/create-book-request/create-book-request.component";
import {ManageBookRequestsComponent} from "./pages/manage-book-requests/manage-book-requests.component";

const routes: Routes = [
  {
    path: '', component: HeaderComponent, children: [
      {path: '', component: HomeComponent},
      {path: 'home', component: HomeComponent},
      {path: 'cart', component: ShoppingCartComponent},
      {path: 'book-requests', component: CreateBookRequestComponent, canActivate: [AuthGuard]},
      {path: 'manage-book-requests', component: ManageBookRequestsComponent, canActivate: [AuthGuard]}
    ]
  },
  {
    path: '', component: BasicLayoutComponent, children: [
      {path: 'login', component: LoginComponent},
      {path: 'register', component: RegisterComponent},
      {path: 'register-representative', component: RegisterRepresentativeComponent},
      {path: 'register-publishing-house', component: RegisterPublishingHouseComponent}
    ]
  },
  {path: '**', redirectTo: 'home'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
