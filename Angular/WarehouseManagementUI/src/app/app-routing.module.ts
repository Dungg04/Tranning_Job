import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddCustomerComponent } from './customer/add-customer/add-customer.component';
import { EditCustomerComponent } from './customer/edit-customer/edit-customer.component';
import { GetCustomerComponent } from './customer/get-customer/get-customer.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { AddManufatureComponent } from './manufactures/add-manufature/add-manufature.component';
import { GetManufactureComponent } from './manufactures/get-manufacture/get-manufacture.component';
import { AddProductComponent } from './product/add-product/add-product.component';
import { EditProductComponent } from './product/edit-product/edit-product.component';
import { AddReceiptComponent } from './receipt/add-receipt/add-receipt.component';
import { ComfimReceiptComponent } from './receipt/comfim-receipt/comfim-receipt.component';
import { ConfirmReportComponent } from './report/confirm-report/confirm-report.component';
import { ReportReceiptComponent } from './report/report-receipt/report-receipt.component';

export const routes: Routes = [
  {path: "", component: LoginComponent},
  {path: "home", component: HomeComponent},
  {path: "addProduct", component: AddProductComponent},
  {path: "editProduct/:productID", component: EditProductComponent},
  {path: "getCustomer", component: GetCustomerComponent},
  {path: "editCustomer/:customerID", component: EditCustomerComponent},
  {path: "addCustomer", component: AddCustomerComponent},
  {path: "addReceipt", component: AddReceiptComponent},
  {path: "getManufatucre", component: GetManufactureComponent},
  {path: "addManufature", component: AddManufatureComponent},
  {path: "reportReceipt", component: ReportReceiptComponent},
  {path: "confirm/:receiptID", component: ConfirmReportComponent},
  {path: "confirmReceipt/:temp", component: ComfimReceiptComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
