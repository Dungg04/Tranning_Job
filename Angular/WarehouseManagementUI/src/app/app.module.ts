import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgxPaginationModule } from 'ngx-pagination';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { AddProductComponent } from './product/add-product/add-product.component';
import { EditProductComponent } from './product/edit-product/edit-product.component';
import { GetCustomerComponent } from './customer/get-customer/get-customer.component';
import { AddCustomerComponent } from './customer/add-customer/add-customer.component';
import { EditCustomerComponent } from './customer/edit-customer/edit-customer.component';
import { AddReceiptComponent } from './receipt/add-receipt/add-receipt.component';
import { GetManufactureComponent } from './manufactures/get-manufacture/get-manufacture.component';
import { AddManufatureComponent } from './manufactures/add-manufature/add-manufature.component';
import { ReportReceiptComponent } from './report/report-receipt/report-receipt.component';
import { ConfirmReportComponent } from './report/confirm-report/confirm-report.component';
import { ComponentsModule } from './components/conponents.module';
import { ComfimReceiptComponent } from './receipt/comfim-receipt/comfim-receipt.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    AddProductComponent,
    EditProductComponent,
    GetCustomerComponent,
    AddCustomerComponent,
    EditCustomerComponent,
    AddReceiptComponent,
    LoginComponent,
    GetManufactureComponent,
    AddManufatureComponent,
    ReportReceiptComponent,
    ConfirmReportComponent,
    ComfimReceiptComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ComponentsModule,
    HttpClientModule,
    FormsModule,
    NgxPaginationModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
