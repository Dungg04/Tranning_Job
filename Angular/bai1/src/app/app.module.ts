import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { FirstComponent } from './first/first.component';
import { HighlightDirective } from './highlight.directive';
import { TemplateDrivenFormComponent } from './template-driven-form/template-driven-form.component';
import { ReactiveComponent } from './reactive/reactive.component';
import { GetDataComponent } from './get-data/get-data.component';
import { HttpClientModule } from '@angular/common/http';
import { GetDetailDataComponent } from './get-detail-data/get-detail-data.component';
import { PostDataComponent } from './post-data/post-data.component';
import { SvgComponent } from './svg/svg.component';
import { UnlessDirective } from './unless.directive';
import { IfLoadedDirective } from './if-loaded.directive';
import { TrigonometryDirective } from './trigonometry.directive';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    PageNotFoundComponent,
    FirstComponent,
    HighlightDirective,
    TemplateDrivenFormComponent,
    ReactiveComponent,
    GetDataComponent,
    GetDetailDataComponent,
    PostDataComponent,
    SvgComponent,
    UnlessDirective,
    IfLoadedDirective,
    TrigonometryDirective,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
