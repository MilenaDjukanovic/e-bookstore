import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
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
import {HttpClientModule} from "@angular/common/http";
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
import { DynamicFieldComponent } from './shared/components/form/dynamic-field/dynamic-field.component';
import { DynamicFieldDirective } from './shared/components/form/dynamic-field/dynamic-field.directive';
import { DynamicFormComponent } from './shared/components/form/dynamic-form/dynamic-form.component';
import {InputComponent} from "./shared/components/form/input/input.component";
import { CreateBookRequestComponent } from './pages/create-book-request/create-book-request.component';

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
    DynamicFieldComponent,
    DynamicFieldDirective,
    DynamicFormComponent,
    CreateBookRequestComponent
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
        MatSelectModule
    ],
  providers: [],
  bootstrap: [AppComponent],
  entryComponents: [
    InputComponent,
    ButtonComponent,
    SelectComponent,
  ]

})
export class AppModule {
}
