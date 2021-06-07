import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import { ErrorInterceptor } from "./interceptors/error.interceptor";
import { JwtInterceptor } from "./interceptors/jwt.interceptor";
import {LoginComponent} from './pages/login/login.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatCardModule} from "@angular/material/card";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {FlexLayoutModule} from "@angular/flex-layout";
import {MatButtonModule} from "@angular/material/button";
import { HomeComponent } from './pages/home/home.component';
import { BasicLayoutComponent } from './layout/basic-layout/basic-layout.component';
import { HeaderComponent } from './shared/components/header/header.component';
import { RegisterComponent } from './pages/register/register.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from "@angular/common/http";
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatIconModule} from "@angular/material/icon";
import {MatSidenavModule} from "@angular/material/sidenav";
import {MatListModule} from "@angular/material/list";
import { BookListComponent } from './shared/components/book-list/book-list.component';
import {MatPaginatorModule} from "@angular/material/paginator";
import { BookCardComponent } from './shared/components/book-card/book-card.component';
import { ShoppingCartComponent } from './pages/shopping-cart/shopping-cart.component';
import {MatTableModule} from "@angular/material/table";
import { ButtonComponent } from './shared/components/form/button/button.component';
import { SelectComponent } from './shared/components/form/select/select.component';
import {MatSelectModule} from "@angular/material/select";
import { DynamicFieldDirective } from './shared/components/form/dynamic-field/dynamic-field.directive';
import { DynamicFormComponent } from './shared/components/form/dynamic-form/dynamic-form.component';
import {InputComponent} from "./shared/components/form/input/input.component";
import { CreateBookRequestComponent } from './pages/create-book-request/create-book-request.component';
import { AuthorDialogComponent } from './shared/components/author-dialog/author-dialog.component';
import {MatDialogModule} from "@angular/material/dialog";
import { TableComponent } from './shared/components/table/table.component';
import {MatSortModule} from "@angular/material/sort";
import {MatTooltipModule} from "@angular/material/tooltip";
import { ManageBookRequestsComponent } from './pages/manage-book-requests/manage-book-requests.component';
import { RegisterRepresentativeComponent } from './pages/register-representative/register-representative.component';
import { RegisterPublishingHouseComponent } from './pages/register-publishing-house/register-publishing-house.component';
import { BaseRegisterComponent } from './shared/components/base-register/base-register.component';
import { CategoryDialogComponent } from './shared/components/category-dialog/category-dialog.component';
import { RestSelectComponent } from './shared/components/form/rest-select/rest-select.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    BasicLayoutComponent,
    HeaderComponent,
    RegisterComponent,
    BookListComponent,
    BookCardComponent,
    ShoppingCartComponent,
    InputComponent,
    ButtonComponent,
    SelectComponent,
    DynamicFieldDirective,
    DynamicFormComponent,
    CreateBookRequestComponent,
    AuthorDialogComponent,
    TableComponent,
    ManageBookRequestsComponent,
    RegisterRepresentativeComponent,
    RegisterPublishingHouseComponent,
    BaseRegisterComponent,
    CategoryDialogComponent,
    RestSelectComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatCardModule,
    MatInputModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    FormsModule,
    FlexLayoutModule,
    MatButtonModule,
    HttpClientModule,
    MatToolbarModule,
    MatIconModule,
    MatSidenavModule,
    MatListModule,
    MatPaginatorModule,
    MatTableModule,
    MatSelectModule,
    MatDialogModule,
    MatSortModule,
    MatTooltipModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true }
  ],
  bootstrap: [AppComponent],
  entryComponents: [
    InputComponent,
    ButtonComponent,
    SelectComponent,
  ]

})
export class AppModule {
}
