import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule,FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MercancialistComponent } from './components/mercancialist/mercancialist.component';
import { MercanciaformComponent } from './components/mercanciaform/mercanciaform.component';
import { HttpClientModule } from '@angular/common/http';
import { NavMercanciaComponent } from './components/nav-mercancia/nav-mercancia.component';
import { MercanciaTableComponent } from './components/mercancia-table/mercancia-table.component';

@NgModule({
  declarations: [
    AppComponent,
    MercancialistComponent,
    MercanciaformComponent,
    NavMercanciaComponent,
    MercanciaTableComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
